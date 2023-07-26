package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemStockItemizationReqDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsOutStorageFinishDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsOutStorageFinishIostDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaLogisticsOutStorageFinishMapper;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.validation.ValidAssert;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 물류센터 출고완료 처리 서비스 (W-SV-B-0033 배치에서 호출)
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-06-14
 */

@Service
@RequiredArgsConstructor
public class WsnaLogisticsOutStorageFinishService {

    private final WsnaLogisticsOutStorageFinishMapper mapper;

    // 메시지 서비스
    private final MessageResourceService messageService;

    // 재고내역 서비스
    private final WsnaItemStockItemizationService stockService;

    // 출고
    private static final String GUBUN_OSTR = "O";
    // 입고
    private static final String GUBUN_STR = "I";
    // 작업구분 - 등록
    private static final String WORK_DIV_A = "A";

    /**
     * 물류센터 출고완료 처리
     * @param map 배치 파라미터
     */
    @Transactional
    public void saveLogisticsOutStorageFinish(Map<String, String> map) {

        // 출고요청번호
        String ostrAkNo = map.get("PARAM1");

        // 출고완료 수신 테이블 조회
        List<WsnaLogisticsOutStorageFinishDvo> dvos = this.mapper.selectItmOstrFshRcvEtxt(ostrAkNo);

        if (CollectionUtils.isNotEmpty(dvos)) {
            UserSessionDvo userSession = SFLEXContextHolder.getContext().getUserSession();
            // Batch Interface에서 호출하면 session langId가 없음.
            if (userSession != null && StringUtils.isEmpty(userSession.getLangId())) {
                userSession.setLangId("ko");
            }

            // 비고내용 - 직배창고 물량배정
            String rmkCn = this.messageService.getMessage("MSG_TXT_DIDY_WARE_QOM_ASN");

            // 출고유형
            String ostrAkTpCd = dvos.get(0).getOstrAkTpCd();

            // 정상출고, 자동출고
            if (SnServiceConst.OSTR_AK_TP_CD_NOM_OSTR.equals(ostrAkTpCd)
                || SnServiceConst.OSTR_AK_TP_CD_AUTO_OSTR.equals(ostrAkTpCd)) {

                this.saveOutOfStorage(dvos);

                // 물량배정
            } else if (SnServiceConst.OSTR_AK_TP_CD_QOM_ASN.equals(ostrAkTpCd)) {
                this.saveQuantityOfMaterialsAssign(dvos, rmkCn);

                // 그외
            } else {
                for (WsnaLogisticsOutStorageFinishDvo itm : dvos) {
                    // 출고요청내역 UPDATE
                    this.mapper.updateItmOstrAkIz(itm);

                    // 연계발생ID
                    String linkOcrnId = itm.getLinkOcrnId();
                    // TB_IFIN_ITM_OSTR_FSH_RCV_ETXT - 품목출고완료수신전문 상태 변경
                    this.mapper.updateItmOstrFshRcvEtxt(linkOcrnId);
                }
            } // 출고유형 if-end
        }
    }

    /**
     * 정상출고, 자동출고 처리
     * @param itms  (필수) 출고완료 품목 리스트
     */
    @Transactional
    public void saveOutOfStorage(List<WsnaLogisticsOutStorageFinishDvo> itms) {

        ValidAssert.notEmpty(itms);
        // 입고번호 채번
        String newItmStrNo = this.mapper.selectNewItmStrNo(SnServiceConst.NOM_STR);
        // 출고번호 채번
        String newItmOstrNo = this.mapper.selectNewItmOstrNo(SnServiceConst.NOM_OSTR);

        WsnaLogisticsOutStorageFinishIostDvo iostDvo = new WsnaLogisticsOutStorageFinishIostDvo();
        iostDvo.setItmStrNo(newItmStrNo);
        iostDvo.setItmOstrNo(newItmOstrNo);

        for (WsnaLogisticsOutStorageFinishDvo itm : itms) {
            int strSn = this.mapper.selectNewStrSn(newItmStrNo);
            iostDvo.setStrSn(strSn);

            int ostrSn = this.mapper.selectNewOstrSn(newItmOstrNo);
            iostDvo.setOstrSn(ostrSn);

            // 정상입고
            iostDvo.setStrTpCd(SnServiceConst.NOM_STR);

            iostDvo.setStrOjWareNo(itm.getStrOjWareNo());
            iostDvo.setStrPrtnrNo(itm.getWareMngtPrtnrNo());
            iostDvo.setStrPrtnrOgTpCd(itm.getWareMngtPrtnrOgTpCd());
            iostDvo.setStrWareDvCd(itm.getOstrAkWareDvCd());

            iostDvo.setItmPdCd(itm.getItmPdCd());
            iostDvo.setItmGdCd(itm.getItmGdCd());
            iostDvo.setMngtUnitCd(itm.getMngtUnitCd());
            iostDvo.setStrQty(itm.getOstrCnfmQty());

            iostDvo.setOstrAkNo(itm.getOstrAkNo());
            iostDvo.setOstrAkSn(itm.getOstrAkSn());

            iostDvo.setOstrOjWareNo(itm.getOstrOjWareNo());
            iostDvo.setOstrWareDvCd(itm.getOstrWareDvCd());
            iostDvo.setOstrPrtnrOgTpCd(itm.getOstrPrtnrOgTpCd());
            iostDvo.setOstrPrtnrNo(itm.getOstrPrtnrNo());

            iostDvo.setOstrDt(itm.getOstrDt());
            iostDvo.setOstrTpCd(SnServiceConst.NOM_OSTR);

            // 품목출고내역 생성
            this.mapper.insertItmOstrIz(iostDvo);

            // 품목입고내역 생성
            this.mapper.insertItmStrIz(iostDvo);

            // 품목재고내역 등록 - 출고창고
            WsnaItemStockItemizationReqDvo ostrStockReq = this.convertStockItemizationCreateReq(iostDvo, GUBUN_OSTR);
            this.stockService.createStock(ostrStockReq);

            // 품목재고내역 이동 - 입고창고
            WsnaItemStockItemizationReqDvo strMoveReq = this.convertStockItemizationMoveReq(iostDvo);
            this.stockService.saveStockMovement(strMoveReq);
            // 품목재고내역 등록 - 입고창고
            WsnaItemStockItemizationReqDvo strStockReq = this.convertStockItemizationCreateReq(iostDvo, GUBUN_STR);
            this.stockService.createStock(strStockReq);

            // 출고요청내역 UPDATE
            this.mapper.updateItmOstrAkIz(itm);

            // 연계발생ID
            String linkOcrnId = itm.getLinkOcrnId();
            // TB_IFIN_ITM_OSTR_FSH_RCV_ETXT - 품목출고완료수신전문 상태 변경
            this.mapper.updateItmOstrFshRcvEtxt(linkOcrnId);
        }
    }

    /**
     * 물량배정 처리
     * @param itms  (필수) 출고완료 품목 리스트
     * @param rmkCn (필수) 비고내용
     */
    @Transactional
    public void saveQuantityOfMaterialsAssign(List<WsnaLogisticsOutStorageFinishDvo> itms, String rmkCn) {

        ValidAssert.notEmpty(itms);
        ValidAssert.hasText(rmkCn);

        // 입고번호 채번 - 물량배정
        String newItmStrNo = this.mapper.selectNewItmStrNo(SnServiceConst.QOM_ASN);
        // 출고번호 채번
        String newItmOstrNo = this.mapper.selectNewItmOstrNo(SnServiceConst.QOM_ASN_OSTR);

        WsnaLogisticsOutStorageFinishIostDvo iostDvo = new WsnaLogisticsOutStorageFinishIostDvo();
        iostDvo.setItmStrNo(newItmStrNo);
        iostDvo.setItmOstrNo(newItmOstrNo);

        WsnaLogisticsOutStorageFinishIostDvo hgrIostDvo = new WsnaLogisticsOutStorageFinishIostDvo();
        hgrIostDvo.setRmkCn(rmkCn);

        for (WsnaLogisticsOutStorageFinishDvo itm : itms) {
            // 입고창고번호 = 출고요청창고의 상위창고 번호, 출고창고번호 = 물류창고번호
            int strSn = this.mapper.selectNewStrSn(newItmStrNo);
            iostDvo.setStrSn(strSn);

            int ostrSn = this.mapper.selectNewOstrSn(newItmOstrNo);
            iostDvo.setOstrSn(ostrSn);

            // 물량배정
            iostDvo.setStrTpCd(SnServiceConst.QOM_ASN);

            iostDvo.setStrOjWareNo(itm.getStrHgrWareNo());
            iostDvo.setStrPrtnrNo(itm.getStrHgrPrtnrNo());
            iostDvo.setStrPrtnrOgTpCd(itm.getStrHgrPrtnrOgTpCd());
            iostDvo.setStrWareDvCd(itm.getStrHgrDvCd());

            iostDvo.setItmPdCd(itm.getItmPdCd());
            iostDvo.setItmGdCd(itm.getItmGdCd());
            iostDvo.setMngtUnitCd(itm.getMngtUnitCd());
            iostDvo.setStrQty(itm.getOstrCnfmQty());

            iostDvo.setOstrAkNo(itm.getOstrAkNo());
            iostDvo.setOstrAkSn(itm.getOstrAkSn());

            iostDvo.setOstrOjWareNo(itm.getOstrOjWareNo());
            iostDvo.setOstrWareDvCd(itm.getOstrWareDvCd());
            iostDvo.setOstrPrtnrOgTpCd(itm.getOstrPrtnrOgTpCd());
            iostDvo.setOstrPrtnrNo(itm.getOstrPrtnrNo());

            iostDvo.setOstrDt(itm.getOstrDt());
            iostDvo.setOstrTpCd(SnServiceConst.QOM_ASN_OSTR);

            // 품목출고내역 생성
            this.mapper.insertItmOstrIz(iostDvo);

            // 품목입고내역 생성
            this.mapper.insertItmStrIz(iostDvo);

            // 품목재고내역 등록 - 출고창고
            WsnaItemStockItemizationReqDvo ostrStockReq = this.convertStockItemizationCreateReq(iostDvo, GUBUN_OSTR);
            this.stockService.createStock(ostrStockReq);

            // 품목재고내역 이동 - 입고창고
            WsnaItemStockItemizationReqDvo strMoveReq = this.convertStockItemizationMoveReq(iostDvo);
            this.stockService.saveStockMovement(strMoveReq);
            // 품목재고내역 등록 - 입고창고
            WsnaItemStockItemizationReqDvo strStockReq = this.convertStockItemizationCreateReq(iostDvo, GUBUN_STR);
            this.stockService.createStock(strStockReq);

            // 수불 데이터 생성
            if (strSn == 1) {
                // 수불데이터 입고번호 채번 - 물량배정
                String newHgrStrNo = this.mapper.selectNewItmStrNo(SnServiceConst.QOM_ASN);
                // 수불데이터 출고번호 채번 - 물량배정
                String newHgrOstrNo = this.mapper.selectNewItmOstrNo(SnServiceConst.QOM_ASN_OSTR);
                // 수불데이터 출고요청번호 채번 - 물량배정
                String newHgrOstrAkNo = this.mapper.selectNewOstrAkNo(SnServiceConst.OSTR_AK_TP_CD_QOM_ASN);
                hgrIostDvo.setItmStrNo(newHgrStrNo);
                hgrIostDvo.setItmOstrNo(newHgrOstrNo);
                hgrIostDvo.setOstrAkNo(newHgrOstrAkNo);
            }
            this.saveReceivingAndPaying(itm, hgrIostDvo);

            // 출고요청내역 UPDATE
            this.mapper.updateItmOstrAkIz(itm);

            // 연계발생ID
            String linkOcrnId = itm.getLinkOcrnId();
            // TB_IFIN_ITM_OSTR_FSH_RCV_ETXT - 품목출고완료수신전문 상태 변경
            this.mapper.updateItmOstrFshRcvEtxt(linkOcrnId);
        }
    }

    /**
     * 수불데이터 생성
     * @param dvo           (필수) 출고완료 dvo
     * @param hgrIostDvo    (필수) 입출고내역 dvo
     */
    @Transactional
    public void saveReceivingAndPaying(
        WsnaLogisticsOutStorageFinishDvo dvo, WsnaLogisticsOutStorageFinishIostDvo hgrIostDvo
    ) {

        ValidAssert.notNull(dvo);
        ValidAssert.notNull(hgrIostDvo);

        String nowDay = DateUtil.getNowDayString();

        String newHgrStrNo = hgrIostDvo.getItmStrNo();
        String newHgrOstrNo = hgrIostDvo.getItmOstrNo();
        String newHgrOstrAkNo = hgrIostDvo.getOstrAkNo();

        int newStrSn = this.mapper.selectNewStrSn(newHgrStrNo);
        hgrIostDvo.setStrSn(newStrSn);

        int newOstrSn = this.mapper.selectNewOstrSn(newHgrOstrNo);
        hgrIostDvo.setOstrSn(newOstrSn);

        int newOStrAkSn = this.mapper.selectNewOstrAkSn(newHgrOstrAkNo);
        hgrIostDvo.setOstrAkSn(newOStrAkSn);

        // 물량배정
        hgrIostDvo.setOstrAkTpCd(SnServiceConst.OSTR_AK_TP_CD_QOM_ASN);
        hgrIostDvo.setStrTpCd(SnServiceConst.QOM_ASN);
        hgrIostDvo.setStrOjWareNo(dvo.getStrOjWareNo());
        hgrIostDvo.setStrPrtnrNo(dvo.getWareMngtPrtnrNo());
        hgrIostDvo.setStrPrtnrOgTpCd(dvo.getWareMngtPrtnrOgTpCd());
        hgrIostDvo.setStrWareDvCd(dvo.getOstrAkWareDvCd());

        hgrIostDvo.setItmPdCd(dvo.getItmPdCd());
        hgrIostDvo.setItmKndCd(dvo.getItmKndCd());
        hgrIostDvo.setItmGdCd(dvo.getItmGdCd());
        hgrIostDvo.setMngtUnitCd(dvo.getMngtUnitCd());
        hgrIostDvo.setBoxUnitQty(dvo.getBoxUnitQty());
        hgrIostDvo.setStrQty(dvo.getOstrCnfmQty());

        hgrIostDvo.setOstrOjWareNo(dvo.getStrHgrWareNo());
        hgrIostDvo.setOstrWareDvCd(dvo.getStrHgrDvCd());
        hgrIostDvo.setOstrPrtnrNo(dvo.getStrHgrPrtnrNo());
        hgrIostDvo.setOstrPrtnrOgTpCd(dvo.getStrHgrPrtnrOgTpCd());
        hgrIostDvo.setOstrDt(nowDay);
        hgrIostDvo.setOstrTpCd(SnServiceConst.QOM_ASN_OSTR);

        // 출고요청내역 생성
        this.mapper.insertItmOstrAkIz(hgrIostDvo);

        // 품목출고내역 생성
        this.mapper.insertItmOstrIz(hgrIostDvo);

        // 품목입고내역 생성
        this.mapper.insertItmStrIz(hgrIostDvo);

        // 품목재고내역 등록 - 출고창고
        WsnaItemStockItemizationReqDvo ostrStockReq = this.convertStockItemizationCreateReq(hgrIostDvo, GUBUN_OSTR);
        this.stockService.createStock(ostrStockReq);
        // 품목재고내역 이동 - 입고창고
        WsnaItemStockItemizationReqDvo strMoveReq = this.convertStockItemizationMoveReq(hgrIostDvo);
        this.stockService.saveStockMovement(strMoveReq);
        // 품목재고내역 등록 - 입고창고
        WsnaItemStockItemizationReqDvo strStockReq = this.convertStockItemizationCreateReq(hgrIostDvo, GUBUN_STR);
        this.stockService.createStock(strStockReq);

        // 품목출고내역 입고처리
        this.mapper.updateItmOstrIzForStr(hgrIostDvo);
        // 품목입고내역 입고처리
        this.mapper.updateItmStrIzForStr(hgrIostDvo);

        // 고객서비스품목재고내역 수량 처리
        this.mapper.updateCstSvItmStocForStr(hgrIostDvo);

        // 출고요청내역 UPDATE
        this.mapper.updateItmOstrAkIzForHgr(hgrIostDvo);
    }

    /**
     * 재고내역 파라미터 변환
     * @param dvo       (필수) 품목 입/출고 dvo
     * @param iostGb    (필수) 입출고 구분 (O : 출고, I : 입고)
     * @return 재고등록 request dvo
     */
    private WsnaItemStockItemizationReqDvo convertStockItemizationCreateReq(
        WsnaLogisticsOutStorageFinishIostDvo dvo, String iostGb
    ) {

        ValidAssert.notNull(dvo);
        ValidAssert.hasText(iostGb);

        String nowDay = DateUtil.getNowDayString();

        String wareDv = GUBUN_OSTR.equals(iostGb) ? dvo.getOstrWareDvCd() : dvo.getStrWareDvCd();
        String wareNo = GUBUN_OSTR.equals(iostGb) ? dvo.getOstrOjWareNo() : dvo.getStrOjWareNo();
        String wareMngtPrtnrNo = GUBUN_OSTR.equals(iostGb) ? dvo.getOstrPrtnrNo() : dvo.getStrPrtnrNo();
        String iostTp = GUBUN_OSTR.equals(iostGb) ? dvo.getOstrTpCd() : dvo.getStrTpCd();

        WsnaItemStockItemizationReqDvo reqDvo = new WsnaItemStockItemizationReqDvo();
        reqDvo.setProcsYm(nowDay.substring(0, 6));
        reqDvo.setProcsDt(nowDay);
        reqDvo.setWareDv(wareDv);
        reqDvo.setWareNo(wareNo);
        reqDvo.setWareMngtPrtnrNo(wareMngtPrtnrNo);
        reqDvo.setIostTp(iostTp);
        reqDvo.setWorkDiv(WORK_DIV_A);
        reqDvo.setItmPdCd(dvo.getItmPdCd());
        reqDvo.setMngtUnit(dvo.getMngtUnitCd());
        reqDvo.setItemGd(dvo.getItmGdCd());
        reqDvo.setQty(String.valueOf(dvo.getStrQty()));

        return reqDvo;
    }

    /**
     * 재고이동 파라미터 변환
     * @param dvo   (필수) 품목 입/출고 dvo
     * @return 재고이동 request dvo
     */
    private WsnaItemStockItemizationReqDvo convertStockItemizationMoveReq(WsnaLogisticsOutStorageFinishIostDvo dvo) {

        ValidAssert.notNull(dvo);

        String nowDay = DateUtil.getNowDayString();
        WsnaItemStockItemizationReqDvo reqDvo = new WsnaItemStockItemizationReqDvo();
        reqDvo.setProcsYm(nowDay.substring(0, 6));
        reqDvo.setProcsDt(nowDay);
        reqDvo.setWareDv(dvo.getStrWareDvCd());
        reqDvo.setWareNo(dvo.getStrOjWareNo());
        reqDvo.setWareMngtPrtnrNo(dvo.getStrPrtnrNo());
        // 이동입고(991)
        reqDvo.setIostTp(SnServiceConst.MMT_STR);
        reqDvo.setWorkDiv(WORK_DIV_A);
        reqDvo.setItmPdCd(dvo.getItmPdCd());
        reqDvo.setMngtUnit(dvo.getMngtUnitCd());
        reqDvo.setItemGd(dvo.getItmGdCd());
        reqDvo.setQty(String.valueOf(dvo.getStrQty()));

        return reqDvo;
    }

}
