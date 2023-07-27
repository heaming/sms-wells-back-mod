package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractDto.*;

import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.api.client.util.Lists;
import com.kyowon.sflex.common.message.dvo.EmailSendReqDvo;
import com.kyowon.sflex.common.message.service.EmailService;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrDetailChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrDtlStatChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzContractNotifyFowrdindHistDvo;
import com.kyowon.sms.wells.web.contract.common.service.WctzHistoryService;
import com.kyowon.sms.wells.web.contract.ordermgmt.converter.WctaContractConverter;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.*;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaContractMapper;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.common.common.service.TemplateService;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaContractService {

    private final WctaContractMapper mapper;
    private final WctaContractConverter converter;
    private final WctzHistoryService historyService;
    private final TemplateService templateService;
    private final EmailService emailService;

    public PagingResult<SearchCntrNoRes> getContractNumberInqrPages(
        SearchCntrNoReq dto, PageInfo pageInfo
    ) {
        return mapper.selectContractNumberInqrPages(dto, pageInfo);
    }

    public List<String> sendContractEmail(List<SaveSendEmailsReq> dtos) throws Exception {
        String templateId = "TMP_CTA_WELLS_ELCN_GUD";
        String pdfUrl = ""; // TODO 계약서 pdf 생성 로직 추가
        List<String> emailUids = Lists.newArrayList();

        for (SaveSendEmailsReq dto : dtos) {
            String content = templateService.getTemplateContent(
                templateId, Map.of(
                    "cnrtNm", dto.cntrNm(),
                    "pdfUrl", pdfUrl
                )
            );
            String emailUid = emailService.sendEmail(
                EmailSendReqDvo.builder()
                    .title(templateService.getTemplateByTemplateId(templateId).getSendTemplateTitle())
                    .content(content)
                    .receiveUsers(List.of(EmailSendReqDvo.ReceiveUser.fromEmail(dto.emadr())))
                    .build()
            );
            String now = DateUtil.todayNnow();
            UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession();
            historyService.createContractNotifyFowrdindHistory(
                WctzContractNotifyFowrdindHistDvo.builder()
                    .notyFwTpCd("10") // 알림발송유형코드
                    .notyFwBizDvCd("10") // 알림발송업무구분코드
                    .akUsrId(session.getUserId()) // 요청자 ID
                    .rqrNm(session.getUserName())// 요청자명
                    .akDtm(now) // 요청일시
                    .fwDtm(now) // 발송일시
                    .cntrNo(dto.cntrNo()) // 계약번호
                    .cntrSn(dto.cntrSn()) // 계약일련번호
                    .fwLkIdkVal(emailUid) // 발송연계식별키값
                    .fwOjRefkVal1(templateId) // 발송대상참조키값1
                    .rcvrNm(dto.cntrNm()) // 수신자명
                    .notyFwRsCd("10") // 알림발송결과코드
                    .dtaDlYn("N") // 삭제여부
                    .build(),
                false
            );
            emailUids.add(emailUid);
        }
        return emailUids;
    }

    public List<SearchHomecareContractsRes> getHomecareContracts(List<SearchHomecareContractsReq> dtos) {
        return mapper.selectHomecareContracts(dtos);
    }

    @Transactional
    public int saveHomecareContracts(List<SaveHomecareContractsReq> dtos) {
        int processCount = 0;
        Iterator<SaveHomecareContractsReq> iterator = dtos.iterator();
        while (iterator.hasNext()) {
            SaveHomecareContractsReq dto = iterator.next();
            String histStrtDtm = DateUtil.getNowString();
            if (StringUtil.isNotEmpty(dto.duedt())) {
                mapper.updateHomecareContractsDuedt(dto);
            }
            if (StringUtil.isNotEmpty(dto.candt())) {
                mapper.updateHomecareContractsCandt(dto);
                historyService.createContractDetailStatChangeHistory(
                    WctzCntrDtlStatChangeHistDvo.builder()
                        .cntrNo(dto.cntrNo())
                        .cntrSn(dto.cntrSn())
                        .cntrDtlStatCd("303")
                        .histStrtDtm(histStrtDtm)
                        .build()
                );
            }
            historyService.createContractDetailChangeHistory(
                WctzCntrDetailChangeHistDvo.builder()
                    .cntrNo(dto.cntrNo())
                    .cntrSn(dto.cntrSn())
                    .histStrtDtm(histStrtDtm)
                    .build()
            );
        }
        return processCount;
    }

    public List<SearchRes> getApprovalAskDivides(String standardDt) {
        return mapper.selectApprovalAskDivides(standardDt);
    }

    public List<SearchConfirmAprPsicAksRes> getConfirmAprPsicAks(String cntrNo) {
        return mapper.selectConfirmAprPsicAks(cntrNo);
    }

    public List<SearchConfirmAprPsicPrchssRes> getConfirmAprPsicPrchss(String cntrNo) {
        return mapper.selectConfirmAprPsicPrchss(cntrNo);
    }

    public PagingResult<SearchConfirmApprovalBaseRes> getConfirmApprovalBasePages(
        SearchConfirmApprovalBaseReq dto,
        PageInfo pageInfo
    ) {
        return mapper.selectConfirmApprovalBases(dto, pageInfo);
    }

    public List<SearchConfirmApprovalBaseRes> getConfirmApprovalBasesExcelDownload(
        SearchConfirmApprovalBaseReq dto
    ) {
        return mapper.selectConfirmApprovalBases(dto);
    }

    // 렌탈료 가져오기
    public WctaPdPrcFnlDtlDvo getRentalFee(SearchRentalFeeReq req) {
        WctaPdPrcFnlDtlDvo dvo = mapper.selectRentalFee(req);
        if (Objects.isNull(dvo)) {
            dvo = new WctaPdPrcFnlDtlDvo();
            dvo.setRentalCheck("1");
        }
        return dvo;
    }

    @Transactional
    public int removeApprovalAskDivides(List<RemoveReq> dtos) {
        int processCount = 0;
        Iterator<RemoveReq> iterator = dtos.iterator();
        while (iterator.hasNext()) {
            RemoveReq dto = iterator.next();
            WctaCntrAprAkDvCdDvo dvo = converter.mapRemoveReqToWctaCntrAprAkDvCdDvo(dto);
            int result = mapper.deleteApprovalAskDivides(dvo);
            BizAssert.isTrue(result == 1, "MSG_ALT_DEL_ERR");
            processCount += result;
        }
        return processCount;
    }

    @Transactional
    public int saveConfirmApprovalBases(List<SaveConfirmApprovalBaseReq> dtos) {
        int processCount = 0;
        Iterator<SaveConfirmApprovalBaseReq> iterator = dtos.iterator();
        while (iterator.hasNext()) {
            SaveConfirmApprovalBaseReq dto = iterator.next();
            WctaCntrAprBaseBasDvo dvo = converter.mapSaveReqpToWctaCntrAprBaseBasDvo(dto);
            dvo.setCheckType("A");
            int checkCount = mapper.selectCountConfirmApprovalBases(dvo);
            BizAssert.isTrue(checkCount <= 0, "MSG_ALT_DUPLICATE_ROW_EXISTS");
            processCount += switch (dto.rowState()) {
                case CommConst.ROW_STATE_CREATED -> mapper.insertConfirmApprovalBases(dvo);
                case CommConst.ROW_STATE_UPDATED -> {
                    int result = mapper.updateConfirmApprovalBases(dvo);
                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
                    yield result;
                }
                default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");
            };
        }
        return processCount;
    }

    @Transactional
    public int removeConfirmApprovalBases(List<RemoveConfirmApprovalBaseReq> dtos) {
        int processCount = 0;
        Iterator<RemoveConfirmApprovalBaseReq> iterator = dtos.iterator();
        while (iterator.hasNext()) {
            RemoveConfirmApprovalBaseReq dto = iterator.next();
            WctaCntrAprBaseBasDvo dvo = converter.mapRemoveReqpToWctaCntrAprBaseBasDvo(dto);
            dvo.setCheckType("U");
            int checkCount = mapper.selectCountConfirmApprovalBases(dvo);
            String[] param = {dvo.getCntrAprAkDvCdNm()};
            BizAssert.isTrue(checkCount > 0, "MSG_ALT_PIC_NO_DEL_OBJ", param);
            int result = mapper.deleteConfirmApprovalBases(dvo);
            BizAssert.isTrue(result == 1, "MSG_ALT_DEL_ERR");
            processCount += result;
        }
        return processCount;
    }

    public List<SearchSpaySlamtInqrRes> getSpaySlamtInqr(SearchSpaySlamtInqrReq dto) {
        WctaSpaySlamtInqrDvo dvo = converter.mapSearchSpaySlamtInqrReqToWctaSpaySlamtInqrDvo(dto);

        //GUBN : W-WEB(홈케어）H-KSS(홈케어）, K-KSS(일반상품)
        if ("W".equals(dvo.getPdGubn())) {
            if (StringUtils.isAnyEmpty(dvo.getPdCd(), dvo.getDscGubn(), dvo.getVlDtm())) {
                return null;
            }
            /*if (StringUtil.isNull(dvo.getPdCd()) || StringUtil.isNull(dvo.getDscGubn())
                || StringUtil.isNull(dvo.getVlDtm())) {
                return null;
            }*/
        }

        //상품구분/상품코드／접수일/할인구분 필수 체크
        if (StringUtils.isAnyEmpty(dvo.getPdGubn(), dvo.getPdCd(), dvo.getVlDtm(), dvo.getDscGubn())) {
            return null;
        }
        /*if (StringUtil.isNull(dvo.getPdGubn()) || StringUtil.isNull(dvo.getPdCd()) || StringUtil.isNull(dvo.getVlDtm())
            || StringUtil.isNull(dvo.getDscGubn())) {
            return null;
        }*/

        //상품구분 (홈케어/일반상품) 필수 체크
        if (!("W".equals(dvo.getPdGubn()) || "K".equals(dvo.getPdGubn()) || "H".equals(dvo.getPdGubn()))) {
            return null;
        }

        //쿠폰은 웹에서만 등록 가능
        if ("C".equals(dvo.getDscGubn()) || "D".equals(dvo.getDscGubn())) {
            if (!dvo.getPdGubn().equals("W")) {
                return null;
            }
        }

        //쿠폰일 경우 ETC3-ETC4는　필수조건
        if ("W".equals(dvo.getPdGubn()) && ("C".equals(dvo.getDscGubn()) || "D".equals(dvo.getDscGubn()))) {
            if (StringUtil.isNull(dvo.getDscType())) {
                return null;
            }
        }

        //상품코드별 필수 체크
        if (Arrays.asList(new String[] {"3720", "3730", "3810", "3820", "3830", "3840"}).contains(dvo.getPdCd())) {
            if (StringUtil.isNull(dvo.getPdClsfId())) {
                return null;
            }
        }

        //홈케어　상품구분１，２필수체크위한　변수
        if (("W".equals(dvo.getPdGubn()) || "H".equals(dvo.getPdGubn())) && (!"3710".equals(dvo.getPdCd()))) {
            dvo.setPdClsfId("Y");
        }

        if ("3710".equals(dvo.getPdCd())) {
            dvo.setPdClsfId("");
        }

        //홈케어 용도구분 강제세팅
        if (Arrays.asList(new String[] {"3720", "3730", "3810", "3820", "3830", "3840", "3710"})
            .contains(dvo.getPdCd())) {
            if (StringUtil.isNull(dvo.getUseGubn())) {
                dvo.setUseGubn("0");
            }
        }

        return mapper.selectSpaySlamtInqr(dvo);
    }

    public List<SearchHomeCareMshChecksRes> getHomeCareMshChecks(String cntrCstNo) {
        return mapper.selectHomeCareMshChecks(cntrCstNo);
    }

    @Transactional
    public String saveRentalPackageGrpMngts(WctaRentalPackageGrpMngtsDvo dvo) {
        //사용자구분이 1:현업 아닐 경우 종료
        if (!"1".equals(dvo.getUserGubn())) {
            BizAssert.hasText(dvo.getBaseCntrNo(), "MSG_ALT_CHK_CONFIRM", new String[] {"MSG_TXT_USR_TYPE"}); //사용자유형을 확인하세요.
        }

        //작업구분이 1:등록, 4:삭제가 아닐 경우 종료
        if (!Arrays.asList(new String[] {"1", "4"}).contains(dvo.getInpGubn())) {
            BizAssert.hasText(dvo.getBaseCntrNo(), "MSG_ALT_CHK_CONFIRM", new String[] {"MSG_TXT_WK_CLS"}); //작업구분을 확인하세요.
        }
        if ("1".equals(dvo.getInpGubn())) {
            if ("22P".equals(dvo.getCntrRelTpCd())) {
                //계약번호, 계약상세번호가 존재하는지 여부 확인 비어었으면 종료
                BizAssert.hasText(dvo.getBaseCntrNo(), "MSG_ALT_CHK_CONFIRM", new String[] {"MSG_TXT_CNTR_NO"}); //계약번호를 확인하세요.
                BizAssert.hasText(dvo.getBaseDtlCntrNo(), "MSG_ALT_CHK_CONFIRM", new String[] {"MSG_TXT_CNTR_SN"}); //계약일련번호를 확인하세요.

                //계약관계상세내용(CNTR_REL_DTL_CN) 에 할인코드(14,15,16)이 존재하는지 여부 체크
                if (!Arrays.asList(new String[] {"14", "15", "16"}).contains(dvo.getCntrRelDtlCn())) {
                    return null;
                }

                List<SearchRentalPackageGrpMngtsRes> rentalPackageGrpMngts = mapper
                    .selectRentalPackageGrpMngts(dvo);
                int checkCount = rentalPackageGrpMngts.size();
                BizAssert.isTrue(checkCount <= 0, "MSG_ALT_DUPLICATE_ROW_EXISTS");
            }
            // String startYn = rentalPackageGrpMngts.get(0).startYn();
            int result = mapper.insertRentalPackageGrpMngts(dvo);
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR"); //저장에 실패 하였습니다.
        } else if ("4".equals(dvo.getInpGubn())) {
            if ("22P".equals(dvo.getCntrRelTpCd())) {
                BizAssert.hasText(dvo.getBaseCntrNo(), "MSG_ALT_CHK_CONFIRM", new String[] {"MSG_TXT_CNTR_NO"}); //계약번호를 확인하세요.
                BizAssert.hasText(dvo.getBaseDtlCntrNo(), "MSG_ALT_CHK_CONFIRM", new String[] {"MSG_TXT_CNTR_SN"}); //계약일련번호를 확인하세요.

                List<SearchRentalPackageGrpMngtsRes> rentalPackageGrpMngts = mapper
                    .selectRentalPackageGrpMngts(dvo);
                int checkCount = rentalPackageGrpMngts.size();
                BizAssert.isTrue(checkCount <= 0, "MSG_ALT_DUPLICATE_ROW_EXISTS");

                //작업구분이 4 && 상품시작여부가 'Y' 이면 삭제 불가
                String startYn = rentalPackageGrpMngts.get(0).startYn();
                if ("y".equals(startYn)) {
                    return null;
                }
            }
            int result = mapper.deleteRentalPackageGrpMngts(dvo);
            BizAssert.isTrue(result == 1, "MSG_ALT_DEL_ERR"); //삭제에 실패 하였습니다.
        }
        //등록된(입력된) 그룹번호 리턴
        return dvo.getCntrCstGrpId();
    }

    public PagingResult<SearchRnwMshCstRes> getRenewalMembershipCustomerPages(
        SearchRnwMshCstReq dto, PageInfo pageInfo
    ) {
        return mapper.selectRenewalMembershipCustomerPages(dto, pageInfo);
    }

    public PagingResult<SearchMngerPrtnrRes> getDistrictManagerPartnerPages(
        SearchMngerPrtnrReq dto, PageInfo pageInfo
    ) {
        return mapper.selectDistrictManagerPartnerPages(dto, pageInfo);
    }

    public List<SearchOnepluseoneRes> getOneplusoneContracts(SearchOnepluseoneReq dto) {
        return mapper.selectOneplusoneContracts(dto);
    }

    public List<SearchConfirmMshRes> getConfirmMemberships(SearchConfirmMshReq dto) {
        return mapper.selectConfirmMemberships(dto);
    }

    public List<SearchKMembersCancelAsksRes> getKMembersCancelAsks(SearchKMembersCancelAsksReq dto) {
        return mapper.selectKMembersCancelAsks(dto);
    }

    /**
     * 판매유입채널상세코드
     * @param cntrTpCd
     * @return sellInflwChnlDtlCd
     */
    public String getSaleInflowChnlDtlCd(String cntrTpCd) {
        if (CtContractConst.CNTR_TP_CD_ENSM.equals(cntrTpCd)) {
            return "9020";
        } else {
            UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession();
            String userTypeCode = session.getUserTypeCode();
            if (StringUtils.isEmpty(userTypeCode)) {
                return "1010"; // FIXME 테스트용, 추후 9999로 수정
                // return "9999";
            }
            String ogTpCd = session.getOgTpCd();
            if (userTypeCode.equals("P")) {
                if (ogTpCd.equals("W05")) {
                    return "5010";
                } else if (ogTpCd.equals("W04")) {
                    return "8050";
                } else if (ogTpCd.equals("W03")) {
                    return "1090";
                } else {
                    return "1010";
                }
            } else if (userTypeCode.equals("E")) {
                return "1030";
            } else if (userTypeCode.equals("C")) {
                return "1040";
            } else {
                return "9999";
            }
        }
    }
}
