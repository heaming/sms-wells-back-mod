package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-S-0086 품목재고내역 관리
 * </pre>
 *
 * @author songTaeSung
 * @since 2023.03.13
 */
@Getter
@Setter
public class WsnaItemStockItemizationDvo {

    String dspsYrmn; /*처리년월*/
    String dspsDt; /*처리일자*/
    String wareDv; /*창고구분*/
    //    String wareNo; /*창고번호*/
    String wareMngtPrtnrNo; /*창고관리파트너번호*/
    String stdlTyp; /*입출고유형*/
    String workDiv; /*작업구분*/
    String itemCd; /*상품코드*/
    String mngtUnit; /*관리단위*/
    String itemGd; /*상품등급*/
    String qty; /*수량*/
    /*TODO : 아래 3개항목 새로 정의 필요*/
    String mvcYn; /*??*/
    String freeYn; /*??*/
    String chgYn; /*??*/
    String wareNo; /* 창고번호 */
    String itmPdCd; /* 품목상품코드 */
    String itmCd; /* 품목코드 */
    String pitmStocAGdQty; /* 시점재고A등급수량 */
    String pitmStocEGdQty; /* 시점재고E등급수량 */
    String pitmStocRGdQty; /* 시점재고R등급수량 */
    String mmtStocAGdQty; /* 이동재고A등급수량 */
    String mmtStocEGdQty; /* 이동재고E등급수량 */
    String mmtStocRGdQty; /* 이동재고R등급수량 */
    String sftStocAGdQty; /* 안전재고A등급수량 */
    String sftStocEGdQty; /* 안전재고E등급수량 */
    String sftStocRGdQty; /* 안전재고R등급수량 */
    String strExpAGdQty; /* 입고예정A등급수량 */
    String strExpEGdQty; /* 입고예정E등급수량 */
    String strExpRGdQty; /* 입고예정R등급수량 */
    String freExpnStocAGdQty; /* 무료체험재고A등급수량 */
    String freExpnStocEGdQty; /* 무료체험재고E등급수량 */
    String freExpnStocRGdQty; /* 무료체험재고R등급수량 */
    String sftStocBGdQty; /* 안전재고B등급수량 */
    String pitmStocBGdQty; /* 시점재고B등급수량 */
    String strExpBGdQty; /* 입고예정B등급수량 */
    String mmtStocBGdQty; /* 이동재고B등급수량 */
    String freExpnStocBGdQty; /* 무료체험재고B등급수량 */
    String ostrStpYn; /* 출고중지여부 */
    String ostrStpRsonCd; /* 출고중지사유코드 */
    String ostrStpStrtdt; /* 출고중지시작일자 */
    String ostrStpExdt; /* 출고중지만기일자 */
    String fnlStrDt; /* 최종입고일자 */
    String fnlOstrDt; /* 최종출고일자 */
    String dtaDlYn; /* 데이터삭제여부 */

}
