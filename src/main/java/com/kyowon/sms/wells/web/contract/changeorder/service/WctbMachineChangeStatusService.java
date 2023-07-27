package com.kyowon.sms.wells.web.contract.changeorder.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbMachineChStatBasInfDvo;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbMachineChStatInOutDvo;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbMembershipWdwalDvo;
import com.kyowon.sms.wells.web.contract.changeorder.mapper.WctbMachineChangeStatusMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;
import com.sds.sflex.system.config.validation.ValidAssert;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctbMachineChangeStatusService {

    private final WctbMachineChangeStatusMapper mapper;
    private final MessageResourceService messageService;

    /**
      * 기기 변경 고객 유효성 확인
      * @programId  W-SS-S-0049
      * @param  inDvo
      * @return inDvo
      */
    public WctbMachineChStatInOutDvo getMachineChangeStatus(WctbMachineChStatInOutDvo inDvo) throws Exception {
        ValidAssert.hasText(inDvo.getCntrNo());
        ValidAssert.hasText(inDvo.getCntrSn());
        ValidAssert.hasText(inDvo.getCstNo());
        ValidAssert.hasText(inDvo.getPdCd());

        String rctDt = DateUtil.getNowDayString();
        inDvo.setRctDt(rctDt);

        //기본 정보 조회
        WctbMachineChStatBasInfDvo baseInfoDvo = mapper.selectContractBaseInfo(inDvo);

        BizAssert.notNull(baseInfoDvo, "MSG_ALT_CHK_CNTR_SN"); // 계약일련번호를 확인해주세요.
        BizAssert.notNull(baseInfoDvo.getIstDt(), "MSG_ALT_UNINSTALL_CUSTOMER"); // message : 미설치 고객입니다.

        if ("1".equals(baseInfoDvo.getSellTpCd()) && baseInfoDvo.getIstmMcn() > 0
            && StringUtils.isEmpty(baseInfoDvo.getFulpyDt())) {
            throw new BizException("MSG_ALT_FULL_PAYMENT_PSBL"); // message : 할부고객일 때, 할부완납의 경우만 가능합니다.

        }else if(baseInfoDvo.getEotDlqAmt() > 0 ){
            //연체금액이 존재하는 경우, 계약예외 여부 조회
            if ("N".equals(mapper.selectDlqExYn("W02", inDvo.getCstNo()))) { // 02 - 렌탈 기변 상대코드 연체건 접수 허용
                throw new BizException("MSG_ALT_DEVICE_CH_IMP_DLQ"); // message : 연체고객은 기기변경이 불가합니다.
            }
        } else if (baseInfoDvo.getEotUcAmt() > 0 && !inDvo.getCstNo().equals(baseInfoDvo.getCntrCstNo())) {
            throw new BizException("MSG_ALT_INVALID_CST_NO_BFAF"); // message : 미수고객이면서 기변 이전/이후 고객번호가 다릅니다.
        }

        //기기변경가능여부 확인
        if(mapper.selectMachineChPsbYn(baseInfoDvo.getPdCd()) <= 0){
            throw new BizException("MSG_ALT_DEVICE_CH_IMP_PD"); // message : 기기변경 가능 상품이 아닙니다.
        }

        //상품분류 확인
        String pdClsf = mapper.selectProductClsf(inDvo.getPdCd()).orElseGet(String::new);
        if (!pdClsf.equals(
            StringUtils.defaultString(baseInfoDvo.getPdHclsfId())
                .concat(StringUtils.defaultString(baseInfoDvo.getPdMclsfId()))
        )) {
            throw new BizException("MSG_ALT_INVALID_CST_SAME_PD"); // message : 동일 종류의 제품 구입 고객이 아닙니다.
        }

        //주요날짜 계산
        if(StringUtils.isNotEmpty(baseInfoDvo.getReqdDt()) && calcPeriodTermDc(baseInfoDvo.getReqdDt(), rctDt) > 0){
            baseInfoDvo.setReqdDt("");
        }

        /// 이하 모두 매출일자가 not null 이여야 함.
        String dutyPermStrtDt = baseInfoDvo.getSlDt(); // 의무기간 시작일
        String dutyPermEndDt = DateUtil.addMonths(dutyPermStrtDt, baseInfoDvo.getRecapDutyPtrmN()); // 의무기간 종료일
        String rental57Dt = DateUtil.addMonths(dutyPermStrtDt, 57); // 렌탈 57차월
        String rentalExpDt = DateUtil.addMonths(dutyPermStrtDt, baseInfoDvo.getIstmMcn()); // 렌탈만료일자

        //렌탈 차월 수 - 쿼리에서 계산

        if(StringUtils.isNotEmpty(baseInfoDvo.getReqdDt())){
            //렌탈이면서 철거일자가 존재하는 경우, 철거후 6개월이 지난 경우 기기변경 불가
            if("2".equals(baseInfoDvo.getSellTpCd())){
                int reqd6YM = Integer.parseInt(DateUtil.addMonths(baseInfoDvo.getReqdDt(), 6)
                                                        .substring(0, 6));
                int nowYM = Integer.parseInt(DateUtil.getNowDayString().substring(0, 6));
                if(reqd6YM <= nowYM){
                    throw new BizException("MSG_ALT_DEVICE_CH_IMP_UNINSTALL_6MTH"); // message : 취소 후 6개월 경과!, 기기변경이 불가합니다.
                }
            }

            //설치일철거일까지의 개월수 - 쿼리에서 계산
        }else {
            baseInfoDvo.setCancelInsN(0);
        }

        //기기변경 관련 최종 상태 체크
        FindRes validResult = checkValidMachindChangeStatus(inDvo);

        String workFlag = validResult.workFlag();
        String msgCd = validResult.msgCd();
        int workRat1 = 0;

        if ("22".equals(inDvo.getAlncmpCd())) {
            workRat1 =  switch (workFlag){
                            case "15", "16" -> 90;
                            case "18" -> 0;
                            default -> 0;
                        };
        }else{
            workRat1 =  switch (workFlag){
                            case "15", "16" -> 80;
                            case "18" -> "W01".equals(inDvo.getOgTpCd()) ? 10 : 20;
                            default -> 0;
                        };
        }

        if ("18".equals(workFlag)
            && "W01".equals(inDvo.getOwnrsExnDt())
            && "2".equals(baseInfoDvo.getCopnDvCd())
            && !"22".equals(inDvo.getAlncmpCd())) {
            workRat1 = 50;
        }

        // 리턴 메시지 SET
        String returnMessage = switch (msgCd){
            case "A", "B" -> "18".equals(workFlag)? "약정만료일 이전 설치시 위약금 발생":"";
            case "C" -> "재약정 고객！기변시 위약금 발생 가능!";
            case "D" -> "상조재약정 고객！기변시 위약금 발생 가능!";
            default -> "";
        };

        // 재약정 의무기간 check
        if("C".equals(msgCd) && calcPeriodTermDc(baseInfoDvo.getStplEnddt(), rctDt) < 0){
            msgCd = "Y";
        }

        // 의무기간 이내 기변 등록 불가(의무기간 예외처리 체크)
        if("01".equals(workFlag)) {
            if ("N".equals(mapper.selectDlqExYn("W03", inDvo.getCntrNo() + inDvo.getCntrSn()))) { // 02 - 03 - 렌탈 기변 의무기간내 접수허용
                throw new BizException("MSG_ALT_DEVICE_CH_IMP_DUTY_TERM"); // message : 의무기간 내 기변 등록 불가!
            }
        }

        if ("2".equals(inDvo.getDscTp()) && "2".equals(inDvo.getSellTpCd())) {
            if(StringUtils.containsAny(workFlag, "01", "18")){
                String messageDetail = messageService
                    .getMessage("3".equals(baseInfoDvo.getSellTpDtlCd()) ? "MSG_TXT_FNN_LEASE" : "MSG_TXT_RENTAL");
                throw new BizException("MSG_ALT_DEVICE_CH_IMP_EMP_PRCHS", messageDetail); // message : 직원구매! (금융리스 OR 렌탈 - 57차월미만 기변 불가!
            }
        }

        //최종 세팅
        inDvo.setWorkFlag(workFlag);
        inDvo.setResultDvCheck(msgCd);
        inDvo.setResultMessage(returnMessage);
        inDvo.setFinalPerfRt(workRat1);
        inDvo.setPtyCopnDvCd(baseInfoDvo.getCopnDvCd());
        inDvo.setPdCd(baseInfoDvo.getPdCd());
        inDvo.setRentalNmnN(baseInfoDvo.getRentalNmnN());
        inDvo.setRerntPsbDt(rental57Dt);
        inDvo.setStplDutyStrtDt(dutyPermStrtDt);
        inDvo.setStplDutyEndDt(dutyPermEndDt);
        inDvo.setRstlDutyStrtDt(baseInfoDvo.getStplStrtdt());
        inDvo.setRstlDutyEndDt(baseInfoDvo.getStplEnddt());
        inDvo.setOwnrsExnDt(rentalExpDt);
        inDvo.setDlqAmt(baseInfoDvo.getEotDlqAmt());
        inDvo.setUcAmt(baseInfoDvo.getEotUcAmt());
        inDvo.setRecapDutyPtrmN(baseInfoDvo.getRecapDutyPtrmN());
        inDvo.setPdNm(baseInfoDvo.getPdNm());

        return inDvo;
    }


     /**
      * 기기변경 상태 조회
      * @programId  W-SS-S-0051
      * @param  dvo cntrNo 계약번호
      * @param  dvo cntrSn 계약일련번호
      * @param  dvo sellTpCd 판매유형코드
      * @return
      *  오류여부 Y 고객없음
      *          A 약정만료월기준적용 ==> 이것밖에 없음.
      *          B 57차월월기준적용
      *          C 일반재약정고객
      *          D 상조재약정고객
      */
    public FindRes checkValidMachindChangeStatus(WctbMachineChStatInOutDvo dvo) throws Exception{
        ValidAssert.hasText(dvo.getCntrNo());
        ValidAssert.hasText(dvo.getCntrSn());

        String rctDt  = DateUtil.getNowDayString();
        String sellTpCd = dvo.getSellTpCd();
        String workFlag = "01";
        String msgCd = "";

        //멤버십 자료 조회
        WctbMachineChStatBasInfDvo baseInfDvo = mapper.selectMachineInfo(dvo);

        //멤버쉽 탈퇴조회
        WctbMembershipWdwalDvo wdwalDvo = mapper.selectMembershipWdwal(dvo.getCntrNo(), dvo.getCntrSn()).orElseGet(WctbMembershipWdwalDvo::new);

        if("1".equals(sellTpCd)){
            if(StringUtils.isEmpty(wdwalDvo.getMemCntrNo()) || StringUtils.isNotEmpty(wdwalDvo.getCntrPdEnddt())) {
                //멤버십이 없거나, 멤버십이 있어도 탈퇴한 경우
                workFlag = "15";
            } else if(StringUtils.isNotEmpty(wdwalDvo.getMemCntrNo()) || StringUtils.isEmpty(wdwalDvo.getCntrPdEnddt())) {
                //멤버십은 있는데 탈퇴는 안한 경우
                workFlag = "16";
            }
        }else if("2".equals(sellTpCd)){
            if(StringUtils.isEmpty(baseInfDvo.getExnDt())){    // 만료전
                //필요일자 계산
                /// 이하 매출일자가 반드시 있으야 함. 없을 때, 기본값 처리 여부 확인 필요
                String calR57Dt = StringUtils.isEmpty(baseInfDvo.getSlDt())? "": DateUtil.addMonths(baseInfDvo.getSlDt(), (baseInfDvo.getIstmMcn() - baseInfDvo.getFrisuBfsvcPtrmN() -3));
                String calRudtDt = StringUtils.isEmpty(baseInfDvo.getSlDt())? "": DateUtil.addMonths(baseInfDvo.getSlDt(), (baseInfDvo.getRecapDutyPtrmN() - baseInfDvo.getFrisuBfsvcPtrmN()));

                int calR57Ym = StringUtils.isEmpty(calR57Dt)? 0: Integer.parseInt(StringUtils.substring(calR57Dt, 0, 6));
                int calRudtYm = StringUtils.isEmpty(calRudtDt)? 0: Integer.parseInt(StringUtils.substring(calRudtDt, 0, 6));
                boolean isCancel = StringUtils.containsAny(baseInfDvo.getCntrDtlStatCd(), "304", "305");

                if(!isCancel
                    || (isCancel && Integer.parseInt(StringUtils.defaultString(baseInfDvo.getCntrPdEnddt(), "0")) > Integer.parseInt(rctDt))){

                    int rctYm = Integer.parseInt(StringUtils.substring(rctDt, 0, 6));
                    if(rctYm <= calR57Ym && rctYm >= calRudtYm){
                        workFlag = "18";

                        if(Integer.parseInt(rctDt) < Integer.parseInt(calRudtDt)){
                            msgCd = "A"; // 약정만료월기준적용
                        }
                    }
                }else if(isCancel){

                    int pdEndYm = Integer.parseInt(StringUtils.substring(baseInfDvo.getCntrPdEnddt(), 0, 6));
                    if(pdEndYm <= calR57Ym &&  pdEndYm >= calRudtYm){
                        workFlag = "18";

                        if(Integer.parseInt(baseInfDvo.getCntrPdEnddt()) < Integer.parseInt(calR57Dt)){
                            msgCd = "A"; //  약정만료월기준적용
                        }
                    }
                }
            }else{
                workFlag = (StringUtils.isEmpty(wdwalDvo.getMemCntrNo()))? "15": (StringUtils.isEmpty(wdwalDvo.getCntrPdEnddt())) ? "16": "15";
            }

/* TODO : 아래 경고메시지 관련 일부 재정의 후 다시 SET 현상황 SKIP

  *---일반재약정（의무기간경과）　경고메시지추가
IF     ( WORK-FLAG  =  18    )  AND
        ( WORK-CANY  =  0     )  AND
        ( L310-RAM3  >  0     )
               MOVE      "C"         TO  PARM-CHEK.
   *---상조재약정（의무기간경과）　경고메시지추가
IF     ( WORK-FLAG  =  18    )  AND
        ( WORK-CANY  =  0     )  AND
        ( L310-ETC8  =  "24"  )  AND
        ( L310-ETC2  =  "1"   )
               MOVE      "D"         TO  PARM-CHEK.
*/

        }else if("4".equals(sellTpCd)){
            if(StringUtils.isNotEmpty(baseInfDvo.getExnDt())){
                workFlag = (StringUtils.isEmpty(wdwalDvo.getMemCntrNo()))? "15": (StringUtils.isEmpty(wdwalDvo.getCntrPdEnddt())) ? "16": "15";
            }
        }

        return FindRes.builder()
                .workFlag(workFlag)
                .msgCd(msgCd)
                .build();
    }

    /**
     * 일수 계산－(기간 기준)
     *  - EctbPeriodTermDcCalcService 참고
     * @param startDate 시작일(yyyyMMdd)
     * @param endDate   종료일(yyyyMMdd)
     * @return 종료일-시작일 차이 일수
     */
    private int calcPeriodTermDc(String startDate, String endDate) {
        if(StringUtils.isEmpty(startDate) || startDate.length() != 8)  return 0;
        if(StringUtils.isEmpty(endDate) || endDate.length() != 8)  return 0;

        int dayCount = 0;

        //1. 두 날짜 사이의 일수 계산
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate startLD = LocalDate.parse(startDate, formatter);
        LocalDate endLD = LocalDate.parse(endDate, formatter);

        dayCount = (int) Duration.between(startLD.atStartOfDay(), endLD.atStartOfDay()).toDays();

        return dayCount+1;
    }

    @Builder
    private record FindRes(
        String workFlag,
        String msgCd
    ) {}
}
