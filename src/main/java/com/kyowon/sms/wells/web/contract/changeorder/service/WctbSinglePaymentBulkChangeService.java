package com.kyowon.sms.wells.web.contract.changeorder.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.changeorder.converter.WctbSinglePaymentBulkChangeConverter;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbSinglePaymentBulkChangeDto;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbSinglePaymentBulkChangeDto.SearchRes;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbSinglePaymentBulkChangeDvo;
import com.kyowon.sms.wells.web.contract.changeorder.mapper.WctbSinglePaymentBulkChangeMapper;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctbSinglePaymentBulkChangeService {

    private final WctbSinglePaymentBulkChangeMapper mapper;
    private final WctbSinglePaymentBulkChangeConverter converter;

    private final MessageResourceService messageService;

    public List<SearchRes> getSinglePaymentBulkChangs(String cntrNo, String cntrSn, String rfDt) {
        return mapper.selectSinglePaymentBulkChangs(cntrNo, cntrSn, rfDt);
    }

    public WctbSinglePaymentBulkChangeDto.SearchRgstRes getSinglePaymentBulkChangsRgst(String cntrNo, String cntrSn) {
        return mapper.selectSinglePaymentBulkChangsRgst(cntrNo, cntrSn);
    }

    public int saveSinglePaymentBulkChs(WctbSinglePaymentBulkChangeDto.SaveReq dto) throws ParseException {
        int res = 0;
        List<WctbSinglePaymentBulkChangeDto.SaveListReq> listDtos = dto.saveListReqs();
        WctbSinglePaymentBulkChangeDto.SaveStatusReq saveStatusDto = dto.saveStatusReq();
        for (WctbSinglePaymentBulkChangeDto.SaveListReq listDto : listDtos) {
            WctbSinglePaymentBulkChangeDvo dvo = converter.mapSaveListReqToWctbSinglePaymentBulkChangeDvo(listDto);

            //변경전내용
            String bfchCn = "매출일자:"
                + dvo.getCntrPdStrtdt()
                + "|취소일자:" + (StringUtils.isEmpty(dvo.getCntrCanDt()) ? "" : dvo.getCntrCanDt())
                + "|인정실적율:" + (StringUtils.isEmpty(dvo.getAckmtPerfRt()) ? "" : dvo.getAckmtPerfRt())
                + "|인정실적금액:" + (StringUtils.isEmpty(dvo.getAckmtPerfAmt()) ? "" : dvo.getAckmtPerfAmt())
                + "|수수료인정건수:" + (StringUtils.isEmpty(dvo.getFeeAckmtCt()) ? "" : dvo.getFeeAckmtCt())
                + "|수수료인정기준금액:" + (StringUtils.isEmpty(dvo.getFeeAckmtBaseAmt()) ? "" : dvo.getFeeAckmtBaseAmt())
                + "|수수료정액여부:" + (StringUtils.isEmpty(dvo.getFeeFxamYn()) ? "" : dvo.getFeeFxamYn())
                + "|무상멤버십기간:" + (StringUtils.isEmpty(dvo.getFrisuBfsvcPtrmN()) ? "" : dvo.getFrisuBfsvcPtrmN())
                + "|무상AS기간:" + (StringUtils.isEmpty(dvo.getFrisuAsPtrmN()) ? "" : dvo.getFrisuAsPtrmN())
                + "|설치일자:" + (StringUtils.isEmpty(dvo.getIstDt()) ? "" : dvo.getIstDt())
                + "|철거일자:" + (StringUtils.isEmpty(dvo.getReqdDt()) ? "" : dvo.getReqdDt())
                + "|보상일자:" + (StringUtils.isEmpty(dvo.getCpsDt()) ? "" : dvo.getCpsDt())
                + "|행사코드:" + (StringUtils.isEmpty(dvo.getSellEvCd()) ? "" : dvo.getSellEvCd())
                + "|BS업체구분코드:" + (StringUtils.isEmpty(dvo.getBfsvcBzsDvCd()) ? "" : dvo.getBfsvcBzsDvCd())
                + "|조달업체구분코드:" + (StringUtils.isEmpty(dvo.getSplyBzsDvCd()) ? "" : dvo.getSplyBzsDvCd())
                + "|컨택예약일:" + (StringUtils.isEmpty(dvo.getCttDuedt()) ? "" : dvo.getCttDuedt())
                + "|";

            dvo.setBfchCn(bfchCn);

            //세션정보
            UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession();

            // 데이터의 INSERT/UPDATE/유효시작일시/유효종료일시를 일관되게 맞추기 위해, 미리 조회해온다.
            WctbSinglePaymentBulkChangeDvo dateTimeDvo = mapper.selectDateTime();
            dvo.setFstRgstDtm(dateTimeDvo.getFstRgstDtm());
            dvo.setFstRgstUsrId(dateTimeDvo.getFstRgstUsrId());
            dvo.setFstRgstPrgId(dateTimeDvo.getFstRgstPrgId());
            dvo.setFstRgstDeptId(dateTimeDvo.getFstRgstDeptId());
            dvo.setFnlMdfcDtm(dateTimeDvo.getFnlMdfcDtm());
            dvo.setFnlMdfcUsrId(dateTimeDvo.getFnlMdfcUsrId());
            dvo.setFnlMdfcPrgId(dateTimeDvo.getFnlMdfcPrgId());
            dvo.setFnlMdfcDeptId(dateTimeDvo.getFnlMdfcDeptId());
            //AS-IS 와 동일한 메세지의 오류
            // 1)변경case 별 체크 및 데이타 set
            if ("701".equals(saveStatusDto.procsDv())) {// 보상일자변경
                // kiwi철거일자와 다르면 오류
                BizAssert.isTrue(
                    StringUtils.equals(dvo.getReqdDt(), saveStatusDto.compD()),
                    messageService.getMessage("MSG_ALT_KIWI_DEM_DT_CHK", dvo.getCntrDtlNo())
                ); //계약번호 {0}의 KIWI철거일자를 확인하십시오！

                // 계약WELLS상세.보상일자(CPS_DT) = 입력일자
                dvo.setCompD(saveStatusDto.compD());
                res = mapper.updateCntrWellsDtlCpsDt(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_APR_CAN_ERR");
                // 계약WELLS상세변경이력 생성
                res = mapper.updateCntrWellsDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_APR_CAN_ERR");
                res = mapper.insertCntrWellsDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_APR_CAN_ERR");
            } else if ("702".equals(saveStatusDto.procsDv())) { // 취소일자변경
                // 조회데이타에 보상일자가 있으면 보상완료고객으로 수정불가
                BizAssert.isTrue(
                    StringUtils.isEmpty(dvo.getCpsDt()),
                    messageService.getMessage("MSG_TXT_NOT_MODIFY_COMP_COMPLETE", dvo.getCntrDtlNo())
                ); // 수정불가! 보상완료된 고객입니다.
                if (!StringUtils.isEmpty(dvo.getCntrAmt())) { // 청약금이 존재할때
                    BizAssert.isTrue(
                        "57".equals(dvo.getAlncmpCd()),
                        messageService.getMessage("MSG_ALT_NOT_MODIFY_SBSCM_CST", dvo.getCntrDtlNo())
                    ); // 수정불가! 청약금 존재고객입니다.
                }
                if (!("36717".equals(session.getEmployeeIDNumber()) || "38754".equals(session.getEmployeeIDNumber())
                    || "31331".equals(session.getEmployeeIDNumber()))) { // 사번이 36717,38754,31331 이 아니면
                    BizAssert.isTrue(
                        !StringUtils.isEmpty(dvo.getIstDt()),
                        messageService.getMessage("MSG_ALT_NOT_MODIFY_IST_COMPLETE", dvo.getCntrDtlNo())
                    ); // 수정불가! 설치완료된 고객입니다!
                }
                //취소일자(계약상세.계약상품종료일자)=입력취소일자
                dvo.setCancDt(saveStatusDto.cancDt());
                res = mapper.updateCntrDtlCanDtCh(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_APR_CAN_ERR");
                //계약상세 이력생성
                res = mapper.updateCntrDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_APR_CAN_ERR");
                res = mapper.insertCntrDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_APR_CAN_ERR");

                // TODO : 실적체크해서 실적정보가 있으면 실적업데이트
            } else if ("703".equals(saveStatusDto.procsDv())) { // 컨택정보변경
                // 컨택대상ID(CTT_OJ_ID)가 없으면 리턴
                BizAssert.isTrue(
                    StringUtils.isNotEmpty(dvo.getCttOjId()),
                    messageService.getMessage("MSG_ALT_NO_CTT_OBJ", dvo.getCntrDtlNo())
                ); // 컨택대상이 없습니다.

                //계약WELLS상세.보상일자가 있으면 보상완료고객으로 수정불가
                BizAssert.isTrue(
                    StringUtils.isEmpty(dvo.getCpsDt()),
                    messageService.getMessage("MSG_TXT_NOT_MODIFY_COMP_COMPLETE", dvo.getCntrDtlNo())
                ); // 수정불가！　보상완료된　고객입니다！

                //계약상세상태코드(CNTR_DTL_STAT_CD)가 정상(101)이 아니면 수정불가
                BizAssert.isTrue(
                    "101".equals(dvo.getCntrDtlStatCd()),
                    messageService.getMessage("MSG_ALT_NOT_MODIFY_COMP_COMPLETE", dvo.getCntrDtlNo())
                ); // 수정불가！　취소완료된　고객입니다！

                //계약WELLS상세.설치일자(IST_DT)가 있으면 설치고객은 수정불가
                BizAssert.isTrue(
                    StringUtils.isEmpty(dvo.getIstDt()),
                    messageService.getMessage("MSG_ALT_NOT_MODIFY_IST_COMPLETE", dvo.getCntrDtlNo())
                ); // 수정불가！　설치완료된　고객입니다！
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
                Date dateCntrCnfmDtm = formatter.parse(dvo.getCntrCnfmDtm());
                Date dateRgstDtm = formatter.parse(dvo.getRgstDtm());
                BizAssert.isTrue(
                    !dateRgstDtm.after(dateCntrCnfmDtm),
                    messageService.getMessage("MSG_ALT_NOT_MODIFY_RCPDT_CHK", dvo.getCntrDtlNo())
                ); // 접수일자가 너무 과거일자 입니다. 확인바랍니다.

                //컨택코드 셋팅
                dvo.setCttCd(saveStatusDto.cttCd());
                // (입력)예정일이 있으면
                if (StringUtils.isNotEmpty(saveStatusDto.duedt())) {
                    if ("N".equals(saveStatusDto.duedtDel())) { // 예약일삭제='N' 이면
                        // 컨택기본.컨택예정일자 입력일자로 업데이트
                        dvo.setDuedt(saveStatusDto.duedt());
                        res = mapper.updateCttBasCttCdCh(dvo);
                        BizAssert.isTrue(res == 1, "MSG_ALT_APR_CAN_ERR");
                        //컨택변경이력 생성
                        res = mapper.updateCttChHist(dvo);
                        BizAssert.isTrue(res == 1, "MSG_ALT_APR_CAN_ERR");
                        res = mapper.insertCttChHist(dvo);
                        BizAssert.isTrue(res == 1, "MSG_ALT_APR_CAN_ERR");
                    } else if ("Y".equals(saveStatusDto.duedtDel())) { // 예약일삭제='Y' 이면
                        // 컨택기본.컨택예정일자 NULL로 업데이트
                        dvo.setDuedt(null);
                        res = mapper.updateCttBasCttCdCh(dvo);
                        BizAssert.isTrue(res == 1, "MSG_ALT_APR_CAN_ERR");
                        //컨택변경이력 생성
                        res = mapper.updateCttChHist(dvo);
                        BizAssert.isTrue(res == 1, "MSG_ALT_APR_CAN_ERR");
                        res = mapper.insertCttChHist(dvo);
                        BizAssert.isTrue(res == 1, "MSG_ALT_APR_CAN_ERR");

                        // 계약상세.컨택대상ID = NULL
                        res = mapper.updateCntrDtlCttCdCh(dvo);
                        BizAssert.isTrue(res == 1, "MSG_ALT_APR_CAN_ERR");

                        //계약상세 이력생성
                        res = mapper.updateCntrDchHist(dvo);
                        BizAssert.isTrue(res == 1, "MSG_ALT_APR_CAN_ERR");
                        res = mapper.insertCntrDchHist(dvo);
                        BizAssert.isTrue(res == 1, "MSG_ALT_APR_CAN_ERR");
                    }
                } else if (StringUtils.isEmpty(saveStatusDto.duedt())) { // (입력)예정일이 없으면
                    if ("Y".equals(saveStatusDto.duedtDel())) { // 예약일삭제='Y' 이면
                        //컨택기본.컨택예정일자(CTT_DUEDT) NULL로 업데이트
                        dvo.setDuedt(null);
                        res = mapper.updateCttBasCttCdCh(dvo);
                        BizAssert.isTrue(res == 1, "MSG_ALT_APR_CAN_ERR");
                        //컨택변경이력 생성
                        res = mapper.updateCttChHist(dvo);
                        BizAssert.isTrue(res == 1, "MSG_ALT_APR_CAN_ERR");
                        res = mapper.insertCttChHist(dvo);
                        BizAssert.isTrue(res == 1, "MSG_ALT_APR_CAN_ERR");

                        // 계약상세.컨택대상ID = NULL
                        res = mapper.updateCntrDtlCttCdCh(dvo);
                        BizAssert.isTrue(res == 1, "MSG_ALT_APR_CAN_ERR");

                        //계약상세 이력생성
                        res = mapper.updateCntrDchHist(dvo);
                        BizAssert.isTrue(res == 1, "MSG_ALT_APR_CAN_ERR");
                        res = mapper.insertCntrDchHist(dvo);
                        BizAssert.isTrue(res == 1, "MSG_ALT_APR_CAN_ERR");
                    }
                }
            } else if ("704".equals(saveStatusDto.procsDv())) { // 이체정보변경
                // TODO: AS-IS처리내역, 매핑안됨 ( 할부이체(LCCHK7):1은행2카드N보류    , 멤버십이체(LCET02):1은행2카드N보류 업데이트 )
            } else if ("705".equals(saveStatusDto.procsDv())) { // 인정금액정보
                // 참고SQL 에서 로그인사번이 없으면 당월접수건이 아니면 수정불가 TODO: [참고SQL] -> 권한처리 확인필요!!
                dvo.setFxamYnCh(saveStatusDto.fxamYnCh()); // 정액여부 변경
                dvo.setPdAccCnt(saveStatusDto.pdAccCnt()); // 인정건수
                dvo.setRecogAmt(saveStatusDto.recogAmt()); // 인정금액
                dvo.setRecogRt(saveStatusDto.recogRt()); // 인정율
                dvo.setPdStdFee(saveStatusDto.pdStdFee()); // 기준수수료
                res = mapper.updateCntrDtlRecogAmtInfCh(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_APR_CAN_ERR");

                //계약상세 이력생성
                res = mapper.updateCntrDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_APR_CAN_ERR");
                res = mapper.insertCntrDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_APR_CAN_ERR");
            } else if ("706".equals(saveStatusDto.procsDv())) { // 무상멤버십/AS정보 변경
                //계약WELLS상세.행사코드(SELL_EV_CD) = 'F' 체크해서 아니면 수정불가(F:국고보조대상상품)
                BizAssert.isTrue(
                    "F".equals(dvo.getSellEvCd()),
                    messageService.getMessage("MSG_ALT_NOT_MODIFY_NO_NAT_SUB", dvo.getCntrDtlNo())
                ); // 수정불가.. 국고보조 대상상품이 아닙니다.

                // 계약기본.계약확정일시(CNTR_CNFM_DTM)월 체크해서 당월접수건만 처리가능
                String cntrCnfmMm = dvo.getCntrCnfmDtm().substring(0, 6);
                String cntrRgstMm = dvo.getRgstDtm().substring(0, 6);
                BizAssert.isTrue(
                    cntrRgstMm.equals(cntrCnfmMm),
                    messageService.getMessage("MSG_ALT_NOT_MODIFY_NO_THM_RCP", dvo.getCntrDtlNo())
                ); // 수정불가! 당월접수만 처리가능합니다!

                //계약WELLS상세.무상BS기간수(FRISU_BFSVC_PTRM_N)[무상멤버십]:입력기간 수     계약WELLS상세.무상AS기간수(FRISU_AS_PTRM_N): 입력기간 수
                if (!StringUtils.isAllEmpty(dvo.getFrisuMsh(), dvo.getFrisuAs())) { // 둘다 null일 경우 path
                    dvo.setFrisuMsh(saveStatusDto.frisuMsh());
                    dvo.setFrisuAs(saveStatusDto.frisuAs());
                    res = mapper.updateCntrWellsDtlFreeAsBsInfCh(dvo);
                    BizAssert.isTrue(res == 1, "MSG_ALT_APR_CAN_ERR");

                    // 계약WELLS상세변경이력 생성
                    res = mapper.updateCntrWellsDchHist(dvo);
                    BizAssert.isTrue(res == 1, "MSG_ALT_APR_CAN_ERR");
                    res = mapper.insertCntrWellsDchHist(dvo);
                    BizAssert.isTrue(res == 1, "MSG_ALT_APR_CAN_ERR");
                }
            } else if ("707".equals(saveStatusDto.procsDv())) { // 행사코드 변경
                //계약WELLS상세.행사코드(SELL_EV_CD) = 'F' 이면(F:국고보조대상상품)
                if ("F".equals(dvo.getSellEvCd())) {
                    // 판매유형상세코드(SELL_TP_DTL_CD)=13,25:환경가전 여부 확인해서 환경가전이 아니면 수정불가 : 국고보조금상품이면 환경가전만 수정가능
                    BizAssert.isTrue(
                        "13".equals(dvo.getSellTpDtlCd()) || "25".equals(dvo.getSellTpDtlCd()), "MSG_ALT_SVE_ERR"
                    ); // 수정불가.. 국고보조는 환경 가전만 가능합니다.

                    //계약기본.계약확정일시(CNTR_CNFM_DTM)월 체크해서 당월접수건만 처리가능
                    String cntrCnfmMm = dvo.getCntrCnfmDtm().substring(0, 6);
                    String cntrRgstMm = dvo.getRgstDtm().substring(0, 6);
                    BizAssert.isTrue(
                        cntrRgstMm.equals(cntrCnfmMm),
                        messageService.getMessage("MSG_ALT_NOT_MODIFY_NO_THM_RCP", dvo.getCntrDtlNo())
                    ); // 수정불가！　당월접수만　처리가능합니다！
                }
                //그외는 행사코드 업데이트
                dvo.setEvCd(saveStatusDto.evCd());
                res = mapper.updateCntrWellsDtlEvCdCh(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_APR_CAN_ERR");

                // 계약WELLS상세변경이력 생성
                res = mapper.updateCntrWellsDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_APR_CAN_ERR");
                res = mapper.insertCntrWellsDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_APR_CAN_ERR");
            } else if ("708".equals(saveStatusDto.procsDv())) { // 설치 취소
                //계약WELLS상세.설치일자(IST_DT)가 없으면 수정불가
                BizAssert.isTrue(
                    StringUtils.isNotEmpty(dvo.getIstDt()),
                    messageService.getMessage("MSG_ALT_NOT_MODIFY_NO_IST_DT", dvo.getCntrDtlNo())
                ); // 수정불가! 설치된 계약만 처리가능합니다!

                //당월설치만 수정가능
                String cntrIstMm = dvo.getIstDt().substring(0, 6);
                String cntrRgstMm = dvo.getRgstDtm().substring(0, 6);
                BizAssert.isTrue(
                    cntrRgstMm.equals(cntrIstMm),
                    messageService.getMessage("MSG_ALT_NOT_MODIFY_NO_THM_IST", dvo.getCntrDtlNo())
                ); // 수정불가! 당월설치만 가능합니다!

                //계약WELLS상세 설치일자 제거
                res = mapper.updateCntrWellsDtlIstCan(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_APR_CAN_ERR");

                // 계약WELLS상세변경이력 생성
                res = mapper.updateCntrWellsDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_APR_CAN_ERR");
                res = mapper.insertCntrWellsDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_APR_CAN_ERR");

                //설치취소(8)일때 출고정보 삭제(TODO : 웰스는 배송정보 삭제하기위해 삭제서비스호출 형태로 )
            } else if ("709".equals(saveStatusDto.procsDv())) { // 접수일변경
                // 계약기본.계약확정일시(CNTR_CNFM_DTM)월 체크해서 당월접수건만 변경가능
                String cntrCnfmMm = dvo.getCntrCnfmDtm().substring(0, 6);
                String cntrRgstMm = dvo.getRgstDtm().substring(0, 6);
                BizAssert.isTrue(
                    cntrRgstMm.equals(cntrCnfmMm),
                    messageService.getMessage("MSG_ALT_NOT_MODIFY_NO_THM_RCP", dvo.getCntrDtlNo())
                ); // 당월 접수 건만 변경 가능합니다.
                                                                                                                                                 // 제휴카드(ALNCMP_CD) = 60:카드사제휴 인 데이터만 변경가능
                BizAssert.isTrue(
                    "60".equals(dvo.getAlncmpCd()),
                    messageService.getMessage("MSG_ALT_NOT_MODIFY_NO_SUBSC_DATA", dvo.getCntrDtlNo())
                ); // 청약 데이터만 변경 가능합니다.

                //매출확정일(계약상품시작일자:CNTR_PD_STRTDT)이 있는 데이터는 수정불가
                BizAssert.isTrue(
                    StringUtils.isEmpty(dvo.getCntrPdStrtdt()),
                    messageService.getMessage("MSG_ALT_NOT_MODIFY_SL_CNFM_DATA", dvo.getCntrDtlNo())
                ); // 매출 확정 데이터 입니다.

                //계약일자(계약상품시작일자:CNTR_PD_STRTDT)에 입력일자 저장
                dvo.setRcpDtChDuedt(saveStatusDto.rcpDtChDuedt());
                res = mapper.updateCntrDtlRcpdtCh(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_APR_CAN_ERR");
                //계약상세 이력생성
                res = mapper.updateCntrDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_APR_CAN_ERR");
                res = mapper.insertCntrDchHist(dvo);
                BizAssert.isTrue(res == 1, "MSG_ALT_APR_CAN_ERR");

                //TODO : 할부계약이 있으면 할부정보 변경 확인필요!!
            } else if ("710".equals(saveStatusDto.procsDv())) { // 법인미수입금대상설정
                // 법인미수금(CRP_UC_AMT) 없으면 수정불가 : 법인 미수금이 존재하지 않습니다
                BizAssert.isTrue(
                    StringUtils.isNotEmpty(dvo.getCrpUcAmt()),
                    messageService.getMessage("MSG_ALT_NOT_MODIFY_NO_CRP_UC_AMT", dvo.getCntrDtlNo())
                ); // 법인 미수금이 존재하지 않습니다.

                // 법인미수금(CRP_UC_AMT) > 0 이고
                if (Integer.parseInt(dvo.getCrpUcAmt()) > 0) {
                    // TODO : 법인미수입금대상 작업 구분(LCST18) = 'B' 이면 이미 변경된 대상으로 수정불가 컬럼 확인 필요
                }

                // TODO : 법인미수입금대상작업구분(LCST18) = 'B'
            } else if ("711".equals(saveStatusDto.procsDv())) { // BS업체구분값 변경
                if (!StringUtils.isAllEmpty(dvo.getModBfsvcBzsDvCd(), dvo.getModSplyBzsDvCd())) {// 입력값이 없으면 path
                    // 계약WELLS상세.BS업체구분코드(BFSVC_BZS_DV_CD) = 입력값
                    // 계약WELLS상세.조달업체구분코드(SPLY_BZS_DV_CD) = 입력값
                    res = mapper.updateCntrWellsDtlBsCh(dvo);
                    BizAssert.isTrue(res == 1, "MSG_ALT_APR_CAN_ERR");

                    // 계약WELLS상세변경이력 생성
                    res = mapper.updateCntrWellsDchHist(dvo);
                    BizAssert.isTrue(res == 1, "MSG_ALT_APR_CAN_ERR");
                    res = mapper.insertCntrWellsDchHist(dvo);
                    BizAssert.isTrue(res == 1, "MSG_ALT_APR_CAN_ERR");
                }

            }
            //변경사유 세팅
            dvo.setChRson(saveStatusDto.chRson());

            // 계약변경요청내용
            String cntrChAkCn = "매출일자:" + (StringUtils.isEmpty(dvo.getRcpDtChDuedt()) ? "" : dvo.getRcpDtChDuedt())
                + "|취소일자:" + (StringUtils.isEmpty(dvo.getCancDt()) ? "" : dvo.getCancDt())
                + "|인정실적율:" + (StringUtils.isEmpty(dvo.getRecogRt()) ? "" : dvo.getRecogRt())
                + "|인정실적금액:" + (StringUtils.isEmpty(dvo.getRecogAmt()) ? "" : dvo.getRecogAmt())
                + "|수수료인정건수:" + (StringUtils.isEmpty(dvo.getPdAccCnt()) ? "" : dvo.getPdAccCnt())
                + "|수수료인정기준금액:" + (StringUtils.isEmpty(dvo.getPdStdFee()) ? "" : dvo.getPdStdFee())
                + "|수수료정액여부:" + (StringUtils.isEmpty(dvo.getFxamYnCh()) ? "" : dvo.getFxamYnCh())
                + "|무상멤버십기간:" + (StringUtils.isEmpty(dvo.getFrisuMsh()) ? "" : dvo.getFrisuMsh())
                + "|무상AS기간:" + (StringUtils.isEmpty(dvo.getFrisuAs()) ? "" : dvo.getFrisuAs())
                + "|설치일자:"
                + "|철거일자:"
                + "|보상일자:" + (StringUtils.isEmpty(dvo.getCompD()) ? "" : dvo.getCompD())
                + "|행사코드:" + (StringUtils.isEmpty(dvo.getEvCd()) ? "" : dvo.getEvCd())
                + "|BS업체구분코드:" + (StringUtils.isEmpty(dvo.getModBfsvcBzsDvCd()) ? "" : dvo.getModBfsvcBzsDvCd())
                + "|조달업체구분코드:" + (StringUtils.isEmpty(dvo.getModSplyBzsDvCd()) ? "" : dvo.getModSplyBzsDvCd())
                + "|컨택예약일:" + (StringUtils.isEmpty(dvo.getDuedt()) ? "" : dvo.getDuedt())
                + "|변경사유:" + (StringUtils.isEmpty(dvo.getChRson()) ? "" : dvo.getChRson())
                + "|";

            dvo.setCntrChAkCn(cntrChAkCn);

            //계약변경접수기본 생성
            res = mapper.insertCntrChRcpBas(dvo);
            BizAssert.isTrue(res == 1, "MSG_ALT_APR_CAN_ERR");

            // 계약변경접수변경이력 생성
            res = mapper.insertCntrChRcchHist(dvo);
            BizAssert.isTrue(res == 1, "MSG_ALT_APR_CAN_ERR");

            //계약변경접수상세 생성
            res = mapper.insertCntrChRcpDtl(dvo);
            BizAssert.isTrue(res == 1, "MSG_ALT_APR_CAN_ERR");

            // 계약변경접수상세변경이력 생성
            res = mapper.insertCntrChRcpDchHist(dvo);
            BizAssert.isTrue(res == 1, "MSG_ALT_APR_CAN_ERR");
        }
        return res;
    }
}
