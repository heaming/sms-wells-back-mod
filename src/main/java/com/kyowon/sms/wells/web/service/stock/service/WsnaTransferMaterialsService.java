package com.kyowon.sms.wells.web.service.stock.service;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaTransferMaterialsConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaItemStockItemizationDto;
import com.kyowon.sms.wells.web.service.stock.dvo.*;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaTransferMaterialsMapper;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.ValidAssert;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-S-0092 물량이동 수불데이터 생성 서비스
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-06-08
 */

@Service
@RequiredArgsConstructor
public class WsnaTransferMaterialsService {

    private final WsnaTransferMaterialsMapper mapper;

    private final WsnaTransferMaterialsConverter converter;

    // 재고내역 서비스
    private final WsnaItemStockItemizationService stockService;

    // 메시지 서비스
    private final MessageResourceService messageService;

    // 출고
    private static final String GUBUN_OSTR = "O";
    // 입고
    private static final String GUBUN_STR = "I";

    /**
     * 물량이동 수불 데이터 처리
     * @param dvo   (필수) 물량이동 request dvo
     * @throws ParseException 품목재고내역 Date Parsing Exception
     */
    @Transactional
    public void saveTransferMaterials(WsnaTransferMaterialsReqDvo dvo) throws ParseException {

        // 유효성 체크
        ValidAssert.notNull(dvo);
        ValidAssert.hasText(dvo.getOstrAkNo());
        ValidAssert.hasText(dvo.getOstrOjWareNo());
        ValidAssert.hasText(dvo.getStrOjWareNo());

        // 물량이동 데이터 조회
        List<WsnaTransferMaterialsDataDvo> dataDvos = this.mapper.selectTransferMaterials(dvo);
        if (CollectionUtils.isNotEmpty(dataDvos)) {
            // 품목출고번호 채번 (반품출고)
            String newItmOstrNo = this.mapper.selectNewItmOstrNo(SnServiceConst.RTNGD_OSTR_INSI);
            // 품목입고번호 채번 (반품입고)
            String newItmStrNo = this.mapper.selectNewItmStrNo(SnServiceConst.RTNGD_STR);
            // 비고, 하위창고간 재고이동-반품내부
            String rmkCn = this.messageService.getMessage("MSG_TXT_STOC_MMT_RTNGD_INSI");

            for (WsnaTransferMaterialsDataDvo dataDvo : dataDvos) {
                // 하위창고 재고를 상위창고로 내부반품
                WsnaTransferMaterialsIostDvo returnDvo = this.converter
                    .mapWsnaTransferMaterialsListDvoToWsnaTransferMaterialsReturnDvo(dataDvo);
                returnDvo.setItmOstrNo(newItmOstrNo);
                returnDvo.setItmStrNo(newItmStrNo);
                // 반품출고(내부)(261)
                returnDvo.setOstrTpCd(SnServiceConst.RTNGD_OSTR_INSI);
                // 반품입고(내부) - 161
                returnDvo.setStrTpCd(SnServiceConst.RTNGD_STR);
                returnDvo.setRmkCn(rmkCn);
                returnDvo.setOstrRsonCd("10");

                // 내부 반품 처리
                this.saveItmIostMaterials(returnDvo);
            }

            WsnaTransferMaterialsDataDvo dataDvo = dataDvos.get(0);
            // 출고 상위
            String ostrHgrWareNo = dataDvo.getOstrHgrWareNo();
            // 입고 상위
            String strHgrWareNo = dataDvo.getStrHgrWareNo();

            WsnaTransferMaterialsHgrDvo hgrDvo = this.converter
                .mapWsnaTransferMaterialsListDvoToWsnaTransferMaterialsHgrDvo(dataDvo);
            hgrDvo.setItmStrNo(newItmStrNo);
            // 반품입고(내부) - 161
            hgrDvo.setStrTpCd(SnServiceConst.RTNGD_STR);

            // 입출고 상위 창고가 다른 경우 상위 창고간 물량이동
            if (StringUtils.isNotEmpty(ostrHgrWareNo) && StringUtils.isNotEmpty(strHgrWareNo)
                && !ostrHgrWareNo.equals(strHgrWareNo)) {

                // 물량이동 처리
                this.saveTransferMaterialsForHgr(hgrDvo);
            }

            // 정상 입출고 처리
            this.saveOutOfMaterials(hgrDvo, dataDvo);

            // 출고요청내역 수정
            this.mapper.updateItmOstrAkIzForTransfer(dvo);
        }

    }

    /**
     * 품목 입/출고 처리
     * @param dvo   (필수) 품목 입/출고 dvo
     * @throws ParseException 품목재고내역 Date Parsing Exception
     */
    @Transactional
    public void saveItmIostMaterials(WsnaTransferMaterialsIostDvo dvo) throws ParseException {
        ValidAssert.notNull(dvo);

        String itmOstrNo = dvo.getItmOstrNo();
        String itmStrNo = dvo.getItmStrNo();
        String itmPdCd = dvo.getItmPdCd();

        ValidAssert.hasText(itmOstrNo);
        ValidAssert.hasText(itmStrNo);
        ValidAssert.hasText(itmPdCd);
        ValidAssert.hasText(dvo.getItmGdCd());

        ValidAssert.hasText(dvo.getOstrTpCd());
        ValidAssert.hasText(dvo.getOstrOjWareNo());
        ValidAssert.hasText(dvo.getOstrWareDvCd());
        ValidAssert.hasText(dvo.getOstrPrtnrNo());
        ValidAssert.hasText(dvo.getOstrPrtnrOgTpCd());

        ValidAssert.hasText(dvo.getStrTpCd());
        ValidAssert.hasText(dvo.getStrOjWareNo());
        ValidAssert.hasText(dvo.getStrWareDvCd());
        ValidAssert.hasText(dvo.getStrPrtnrNo());
        ValidAssert.hasText(dvo.getStrPrtnrOgTpCd());

        // 상품정보 조회
        if (StringUtils.isEmpty(dvo.getItmKndCd())) {
            WsnaTransferMaterialsPdDvo pdDvo = this.mapper.selectPartMasterPdInfo(itmPdCd)
                .orElseThrow(() -> new BizException("MSG_ALT_NO_DATA"));
            dvo.setItmKndCd(pdDvo.getItmKndCd());
            dvo.setItmCd(pdDvo.getItmCd());
            dvo.setMngtUnitCd(pdDvo.getMngtUnitCd());
            dvo.setBoxUnitQty(pdDvo.getBoxUnitQty());
        }

        // 출고일련번호 채번
        int newOstrSn = this.mapper.selectNewOstrSn(itmOstrNo);
        dvo.setOstrSn(newOstrSn);

        // 입고일련번호 채번
        int newStrSn = this.mapper.selectNewStrSn(itmStrNo);
        dvo.setStrSn(newStrSn);

        // 품목출고내역 데이터 생성
        this.mapper.insertItmOstrIz(dvo);
        // 품목입고내역 데이터 생성
        this.mapper.insertItmStrIz(dvo);

        // 품목재고내역 등록 - 출고창고
        WsnaItemStockItemizationDto.SaveReq ostrStockReq = this.convertStockItemizationCreateReq(dvo, GUBUN_OSTR);
        this.stockService.createStock(ostrStockReq);

        // 품목재고내역 이동 - 입고창고
        WsnaItemStockItemizationDto.SaveReq strMoveReq = this.convertStockItemizationMoveReq(dvo);
        this.stockService.saveStockMovement(strMoveReq);

        // 품목재고내역 등록 - 입고창고
        WsnaItemStockItemizationDto.SaveReq strStockReq = this.convertStockItemizationCreateReq(dvo, GUBUN_STR);
        this.stockService.createStock(strStockReq);

        // 품목출고내역 입고처리
        this.mapper.updateItmOstrIzForStr(dvo);
        // 품목입고내역 입고처리
        this.mapper.updateItmStrIzForStr(dvo);

        // 고객서비스품목재고내역 수량 처리
        this.mapper.updateCstSvItmStocForStr(dvo);
    }

    /**
     * 재고내역 파라미터 변환
     * @param dvo       (필수) 품목 입/출고 dvo
     * @param iostGb    (필수) 입출고 구분 (O : 출고, I : 입고)
     * @return 재고등록 request dto
     */
    private WsnaItemStockItemizationDto.SaveReq convertStockItemizationCreateReq(
        WsnaTransferMaterialsIostDvo dvo, String iostGb
    ) {

        ValidAssert.notNull(dvo);
        ValidAssert.hasText(iostGb);

        String nowDay = DateUtil.getNowDayString();

        String procsYm = nowDay.substring(0, 6);

        String wareDv = GUBUN_OSTR.equals(iostGb) ? dvo.getOstrWareDvCd() : dvo.getStrWareDvCd();
        String wareNo = GUBUN_OSTR.equals(iostGb) ? dvo.getOstrOjWareNo() : dvo.getStrOjWareNo();
        String wareMngtPrtnrNo = GUBUN_OSTR.equals(iostGb) ? dvo.getOstrPrtnrNo() : dvo.getStrPrtnrNo();
        String iostTp = GUBUN_OSTR.equals(iostGb) ? dvo.getOstrTpCd() : dvo.getStrTpCd();

        String workDiv = dvo.getItmGdCd();
        String itmPdCd = dvo.getItmPdCd();
        String mngtUnit = dvo.getMngtUnitCd();
        String itemGd = dvo.getItmGdCd();
        String qty = String.valueOf(dvo.getOstrAkQty());

        return new WsnaItemStockItemizationDto.SaveReq(
            procsYm, nowDay, wareDv, wareNo, wareMngtPrtnrNo, iostTp, workDiv, itmPdCd, mngtUnit, itemGd, qty, null,
            null, null
        );
    }

    /**
     * 재고이동 파라미터 변환
     * @param dvo   (필수) 품목 입/출고 dvo
     * @return 재고이동 request dto
     */
    private WsnaItemStockItemizationDto.SaveReq convertStockItemizationMoveReq(WsnaTransferMaterialsIostDvo dvo) {

        ValidAssert.notNull(dvo);

        String nowDay = DateUtil.getNowDayString();

        String procsYm = nowDay.substring(0, 6);
        String wareDv = dvo.getStrWareDvCd();
        String wareNo = dvo.getStrOjWareNo();
        String wareMngtPrtnrNo = dvo.getStrPrtnrNo();
        // 이동입고(991)
        String iostTp = SnServiceConst.MMT_STR;
        String workDiv = dvo.getItmGdCd();
        String itmPdCd = dvo.getItmPdCd();
        String mngtUnit = dvo.getMngtUnitCd();
        String itemGd = dvo.getItmGdCd();
        String qty = String.valueOf(dvo.getOstrAkQty());

        return new WsnaItemStockItemizationDto.SaveReq(
            procsYm, nowDay, wareDv, wareNo, wareMngtPrtnrNo, iostTp, workDiv, itmPdCd, mngtUnit, itemGd, qty, null,
            null, null
        );
    }

    /**
     * 상위 창고 물량이동 처리
     * @param dvo   (필수) 상위 창고정보 dvo
     * @throws ParseException 품목재고내역 Date Parsing Exception
     */
    @Transactional
    public void saveTransferMaterialsForHgr(WsnaTransferMaterialsHgrDvo dvo) throws ParseException {

        ValidAssert.notNull(dvo);
        ValidAssert.hasText(dvo.getItmStrNo());
        ValidAssert.hasText(dvo.getStrTpCd());
        ValidAssert.hasText(dvo.getOstrHgrWareNo());
        ValidAssert.hasText(dvo.getItmGdCd());

        List<WsnaTransferMaterialsStrDvo> strDvos = this.mapper.selectItmStrIz(dvo);
        if (CollectionUtils.isNotEmpty(strDvos)) {
            // 출고요청번호 채번, 물량이동
            String newOstrAkNo = this.mapper.selectNewOstrAkNo(SnServiceConst.OSTR_AK_TP_CD_QOM_MMT);
            // 품목출고번호 채번, 물량이동
            String newItmOstrNo = this.mapper.selectNewItmOstrNo(SnServiceConst.QOM_MMT_OSTR);
            // 품목입고번호 채번, 물량이동
            String newItmStrNo = this.mapper.selectNewItmStrNo(SnServiceConst.QOM_MMT);
            // 비고, 하위창고간 재고이동-물량이동
            String rmkCn = this.messageService.getMessage("MSG_TXT_STOC_MMT_QOM_MMT");

            for (WsnaTransferMaterialsStrDvo strDvo : strDvos) {

                // 출고요청일련번호 생성
                int newOstrAkSn = this.mapper.selectNewOstrAkSn(newOstrAkNo);

                WsnaTransferMaterialsOstrAkDvo ostrAkDvo = this.converter
                    .mapWsnaTransferMaterialsHgrDvoToWsnaTransferMaterialsOstrAkDvo(dvo);

                ostrAkDvo.setOstrAkNo(newOstrAkNo);
                ostrAkDvo.setOstrAkSn(newOstrAkSn);
                ostrAkDvo.setOstrAkTpCd(SnServiceConst.OSTR_AK_TP_CD_QOM_MMT);
                ostrAkDvo.setStrQty(strDvo.getStrQty());
                ostrAkDvo.setItmKndCd(strDvo.getItmKndCd());
                ostrAkDvo.setItmPdCd(strDvo.getItmPdCd());
                ostrAkDvo.setItmCd(strDvo.getItmCd());
                ostrAkDvo.setMngtUnitCd(strDvo.getMngtUnitCd());
                ostrAkDvo.setBoxUnitQty(strDvo.getBoxUnitQty());
                ostrAkDvo.setRmkCn(rmkCn);

                // 출고요청내역 데이터 생성, 물량이동
                this.mapper.insertItmOstrAkIz(ostrAkDvo);

                // 물량이동 입출고 처리
                WsnaTransferMaterialsIostDvo iostDvo = this.converter
                    .mapWsnaTransferMaterialsOstrAkDvoToWsnaTransferMaterialsIostDvo(ostrAkDvo);

                iostDvo.setItmOstrNo(newItmOstrNo);
                iostDvo.setOstrTpCd(SnServiceConst.QOM_MMT_OSTR);
                iostDvo.setItmStrNo(newItmStrNo);
                iostDvo.setStrTpCd(SnServiceConst.QOM_MMT);
                iostDvo.setOstrAkNo(newOstrAkNo);
                iostDvo.setOstrAkSn(newOstrAkSn);

                this.saveItmIostMaterials(iostDvo);

                // 출고요청내역 수정
                this.mapper.updateItmOstrAkIz(ostrAkDvo);
            }
        }

    }

    /**
     * 입고 상위창고 -> 입고창고 출고처리
     * @param dvo       (필수) 상위 창고정보 dvo
     * @param dataDvo   (필수) 물량이동 상세 data dvo
     * @throws ParseException 품목재고내역 Date Parsing Exception
     */
    @Transactional
    public void saveOutOfMaterials(WsnaTransferMaterialsHgrDvo dvo, WsnaTransferMaterialsDataDvo dataDvo)
        throws ParseException {

        ValidAssert.notNull(dvo);
        ValidAssert.hasText(dvo.getItmStrNo());
        ValidAssert.hasText(dvo.getStrTpCd());
        ValidAssert.hasText(dvo.getOstrHgrWareNo());

        ValidAssert.notNull(dataDvo);
        ValidAssert.hasText(dataDvo.getItmGdCd());
        ValidAssert.hasText(dataDvo.getStrOjWareNo());
        ValidAssert.hasText(dataDvo.getStrWareDvCd());
        ValidAssert.hasText(dataDvo.getStrPrtnrNo());
        ValidAssert.hasText(dataDvo.getStrPrtnrOgTpCd());
        ValidAssert.hasText(dataDvo.getStrHgrWareNo());
        ValidAssert.hasText(dataDvo.getStrHgrDvCd());
        ValidAssert.hasText(dataDvo.getStrHgrPrtnrNo());
        ValidAssert.hasText(dataDvo.getStrHgrPrtnrOgTpCd());

        List<WsnaTransferMaterialsStrDvo> strDvos = this.mapper.selectItmStrIz(dvo);
        if (CollectionUtils.isNotEmpty(strDvos)) {
            // 출고요청번호 채번, 정상출고
            String newOstrAkNo = this.mapper.selectNewOstrAkNo(SnServiceConst.OSTR_AK_TP_CD_NOM_OSTR);
            // 품목출고번호 채번, 정상출고
            String newItmOstrNo = this.mapper.selectNewItmOstrNo(SnServiceConst.NOM_OSTR);
            // 품목입고번호 채번, 정상입고
            String newItmStrNo = this.mapper.selectNewItmStrNo(SnServiceConst.NOM_STR);
            // 비고, 하위창고간 재고이동-물량이동
            String rmkCn = this.messageService.getMessage("MSG_TXT_STOC_MMT_QOM_MMT");

            for (WsnaTransferMaterialsStrDvo strDvo : strDvos) {

                // 출고요청일련번호 생성
                int newOstrAkSn = this.mapper.selectNewOstrAkSn(newOstrAkNo);

                WsnaTransferMaterialsOstrAkDvo ostrAkDvo = this.convertNormalMaterialsDvo(dataDvo, strDvo);

                ostrAkDvo.setOstrAkNo(newOstrAkNo);
                ostrAkDvo.setOstrAkSn(newOstrAkSn);
                ostrAkDvo.setRmkCn(rmkCn);

                // 출고요청내역 데이터 생성, 정상출고
                this.mapper.insertItmOstrAkIz(ostrAkDvo);

                // 정상 입출고 처리
                WsnaTransferMaterialsIostDvo iostDvo = this.converter
                    .mapWsnaTransferMaterialsOstrAkDvoToWsnaTransferMaterialsIostDvo(ostrAkDvo);

                iostDvo.setItmOstrNo(newItmOstrNo);
                iostDvo.setOstrTpCd(SnServiceConst.NOM_OSTR);
                iostDvo.setItmStrNo(newItmStrNo);
                iostDvo.setStrTpCd(SnServiceConst.NOM_STR);
                iostDvo.setOstrAkNo(newOstrAkNo);
                iostDvo.setOstrAkSn(newOstrAkSn);

                this.saveItmIostMaterials(iostDvo);

                // 출고요청내역 수정
                this.mapper.updateItmOstrAkIz(ostrAkDvo);
            }
        }
    }

    /**
     * 정상 입출고 처리 파라미터 변환
     * @param dataDvo   (필수) 물량이동 상세 data dvo
     * @param strDvo    (필수) 입고내역 dvo
     * @return 정상 출고요청내역 dvo
     */
    private WsnaTransferMaterialsOstrAkDvo convertNormalMaterialsDvo(
        WsnaTransferMaterialsDataDvo dataDvo, WsnaTransferMaterialsStrDvo strDvo
    ) {

        ValidAssert.notNull(dataDvo);
        ValidAssert.notNull(strDvo);

        WsnaTransferMaterialsOstrAkDvo ostrAkDvo = new WsnaTransferMaterialsOstrAkDvo();

        ostrAkDvo.setOstrAkTpCd(SnServiceConst.OSTR_AK_TP_CD_NOM_OSTR);

        ostrAkDvo.setStrOjWareNo(dataDvo.getStrOjWareNo());
        ostrAkDvo.setOstrAkWareDvCd(dataDvo.getStrWareDvCd());
        ostrAkDvo.setWareMngtPrtnrNo(dataDvo.getStrPrtnrNo());
        ostrAkDvo.setWareMngtPrtnrOgTpCd(dataDvo.getStrPrtnrOgTpCd());

        ostrAkDvo.setOstrOjWareNo(dataDvo.getStrHgrWareNo());
        ostrAkDvo.setOstrOjWareDvCd(dataDvo.getStrHgrDvCd());
        ostrAkDvo.setOstrWareMngtPrtnrNo(dataDvo.getStrHgrPrtnrNo());
        ostrAkDvo.setOstrWareMngtPrtnrOgTpCd(dataDvo.getStrHgrPrtnrOgTpCd());

        ostrAkDvo.setStrQty(strDvo.getStrQty());
        ostrAkDvo.setItmKndCd(strDvo.getItmKndCd());
        ostrAkDvo.setItmPdCd(strDvo.getItmPdCd());
        ostrAkDvo.setItmCd(strDvo.getItmCd());
        ostrAkDvo.setItmGdCd(dataDvo.getItmGdCd());
        ostrAkDvo.setMngtUnitCd(strDvo.getMngtUnitCd());
        ostrAkDvo.setBoxUnitQty(strDvo.getBoxUnitQty());

        return ostrAkDvo;
    }

}
