package com.kyowon.sms.wells.web.service.stock.service;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaItemStockItemizationDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemStockItemizationDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaItemStockItemizationMapper;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;
import org.apache.commons.lang.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

/**
 * <pre>
 * W-SV-S-0086 품목재고내역 관리
 * </pre>
 *
 * @author SongTaeSung
 * @since 2023.03.07
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WsnaItemStockItemizationService {

    private final WsnaItemStockItemizationMapper mapper;

    /**
     * @param dto :[{procsYm : 처리년월 , procsDt : 처리일자, wareDv : 창고구분 , wareNo : 창고번호 , wareMngtPrtnrNo : 창고파트너번호
     *            iostTp : 입출고유형 , workDiv : 작업구분, itmPdCd : 품목상품코드, mngtUnit : 관리단위
     *            itemGd : 상품등급 , qty : 수량 , mvcYn : ?? , freeYn : ??, chgYn : ??}]
     */
    public int createStock(WsnaItemStockItemizationDto.SaveReq dto) throws ParseException {
        WsnaItemStockItemizationDvo dvo = new WsnaItemStockItemizationDvo();
        int processCount = 0;
        int cmpPlnQty = 0; /*입고예정수량과 파라미터로 넘어온 수량을 뺀 결과값*/
        int cmpBuffQty = 0; /*이동재고*/
        int pitmStocGdQty = 0; /*시점재고등급수량*/
        int cmpOnQty = 0;
        int strExpGdQty = 0; /*입고예정등급수량*/
        String sampleDate = "";
        String endOstrDate = "";

        /*입력된 품목상품코드 정보로 COUNT 조회 처리*/
        int chkValue = mapper.selectCountItmPdCdInfo(dto);

        if (chkValue > 0) {
            //데이터 체크용 dvo
            WsnaItemStockItemizationDvo varbDvo = mapper.selectItmPdCdInformation(dto);
            log.info("WsnaItemStockItemizationDvo.dvo", dvo);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            /*처리일자*/
            Date procsDt = new Date(dateFormat.parse(dto.procsDt()).getTime());
            /*최종입고일자*/
            Date fnlStrDt = new Date(dateFormat.parse(varbDvo.getFnlStrDt()).getTime());
            /*최종출고일자*/
            Date fnlOstrDt = new Date(dateFormat.parse(varbDvo.getFnlOstrDt()).getTime());

            log.info("처리일자 -------->", procsDt);
            log.info("최종입고일자 -------->", fnlStrDt);
            log.info("최종출고일자 -------->", fnlOstrDt);
            int fnlStrDtcompare = fnlStrDt.compareTo(procsDt);
            int fnlOstrDtCompare = fnlOstrDt.compareTo(procsDt);

            log.info("WsnaItemStockItemizationDvo STEP01 -> ", fnlStrDtcompare);
            log.info("WsnaItemStockItemizationDvo STEP01 -> ", fnlOstrDtCompare);
            /*최종입고일자 : 기존일자보다 최근일자이면 최근일자(수불일자)를 최종입고일자로 설정 */
            if (fnlStrDtcompare > 0) {
                sampleDate = dateFormat.format(procsDt);
                log.info("WsnaItemStockItemizationDvo STEP02 -> ", sampleDate);
            } else if (fnlStrDtcompare < 0) {
                sampleDate = dateFormat.format(fnlStrDt);
                log.info("WsnaItemStockItemizationDvo STEP03 -> ", sampleDate);
            }
            /*최종출고일자 : 기존일자보다 최근일자이면 최근일자(수불일자)를 최종출고일자로 설정*/
            if (fnlOstrDtCompare > 0) {
                endOstrDate = dateFormat.format(procsDt);

                log.info("WsnaItemStockItemizationDvo STEP04 -> ", endOstrDate);
            } else if (fnlOstrDtCompare < 0) {
                endOstrDate = dateFormat.format(fnlOstrDt);
                log.info("WsnaItemStockItemizationDvo STEP05 -> ", endOstrDate);
            }
            /*작업구분이 입력(A)일때 */
            if (StringUtils.startsWith(dto.workDiv(), "A")) {
                /*===============================================================================
                구매입고 유형에 따른 처리
                ---------------------------------------------------------------------------------
                --구매입고(110)
                ---------------------------------------------------------------------------------
                --품목등급(구매입고는 정상품(A등급)만 발생)- 2012.10.26 이영진 구매입고 등급 B(리퍼)등급 추가 됨
                --시점재고 = 시점재고 + 구매입고
                --구매입고 = 구매입고 + 구매입고
                --입고예정 = 입고예정 - 구매입고
                --발주시 입고예정부수가 발생, 구매입고시 입고에정부수 MINUS 처리
                --입고예정부수가 0보다 적으면 ERROR 발생
                ===============================================================================*/
                if ("110".equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                    cmpPlnQty = Integer.parseInt(varbDvo.getStrExpAGdQty()) - Integer.parseInt(dto.qty());
                    cmpBuffQty = Integer.parseInt(varbDvo.getPitmStocAGdQty());

                    BizAssert.isTrue(cmpPlnQty >= 0, "MSG_TXT_STR_EXP_MINUS");

                    /*시점재고등급수량*/
                    pitmStocGdQty = Integer.parseInt(varbDvo.getPitmStocAGdQty()) + Integer.parseInt(dto.qty());
                    /*입고예정등급수량*/
                    strExpGdQty = Integer.parseInt(varbDvo.getStrExpAGdQty()) - Integer.parseInt(dto.qty());
                    dvo.setPitmStocAGdQty(String.valueOf(pitmStocGdQty));
                    dvo.setStrExpAGdQty(String.valueOf(strExpGdQty));
                    dvo.setFnlStrDt(sampleDate);
                    dvo.setWareNo(dto.wareNo());
                    dvo.setItmPdCd(dto.itmPdCd());

                    processCount += mapper.updatePurchaseAStore(dvo);

                } else if ("110".equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                    cmpPlnQty = Integer.parseInt(varbDvo.getSftStocBGdQty()) - Integer.parseInt(dto.qty());
                    cmpBuffQty = Integer.parseInt(varbDvo.getPitmStocBGdQty());

                    //입고예정부수가 MINUS 발생 오류!
                    BizAssert.isTrue(cmpPlnQty >= 0, "MSG_TXT_STR_EXP_MINUS");

                    pitmStocGdQty = Integer.parseInt(varbDvo.getPitmStocBGdQty()) + Integer.parseInt(dto.qty());
                    /*입고예정등급수량*/
                    strExpGdQty = Integer.parseInt(dvo.getStrExpBGdQty()) - Integer.parseInt(dto.qty());
                    dvo.setPitmStocBGdQty(String.valueOf(pitmStocGdQty));
                    dvo.setStrExpBGdQty(String.valueOf(strExpGdQty));
                    dvo.setFnlStrDt(sampleDate);
                    dvo.setWareNo(dto.wareNo());
                    dvo.setItmPdCd(dto.itmPdCd());

                    processCount += mapper.updatePurchaseBStore(dvo);

                }
                /*===============================================================================
                기타입고 유형에 따른 처리
                ---------------------------------------------------------------------------------
                --기타입고(117)
                ---------------------------------------------------------------------------------
                --입출유형(117:기타입고:ST122_ETC_IN_A)
                ===============================================================================*/
                if ("117".equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                    cmpOnQty = Integer.parseInt(varbDvo.getPitmStocAGdQty()) + Integer.parseInt(dto.qty());
                    //시점재고부수가 MINUS 발생 오류!
                    BizAssert.isTrue(cmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocAGdQty(String.valueOf(cmpOnQty));
                    dvo.setFnlOstrDt(String.valueOf(fnlOstrDt));
                    dvo.setWareNo(dto.wareNo());
                    dvo.setItmPdCd(dto.itmPdCd());
                    processCount += mapper.updatePitmStocAGd(dvo);

                } else if ("117".equals(dto.iostTp()) && "E".equals(dto.itemGd())) {
                    cmpOnQty = Integer.parseInt(varbDvo.getPitmStocEGdQty()) + Integer.parseInt(dto.qty());

                    BizAssert.isTrue(cmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    dvo.setPitmStocEGdQty(String.valueOf(cmpOnQty));
                    dvo.setFnlOstrDt(String.valueOf(fnlOstrDt));
                    dvo.setWareNo(dto.wareNo());
                    dvo.setItmPdCd(dto.itmPdCd());
                    processCount += mapper.updatePitmStocEGd(dvo);

                }
                /*===============================================================================
                내부입고 유형에 따른 처리
                ---------------------------------------------------------------------------------
                --내부입고란?
                    정상입고(121), 물량배정입고(122), 물량이동입고(123)
                    반품입고내부(161)
                ---------------------------------------------------------------------------------
                --시점재고 = 시점재고 + 내부입고
                --내부입고 = 내부입고 + 내부입고
                --이동재고 = 이동재고 - 내부입고
                --내부입고는 출고시점에 이동재고부수가 발생, 내부입고시 이동재고부수 MINUS 처리
                --이동재고부수가 0보다 적으면 ERROR 발생
                ===============================================================================*/
                /*입출유형(121:정상입고*/
                /*입출고유형이 정상입고(121), 물량배정(122), 물량이동(123), 반품입고(161)*/
                if (List.of("121", "122", "123", "161").contains(dto.iostTp()) && "A".equals(dto.itemGd())) {
                    //이동재고A등급수량 - 입력된수량
                    cmpBuffQty = Integer.parseInt(varbDvo.getMmtStocAGdQty()) - Integer.parseInt(dto.qty());

                    //이동재고부수가 MINUS 발생 오류!
                    BizAssert.isTrue(cmpBuffQty >= 0, "MSG_TXT_MMT_STOC_MINUS");

                    pitmStocGdQty = Integer.parseInt(varbDvo.getPitmStocAGdQty()) + Integer.parseInt(dto.qty());
                    dvo.setPitmStocAGdQty(String.valueOf(pitmStocGdQty));
                    dvo.setFnlStrDt(sampleDate);
                    dvo.setWareNo(dto.wareNo());
                    dvo.setItmPdCd(dto.itmPdCd());
                    processCount += mapper.updateNormalStoreAQty(dvo);

                } else if (List.of("121", "122", "123", "161").contains(dto.iostTp()) && "B".equals(dto.itemGd())) {
                    cmpBuffQty = Integer.parseInt(varbDvo.getMmtStocBGdQty()) - Integer.parseInt(dto.qty());

                    //이동재고부수가 MINUS 발생 오류!
                    BizAssert.isTrue(cmpBuffQty >= 0, "MSG_TXT_MMT_STOC_MINUS");

                    pitmStocGdQty = Integer.parseInt(varbDvo.getPitmStocBGdQty()) + Integer.parseInt(dto.qty());
                    dvo.setPitmStocBGdQty(String.valueOf(pitmStocGdQty));
                    dvo.setFnlStrDt(sampleDate);
                    dvo.setWareNo(dto.wareNo());
                    dvo.setItmPdCd(dto.itmPdCd());
                    processCount += mapper.updateNormalStoreBQty(dvo);

                } else if (List.of("121", "122", "123", "161").contains(dto.iostTp()) && "E".equals(dto.itemGd())) {
                    cmpBuffQty = Integer.parseInt(varbDvo.getMmtStocEGdQty()) - Integer.parseInt(dto.qty());

                    //이동재고부수가 MINUS 발생 오류!
                    BizAssert.isTrue(cmpBuffQty >= 0, "MSG_TXT_MMT_STOC_MINUS");

                    pitmStocGdQty = Integer.parseInt(varbDvo.getPitmStocEGdQty()) + Integer.parseInt(dto.qty());
                    dvo.setPitmStocEGdQty(String.valueOf(pitmStocGdQty));
                    dvo.setFnlStrDt(sampleDate);
                    dvo.setWareNo(dto.wareNo());
                    dvo.setItmPdCd(dto.itmPdCd());
                    processCount += mapper.updateNormalStoreEQty(dvo);

                } else if (List.of("121", "122", "123", "161").contains(dto.iostTp()) && "R".equals(dto.itemGd())) {
                    cmpBuffQty = Integer.parseInt(varbDvo.getMmtStocRGdQty()) - Integer.parseInt(dto.qty());

                    //이동재고부수가 MINUS 발생 오류!
                    BizAssert.isTrue(cmpBuffQty >= 0, "MSG_TXT_MMT_STOC_MINUS");

                    pitmStocGdQty = Integer.parseInt(varbDvo.getPitmStocRGdQty()) + Integer.parseInt(dto.qty());
                    dvo.setPitmStocEGdQty(String.valueOf(pitmStocGdQty));
                    dvo.setFnlStrDt(sampleDate);
                    dvo.setWareNo(dto.wareNo());
                    dvo.setItmPdCd(dto.itmPdCd());
                    processCount += mapper.updateNormalStoreRQty(dvo);

                }
                /*===============================================================================
                    외부입고 유형에 따른 처리
                    ---------------------------------------------------------------------------------
                    --외부반품입고란?
                        반품입고외부(162)
                    ---------------------------------------------------------------------------------
                    --시점재고 = 시점재고 + 외부반품입고
                    --외부반품입고 = 외부반품입고 + 외부반품입고
                    --외부반품은 작업자(엔지니어,LP)가 고객에게 출고하였던 상품을 반품하는 경우에 발생( 미존재은 오류 발생)
                    --외부반품위 경우, 입고일자 UPDATE를 하지 않음
                    ---------------------------------------------------------------------------------
                    재고조정입고 (181)
                    ---------------------------------------------------------------------------------
                    --시점재고 = 시점재고 + 재고조정입고
                 ===============================================================================*/

                /*입출고유형이 반품외부입고(162), 재고조정입고(181)*/
                if (List.of("162", "181").contains(dto.iostTp()) && "A".equals(dto.itemGd())) {
                    pitmStocGdQty = Integer.parseInt(varbDvo.getPitmStocAGdQty()) + Integer.parseInt(dto.qty());
                    dvo.setPitmStocAGdQty(String.valueOf(pitmStocGdQty));
                    dvo.setWareNo(dto.wareNo());
                    dvo.setItmPdCd(dto.itmPdCd());

                    processCount += mapper.updateOtsdRtngdAGdStr(dvo);

                } else if (List.of("162", "181").contains(dto.iostTp()) && "B".equals(dto.itemGd())) {
                    pitmStocGdQty = Integer.parseInt(varbDvo.getPitmStocBGdQty()) + Integer.parseInt(dto.qty());
                    dvo.setPitmStocBGdQty(String.valueOf(pitmStocGdQty));
                    dvo.setWareNo(dto.wareNo());
                    dvo.setItmPdCd(dto.itmPdCd());
                    processCount += mapper.updateOtsdRtngdBGdStr(dvo);

                } else if (List.of("162", "181").contains(dto.iostTp()) && "E".equals(dto.itemGd())) {
                    pitmStocGdQty = Integer.parseInt(varbDvo.getPitmStocEGdQty()) + Integer.parseInt(dto.qty());
                    dvo.setPitmStocEGdQty(String.valueOf(pitmStocGdQty));
                    dvo.setWareNo(dto.wareNo());
                    dvo.setItmPdCd(dto.itmPdCd());

                    processCount += mapper.updateOtsdRtngdEGdStr(dvo);
                } else if (List.of("162", "181").contains(dto.iostTp()) && "R".equals(dto.itemGd())) {
                    pitmStocGdQty = Integer.parseInt(varbDvo.getPitmStocRGdQty()) + Integer.parseInt(dto.qty());
                    dvo.setPitmStocRGdQty(String.valueOf(pitmStocGdQty));
                    dvo.setWareNo(dto.wareNo());
                    dvo.setItmPdCd(dto.itmPdCd());

                    processCount += mapper.updateOtsdRtngdRGdStr(dvo);
                }
                /*===============================================================================
                내부출고 유형에 따른 처리
                ---------------------------------------------------------------------------------
                --내부출고란?
                    정상출고(221), 물량배정출고(222), 물량이동출고(223)
                    반품출고내부(261)
                ---------------------------------------------------------------------------------
                --시점재고 = 시점재고 - 내부출고
                --내부출고 = 내부출고 + 내부출고
                --이동재고 = 이동재고 + 내부출고 (？--내부출고는 출고시점에 입고창고의 이동재고부수가 발생, 내부입고시 이동재고부수 PLUS 처리
                --시점재고부수가 0보다 적으면 ERROR 발생
                ===============================================================================*/
                /*===============================================================================
                외부출고 유형에 따른 처리
                ---------------------------------------------------------------------------------
                --외부출고란?
                    반품출고외부(262), 판매(211), 폐기(212), 작업(213), 기타(217), 리퍼완료(218)
                    기타1(291),기타2(292)
                ---------------------------------------------------------------------------------
                --시점재고 = 시점재고 - 외부출고
                --외부출고 = 외부출고 + 외부출고
                --판매는 정상품(A등급)만 발생
                --시점재고부수가 0보다 적으면 ERROR 발생
                ===============================================================================*/
                /*입출고유형이 정상출고(221), 물량배정(222), 물량이동(223), 반품출고(내부)(261), 반품출고(외부)(262), 외부출고(판매)(211), 외부출고(폐기)(212), 외부출고(작업)(213),
                외부출고(기타)(217), 외부출고(리퍼완료)(218), 재고조정출고(281), 외부출고(기타)(292)*/
                if (List.of("221", "222", "223", "261", "262", "211", "212", "213", "217", "218", "281", "292")
                    .contains(dto.iostTp()) && "A".equals(dto.itemGd())) {

                    cmpOnQty = Integer.parseInt(varbDvo.getPitmStocAGdQty()) - Integer.parseInt(dto.qty());

                    //시점재고부수가 MINUS 발생 오류 !
                    BizAssert.isTrue(cmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    pitmStocGdQty = Integer.parseInt(varbDvo.getPitmStocAGdQty()) - Integer.parseInt(dto.qty());
                    dvo.setPitmStocAGdQty(String.valueOf(pitmStocGdQty));
                    dvo.setFnlOstrDt(endOstrDate);
                    dvo.setWareNo(dto.wareNo());
                    dvo.setItmPdCd(dto.itmPdCd());

                    processCount += mapper.updateInsiOtsdAGdOstr(dvo);

                } else if (List.of("221", "222", "223", "261", "262", "211", "212", "213", "217", "218", "281", "292")
                    .contains(dto.iostTp()) && "B".equals(dto.itemGd())) {

                    cmpOnQty = Integer.parseInt(varbDvo.getPitmStocBGdQty()) - Integer.parseInt(dto.qty());

                    //시점재고부수가 MINUS 발생 오류 !
                    BizAssert.isTrue(cmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    pitmStocGdQty = Integer.parseInt(varbDvo.getPitmStocBGdQty()) - Integer.parseInt(dto.qty());
                    dvo.setPitmStocBGdQty(String.valueOf(pitmStocGdQty));
                    dvo.setFnlOstrDt(endOstrDate);
                    dvo.setWareNo(dto.wareNo());
                    dvo.setItmPdCd(dto.itmPdCd());

                    processCount += mapper.updateInsiOtsdBGdOstr(dvo);

                } else if (List.of("221", "222", "223", "261", "262", "211", "212", "213", "217", "218", "281", "292")
                    .contains(dto.iostTp()) && "E".equals(dto.itemGd())) {

                    cmpOnQty = Integer.parseInt(varbDvo.getPitmStocEGdQty()) - Integer.parseInt(dto.qty());

                    //시점재고부수가 MINUS 발생 오류 !
                    BizAssert.isTrue(cmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    pitmStocGdQty = Integer.parseInt(varbDvo.getPitmStocEGdQty()) - Integer.parseInt(dto.qty());
                    dvo.setPitmStocEGdQty(String.valueOf(pitmStocGdQty));
                    dvo.setFnlOstrDt(endOstrDate);
                    dvo.setWareNo(dto.wareNo());
                    dvo.setItmPdCd(dto.itmPdCd());

                    processCount += mapper.updateInsiOtsdEGdOstr(dvo);

                } else if (List.of("221", "222", "223", "261", "262", "211", "212", "213", "217", "218", "281", "292")
                    .contains(dto.iostTp()) && "R".equals(dto.itemGd())) {

                    cmpOnQty = Integer.parseInt(varbDvo.getPitmStocRGdQty()) - Integer.parseInt(dto.qty());

                    //시점재고부수가 MINUS 발생 오류 !
                    BizAssert.isTrue(cmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                    pitmStocGdQty = Integer.parseInt(varbDvo.getPitmStocRGdQty()) - Integer.parseInt(dto.qty());
                    dvo.setPitmStocEGdQty(String.valueOf(pitmStocGdQty));
                    dvo.setFnlOstrDt(endOstrDate);
                    dvo.setWareNo(dto.wareNo());
                    dvo.setItmPdCd(dto.itmPdCd());

                    processCount += mapper.updateInsiOtsdRGdOstr(dvo);

                }
                //TODO : 월별품목재고내역 관리 서비스(W-SV-S-0086)의 등록 메소드를(saveMcbyItmStocIzRgsts)를 호출한다. (개발완료시 반영예정)
            }
        } else if (chkValue == 0) {
            if (StringUtils.startsWith(dto.workDiv(), "A")) {
                /*작업유형이 등록 (V_WCOM_WRK_GB = 'A')이고 입출고유형이 기타입고(117), 외부 반품입고(162) 인경우 */
                if (List.of("117", "162").contains(dto.iostTp()) && "A".equals(dto.itemGd())) {
                    dvo.setWareNo(dto.wareNo());
                    dvo.setItmPdCd(dto.itmPdCd());
                    dvo.setPitmStocAGdQty(dto.qty());

                    processCount += mapper.insertAGdSvstCstSvItmStocIz(dvo);

                } else if (List.of("117", "162").contains(dto.iostTp()) && "B".equals(dto.itemGd())) {
                    dvo.setWareNo(dto.wareNo());
                    dvo.setItmPdCd(dto.itmPdCd());
                    dvo.setPitmStocBGdQty(dto.qty());

                    processCount += mapper.insertBGdSvstCstSvItmStocIz(dvo);

                } else if (List.of("117", "162").contains(dto.iostTp()) && "E".equals(dto.itemGd())) {
                    dvo.setWareNo(dto.wareNo());
                    dvo.setItmPdCd(dto.itmPdCd());
                    dvo.setPitmStocEGdQty(dto.qty());

                    processCount += mapper.insertEGdSvstCstSvItmStocIz(dvo);

                } else if (List.of("117", "162").contains(dto.iostTp()) && "R".equals(dto.itemGd())) {
                    dvo.setWareNo(dto.wareNo());
                    dvo.setItmPdCd(dto.itmPdCd());
                    dvo.setPitmStocRGdQty(dto.qty());

                    processCount += mapper.insertRGdSvstCstSvItmStocIz(dvo);
                }

                //TODO : 월별품목재고내역 관리 서비스(W-SV-S-0086)의 등록 메소드를(saveMcbyItmStocIzRgsts)를 호출 (개발완료시 반영예정)
            }

        }

        return processCount;

    }

    /**
     * @param dto :[{procsYm : 처리년월 , procsDt : 처리일자, wareDv : 창고구분 , wareNo : 창고번호 , wareMngtPrtnrNo : 창고파트너번호
     *            iostTp : 입출고유형 , workDiv : 작업구분, itmPdCd : 품목상품코드, mngtUnit : 관리단위
     *            itemGd : 상품등급 , qty : 수량 , mvcYn : ?? , freeYn : ??, chgYn : ??}]
     */
    public int removeStock(WsnaItemStockItemizationDto.SaveReq dto) throws ParseException {

        WsnaItemStockItemizationDvo dvo = new WsnaItemStockItemizationDvo();
        int processCount = 0;
        int cmpPlnQty = 0; /*입고예정수량과 파라미터로 넘어온 수량을 뺀 결과값*/
        int cmpBuffQty = 0; /*이동재고*/
        int pitmStocGdQty = 0; /*시점재고등급수량*/
        int cmpOnQty = 0;
        int strExpGdQty = 0; /*입고예정등급수량*/
        String sampleDate = "";
        String endOstrDate = "";

        WsnaItemStockItemizationDvo varbDvo = mapper.selectItmPdCdInformation(dto);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

        /*처리일자*/
        Date procsDt = new Date(dateFormat.parse(dto.procsDt()).getTime());
        /*최종입고일자*/
        Date fnlStrDt = new Date(dateFormat.parse(varbDvo.getFnlStrDt()).getTime());
        /*최종출고일자*/
        Date fnlOstrDt = new Date(dateFormat.parse(varbDvo.getFnlOstrDt()).getTime());

        log.info("처리일자 -------->", procsDt);
        log.info("최종입고일자 -------->", fnlStrDt);
        log.info("최종출고일자 -------->", fnlOstrDt);
        int fnlStrDtcompare = fnlStrDt.compareTo(procsDt);
        int fnlOstrDtCompare = fnlOstrDt.compareTo(procsDt);

        log.info("WsnaItemStockItemizationDvo STEP01 -> ", fnlStrDtcompare);
        log.info("WsnaItemStockItemizationDvo STEP01 -> ", fnlOstrDtCompare);
        /*최종입고일자 : 기존일자보다 최근일자이면 최근일자(수불일자)를 최종입고일자로 설정 */
        if (fnlStrDtcompare > 0) {
            sampleDate = dateFormat.format(procsDt);
            log.info("WsnaItemStockItemizationDvo STEP02 -> ", sampleDate);
        } else if (fnlStrDtcompare < 0) {
            sampleDate = dateFormat.format(fnlStrDt);
            log.info("WsnaItemStockItemizationDvo STEP03 -> ", sampleDate);
        }
        /*최종출고일자 : 기존일자보다 최근일자이면 최근일자(수불일자)를 최종출고일자로 설정*/
        if (fnlOstrDtCompare > 0) {
            endOstrDate = dateFormat.format(procsDt);

            log.info("WsnaItemStockItemizationDvo STEP04 -> ", endOstrDate);
        } else if (fnlOstrDtCompare < 0) {
            endOstrDate = dateFormat.format(fnlOstrDt);
            log.info("WsnaItemStockItemizationDvo STEP05 -> ", endOstrDate);
        }

        /*작업구분이 입력(D)일때 */
        if (StringUtils.startsWith(dto.workDiv(), "D")) {
            /*===============================================================================
            구매입고 유형에 따른 처리
            ---------------------------------------------------------------------------------
            --구매입고(110)
            ---------------------------------------------------------------------------------
            --품목등급(구매입고는 정상품(A등급)만 발생)
            --시점재고 = 시점재고 - 구매입고
            --구매입고 = 구매입고 - 구매입고
            --입고예정 = 입고예정 + 구매입고
            --발주시 입고예정부수가 발생, 구매입고시 입고에정부수 PLUS 처리
            --입고예정부수가 0보다 적으면 ERROR 발생
            ===============================================================================*/
            if ("110".equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                cmpPlnQty = Integer.parseInt(varbDvo.getStrExpAGdQty()) + Integer.parseInt(dto.qty());

                //입고예정부수가 MINUS 발생 오류!
                BizAssert.isTrue(cmpPlnQty >= 0, "MSG_TXT_STR_EXP_MINUS");

                /*시점재고등급수량*/
                pitmStocGdQty = Integer.parseInt(varbDvo.getPitmStocAGdQty()) - Integer.parseInt(dto.qty());
                /*입고예정등급수량*/
                strExpGdQty = Integer.parseInt(varbDvo.getStrExpAGdQty()) + Integer.parseInt(dto.qty());
                dvo.setPitmStocAGdQty(String.valueOf(pitmStocGdQty));
                dvo.setStrExpAGdQty(String.valueOf(strExpGdQty));
                dvo.setFnlStrDt(sampleDate);
                dvo.setWareNo(dto.wareNo());
                dvo.setItmPdCd(dto.itmPdCd());

                processCount += mapper.updatePurchaseAGdStore(dvo);

            } else if ("110".equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                cmpPlnQty = Integer.parseInt(varbDvo.getStrExpBGdQty()) + Integer.parseInt(dto.qty());

                //입고예정부수가 MINUS 발생 오류!
                BizAssert.isTrue(cmpPlnQty >= 0, "MSG_TXT_STR_EXP_MINUS");

                pitmStocGdQty = Integer.parseInt(varbDvo.getPitmStocBGdQty()) - Integer.parseInt(dto.qty());
                /*입고예정등급수량*/
                strExpGdQty = Integer.parseInt(dvo.getStrExpBGdQty()) + Integer.parseInt(dto.qty());
                dvo.setPitmStocBGdQty(String.valueOf(pitmStocGdQty));
                dvo.setStrExpBGdQty(String.valueOf(strExpGdQty));
                dvo.setFnlStrDt(sampleDate);
                dvo.setWareNo(dto.wareNo());
                dvo.setItmPdCd(dto.itmPdCd());

                processCount += mapper.updatePurchaseBGdStore(dvo);

            }

            /*===============================================================================
            내부입고 유형에 따른 처리
            ---------------------------------------------------------------------------------
            --내부입고란?
                정상입고(121), 물량배정입고(122), 물량이동입고(123)
                반품입고내부(161)
            ---------------------------------------------------------------------------------
            --시점재고 = 시점재고 - 내부입고
            --내부입고 = 내부입고 - 내부입고
            --이동재고 = 이동재고 + 내부입고
            --내부입고는 출고시점에 이동재고부수가 발생, 내부입고시 이동재고부수 PLUS 처리
            --이동재고부수가 0보다 적으면 ERROR 발생
            ===============================================================================*/
            if (List.of("121", "122", "123", "161").contains(dto.iostTp()) && "A".equals(dto.itemGd())) {

                cmpBuffQty = Integer.parseInt(varbDvo.getMmtStocAGdQty()) + Integer.parseInt(dto.qty());

                //이동재고부수가 MINUS 발생 오류!
                BizAssert.isTrue(cmpBuffQty >= 0, "MSG_TXT_MMT_STOC_MINUS");

                pitmStocGdQty = Integer.parseInt(varbDvo.getPitmStocAGdQty()) - Integer.parseInt(dto.qty());
                dvo.setPitmStocAGdQty(String.valueOf(pitmStocGdQty));
                dvo.setFnlStrDt(sampleDate);
                dvo.setWareNo(dto.wareNo());
                dvo.setItmPdCd(dto.itmPdCd());

                processCount += mapper.updateInsiStoreAGdQty(dvo);

            } else if (List.of("121", "122", "123", "161").contains(dto.iostTp()) && "B".equals(dto.itemGd())) {

                cmpBuffQty = Integer.parseInt(varbDvo.getMmtStocBGdQty()) + Integer.parseInt(dto.qty());

                //이동재고부수가 MINUS 발생 오류!
                BizAssert.isTrue(cmpBuffQty >= 0, "MSG_TXT_MMT_STOC_MINUS");

                pitmStocGdQty = Integer.parseInt(varbDvo.getPitmStocBGdQty()) - Integer.parseInt(dto.qty());
                dvo.setPitmStocBGdQty(String.valueOf(pitmStocGdQty));
                dvo.setFnlStrDt(sampleDate);
                dvo.setWareNo(dto.wareNo());
                dvo.setItmPdCd(dto.itmPdCd());

                processCount += mapper.updateInsiStoreBGdQty(dvo);

            } else if (List.of("121", "122", "123", "161").contains(dto.iostTp()) && "E".equals(dto.itemGd())) {

                cmpBuffQty = Integer.parseInt(varbDvo.getMmtStocEGdQty()) + Integer.parseInt(dto.qty());

                //이동재고부수가 MINUS 발생 오류!
                BizAssert.isTrue(cmpBuffQty >= 0, "MSG_TXT_MMT_STOC_MINUS");

                pitmStocGdQty = Integer.parseInt(varbDvo.getPitmStocEGdQty()) - Integer.parseInt(dto.qty());
                dvo.setPitmStocEGdQty(String.valueOf(pitmStocGdQty));
                dvo.setFnlStrDt(sampleDate);
                dvo.setWareNo(dto.wareNo());
                dvo.setItmPdCd(dto.itmPdCd());

                processCount += mapper.updateInsiStoreEGdQty(dvo);

            } else if (List.of("121", "122", "123", "161").contains(dto.iostTp()) && "R".equals(dto.itemGd())) {
                cmpBuffQty = Integer.parseInt(varbDvo.getMmtStocRGdQty()) + Integer.parseInt(dto.qty());

                //이동재고부수가 MINUS 발생 오류!
                BizAssert.isTrue(cmpBuffQty >= 0, "MSG_TXT_MMT_STOC_MINUS");

                pitmStocGdQty = Integer.parseInt(varbDvo.getPitmStocRGdQty()) - Integer.parseInt(dto.qty());
                dvo.setPitmStocRGdQty(String.valueOf(pitmStocGdQty));
                dvo.setFnlStrDt(sampleDate);
                dvo.setWareNo(dto.wareNo());
                dvo.setItmPdCd(dto.itmPdCd());

                processCount += mapper.updateInsiStoreRGdQty(dvo);

            }
            /*입출고유형이 반품외부입고(162), 재고조정입고(181)*/
            if (List.of("162", "181").contains(dto.iostTp()) && "A".equals(dto.itemGd())) {

                pitmStocGdQty = Integer.parseInt(varbDvo.getPitmStocAGdQty()) - Integer.parseInt(dto.qty());

                dvo.setPitmStocAGdQty(String.valueOf(pitmStocGdQty));
                dvo.setWareNo(dto.wareNo());
                dvo.setItmPdCd(dto.itmPdCd());

                processCount += mapper.updateOtsdStrAGdQty(dvo);

            } else if (List.of("162", "181").contains(dto.iostTp()) && "B".equals(dto.itemGd())) {

                pitmStocGdQty = Integer.parseInt(varbDvo.getPitmStocBGdQty()) - Integer.parseInt(dto.qty());

                dvo.setPitmStocBGdQty(String.valueOf(pitmStocGdQty));
                dvo.setWareNo(dto.wareNo());
                dvo.setItmPdCd(dto.itmPdCd());

                processCount += mapper.updateOtsdStrBGdQty(dvo);

            } else if (List.of("162", "181").contains(dto.iostTp()) && "E".equals(dto.itemGd())) {

                pitmStocGdQty = Integer.parseInt(varbDvo.getPitmStocEGdQty()) - Integer.parseInt(dto.qty());

                dvo.setPitmStocEGdQty(String.valueOf(pitmStocGdQty));
                dvo.setWareNo(dto.wareNo());
                dvo.setItmPdCd(dto.itmPdCd());

                processCount += mapper.updateOtsdStrEGdQty(dvo);
            } else if (List.of("162", "181").contains(dto.iostTp()) && "R".equals(dto.itemGd())) {

                pitmStocGdQty = Integer.parseInt(varbDvo.getPitmStocRGdQty()) - Integer.parseInt(dto.qty());

                dvo.setPitmStocRGdQty(String.valueOf(pitmStocGdQty));
                dvo.setWareNo(dto.wareNo());
                dvo.setItmPdCd(dto.itmPdCd());

                processCount += mapper.updateOtsdStrRGdQty(dvo);
            }

            /*===============================================================================
            내부출고 유형에 따른 처리
            ---------------------------------------------------------------------------------
            --내부출고란?
                정상출고(221), 물량배정출고(222), 물량이동출고(223)
                반품출고내부(261)
            ---------------------------------------------------------------------------------
            --시점재고 = 시점재고 + 내부출고
            --내부출고 = 내부출고 - 내부출고
            --이동재고 = 이동재고 - 내부출고 (991에서 생성)
            --내부출고는 출고시점에 입고창고의 이동재고부수가 발생, 내부입고시 이동재고부수 MINUS 처리
            --시점재고부수가 0보다 적으면 ERROR 발생
            ===============================================================================*/
            /*입출고유형이 정상출고(221), 물량배정(222), 물량이동(223), 반품출고(내부)(261), 반품출고(외부)(262), 외부출고(판매)(211), 외부출고(폐기)(212), 외부출고(작업)(213),
            외부출고(기타)(217), 외부출고(리퍼완료)(218), 재고조정출고(281), 외부출고(기타)(292) 일경우 */
            if (List.of("221", "222", "223", "261", "262", "211", "212", "213", "217", "218", "281", "292")
                .contains(dto.iostTp()) && "A".equals(dto.itemGd())) {
                cmpOnQty = Integer.parseInt(varbDvo.getPitmStocAGdQty()) + Integer.parseInt(dto.qty());

                //시점재고부수가 MINUS 발생 오류 !
                BizAssert.isTrue(cmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                pitmStocGdQty = Integer.parseInt(varbDvo.getPitmStocAGdQty()) + Integer.parseInt(dto.qty());

                dvo.setPitmStocAGdQty(String.valueOf(pitmStocGdQty));
                dvo.setFnlOstrDt(endOstrDate);
                dvo.setWareNo(dto.wareNo());
                dvo.setItmPdCd(dto.itmPdCd());

                processCount += mapper.updateInsiOstrAGdQty(dvo);

            } else if (List.of("221", "222", "223", "261", "262", "211", "212", "213", "217", "218", "281", "292")
                .contains(dto.iostTp()) && "B".equals(dto.itemGd())) {

                cmpOnQty = Integer.parseInt(varbDvo.getPitmStocBGdQty()) + Integer.parseInt(dto.qty());

                //시점재고부수가 MINUS 발생 오류 !
                BizAssert.isTrue(cmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                pitmStocGdQty = Integer.parseInt(varbDvo.getPitmStocBGdQty()) + Integer.parseInt(dto.qty());

                dvo.setPitmStocBGdQty(String.valueOf(pitmStocGdQty));
                dvo.setFnlOstrDt(endOstrDate);
                dvo.setWareNo(dto.wareNo());
                dvo.setItmPdCd(dto.itmPdCd());

                processCount += mapper.updateInsiOstrBGdQty(dvo);

            } else if (List.of("221", "222", "223", "261", "262", "211", "212", "213", "217", "218", "281", "292")
                .contains(dto.iostTp()) && "E".equals(dto.itemGd())) {

                cmpOnQty = Integer.parseInt(varbDvo.getPitmStocEGdQty()) + Integer.parseInt(dto.qty());

                //시점재고부수가 MINUS 발생 오류 !
                BizAssert.isTrue(cmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                pitmStocGdQty = Integer.parseInt(varbDvo.getPitmStocEGdQty()) + Integer.parseInt(dto.qty());

                dvo.setPitmStocEGdQty(String.valueOf(pitmStocGdQty));
                dvo.setFnlOstrDt(endOstrDate);
                dvo.setWareNo(dto.wareNo());
                dvo.setItmPdCd(dto.itmPdCd());

                processCount += mapper.updateInsiOstrEGdQty(dvo);

            } else if (List.of("221", "222", "223", "261", "262", "211", "212", "213", "217", "218", "281", "292")
                .contains(dto.iostTp()) && "R".equals(dto.itemGd())) {

                cmpOnQty = Integer.parseInt(varbDvo.getPitmStocRGdQty()) + Integer.parseInt(dto.qty());

                //시점재고부수가 MINUS 발생 오류 !
                BizAssert.isTrue(cmpOnQty >= 0, "MSG_TXT_PITM_STOC_MINUS");

                pitmStocGdQty = Integer.parseInt(varbDvo.getPitmStocRGdQty()) + Integer.parseInt(dto.qty());

                dvo.setPitmStocRGdQty(String.valueOf(pitmStocGdQty));
                dvo.setFnlOstrDt(endOstrDate);
                dvo.setWareNo(dto.wareNo());
                dvo.setItmPdCd(dto.itmPdCd());

                processCount += mapper.updateInsiOstrRGdQty(dvo);

            }

            // TODO: 2023-03-10 월별품목재고내역 관리 서비스(W-SV-S-0086)의 월별품목재고내역 삭제 메소드를(saveMcbyItmStocIzDls) (개발진행 후 반영예정)
        }

        return processCount;
    }

    /**
     * @param dto :[{procsYm : 처리년월 , procsDt : 처리일자, wareDv : 창고구분 , wareNo : 창고번호 , wareMngtPrtnrNo : 창고파트너번호
     *            iostTp : 입출고유형 , workDiv : 작업구분, itmPdCd : 품목상품코드, mngtUnit : 관리단위
     *            itemGd : 상품등급 , qty : 수량 , mvcYn : ?? , freeYn : ??, chgYn : ??}]
     */
    public int saveStockMovement(WsnaItemStockItemizationDto.SaveReq dto) {

        int processCount = 0;
        int movementValue = 0;
        WsnaItemStockItemizationDvo dvo = new WsnaItemStockItemizationDvo();

        /*입력된 품목상품코드 정보로 COUNT 조회 처리*/
        int chkValue = mapper.selectCountItmPdCdInfo(dto);

        if (chkValue > 0) {
            WsnaItemStockItemizationDvo varbDvo = mapper.selectItmPdCdInformation(dto);
            if (StringUtils.startsWith(dto.workDiv(), "D")) {
                //입출고유형이 이동입고(991) 일경우
                if ("991".equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                    movementValue = Integer.parseInt(varbDvo.getMmtStocAGdQty()) - Integer.parseInt(dto.qty());

                    if (movementValue < 0) {
                        movementValue = 0;
                        dvo.setMmtStocAGdQty(String.valueOf(movementValue));
                    } else {
                        dvo.setMmtStocAGdQty(String.valueOf(movementValue));
                    }
                    dvo.setWareNo(dto.wareNo());
                    dvo.setItmPdCd(dto.itmPdCd());

                    processCount += mapper.updateMovementAGdStock(dvo);
                } else if ("991".equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                    movementValue = Integer.parseInt(varbDvo.getMmtStocBGdQty()) - Integer.parseInt(dto.qty());

                    if (movementValue < 0) {
                        movementValue = 0;
                        dvo.setMmtStocBGdQty(String.valueOf(movementValue));
                    } else {
                        dvo.setMmtStocBGdQty(String.valueOf(movementValue));
                    }
                    dvo.setWareNo(dto.wareNo());
                    dvo.setItmPdCd(dto.itmPdCd());

                    processCount += mapper.updateMovementBGdStock(dvo);
                } else if ("991".equals(dto.iostTp()) && "E".equals(dto.itemGd())) {
                    movementValue = Integer.parseInt(varbDvo.getMmtStocEGdQty()) - Integer.parseInt(dto.qty());

                    if (movementValue < 0) {
                        movementValue = 0;
                        dvo.setMmtStocEGdQty(String.valueOf(movementValue));
                    } else {
                        dvo.setMmtStocEGdQty(String.valueOf(movementValue));
                    }
                    dvo.setWareNo(dto.wareNo());
                    dvo.setItmPdCd(dto.itmPdCd());

                    processCount += mapper.updateMovementEGdStock(dvo);
                } else if ("991".equals(dto.iostTp()) && "R".equals(dto.itemGd())) {
                    movementValue = Integer.parseInt(varbDvo.getMmtStocRGdQty()) - Integer.parseInt(dto.qty());

                    if (movementValue < 0) {
                        movementValue = 0;
                        dvo.setMmtStocRGdQty(String.valueOf(movementValue));
                    } else {
                        dvo.setMmtStocRGdQty(String.valueOf(movementValue));
                    }
                    dvo.setWareNo(dto.wareNo());
                    dvo.setItmPdCd(dto.itmPdCd());

                    processCount += mapper.updateMovementRGdStock(dvo);
                }
            } else if (StringUtils.startsWith(dto.workDiv(), "A")) {
                if ("991".equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                    movementValue = Integer.parseInt(varbDvo.getMmtStocAGdQty()) + Integer.parseInt(dto.qty());

                    if (movementValue < 0) {
                        movementValue = 0;
                        dvo.setMmtStocAGdQty(String.valueOf(movementValue));
                    } else {
                        dvo.setMmtStocAGdQty(String.valueOf(movementValue));
                    }
                    dvo.setWareNo(dto.wareNo());
                    dvo.setItmPdCd(dto.itmPdCd());
                    processCount += mapper.updateMovementAGdStockQty(dvo);

                } else if ("991".equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                    movementValue = Integer.parseInt(varbDvo.getMmtStocBGdQty()) + Integer.parseInt(dto.qty());

                    if (movementValue < 0) {
                        movementValue = 0;
                        dvo.setMmtStocBGdQty(String.valueOf(movementValue));
                    } else {
                        dvo.setMmtStocBGdQty(String.valueOf(movementValue));
                    }
                    dvo.setWareNo(dto.wareNo());
                    dvo.setItmPdCd(dto.itmPdCd());
                    processCount += mapper.updateMovementBGdStockQty(dvo);

                } else if ("991".equals(dto.iostTp()) && "E".equals(dto.itemGd())) {
                    movementValue = Integer.parseInt(varbDvo.getMmtStocEGdQty()) + Integer.parseInt(dto.qty());

                    if (movementValue < 0) {
                        movementValue = 0;
                        dvo.setMmtStocEGdQty(String.valueOf(movementValue));
                    } else {
                        dvo.setMmtStocEGdQty(String.valueOf(movementValue));
                    }
                    dvo.setWareNo(dto.wareNo());
                    dvo.setItmPdCd(dto.itmPdCd());
                    processCount += mapper.updateMovementEGdStockQty(dvo);

                } else if ("991".equals(dto.iostTp()) && "R".equals(dto.itemGd())) {
                    movementValue = Integer.parseInt(varbDvo.getMmtStocRGdQty()) + Integer.parseInt(dto.qty());

                    if (movementValue < 0) {
                        movementValue = 0;
                        dvo.setMmtStocRGdQty(String.valueOf(movementValue));
                    } else {
                        dvo.setMmtStocRGdQty(String.valueOf(movementValue));
                    }
                    dvo.setWareNo(dto.wareNo());
                    dvo.setItmPdCd(dto.itmPdCd());
                    processCount += mapper.updateMovementRGdStockQty(dvo);

                }

            }

        } else {
            if (StringUtils.startsWith(dto.workDiv(), "A")) {

                if ("991".equals(dto.iostTp()) && "A".equals(dto.itemGd())) {
                    dvo.setWareNo(dto.wareNo());
                    dvo.setItmPdCd(dto.itmPdCd());
                    dvo.setMmtStocAGdQty(dto.qty());
                    processCount += mapper.insertMovementAGdQty(dvo);

                } else if ("991".equals(dto.iostTp()) && "B".equals(dto.itemGd())) {
                    dvo.setWareNo(dto.wareNo());
                    dvo.setItmPdCd(dto.itmPdCd());
                    dvo.setMmtStocBGdQty(dto.qty());
                    processCount += mapper.insertMovementBGdQty(dvo);

                } else if ("991".equals(dto.iostTp()) && "E".equals(dto.itemGd())) {
                    dvo.setWareNo(dto.wareNo());
                    dvo.setItmPdCd(dto.itmPdCd());
                    dvo.setMmtStocEGdQty(dto.qty());
                    processCount += mapper.insertMovementEGdQty(dvo);

                } else if ("991".equals(dto.iostTp()) && "R".equals(dto.itemGd())) {
                    dvo.setWareNo(dto.wareNo());
                    dvo.setItmPdCd(dto.itmPdCd());
                    dvo.setMmtStocRGdQty(dto.qty());
                    processCount += mapper.insertMovementRGdQty(dvo);

                }
            } else {
                throw new BizException("MSG_TXT_WK_DV_IS_NOT_A"); //작업구분 오류! (신규이므로 작업구분이 입력(A) 이여야 합니다.)
            }
        }

        return processCount;

    }
}
