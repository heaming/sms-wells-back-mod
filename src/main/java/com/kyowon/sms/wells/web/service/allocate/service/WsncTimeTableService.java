package com.kyowon.sms.wells.web.service.allocate.service;

import com.kyowon.sms.wells.web.service.allocate.converter.WsncTimeTableConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.*;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncTimeTableMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.exception.BizException;

import java.text.ParseException;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 *
 *
 * @author gs.piit122 김동엽
 * @since 2023-05-08
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsncTimeTableService {

    private final WsncTimeTableMapper mapper;
    private final WsncTimeTableConverter converter;


    /**
     * 타임테이블 조회(판매)
     *
     * @programId W-SV-U-0062M01
     * @param req : 조회파라메터
     * @return 조회결과
     */
    /**
    * @see "timeAssign.do GET"
    * */
    public WsncTimeTableDto.FindRes getTmeAssign(WsncTimeTableDto.FindTimeAssignReq req)
        throws ParseException {

        WsncTimeTableDvo dvo = converter.mapTimeAssignReqToParamDvo(req);
        dvo.getSmTimes().clear();
        dvo.getAmTimes().clear();
        dvo.getPmTimes1().clear();
        dvo.getPmTimes2().clear();
        dvo.getNtTimes().clear();

        WsncTimeTableRpbLocaraPsicDvo rpbLocaraPsic = null;
        List<WsncTimeTableSidingDaysDvo> sidingDays = null;
        WsncTimeTablePsicDvo psic = null;
        List<WsncTimeTableAssignTimeDvo> assignTimes = null;

        // 00000001
        dvo.setSeq(StringUtils.leftPad(StringUtil.nvl(req.seq(), "1"), 8, "0"));
        String sellDate = StringUtil.isEmpty(dvo.getSellDate()) ? DateUtil.getNowDayString() : dvo.getSellDate();

        if (dvo.getSvDvCd().equals("4")) {
            dvo.setSellDate(DateUtil.addDays(DateUtil.getNowDayString(), 1));
        }

        if (dvo.getSvDvCd().equals("1") && (sellDate == null || sellDate.equals(dvo.getWrkDt()))) {
            dvo.setSellDate(DateUtil.addDays(DateUtil.getNowDayString(), 5));
        }

        if (StringUtil.isEmpty(req.sellDate())) {
            dvo.setSellDate(DateUtil.addDays(DateUtil.getNowDayString(), 5));
        }

        WsncTimeTableCntrDvo contractDvo = mapper.selectContract(dvo)
            .orElseThrow(() -> new BizException("MSG_ALT_NO_CONTRACT_FOUND"));

        dvo.setBasePdCd(contractDvo.getBasePdCd());
        dvo.setPdctPdCd(contractDvo.getPdctPdCd());
        dvo.setNewAdrZip(contractDvo.getNewAdrZip());
        dvo.setContDt(contractDvo.getCntrDt());
        dvo.setCopnDvCd(contractDvo.getCopnDvCd());
        dvo.setSellDscDbCd(contractDvo.getSellDscDbCd());
        dvo.setSdingCombin(contractDvo.getSdingCombin()); // lcst0);
        dvo.setCstSvAsnNo(
            StringUtil.isNotEmpty(dvo.getCstSvAsnNo()) ? dvo.getCstSvAsnNo() : contractDvo.getCstSvAsnNo()
        );

        WsncTimeTableProductDvo productDvo = mapper.selectProduct(dvo)
            .orElseThrow(() -> new BizException("MSG_ALT_NO_PRODUCT_FOUND"));
        dvo.setSidingYn(productDvo.getSidingYn());
        dvo.setSpayYn("3".equals(productDvo.getRglrSppPrcDvCd()) ? "Y" : "N"); // 일시불여부
        //------------------------------------------------------

        // 모종인지 확인
        if ("Y".equals(dvo.getSidingYn())) {

            // 일시불여부
            if ("Y".equals(dvo.getSpayYn())) {

                //------------------------------------------------------
                // getMojongDays_ilsibul
                sidingDays = this.mapper.selectSidingDaysForSpay(dvo);
                //------------------------------------------------------

                boolean isAdd40Days = false;

                for (WsncTimeTableSidingDaysDvo sidingDay : sidingDays) {
                    if (sidingDay.getAblDays().equals(DateUtil.formatDate(sellDate, "-"))) {
                        isAdd40Days = true;
                        dvo.setSowDay(sidingDay.getSowDay());
                        break;
                    }
                }

                if (!isAdd40Days) {
                    sellDate = sidingDays.get(0).getW3th();
                    dvo.setSowDay(sidingDays.get(0).getSowDay());
                }
                // 일반모종 타임테이블
            } else {
                //------------------------------------------------------
                sidingDays = mapper.selectSidingDays(dvo);
                //------------------------------------------------------
            }

        }

        dvo.setHcr("Y".equals(productDvo.getHcrYn()));

        // Cubig CC 홈케어 조회용 타임테이블 http://ccwells.kyowon.co.kr/obm/obm0800/obm0800.jsp
        // KSS접수와 동일하게 하기위해 (백현아 K 요청)
        // Cubig CC DATA_GB 변경할수 없음.
        // 상품코드로 접수구분과 DATA_GB 변경
        if (dvo.isHcr() && "C".equals(dvo.getChnlDvCd()) && "1".equals(dvo.getSvDvCd())
            && (StringUtil.isEmpty(dvo.getReturnUrl()))) {
            dvo.setChnlDvCd("W");
            dvo.setSvDvCd("4");
            dvo.setReturnUrl("http://ccwells.kyowon.co.kr/obm/obm0800/obm0800.jsp");
        }

        String prtnrNo01 = mapper.selectFnSvpdLocaraPrtnr01(dvo);
        String prtnrNoBS01 = mapper.selectFnSvpdLocaraPrtnrBs01(dvo);
        String prtnrNoOwr01 = mapper.selectFnSvpdLocaraPrtnrOwr01(dvo);

        dvo.setPrtnrNo01(prtnrNo01);
        dvo.setPrtnrNoBS01(prtnrNoBS01);
        dvo.setPrtnrNoOwr01(prtnrNoOwr01);
        dvo.setHcrYn(productDvo.getHcrYn());
        dvo.setExYn("C".equals(dvo.getChnlDvCd()) ? "Y" : "");

        //-----------------------------------------------------------------------------------------

        // 책임지역 담당자 찾기 selectTimeAssign_v2_step1
        rpbLocaraPsic = mapper.selectRpbLocaraPsic(dvo)
            .orElseThrow(() -> new BizException("MSG_ALT_NO_PSIC_FOUND"));

        // step1_with
        dvo.setPrtnrNo(rpbLocaraPsic.getIchrPrtnrNo());
        dvo.setLocalGb(rpbLocaraPsic.getRpbLocaraCd());
        dvo.setVstDowValCd(rpbLocaraPsic.getVstDowValCd());
        dvo.setOgTpCd(rpbLocaraPsic.getOgTpCd());

        // 담당자 정보 표시 selectTimeAssign_v2_step2
        psic = mapper.selectPsic(rpbLocaraPsic); // left_info

        // 시간표시 selectTimeAssign_v2_step3
        rpbLocaraPsic.setEmpTWrkCnt(mapper.selectEmpTWrkCnt(rpbLocaraPsic));
        rpbLocaraPsic.setDegWrkCnt(mapper.selectDegWrkCnt(rpbLocaraPsic));
        rpbLocaraPsic.setWkHhCd(mapper.selectWkHhCd(rpbLocaraPsic));
        assignTimes = mapper.selectAssignTimes(rpbLocaraPsic); // list1

        dvo.setDays(converter.mapDaysDvoToDto(mapper.selectTimeTableDates(req.baseYm())));
        dvo.setPsic(converter.mapPsicDvoToDto(psic)); // left_info = psics
        dvo.setSidingDays(converter.mapSidingDaysDvoToDto(sidingDays)); // list2 = sidingDays
        dvo.setOffDays(mapper.selectOffDays(dvo)); // offdays = offDays
        dvo.setDisableDays(converter.mapDisableDaysDvoToDto(mapper.selectDisableDays(dvo))); // diabledays = disableDays
        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession();
        dvo.setUserId(session.getEmployeeIDNumber());
        dvo.setRcpOgTpCd(session.getOgTpCd());

        String time = null;
        for (WsncTimeTableAssignTimeDvo assignTime : assignTimes) {

            WsncTimeTableSmPmNtDvo smPmNtDvo = converter.mapAssignTimeDvoToSmPmNtDvo(assignTime);
            time = assignTime.getTm();
            smPmNtDvo.setTime(time.substring(0, 2) + ":" + time.substring(2, 4));

            if (Integer.parseInt(time) >= 10000 && Integer.parseInt(time) < 50000) {
                dvo.getSmTimes().add(converter.mapSmPmNtDvoToSchDto(smPmNtDvo));
            } else if (Integer.parseInt(time) > 80000 && Integer.parseInt(time) < 140000) {
                dvo.getAmTimes().add(converter.mapSmPmNtDvoToSchDto(smPmNtDvo));
            } else if (Integer.parseInt(time) >= 140000 && Integer.parseInt(time) < 180000) {
                dvo.getPmTimes1().add(converter.mapSmPmNtDvoToSchDto(smPmNtDvo));
            } else if (Integer.parseInt(time) >= 180000 && Integer.parseInt(time) < 200000) {
                dvo.getPmTimes2().add(converter.mapSmPmNtDvoToSchDto(smPmNtDvo));
            } else
                dvo.getNtTimes().add(converter.mapSmPmNtDvoToSchDto(smPmNtDvo));
        }

        return converter.mapTimeAssignDvoToRes(dvo);
    }

    /**
    * @see "nosession_timeAssign.do"
    * */
    protected void noSessionTimeAssign() {}

    /**
    * @see "timeAssign.do > getTimeAssign_post_v2"
    * */
    protected void timeAssignWellsKmembers() {

    }

    /**
    * 타임테이블 시간선택(일정변경)
    *
    * @programId W-MP-U-0186P01
    * @see "nosession_mng_as_month.do"
    *
    * */
    public WsncTimeTableDto.FindRes getScheduleChoice(WsncTimeTableDto.FindScheChoReq req) {

        WsncTimeTableDvo dvo = converter.mapScheChoReqToDvo(req);
        dvo.getSmTimes().clear();
        dvo.getAmTimes().clear();
        dvo.getPmTimes1().clear();
        dvo.getPmTimes2().clear();
        dvo.getNtTimes().clear();
        // -------------------------------------------------
        // 고객검색-> 신규AS등록 -> 방문일자 선택
        // ## getMonthAsSchedule ##
        // ZIPNO1      = 449
        // ZIPNO2      = 64
        // GB_CD       = M
        // SEL_DATE    = 20230512
        // SALE_CD     = 4770
        // EMP_ID      = 1251831 -> 로그인한 사용자
        // DATA_GB     = 3
        // WRK_TYP_DTL = 3110
        // ORD_DT      = null
        // ORD_SEQ     = null
        // -------------------------------------------------

        dvo.setSellDate(StringUtil.nvl(req.sellDate(), DateUtil.getNowDayString()));
        dvo.setSvDvCd("M".equals(dvo.getChnlDvCd()) /*매니저*/ ? "3" /*A/S*/ : StringUtil.nvl(dvo.getSvDvCd(), ""));
        WsncTimeTableCntrDvo contractDvo = mapper.selectContract(dvo)
            .orElseThrow(() -> new BizException("MSG_ALT_NO_CONTRACT_FOUND"));
        dvo.setBasePdCd(contractDvo.getBasePdCd());
        dvo.setPdctPdCd(contractDvo.getPdctPdCd());
        dvo.setNewAdrZip(StringUtil.nvl(req.newAdrZip(), contractDvo.getNewAdrZip()));
        dvo.setRpbLocaraCd("");

        WsncTimeTableProductDvo productDvo = mapper.selectProduct(dvo)
            .orElseThrow(() -> new BizException("MSG_ALT_NO_PRODUCT_FOUND"));
        dvo.setSidingYn(productDvo.getSidingYn());

        dvo.setEmpId("3".equals(dvo.getSvDvCd()) ? mapper.selectFnSvpdLocaraPrtnr01(dvo) : dvo.getPrtnrNo());

        // 모종인지 확인
        if ("Y".equals(dvo.getSidingYn())) {
            dvo.setSidingDays(
                converter.mapSidingDaysDvoToDto(
                    this.mapper
                        .selectSidingDaysForSpay(dvo)
                )
            );
            dvo.setMonthSchedules(converter.mapMonthScheduleDvoToDto(mapper.selectMonthSchedule(dvo)));
        }

        List<WsncTimeTableDisableDaysDvo> disableDayDvos = mapper.selectDisableDays(dvo);
        dvo.setDisableDays(converter.mapDisableDaysDvoToDto(disableDayDvos));
        dvo.setDays(converter.mapDaysDvoToDto(mapper.selectTimeTableDates(req.baseYm())));

        return converter.mapSchdChoDvoToRes(dvo);
    }

    /**
    * @see "nosession_as_timeAssign.do"
    * */
    public WsncTimeTableDto.FindRes getTimeChoice(WsncTimeTableDto.FindTimeChoReq req) {

        WsncTimeTableDvo dvo = converter.mapTimeChoReqToDvo(req);

        dvo.setSellDate(StringUtil.nvl(dvo.getSellDate(), DateUtil.getNowDayString()));
        dvo.setSvDvCd("M".equals(dvo.getChnlDvCd()) /*매니저*/ ? "3" /*A/S*/ : StringUtil.nvl(dvo.getSvDvCd(), ""));

        dvo.setPrtnrNo01(mapper.selectFnSvpdLocaraPrtnr01(dvo));
        dvo.setPrtnrNoBS01(mapper.selectFnSvpdLocaraPrtnrBs01(dvo));
        dvo.setPrtnrNoOwr01(mapper.selectFnSvpdLocaraPrtnrOwr01(dvo));

        WsncTimeTableRpbLocaraPsicDvo rpbLocaraPsic = mapper.selectRpbLocaraPsic(dvo)
            .orElseThrow(() -> new BizException("MSG_ALT_NO_PSIC_FOUND"));
        dvo.setPrtnrNo(rpbLocaraPsic.getIchrPrtnrNo());
        dvo.setLocalGb(rpbLocaraPsic.getRpbLocaraCd());
        dvo.setVstDowValCd(rpbLocaraPsic.getVstDowValCd());
        dvo.setOgTpCd(rpbLocaraPsic.getOgTpCd());

        // 담당자 정보 표시 selectTimeAssign_v2_step2
        WsncTimeTablePsicDvo psic = mapper.selectPsic(rpbLocaraPsic); // left_info

        // 시간표시 selectTimeAssign_v2_step3
        rpbLocaraPsic.setEmpTWrkCnt(mapper.selectEmpTWrkCnt(rpbLocaraPsic));
        rpbLocaraPsic.setDegWrkCnt(mapper.selectDegWrkCnt(rpbLocaraPsic));
        rpbLocaraPsic.setWkHhCd(mapper.selectWkHhCd(rpbLocaraPsic));
        List<WsncTimeTableAssignTimeDvo> assignTimes = mapper.selectAssignTimes(rpbLocaraPsic); // list1

        String time = "";
        for (WsncTimeTableAssignTimeDvo assignTime : assignTimes) {

            WsncTimeTableSmPmNtDvo smPmNtDvo = converter.mapAssignTimeDvoToSmPmNtDvo(assignTime);
            time = assignTime.getTm();

            smPmNtDvo.setTime(time.substring(0, 2) + ":" + time.substring(2, 4));

            if (Integer.valueOf(time) >= 10000 && Integer.valueOf(time) < 50000) {
                dvo.getSmTimes().add(converter.mapSmPmNtDvoToTimDto(smPmNtDvo));
            } else if (Integer.valueOf(time) > 80000 && Integer.valueOf(time) < 140000) {
                dvo.getAmTimes().add(converter.mapSmPmNtDvoToTimDto(smPmNtDvo));
            } else if (Integer.valueOf(time) >= 140000 && Integer.valueOf(time) < 180000) {
                dvo.getPmTimes1().add(converter.mapSmPmNtDvoToTimDto(smPmNtDvo));
            } else if (Integer.valueOf(time) >= 180000 && Integer.valueOf(time) <= 190000) {
                dvo.getPmTimes2().add(converter.mapSmPmNtDvoToTimDto(smPmNtDvo));
            } else
                dvo.getNtTimes().add(converter.mapSmPmNtDvoToTimDto(smPmNtDvo));
        }

        dvo.setAssignTimes(converter.mapAssignTimeDvoToDto(assignTimes));
        dvo.setPsic(converter.mapPsicDvoToDto(psic));

        log.debug("[KDY]" + dvo.getPsic().vstPos());

        return converter.mapTimeChoDvoToRes(dvo);
    }

    /**
    * @see "nosession_bsnext_timeAssign"
    * */
    protected void noSessionBsTimeAssign() {

    }
}
