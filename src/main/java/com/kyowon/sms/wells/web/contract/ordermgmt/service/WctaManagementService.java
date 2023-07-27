package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sflex.common.message.dvo.KakaoSendReqDvo;
import com.kyowon.sflex.common.message.service.KakaoMessageService;
import com.kyowon.sms.wells.web.contract.ordermgmt.converter.WctaManagementConverter;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaManagementDto.*;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaDfntAckdReqDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaMastOrdrDtptDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaManagementMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WctaManagementService {
    //TODO : CALLBACK 수정
    private static final String CALLBACK = "15776688";
    private final WctaManagementMapper mapper;
    private final WctaManagementConverter converter;
    private final KakaoMessageService kakaoMessageService; //카카오톡 메신저 알림톡
    WctaDfntAckdReqDvo paramMap = new WctaDfntAckdReqDvo();
    WctaMastOrdrDtptDvo paramKakaoTalk = new WctaMastOrdrDtptDvo();

    public SearchRes getContractManagements(SearchKssOrdrListReq dto) {
        if (Arrays.asList(new String[] {"A", "N", "U"}).contains(dto.cntrDv())) {
            List<SearchKssOrdrListRes> searchKssOrdrListResList = mapper.selectKssOrdrList(dto);
            return new SearchRes(searchKssOrdrListResList, null, null);
        } else if (dto.cntrDv().equals("R")) {
            List<SearchRePromConcListRes> searchRePromConcListResList = mapper.selectRePromConcList(dto);
            return new SearchRes(null, searchRePromConcListResList, null);
        } else if (dto.cntrDv().equals("S")) {
            List<SearchEmployeePurchaseListRes> searchEmployeePurchaseListResList = mapper
                .selectEmployeePurchaseList(dto);
            return new SearchRes(null, null, searchEmployeePurchaseListResList);
        }
        return null;
    }

    public SearchCntrDtlRes getContractMngtDtls(SearchLspyOrdrDtptListReq dto) {

        String cntrNo = dto.cntrNo();
        String cntrPrgsStatCd = dto.cntrPrgsStatCd();

        if (dto.cntrwTpCd().equals("1")) { // 일시불(환경가전)
            List<SearchLspyOrdrDtptListRes> searchLspyOrdrDtptListResList = converter
                .mapWctaLspyOrdrDtptListDvoToSearchLspyOrdrDtptListRes(mapper.selectLspyOrdrDtptList(cntrNo));
            return new SearchCntrDtlRes(searchLspyOrdrDtptListResList, null, null, null, null, null, null);
        } else if (dto.cntrwTpCd().equals("2")) { // 일시불(BH)
            List<SearchBhOrdrDtptListRes> searchBhOrdrDtptListResList = converter
                .mapWctaBhOrdrDtptListDvoToSearchBhOrdrDtptListRes(mapper.selectBhOrdrDtptList(cntrNo));
            return new SearchCntrDtlRes(null, searchBhOrdrDtptListResList, null, null, null, null, null);
        } else if (dto.cntrwTpCd().equals("3") || dto.cntrwTpCd().equals("8")) { // 렌탈/장기할부
            List<SearchRentOrdrDtptListRes> searchRentOrdrDtptListResList = null;
            if (dto.cntrwTpCd().equals("3")) { // 렌탈
                searchRentOrdrDtptListResList = converter
                    .mapWctaRentOrdrDtptListDvoToSearchRentOrdrDtptListRes(
                        mapper.selectRentOrdrDtptList(cntrNo, cntrPrgsStatCd)
                    );
            } else if (dto.cntrwTpCd().equals("8")) { // 장기할부
                searchRentOrdrDtptListResList = converter
                    .mapWctaRentOrdrDtptListDvoToSearchRentOrdrDtptListRes(
                        mapper.selectLtmIstmOrdrDtptList(cntrNo, cntrPrgsStatCd)
                    );
            }
            return new SearchCntrDtlRes(null, null, searchRentOrdrDtptListResList, null, null, null, null);
        } else if (dto.cntrwTpCd().equals("4")) { // 멤버십
            List<SearchMbOrdrDtptListRes> searchMbOrdrDtptListResList = converter
                .mapWctaMbOrdrDtptListDvoToSearchMbOrdrDtptListRes(mapper.selectMbOrdrDtptList(cntrNo));
            return new SearchCntrDtlRes(null, null, null, searchMbOrdrDtptListResList, null, null, null);
        } else if (dto.cntrwTpCd().equals("5")) { // 홈케어서비스
            List<SearchHcsOrdrDtptListRes> searchHcsOrdrDtptListResList = converter
                .mapWctaHcsOrdrDtptListDvoToSearchHcsOrdrDtptListRes(mapper.selectHcsOrdrDtptList(cntrNo));
            return new SearchCntrDtlRes(null, null, null, null, searchHcsOrdrDtptListResList, null, null);
        } else if (dto.cntrwTpCd().equals("6")) { // 모종 일시불
            List<SearchPlantsOrdrDtptListRes> searchPlantsOrdrDtptListResList = converter
                .mapWctaPlantsOrdrDtptListDvoToSearchPlantsOrdrDtptListRes(mapper.selectPlantsOrdrDtptList(cntrNo));
            return new SearchCntrDtlRes(null, null, null, null, null, searchPlantsOrdrDtptListResList, null);
        } else if (dto.cntrwTpCd().equals("7")) { // 정기배송
            List<SearchRglrDlvrOrdrDtptListRes> searchRglrDlvrOrdrDtptListResList = converter
                .mapWctaRglrDlvrOrdrDtptListDvoToSearchRglrDlvrOrdrDtptListRes(
                    mapper.selectRglrDlvrOrdrDtptList(cntrNo)
                );
            return new SearchCntrDtlRes(null, null, null, null, null, null, searchRglrDlvrOrdrDtptListResList);
        }
        return null;
    }

    @Transactional
    public int saveConfirmApprovals(List<SaveConfirmApprovalsReq> dtos) {
        int processCount = 0;
        boolean isPymnSkip = false;

        String cntrNo = dtos.get(0).cntrNo();
        String cntrSn = dtos.get(0).cntrSn();
        List<SearchOrdrInfo4ReqDfntRes> ordrInfo4ReqDfntList = mapper.selectOrdrInfo4ReqDfntList(cntrNo, cntrSn);
        //비대면(30) && 계약서구분(3:렌탈,8:장기할부) && 조직유형코드:W05(Z95)
        if (StringUtil.isNotEmpty(ordrInfo4ReqDfntList.get(0).cstStlmInMthCd())) {
            if (ordrInfo4ReqDfntList.get(0).cstStlmInMthCd().equals("30")
                && Arrays.asList(new String[] {"3", "8"}).contains(ordrInfo4ReqDfntList.get(0).cntrwTpCd())
                && ordrInfo4ReqDfntList.get(0).sellOgTpCd().equals("W05")) {
                isPymnSkip = true;
            }

            //비대면(30) && 판매유입채널상세코드 :직원구매(9020) && 판매유입채널상세코드:아웃바운드(SalesTM)
            if (ordrInfo4ReqDfntList.get(0).cstStlmInMthCd().equals("30")
                && Arrays.asList(new String[] {"9020", "3010"})
                    .contains(ordrInfo4ReqDfntList.get(0).sellInflwChnlDtlCd())) {
                isPymnSkip = true;
            }
        }

        if (ordrInfo4ReqDfntList.size() == 0) {
            throw new BizException("MSG_ALT_ORD_INF_NOT_CONF" + "\n" + "MSG_ALT_CNFM_APR_CANT"); // 주문정보를 확인할 수 없습니다.\n확정승인을 할 수 없습니다.
        }

        //log.debug("cntrPrgsStatCd : {}", ordrInfo4ReqDfntList.get(0).cntrPrgsStatCd());
        //계약진행상태코드 결제완료 인지 여부 체크
        if (!ordrInfo4ReqDfntList.get(0).cntrPrgsStatCd().equals("50") && !isPymnSkip) {
            throw new BizException("MSG_ALT_STLM_FSH_STAT_CAN_ONLY_APR"); // 결제 완료 상태에서만 승인 할 수 있습니다.
        }

        paramMap.setCntrNo(dtos.get(0).cntrNo());
        paramMap.setIsCurReq("Y");
        paramMap.setCancYn("N");
        paramMap.setAprvYn("N");
        paramMap.setRecvId("");

        List<SearchDfntAckdReqListRes> dfntAckdReqList = mapper.selectDfntAckdReqList(paramMap);
        if (dfntAckdReqList.size() == 0) {
            throw new BizException("MSG_ALT_NOT_CNFM_APR_ICHR_ORD_AND_AK_IZ_CONF"); // 확정 승인 담당 주문이 아닙니다. 요청 내역을 확인해주세요.
        }
        for (int i = 0; i < dfntAckdReqList.size(); i++) {
            String cntrAprId = dfntAckdReqList.get(i).cntrAprId();
            processCount += mapper.updateAprvDfntAckdReq(cntrAprId);
            if (processCount != 1) {
                throw new BizException("MSG_ALT_CNFM_AK_APR_PROCS_ERR"); // 확정 요청 승인 처리중 오류(건수<>1)
            }
        }

        //전체 체크 확인(미승인 확정 요청건 조회)
        List<SearchDfntAckdReqListRes> dfntAckdRsltList = mapper.selectDfntAckdReqList(paramMap);
        if (dfntAckdRsltList.size() == 0) {
            processCount = 0;
        } else {
            processCount = dfntAckdRsltList.size();
        }
        return processCount;
    }

    public String saveAckdCnptMsg(List<SaveConfirmApprovalsReq> dtos) {
        String ackdCnptMsg = "== 확정 승인 전 확인 메시지 ==\n"; // 승인 확인 메시지
        boolean gotoNextIndex = false;
        boolean isPymnSkip = false;

        String cntrNo = dtos.get(0).cntrNo();
        String cntrSn = dtos.get(0).cntrSn();
        List<SearchOrdrInfo4ReqDfntRes> ordrInfo4ReqDfntList = mapper.selectOrdrInfo4ReqDfntList(cntrNo, cntrSn);
        //비대면(30) && 계약서구분(3:렌탈,8:장기할부) && 조직유형코드:W05(Z95)
        if (StringUtil.isNotEmpty(ordrInfo4ReqDfntList.get(0).cstStlmInMthCd())) {
            if (ordrInfo4ReqDfntList.get(0).cstStlmInMthCd().equals("30")
                && Arrays.asList(new String[] {"3", "8"}).contains(ordrInfo4ReqDfntList.get(0).cntrwTpCd())
                && ordrInfo4ReqDfntList.get(0).sellOgTpCd().equals("W05")) {
                isPymnSkip = true;
            }

            //비대면(30) && 판매유입채널상세코드 :직원구매(9020) && 판매유입채널상세코드:아웃바운드(SalesTM)
            if (ordrInfo4ReqDfntList.get(0).cstStlmInMthCd().equals("30")
                && Arrays.asList(new String[] {"9020", "3010"})
                    .contains(ordrInfo4ReqDfntList.get(0).sellInflwChnlDtlCd())) {
                isPymnSkip = true;
            }
        }

        if (ordrInfo4ReqDfntList.size() == 0) {
            throw new BizException("MSG_ALT_ORD_INF_NOT_CONF" + "\n" + "MSG_ALT_CNFM_APR_CANT"); // 주문정보를 확인할 수 없습니다.\n확정승인을 할 수 없습니다.
        }
        log.debug("cntrPrgsStatCd : {}", ordrInfo4ReqDfntList.get(0).cntrPrgsStatCd());
        //계약진행상태코드 결제완료 인지 여부 체크
        if (!ordrInfo4ReqDfntList.get(0).cntrPrgsStatCd().equals("50") && !isPymnSkip) {
            throw new BizException("MSG_ALT_STLM_FSH_STAT_CAN_ONLY_APR"); // 결제 완료 상태에서만 승인 할 수 있습니다.
        }

        paramMap.setCntrNo(dtos.get(0).cntrNo());
        paramMap.setIsCurReq("Y");
        paramMap.setCancYn("N");
        paramMap.setAprvYn("N");
        paramMap.setRecvId("");

        List<SearchDfntAckdReqListRes> dfntAckdReqList = mapper.selectDfntAckdReqList(paramMap);
        if (dfntAckdReqList.size() == 0) {
            throw new BizException("MSG_ALT_NOT_CNFM_APR_ICHR_ORD_AND_AK_IZ_CONF"); // 확정 승인 담당 주문이 아닙니다. 요청 내역을 확인해주세요.
        }
        for (int i = 0; i < dfntAckdReqList.size(); i++) {
            gotoNextIndex = false;
            log.info("[{}] {}", i + 1, dfntAckdReqList.get(i).ackdCnftMsg());
            if (dfntAckdReqList.get(i).aprvYn().equals("Y")) {
                gotoNextIndex = true; // 이미승인된 자료는 스킵
            } else {
                if (!StringUtil.isEmpty(ackdCnptMsg))
                    ackdCnptMsg += "\n";
                ackdCnptMsg += String.format(
                    castString(dfntAckdReqList.get(i).ackdCnftMsg()),
                    new String[] {castString(dfntAckdReqList.get(i).cntrAprAkDvCdNm())}
                );
                gotoNextIndex = true;
            }
        }
        return ackdCnptMsg;
    }

    public List<SearchOrderStatCdInfoRes> getContractStatus(String cntrNo) {
        return mapper.selectOrderStatCdInfo(cntrNo);
    }

    @Transactional
    public int saveNotificationTalkFws(List<SaveNotificationTalkFwsReq> dtos) throws Exception {
        int processCount = 0;
        boolean isPymnSkip = false;
        String templetCode = "Wells18104";

        Iterator<SaveNotificationTalkFwsReq> iterator = dtos.iterator();
        while (iterator.hasNext()) {
            SaveNotificationTalkFwsReq dto = iterator.next();
            String cntrNo = dto.cntrNo();
            String cntrSn = dto.cntrSn();
            String cntrDv = dto.cntrDv();
            String stplRcpDt = dto.stplRcpDt();
            String stplPtrm = dto.stplPtrm();

            List<SearchMastOrdrDtptRes> searchMastOrdrDtptList = converter
                .mapWctaMastOrdrDtptDvoToSearchMastOrdrDtptRes(mapper.selectMastOrdrDtpt(cntrNo, cntrSn));
            //log.debug("cntrPrgsStatCd : {}", searchMastOrdrDtptList.get(0).cntrPrgsStatCd());

            //계약진행상태코드 확정인지 여부 체크
            if (!"60".equals(searchMastOrdrDtptList.get(0).cntrPrgsStatCd()) && !isPymnSkip) {
                throw new BizException("MSG_ALT_NOT_CNFM_ORD_AND_NOTAK_FW_IMP"); // 확정되지 않은 주문은 알림톡 발송 불가 합니다.
            }

            // 상품 속성 정보 조회
            int cntrCnt = searchMastOrdrDtptList.size();
            for (int i = 0; i < cntrCnt; i++) {
                if ("01".equals(searchMastOrdrDtptList.get(i).svAlncBzsCd())) {
                    templetCode = "Wells18157";
                    if ("25".equals(searchMastOrdrDtptList.get(i).sellTpDtlCd())) {
                        templetCode = "Wells18202";
                    }
                } else if ("25".equals(searchMastOrdrDtptList.get(i).sellTpDtlCd())) {
                    templetCode = "Wells18203";
                }
                break;
            }

            String recipientNum = searchMastOrdrDtptList.get(0).cntrCralLocaraTno()
                + searchMastOrdrDtptList.get(0).cntrMexnoEncr()
                + searchMastOrdrDtptList.get(0).cntrCralIdvTno();
            if (StringUtil.isEmpty(recipientNum)) {
                throw new BizException("MSG_ALT_NOT_EXIST_CST_PHONE_NUM"); // 고객 휴대전화번호 정보가 없습니다.
            }

            // 모종일시불 주문은 별개의 템플릿으로 전송
            if ("6".equals(searchMastOrdrDtptList.get(0).cntrwTpCd())) {
                templetCode = "wells17944";
            }

            // 계약구분(재약정) 주문은 별개의 템플릿으로 전송
            if ("R".equals(cntrDv)) {
                templetCode = "wells17945";
            }

            paramKakaoTalk.setCstKnm(searchMastOrdrDtptList.get(0).cstKnm());
            paramKakaoTalk.setCntrCralLocaraTno(searchMastOrdrDtptList.get(0).cntrCralLocaraTno());
            paramKakaoTalk.setCntrMexnoEncr(searchMastOrdrDtptList.get(0).cntrMexnoEncr());
            paramKakaoTalk.setCntrCralIdvTno(searchMastOrdrDtptList.get(0).cntrCralIdvTno());
            paramKakaoTalk.setCntrNo(searchMastOrdrDtptList.get(0).cntrNo());
            paramKakaoTalk.setCntrSn(searchMastOrdrDtptList.get(0).cntrSn());

            Map<String, Object> paramMap = new HashMap<>();

            // 모종일시불 알림톡
            if ("wells17944".equals(templetCode)) {
                paramMap.put("cntrRcpFshDt", DateUtil.formatDate(searchMastOrdrDtptList.get(0).cntrRcpFshDt(), ".")); // 접수일자
                paramMap.put("cntrDtlNo", searchMastOrdrDtptList.get(0).cntrDtlNo()); // 주문번호(계약상세번호)
                paramMap.put("cstKnm", searchMastOrdrDtptList.get(0).cstKnm()); //고객명
                paramMap.put("pdNm", searchMastOrdrDtptList.get(0).pdNm()); // 상품명
                paramMap.put("sppDuedt", searchMastOrdrDtptList.get(0).sppDuedt()); // 배송일자
                paramMap.put("sowDuedt", searchMastOrdrDtptList.get(0).sowDuedt()); // 파종예정일자
                paramMap
                    .put(
                        "linkUrl",
                        "http://wellsorder.kyowon.co.kr/cndcView/" + searchMastOrdrDtptList.get(0).cntrDtlNo()
                    ); // 링크(계약서)URL
            } else if (Arrays.asList(new String[] {"Wells18104", "Wells18157", "Wells18202", "Wells18203"})
                .contains(templetCode)) {
                paramMap.put("cntrRcpFshDt", DateUtil.formatDate(searchMastOrdrDtptList.get(0).cntrRcpFshDt(), ".")); // 접수일자
                paramMap.put("cntrDtlNo", searchMastOrdrDtptList.get(0).cntrDtlNo()); // 주문번호(계약상세번호)
                paramMap.put("cstKnm", searchMastOrdrDtptList.get(0).cstKnm()); //고객명
                paramMap
                    .put(
                        "linkUrl",
                        "http://wellsorder.kyowon.co.kr/cndcView/" + searchMastOrdrDtptList.get(0).cntrDtlNo()
                    ); // 링크(계약서)URL

                // 그룹 주문인 경우 개별 주문 전부 조회
                String fmtOrdrNo = "";
                if (castInt(searchMastOrdrDtptList.get(0).cntrCnt()) > 1) {
                    for (int i = 0; i < cntrCnt; i++) {
                        fmtOrdrNo += "●고객번호:" + searchMastOrdrDtptList.get(i).cntrNo();
                        // 설치/배송 예정일 조회
                        String scddDt = searchMastOrdrDtptList.get(i).sppDuedt();
                        if (DateUtil.isValid(scddDt, "yyyyMMdd")) {
                            scddDt = DateUtil.formatDate(scddDt, ".");
                        }

                        fmtOrdrNo += "/제품명:" + searchMastOrdrDtptList.get(i).pdNm() + "/예정일:" + scddDt;
                    }
                    paramMap.put("cntrDtlNo", fmtOrdrNo); // 주문번호
                } else {
                    // 설치/배송 예정일 조회
                    String scddDt = searchMastOrdrDtptList.get(0).sppDuedt();
                    if (DateUtil.isValid(scddDt, "yyyyMMdd")) {
                        scddDt = DateUtil.formatDate(scddDt, ".");
                    }

                    if ("4".equals(searchMastOrdrDtptList.get(0).cntrwTpCd())) {
                        fmtOrdrNo += "/제품명:" + searchMastOrdrDtptList.get(0).pdNm();
                    } else {
                        fmtOrdrNo += "/제품명:" + searchMastOrdrDtptList.get(0).pdNm() + "/예정일:" + scddDt;
                    }
                    paramMap.put("cntrDtlNo", fmtOrdrNo); // 주문번호
                }
            } else if ("wells17945".equals(templetCode)) { // 재약정 알림톡
                paramMap.put("stplRcpDt", DateUtil.formatDate(dto.stplRcpDt(), ".")); // 접수일자(계약일자)
                paramMap.put("cntrDtlNo", searchMastOrdrDtptList.get(0).cntrDtlNo()); // 주문번호(계약상세번호)
                paramMap.put("cstKnm", searchMastOrdrDtptList.get(0).cstKnm()); //고객명
                paramMap.put("pdNm", searchMastOrdrDtptList.get(0).pdNm()); // 상품명
                paramMap.put("dutyUsePeriodTerm", dto.stplPtrm()); // 약정개월(의무사용기간)
                paramMap
                    .put(
                        "linkUrl",
                        "http://wellsorder.kyowon.co.kr/cndcView/rp" + searchMastOrdrDtptList.get(0).cntrDtlNo()
                    ); // 링크(계약서)URL
            }

            // 알림톡 메시지 발송
            processCount = sendKakao(processCount, templetCode, paramMap);
        }
        return processCount;
    }

    private int sendKakao(int processCount, String templetCode, Map<String, Object> paramMap)
        throws Exception {
        log.info("templetCode : " + templetCode);
        log.info("paramMap : " + paramMap);
        log.info("CstKnm : " + paramKakaoTalk.getCstKnm());
        log.info("CntrCralLocaraTno : " + paramKakaoTalk.getCntrCralLocaraTno());
        log.info("CntrMexnoEncr : " + paramKakaoTalk.getCntrMexnoEncr());
        log.info("CntrCralIdvTno : " + paramKakaoTalk.getCntrCralIdvTno());
        KakaoSendReqDvo kakaoSendReqDvo = KakaoSendReqDvo.withTemplateCode()
            .templateCode(templetCode)
            .templateParamMap(paramMap)
            .destInfo(
                paramKakaoTalk.getCstKnm() + "^" + paramKakaoTalk.getCntrCralLocaraTno()
                    + paramKakaoTalk.getCntrMexnoEncr()
                    + paramKakaoTalk.getCntrCralIdvTno()
            )
            .reserved2(paramKakaoTalk.getCntrNo())
            .reserved3(paramKakaoTalk.getCntrSn())
            .callback(CALLBACK)
            .build();

        processCount += kakaoMessageService.sendMessage(kakaoSendReqDvo);
        return processCount;
    }

    /**
     *
     * Obejct Type 을 String Type으로 Casting하여 Return한다.
     *
     * @param value
     * @return java.lang.String
     */
    public static String castString(Object value) {
        String out = null;
        if (value == null || "".equals(value)) {
            out = "";
        } else {
            out = value.toString();
        }
        return out;
    }

    /**
     *
     * Obejct Type 을 int Type으로 Casting하여 Return한다.
     *
     * @param value
     * @return int
     */
    public static int castInt(Object value) throws Exception {
        int out = 0;
        if (value == null || "".equals(value)) {
            out = 0;
        } else if (value instanceof Number) {
            out = ((Number)value).intValue();
        } else {
            out = Integer.parseInt(value.toString().trim());
        }
        return out;
    }
}
