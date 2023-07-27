package com.kyowon.sms.wells.web.contract.changeorder.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sflex.common.message.dvo.KakaoSendReqDvo;
import com.kyowon.sflex.common.message.service.KakaoMessageService;
import com.kyowon.sms.wells.web.contract.changeorder.converter.WctbContractChangeMgtConverter;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbContractChangeMngtDto;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbContractChangeMngtDto.*;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbContractChangeDvo;
import com.kyowon.sms.wells.web.contract.changeorder.mapper.WctbContractChangeMgtMapper;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzContractChRcchStatChangeDtlHistDvo;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzContractNotifyFowrdindHistDvo;
import com.kyowon.sms.wells.web.contract.common.service.WctzHistoryService;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaInstallationShippingDto.SaveAssignProcessingReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaInstallationShippingService;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WctbContractChangeMgtService {
    private final WctbContractChangeMgtMapper mapper;
    private final WctbContractChangeMgtConverter converter;
    private final MessageResourceService messageResourceService;
    private final KakaoMessageService kakaoMessageService;
    private final WctzHistoryService historyService;
    private final WctaInstallationShippingService installService;

    /*
     * 계약변경관리-조회
     * */
    public PagingResult<SearchContractChangeRes> getContractChangePages(
        SearchContractChangeReq dto, PageInfo pageInfo
    ) {
        return mapper.selectContractChanges(dto, pageInfo);

    }

    /*
     *
     * 계약변경관리-변경전 체크
     * */

    public FindBeforeChangeCheckRes getBeforeChangeCheck(FindBeforeChangeCheckReq dto) {
        WctbContractChangeDvo dvo = converter.findCheckReqToWctbContractChangeDvo(dto);

        String alncmpCd = dvo.getAlncmpCd(); // 제휴사코드
        String sellTpCd = dvo.getSellTpCd(); // 판매유형코드
        String sellTpDltCd = dvo.getSellTpDtlCd(); // 판매유형상세코드
        String mclsfRefPdClsfVal = dvo.getMclsfRefPdClsfVal();

        String cntrNo = dvo.getCntrNo(); // 계약번호
        int cntrSn = dvo.getCntrSn(); // 계약일련번호
        String inDv = dvo.getInDv(); // 처리구분
        String istDt = dvo.getIstDt(); // 설치일자
        String aprvDv = dvo.getAprvDv(); // 승인구분

        WctbContractChangeDvo checkDvo = mapper.selectCheckOgTpCd();

        BizAssert.isFalse(ObjectUtils.isEmpty(checkDvo), "MSG_ALT_NO_AUTH"); // 권한이 없습니다.

        String ogTpCd = checkDvo.getOgTpCd();
        String resYn = checkDvo.getResYn();

        BizAssert.isFalse(!"HR1".equals(ogTpCd) && "N".equals(resYn), "MSG_ALT_NO_AUTH"); // 권한이 없습니다.

        List<WctbContractChangeDvo> checkOrderList = mapper.selectCheckOrderChPrgs(cntrNo, cntrSn);

        WctbContractChangeDvo cntrOrderDvo = mapper.selectCntrOrderInfo(cntrNo, cntrSn);

        BizAssert.isFalse(ObjectUtils.isEmpty(cntrOrderDvo), "MSG_ALT_INVAILD_RCP_ORD");

        String pkgYn = cntrOrderDvo.getPkgYn(); // 패키지 주문
        String prmPtrmYn = cntrOrderDvo.getPrmPtrmYn(); // 선납
        String dpYn = cntrOrderDvo.getDpYn(); // 입금처리
        String ftfYn = cntrOrderDvo.getFtfYn(); // 비대면
        String cttRsCd = cntrOrderDvo.getCttRsCd(); // 컨택결과코드
        String canDt = cntrOrderDvo.getCanDt(); // 취소일자
        String onePlusOneYn = cntrOrderDvo.getOnePlusOneYn(); // 1+1여부
        String canPrgsStatCd = cntrOrderDvo.getCanPrgsStatCd(); // 취소진행상태코드
        String istBzsCd = cntrOrderDvo.getIstBzsCd(); //설치업체코드
        String rglrSppCntr = cntrOrderDvo.getRglrSppCntr(); // 정기배송 계약리스트
        String istPcsvTpCd = cntrOrderDvo.getIstPcsvTpCd(); // 설치택배구분

        String msg = ""; // 경고메세지
        String fstRgstUsrId = CollectionUtils.isEmpty(checkOrderList) ? "" : checkOrderList.get(0).getFstRgstUsrId();

        if (!"40".equals(inDv)) {
            //렌탈일때만 체크
            if ("2".equals(sellTpCd)) {

                BizAssert.isFalse(
                    CollectionUtils.isNotEmpty(checkOrderList), "MSG_ALT_CH_ORDER_IN_PROGRESS",
                    new String[] {fstRgstUsrId}
                ); // 주문변경이 진행중입니다.
                                                                                                                                            //계약상세번호기준으로 체크정보 조회

                if ("20".equals(inDv)) {
                    BizAssert.isFalse("Y".equals(pkgYn), "MSG_ALT_PKG_ORD_CANNOT_CHANGE_CNTR_TP"); // 패키지 주문은 계약유형변경 불가합니다.
                    BizAssert.isFalse("Y".equals(prmPtrmYn), "MSG_ALT_PRM_ORD_CANNOT_CHANGE_CNTR_TP"); // 선납 주문은 계약유형변경 불가합니다.
                    BizAssert.isFalse("Y".equals(dpYn), "MSG_ALT_DP_ORD_CANNOT_CHANGE"); // 입금 처리된 주문은 변경 불가합니다.
                    if ("Z95".equals(ogTpCd)) {
                        BizAssert.isFalse("Y".equals(ftfYn), "MSG_ALT_SODBT_NFTF_ORD_CANNT_CHANGE"); // 총판 비대면 접수 주문은 계약변경 불가합니다. 관리자에게 문의 하세요.
                    }
                }
                /* TODO: 변경수정 권한여부 확인(TOBE에서는 아직 변경권한 프로세스없음(2023.06.27) */
                if ("10".equals(inDv)) {

                }
            }
        } else {
            /* 취소요청 체크 처리 로직 */
            cntrOrderDvo.setCntrCnfmDt(dvo.getCntrCnfmDt());
            cntrOrderDvo.setIstDt(istDt);
            cntrOrderDvo.setAprvDv(aprvDv);
            cntrOrderDvo.setSellTpDtlCd(sellTpDltCd);
            cntrOrderDvo.setMclsfRefPdClsfVal(mclsfRefPdClsfVal);
            checkCancelable(cntrOrderDvo, checkOrderList);
        }
        FindBeforeChangeCheckRes res = FindBeforeChangeCheckRes.builder()
            .checkYn("Y")
            .warnMsg(msg)
            .build();
        return res;
    }

    /*
     * 취소요청 체크 처리 로직
     * */
    public String checkCancelable(
        WctbContractChangeDvo cntrOrderDvo, List<WctbContractChangeDvo> checkOrderList
    ) {
        String msg = ""; // 경고메세지

        String istDt = cntrOrderDvo.getIstDt(); // 설치일자
        String sellTpDltCd = cntrOrderDvo.getSellTpDtlCd(); // 판매유형상세코드
        String aprvDv = cntrOrderDvo.getAprvDv(); // 승인구분
        String mclsfRefPdClsfVal = cntrOrderDvo.getMclsfRefPdClsfVal();

        String pkgYn = cntrOrderDvo.getPkgYn(); // 패키지 주문
        String prmPtrmYn = cntrOrderDvo.getPrmPtrmYn(); // 선납
        String dpYn = cntrOrderDvo.getDpYn(); // 입금처리
        String ftfYn = cntrOrderDvo.getFtfYn(); // 비대면
        String cttRsCd = cntrOrderDvo.getCttRsCd(); // 컨택결과코드
        String canDt = cntrOrderDvo.getCanDt(); // 취소일자
        String onePlusOneYn = cntrOrderDvo.getOnePlusOneYn(); // 1+1여부
        String canPrgsStatCd = cntrOrderDvo.getCanPrgsStatCd(); // 취소진행상태코드
        String istBzsCd = cntrOrderDvo.getIstBzsCd(); //설치업체코드
        String rglrSppCntr = cntrOrderDvo.getRglrSppCntr(); // 정기배송 계약리스트
        String istPcsvTpCd = cntrOrderDvo.getIstPcsvTpCd(); // 설치택배구분

        String fstRgstUsrId = CollectionUtils.isEmpty(checkOrderList) ? "" : checkOrderList.get(0).getFstRgstUsrId();

        BizAssert.isFalse(
            !CollectionUtils.isEmpty(checkOrderList), "MSG_ALT_CH_ORDER_IN_PROGRESS",
            new String[] {fstRgstUsrId}
        ); // 주문변경이 진행중입니다.
        BizAssert.isFalse(ObjectUtils.isEmpty(cntrOrderDvo), "MSG_ALT_ORD_INF_NOT_FOUND"); // 주문정보를 찾을 수 없습니다.

        String cntrCnfmDt = cntrOrderDvo.getCntrCnfmDt();
        String cntrCnfmYm = cntrCnfmDt.substring(0, 6);

        String now = DateUtil.getNowDayString();
        BizAssert.isFalse(now.equals(cntrCnfmDt), "MSG_ALT_TOD_ORD_CAN_DEL_NO_APR"); // 당일접수 주문은 계약현황목록에서 승인 절차 없이 삭제 가능합니다.
        BizAssert.isTrue(now.substring(0, 6).equals(cntrCnfmYm), "MSG_ALT_ONLY_CAN_DEL_THM_ORD"); // 당월접수 주문만 삭제 가능합니다.

        BizAssert.isFalse(!StringUtil.isEmpty(istDt), "MSG_ALT_INST_ORD_CANNOT_DEL"); // 설치 완료된 주문은 삭제 불가합니다.
        BizAssert.isFalse("Y".equals(prmPtrmYn), "MSG_ALT_PRM_ORD_CANNOT_CHANGE_CNTR_TP"); // 선납 주문은 계약유형변경 불가합니다.
        BizAssert.isFalse("Y".equals(dpYn), "MSG_ALT_DP_ORD_CANNOT_CHANGE"); // 입금 처리된 주문은 변경 불가합니다.

        //TODO. 키위연동은 현재 미정
        String kiwi = "";

        if (List.of("24", "26").contains(sellTpDltCd) && StringUtil.isNotEmpty(istBzsCd)
            && StringUtil.isNotEmpty(cttRsCd)) {
            BizAssert.isFalse(Integer.parseInt(cttRsCd) < 90, "MSG_ALT_DEL_AFTER_CANC_BY_OTSC"); // 아웃소싱 업체 측에서 취소 후 삭제 가능 합니다.
        }
        BizAssert.isFalse(
            StringUtil.isNotEmpty(istBzsCd) && "2".equals(istPcsvTpCd) && "01".equals(cttRsCd),
            "MSG_ALT_OTSD_INST_PROD_PCSV_PROD_ORD_CANNOT_DEL", new String[] {cttRsCd}
        ); // 외부업체설치제품,택배상품에 대한 주문은 삭제 불가합니다.(컨택코드:01)
        BizAssert.isFalse(!StringUtil.isEmpty(canDt), "MSG_ALT_ALRDY_CANC_ORD"); // 취소 처리된 주문은 삭제가 불가 합니다.

        if ("Y".equals(onePlusOneYn)) {
            msg = messageResourceService.getMessage("MSG_ALT_1PLUS1_DSC_ORD_RESTORE_NTMF_RTLFE"); // 1+1할인으로 매칭된 주문입니다. 삭제하시면 익월 초 렌탈료 원복됩니다.
        }
        if ("Y".equals(pkgYn)) {
            msg = messageResourceService.getMessage("MSG_ALT_PKG_DSC_ORD_DEL_RESTORE_NTMF_RTLFE"); // 패키지 할인으로 매칭된 주문입니다. 삭제하시면 익월 초 렌탈료 원복됩니다.
        }

        if ("20".equals(aprvDv)) {
            BizAssert.isFalse("20".equals(canPrgsStatCd), "MSG_ALT_ARDY_DEL_REQ_STAT"); // 이미 삭제 요청된 상태입니다.
        }
        if ("60".equals(aprvDv)) {
            BizAssert.isFalse(StringUtil.isEmpty(canPrgsStatCd), "MSG_ALT_NO_DEL_REQ_INFO_FOUND"); // 삭제 요청 정보를 찾을 수 없습니다.
            BizAssert.isFalse("30".equals(canPrgsStatCd), "MSG_ALT_ARDY_RJ_STAT"); // 이미 반려 처리된 상태입니다.
        }
        if (List.of("24", "26").contains(sellTpDltCd)
            && "01003".equals(mclsfRefPdClsfVal)
            && "1".equals(istBzsCd)) {
            msg = messageResourceService.getMessage("MSG_ALT_RGLR_PKG_SPRTL_CANC_RCP_CCS", rglrSppCntr); // 정기배송 패키지 rglrSppCntr 은 고객센터로 별도 취소접수 하시길 바랍니다. (배송제품으로 동시 삭제 불가)
        }
        return msg;
    }

    /*
     *
     * 계약변경관리-취소요청
     * */

    @Transactional
    public int saveCancelAsks(SaveCancelReq dto) throws Exception {
        WctbContractChangeDvo dvo = converter.saveCancelReqToWctbContractChangeDvo(dto);

        String cntrNo = dvo.getCntrNo();
        int cntrSn = dvo.getCntrSn();

        String templateCode = "wells17950";
        int mtPr = 0;

        /* 취소요청 체크 로직 한번더 실행*/
        WctbContractChangeDvo cntrOrderDvo = mapper.selectCntrOrderInfo(cntrNo, cntrSn);
        List<WctbContractChangeDvo> checkOrderList = mapper.selectCheckOrderChPrgs(cntrNo, cntrSn);

        /* 지점장 휴대전화번호*/
        String cralLocaraTno = StringUtils.defaultIfEmpty(cntrOrderDvo.getBrmgrCralLocaraTno(), "");
        String mexnoEncr = StringUtils.defaultIfEmpty(cntrOrderDvo.getBrmgrMexnoEncr(), "");
        String cralIdvTno = StringUtils.defaultIfEmpty(cntrOrderDvo.getBrmgrCralIdvTno(), "");
        String phoneNo = cralLocaraTno + mexnoEncr + cralIdvTno;

        String brmgrNm = cntrOrderDvo.getBrmgrNm();

        cntrOrderDvo.setCntrCnfmDt(dvo.getCntrCnfmDt());
        cntrOrderDvo.setIstDt(dvo.getIstDt());
        cntrOrderDvo.setAprvDv(dvo.getAprvDv());
        cntrOrderDvo.setSellTpDtlCd(dvo.getSellTpDtlCd());
        cntrOrderDvo.setMclsfRefPdClsfVal(dvo.getMclsfRefPdClsfVal());

        String msg = checkCancelable(cntrOrderDvo, checkOrderList);

        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession();
        String employeeUserId = session.getEmployeeIDNumber();
        String userName = session.getUserName();

        String now = DateUtil.getNowString();
        String cntrChAkCn = "계약변경유형코드: 계약취소신청";

        WctbContractChangeDvo dateTimeDvo = mapper.selectDateTime();
        dvo.setFstRgstDtm(dateTimeDvo.getFstRgstDtm());
        dvo.setFstRgstUsrId(dateTimeDvo.getFstRgstUsrId());
        dvo.setFstRgstPrgId(dateTimeDvo.getFstRgstPrgId());
        dvo.setFstRgstDeptId(dateTimeDvo.getFstRgstDeptId());
        dvo.setFnlMdfcDtm(dateTimeDvo.getFnlMdfcDtm());
        dvo.setFnlMdfcUsrId(dateTimeDvo.getFnlMdfcUsrId());
        dvo.setFnlMdfcPrgId(dateTimeDvo.getFnlMdfcPrgId());
        dvo.setFnlMdfcDeptId(dateTimeDvo.getFnlMdfcDeptId());

        dvo.setCntrChRcpDtm(now); // 계약변경접수일시
        dvo.setCntrChTpCd("402"); // 계약변경유형코드
        dvo.setChRqrDvCd("2"); // 변경요청자구분코드
        dvo.setChRqrNm(dvo.getCstKnm()); // 변경요청자명
        dvo.setCntrChAkCn(cntrChAkCn); // 계약변경요청내용
        dvo.setCntrChPrgsStatCd("20"); // 계약변경진행상태코드
        dvo.setChRcstDvCd(""); // 변경접수자구분코드
        dvo.setChRcpUsrId(employeeUserId); // 변경접수사용자id
        dvo.setAprDtm(now); /* 승인일시 */
        dvo.setAprUsrId(employeeUserId); /* 승인사용자ID */
        dvo.setCntrChFshDtm(now); // 계약변경완료일시
        dvo.setDtaDlYn("N"); // 데이터삭제여부

        int basRes = mapper.insertContractChRcpBase(dvo); // 계약변경접수기본
        BizAssert.isTrue(basRes > 0, "MSG_ALT_SVE_ERR");

        basRes = mapper.insertSellPartnerToCntrChRcchHist(dvo);
        BizAssert.isTrue(basRes > 0, "MSG_ALT_SVE_ERR");

        //        historyService.createContractChangeRcchStatChangeHistory(
        //            WctzCntrChRcchStatChangeHistDvo
        //                .builder()
        //                .cntrChRcpId(dvo.getCntrChRcpId())
        //                .cntrChPrgsStatCd(dvo.getCntrChPrgsStatCd())
        //                .build()
        //        );

        dvo.setCntrUnitTpCd("020"); // 계약단위유형코드
        dvo.setDtlCntrNo(cntrNo); // 상세계약번호
        dvo.setDtlCntrSn(cntrSn); // 상세계약일련번호
        dvo.setCntrChRsonDvCd(""); // 계약변경사유구분코드
        dvo.setCntrChRsonCd(""); // 계약변경사유코드
        dvo.setCntrChAtcDvCd(""); // 계약변경항목구분코드

        int dtlsRes = mapper.insertContractChRcpDetail(dvo); // 계약변경접수상세
        BizAssert.isTrue(dtlsRes > 0, "MSG_ALT_SVE_ERR");

        historyService.createContractChRcchChangeDtlHistory(
            WctzContractChRcchStatChangeDtlHistDvo
                .builder()
                .cntrChRcpId(dvo.getCntrChRcpId())
                .cntrChSn(Integer.parseInt(dvo.getCntrChSn()))
                .histStrtDtm(dvo.getFnlMdfcDtm())
                .fnlMdfcDtm(dvo.getFnlMdfcDtm())
                .fnlMdfcUsrId(dvo.getFnlMdfcUsrId())
                .fnlMdfcPrgId(dvo.getFnlMdfcPrgId())
                .fnlMdfcDeptId(dvo.getFnlMdfcDeptId())
                .fstRgstDtm(dvo.getFstRgstDtm())
                .fstRgstUsrId(dvo.getFstRgstUsrId())
                .fstRgstPrgId(dvo.getFstRgstPrgId())
                .fstRgstDeptId(dvo.getFstRgstDeptId())
                .build()
        );

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("brmgrNm", brmgrNm); // 지점장명
        paramMap.put("prtnrKnm", dvo.getPrtnrKnm()); // 판매자명
        paramMap.put("cstKnm", dvo.getCstKnm()); // 고객명
        paramMap.put("rcpdt", dvo.getCntrCnfmDt()); // 접수일자
        paramMap.put("cntrDtlNo", dvo.getCntrDtlNo()); // 계약상세번호
        paramMap.put("cralIdvTno", phoneNo); // 전화번호
        paramMap.put("linkUrl", ""); // TODO: 링크 생성 시 추가예정

        if (StringUtil.isEmpty(phoneNo) || phoneNo.length() < 10) {
            throw new BizException("MSG_ALT_WRONG_DEL_REQ_APRV_PHON_NO"); // 삭제 요청 결재 담당자 휴대폰 전화번호가 올바르지 않습니다.
        }

        String destInfo = String.format("%s^%s", brmgrNm, phoneNo);

        KakaoSendReqDvo kakaoSendReqDvo = KakaoSendReqDvo.withTemplateCode()
            .templateCode(templateCode) // 카카오톡템플릿 ID
            .templateParamMap(paramMap) // 템플릿 파라미터
            .destInfo(destInfo) // 수신자 정보
            .callback("15884113") // 콜백
            .build();
        mtPr += kakaoMessageService.sendMessageAndGetInfo(kakaoSendReqDvo).get(0).getMtPr();

        if (mtPr > 0) {
            String notyFwId = historyService.createContractNotifyFowrdindHistory(
                WctzContractNotifyFowrdindHistDvo.builder()
                    .notyFwTpCd("20") // 알림발송유형코드
                    .notyFwBizDvCd("10") // 알림발송업무구분코드
                    .akUsrId(employeeUserId) // 요청자 ID
                    .rqrNm(userName)// 요청자명
                    .akDtm(now) // 요청일시
                    .fwDtm(now) // 발송일시
                    // .msgTit(title) // 메세지 제목  ( 메세지 내용, 제목은 식별키 값으로 이메일 테이블 join 해서 내용 조회 가능)
                    // .msgCn(sendDvo.getContent()) // 메세지 내용
                    .cntrNo(cntrNo) // 계약번호
                    .cntrSn(cntrSn) // 계약일련번호
                    .fwLkIdkVal(Integer.toString(mtPr)) // 발송연계식별키값
                    .fwOjRefkVal1(templateCode) // 발송대상참조키값1
                    .rcvrNm(brmgrNm) // 수신자명
                    .rcvrLocaraTno(cralLocaraTno) // 수신자지역전화번호
                    .rcvrExnoEncr(mexnoEncr) // 수신자전화국번호암호화
                    .rcvrIdvTno(cralIdvTno) // 수신자개별전화번호
                    .notyFwRsCd("10") // 알림발송결과코드
                    .dtaDlYn("N") // 삭제여부
                    .build(),
                false
            );
        }

        return 1;
    }

    /*
     * 계약변경관리-고객정보조회
     * */
    public FindCustomerInformationRes getCustomerInformations(String cntrNo, int cntrSn) {
        return converter.wctbContractChangeDvoToFindCustomerRes(mapper.selectCustomerInformation(cntrNo, cntrSn));
    }

    /*
     * 계약변경관리-고객정보저장
     * */
    @Transactional
    public int editCustomerInformation(SaveChangeReq dto) throws Exception {

        WctbContractChangeDvo dvo = converter.saveChangeReqToWctbContractChangeDvo(dto);

        String cntrNo = dvo.getCntrNo(); // 계약번호
        int cntrSn = dvo.getCntrSn(); // 계약일련번호

        WctbContractChangeDvo orgDvo = mapper.selectCustomerInformation(cntrNo, cntrSn); // 변경 전 데이터

        String now = DateUtil.getNowString(); // 현재 일자
        String nowDay = DateUtil.getNowDayString(); // 현재일
        String rcgvpKnm = dvo.getRcgvpKnm(); // 수령자 한글명
        String adrId = dvo.getIstAdrId(); // 주소 ID

        String cralLocaraTno = dvo.getIstCralLocaraTno(); // 휴대지역전화번호
        String mexnoEncr = dvo.getIstMexnoEncr(); // 휴대전화국번호암호화
        String cralIdvTno = dvo.getIstCralIdvTno(); // 휴대개별전화번호

        /* TODO: 계약자 정보는 고객 I/F 완료 후 추가예정 */

        WctbContractChangeDvo kiwiDvo = mapper.selectKiwiInstallOrders(cntrNo, cntrSn);

        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession();
        String employeeUserId = session.getEmployeeIDNumber();
        String loginName = session.getUserName();

        if (ObjectUtils.isNotEmpty(kiwiDvo)) {

            int wkPrgsStatCd = Integer.parseInt(kiwiDvo.getWkPrgsStatCd()); // 작업진행상태
            if (wkPrgsStatCd < 70) {
                SaveAssignProcessingReq kiwiDto = SaveAssignProcessingReq
                    .builder()
                    .cntrNo(cntrNo)
                    .cntrSn(Integer.toString(cntrSn))
                    .prtnrNo(employeeUserId)
                    .inputGb("3")
                    .wkGb("1")
                    .workDt(kiwiDvo.getRcpdt())
                    .asIstOjNo(kiwiDvo.getAsIstOjNo())
                    .acpgDiv("3")
                    .basePdCd("")
                    .prdDiv("1")
                    .build();
                String checkYn = installService.saveAssignProcessings(kiwiDto);

                if ("N".equals(checkYn)) {
                    throw new BizException("설치배정자료 삭제 오류");
                }
            }
        }
        WctbContractChangeDvo dateTimeDvo = mapper.selectDateTime();
        dvo.setFstRgstDtm(dateTimeDvo.getFstRgstDtm());
        dvo.setFstRgstUsrId(dateTimeDvo.getFstRgstUsrId());
        dvo.setFstRgstPrgId(dateTimeDvo.getFstRgstPrgId());
        dvo.setFstRgstDeptId(dateTimeDvo.getFstRgstDeptId());
        dvo.setFnlMdfcDtm(dateTimeDvo.getFnlMdfcDtm());
        dvo.setFnlMdfcUsrId(dateTimeDvo.getFnlMdfcUsrId());
        dvo.setFnlMdfcPrgId(dateTimeDvo.getFnlMdfcPrgId());
        dvo.setFnlMdfcDeptId(dateTimeDvo.getFnlMdfcDeptId());
        dvo.setAdrId(adrId);
        dvo.setCralLocaraTno(cralLocaraTno);
        dvo.setMexnoEncr(mexnoEncr);
        dvo.setCralIdvTno(cralIdvTno);

        int res = mapper.updateContractAddrBase(dvo);
        BizAssert.isTrue(res > 0, "MSG_ALT_SVE_ERR");

        res = mapper.updateContractAddrChangeHist(dvo);
        BizAssert.isTrue(res > 0, "MSG_ALT_SVE_ERR");

        res = mapper.insertContractAddrChangeHist(dvo);
        BizAssert.isTrue(res > 0, "MSG_ALT_SVE_ERR");

        String orgRcgvpKnm = orgDvo.getRcgvpKnm(); // 수령자한글명
        String orgAdrId = orgDvo.getIstAdrId(); // 주소ID
        String orgCralLocaraTno = orgDvo.getRcgvpKnm(); // 휴대지역전화번호
        String orgMexnoEncr = orgDvo.getIstMexnoEncr(); // 휴대전화국번호
        String orgCralIdvTno = orgDvo.getIstCralIdvTno(); // 휴대개별전화번호

        String cntrChAkCn = "수령자한글명: " + rcgvpKnm
            + "|주소ID:" + adrId
            + "|휴대지역전화번호:" + cralLocaraTno
            + "|휴대전화국번호:" + mexnoEncr
            + "|휴대개별전화번호:" + cralIdvTno + "|";

        String bfchCn = "수령자한글명: " + orgRcgvpKnm
            + "|주소ID:" + orgAdrId
            + "|휴대지역전화번호:" + orgCralLocaraTno
            + "|휴대전화국번호:" + orgMexnoEncr
            + "|휴대개별전화번호:" + orgCralIdvTno + "|";

        dvo.setCntrChRcpDtm(now); // 계약변경접수일시
        dvo.setCntrChTpCd("102"); // 계약변경유형코드
        dvo.setChRqrDvCd("2"); // 변경요청자구분코드 (변경 가능성 존재)
        dvo.setChRqrNm(loginName); // 변경요청자명
        dvo.setCntrChAkCn(cntrChAkCn); // 계약변경요청내용
        dvo.setCntrChPrgsStatCd("50"); // 계약변경진행상태코드
        dvo.setChRcstDvCd(""); // 변경접수자구분코드
        dvo.setChRcpUsrId(employeeUserId); // 변경접수사용자id
        dvo.setAprDtm(now); // 승인일시
        dvo.setAprUsrId(employeeUserId); // 승인사용자ID
        dvo.setCntrChFshDtm(now); // 계약변경완료일시
        dvo.setDtaDlYn("N"); // 데이터삭제여부

        int basRes = mapper.insertContractChRcpBase(dvo); // 계약변경접수기본
        BizAssert.isTrue(basRes > 0, "MSG_ALT_SVE_ERR");

        basRes = mapper.insertSellPartnerToCntrChRcchHist(dvo);
        BizAssert.isTrue(basRes > 0, "MSG_ALT_SVE_ERR");

        dvo.setCntrUnitTpCd("020"); // 계약단위유형코드
        dvo.setCntrChRsonDvCd(""); // 계약변경사유구분코드
        dvo.setCntrChRsonCd(""); // 계약변경사유코드
        dvo.setCntrChAtcDvCd(""); // 계약변경항목구분코드
        dvo.setChApyStrtdt(nowDay); // 변경적용종료일자
        dvo.setChApyEnddt("99991231"); // 변경적용종료일자
        dvo.setBfchCn(bfchCn); // 변경 전 내용
        dvo.setProcsYn("Y"); // 처리여부
        dvo.setDtlCntrNo(cntrNo);

        int dtlRes = mapper.insertContractChRcpDetail(dvo);
        BizAssert.isTrue(dtlRes > 0, "MSG_ALT_SVE_ERR");

        historyService.createContractChRcchChangeDtlHistory(
            WctzContractChRcchStatChangeDtlHistDvo
                .builder()
                .cntrChRcpId(dvo.getCntrChRcpId())
                .cntrChSn(Integer.parseInt(dvo.getCntrChSn()))
                .histStrtDtm(dvo.getFnlMdfcDtm())
                .fnlMdfcDtm(dvo.getFnlMdfcDtm())
                .fnlMdfcUsrId(dvo.getFnlMdfcUsrId())
                .fnlMdfcPrgId(dvo.getFnlMdfcPrgId())
                .fnlMdfcDeptId(dvo.getFnlMdfcDeptId())
                .fstRgstDtm(dvo.getFstRgstDtm())
                .fstRgstUsrId(dvo.getFstRgstUsrId())
                .fstRgstPrgId(dvo.getFstRgstPrgId())
                .fstRgstDeptId(dvo.getFstRgstDeptId())
                .build()
        );
        return 1;
    }

    public FindPartnerRes getPartnerByCntrNo(String cntrNo, String cntrSn) {
        // 계약변경관리-파트너 변경(조회)
        return mapper.selectPartnerByCntrNo(cntrNo, cntrSn);
    }

    @Transactional
    public int editPartner(WctbContractChangeMngtDto.EditPartnerReq dto) {
        // 계약변경관리-파트너 변경(저장)
        // 저장할 데이터 변환 DTO -> DVO
        WctbContractChangeDvo inputDvo = converter.mapEditPartnerReqToWctbContractChangeMngtDvo(dto);

        // 데이터의 INSERT/UPDATE/유효시작일시/유효종료일시를 일관되게 맞추기 위해, 미리 조회해온다.
        WctbContractChangeDvo dateTimeDvo = mapper.selectDateTime();
        inputDvo.setFstRgstDtm(dateTimeDvo.getFstRgstDtm());
        inputDvo.setFstRgstUsrId(dateTimeDvo.getFstRgstUsrId());
        inputDvo.setFstRgstPrgId(dateTimeDvo.getFstRgstPrgId());
        inputDvo.setFstRgstDeptId(dateTimeDvo.getFstRgstDeptId());
        inputDvo.setFnlMdfcDtm(dateTimeDvo.getFnlMdfcDtm());
        inputDvo.setFnlMdfcUsrId(dateTimeDvo.getFnlMdfcUsrId());
        inputDvo.setFnlMdfcPrgId(dateTimeDvo.getFnlMdfcPrgId());
        inputDvo.setFnlMdfcDeptId(dateTimeDvo.getFnlMdfcDeptId());

        int result;
        // 접수기본/상세, 계약기본에 승인, 변경 완료 상태로 처리한다.
        // 1. INSERT 계약변경접수기본
        result = mapper.insertSellPartnerToCntrChRcpBas(inputDvo);
        BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");

        // 2. INSERT 계약변경접수변경이력
        result = mapper.insertSellPartnerToCntrChRcchHist(inputDvo);
        BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");

        // 3. INSERT 계약변경접수상세
        result = mapper.insertSellPartnerToCntrChRcpDtl(inputDvo);
        BizAssert.isTrue(result > 0, "MSG_ALT_SVE_ERR");

        // 4. INSERT 계약변경접수상세이력
        result = mapper.insertSellPartnerToCntrChRcpDchHist(inputDvo);
        BizAssert.isTrue(result > 0, "MSG_ALT_SVE_ERR");

        // 5. UPDATE 계약기본
        result = mapper.updateSellPartnerToCntrBas(inputDvo);
        BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");

        // 6-1. UPDATE 계약변경이력
        result = mapper.updateExSellPartnerToCntrChHist(inputDvo);
        BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");

        // 6-2. INSERT 계약변경이력
        result = mapper.insertSellPartnerToCntrChHist(inputDvo);
        BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");

        // 7-1. UPDATE 계약파트너관계 유효종료일시
        result = mapper.updateSellPartnerToCntrPrtnrRel(inputDvo);
        BizAssert.isTrue(result > 0, "MSG_ALT_SVE_ERR");

        // 7-2. INSERT 계약파트너관계
        result = mapper.insertSellPartnerToCntrPrtnrRel(inputDvo);
        BizAssert.isTrue(result > 0, "MSG_ALT_SVE_ERR");

        return 1;
    }
}
