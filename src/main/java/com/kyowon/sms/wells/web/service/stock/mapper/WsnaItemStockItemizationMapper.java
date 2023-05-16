package com.kyowon.sms.wells.web.service.stock.mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaItemStockItemizationDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemStockItemizationDvo;
import org.apache.ibatis.annotations.Mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaItemStockItemizationDto.*;

/**
 * <pre>
 * W-SV-S-0086 품목재고내역 관리
 * </pre>
 *
 * @author songTaeSung
 * @since 2023.03.13
 */
@Mapper
public interface WsnaItemStockItemizationMapper {
    int selectCountItmPdCdInfo(SaveReq dto);

    WsnaItemStockItemizationDvo selectItmPdCdInformation(SaveReq dto);

    /**
     * 구매입고 - 입출고 유형 : 구매입고(110)
     */
    int updatePurchaseAStore(WsnaItemStockItemizationDvo dvo);

    /**
     * 구매입고 - 입출고 유형 : 구매입고(110)
     */
    int updatePurchaseBStore(WsnaItemStockItemizationDvo dvo);

    /**
     * 기타입고 - 입출고 유형 : 구매입고(117)
     */
    int updatePitmStocAGd(WsnaItemStockItemizationDvo dvo);

    /**
     * 기타입고 - 입출고 유형 : 구매입고(117)
     */
    int updatePitmStocEGd(WsnaItemStockItemizationDvo dvo);

    /**
     * 내부입고 - 입출고 유형 : 정상입고(121), 물량배정(122), 물량이동(123), 반품입고(161) A등급수량
     */
    int updateNormalStoreAQty(WsnaItemStockItemizationDvo dvo);

    /**
     * 내부입고 - 입출고 유형 : 정상입고(121), 물량배정(122), 물량이동(123), 반품입고(161) B등급수량
     */
    int updateNormalStoreBQty(WsnaItemStockItemizationDvo dvo);

    /**
     * 내부입고 - 입출고 유형 : 정상입고(121), 물량배정(122), 물량이동(123), 반품입고(161) E등급수량
     */
    int updateNormalStoreEQty(WsnaItemStockItemizationDvo dvo);

    /**
     * 내부입고 - 입출고 유형 : 정상입고(121), 물량배정(122), 물량이동(123), 반품입고(161) R등급수량
     */
    int updateNormalStoreRQty(WsnaItemStockItemizationDvo dvo);

    /**
     * 외부반품입고 - 입출고 유형 : 반품입고외부(162), 재고조정입고(181) A등급수량
     */
    int updateOtsdRtngdAGdStr(WsnaItemStockItemizationDvo dvo);

    /**
     * 외부반품입고 - 입출고 유형 : 반품입고외부(162), 재고조정입고(181) B등급수량
     */
    int updateOtsdRtngdBGdStr(WsnaItemStockItemizationDvo dvo);

    /**
     * 외부반품입고 - 입출고 유형 : 반품입고외부(162), 재고조정입고(181) E등급수량
     */
    int updateOtsdRtngdEGdStr(WsnaItemStockItemizationDvo dvo);

    /**
     * 외부반품입고 - 입출고 유형 : 반품입고외부(162), 재고조정입고(181) R등급수량
     */
    int updateOtsdRtngdRGdStr(WsnaItemStockItemizationDvo dvo);

    /**
     * 내부외부 출고  - 입출고 유형 : 정상출고(221), 물량배정(222), 물량이동(223), 반품출고(내부)(261), 반품출고(외부)(262), 외부출고(판매)(211), 외부출고(폐기)(212), 외부출고(작업)(213),
     *                 외부출고(기타)(217), 외부출고(리퍼완료)(218), 재고조정출고(281), 외부출고(기타)(292) A등급수량
     */
    int updateInsiOtsdAGdOstr(WsnaItemStockItemizationDvo dvo);

    /**
     * 내부외부 출고  - 입출고 유형 : 정상출고(221), 물량배정(222), 물량이동(223), 반품출고(내부)(261), 반품출고(외부)(262), 외부출고(판매)(211), 외부출고(폐기)(212), 외부출고(작업)(213),
     *                 외부출고(기타)(217), 외부출고(리퍼완료)(218), 재고조정출고(281), 외부출고(기타)(292) B등급수량
     */
    int updateInsiOtsdBGdOstr(WsnaItemStockItemizationDvo dvo);

    /**
     * 내부외부 출고  - 입출고 유형 : 정상출고(221), 물량배정(222), 물량이동(223), 반품출고(내부)(261), 반품출고(외부)(262), 외부출고(판매)(211), 외부출고(폐기)(212), 외부출고(작업)(213),
     *                 외부출고(기타)(217), 외부출고(리퍼완료)(218), 재고조정출고(281), 외부출고(기타)(292) E등급수량
     */
    int updateInsiOtsdEGdOstr(WsnaItemStockItemizationDvo dvo);

    /**
     * 내부외부 출고  - 입출고 유형 : 정상출고(221), 물량배정(222), 물량이동(223), 반품출고(내부)(261), 반품출고(외부)(262), 외부출고(판매)(211), 외부출고(폐기)(212), 외부출고(작업)(213),
     *                 외부출고(기타)(217), 외부출고(리퍼완료)(218), 재고조정출고(281), 외부출고(기타)(292) R등급수량
     */
    int updateInsiOtsdRGdOstr(WsnaItemStockItemizationDvo dvo);

    /**
     * 기타입고(117) , 외부 반품입고(162) A등급 INSERT 처리
     *
     * */
    /*작업유형이 등록 (V_WCOM_WRK_GB = 'A')이고 입출고유형이 기타입고(117), 외부 반품입고(162) 인경우 */
    int insertAGdSvstCstSvItmStocIz(WsnaItemStockItemizationDvo dvo);

    /**
     * 기타입고(117) , 외부 반품입고(162) B등급 INSERT 처리
     *
     * */
    int insertBGdSvstCstSvItmStocIz(WsnaItemStockItemizationDvo dvo);

    /**
     * 기타입고(117) , 외부 반품입고(162) E등급 INSERT 처리
     *
     * */
    int insertEGdSvstCstSvItmStocIz(WsnaItemStockItemizationDvo dvo);

    /**
     * 기타입고(117) , 외부 반품입고(162) R등급 INSERT 처리
     *
     * */
    int insertRGdSvstCstSvItmStocIz(WsnaItemStockItemizationDvo dvo);

    /**
     * 구매입고(110) A등급 UPDATE 처리
     *
     * */
    int updatePurchaseAGdStore(WsnaItemStockItemizationDvo dvo);

    /**
     * 구매입고(110) B등급 UPDATE 처리
     *
     * */
    int updatePurchaseBGdStore(WsnaItemStockItemizationDvo dvo);

    /**
     * 내부입고 - 입출고 유형 : 정상입고(121), 물량배정(122), 물량이동(123), 반품입고(161) A등급수량
     */
    int updateInsiStoreAGdQty(WsnaItemStockItemizationDvo dvo);

    /**
     * 내부입고 - 입출고 유형 : 정상입고(121), 물량배정(122), 물량이동(123), 반품입고(161) A등급수량
     */
    int updateInsiStoreBGdQty(WsnaItemStockItemizationDvo dvo);

    /**
     * 내부입고 - 입출고 유형 : 정상입고(121), 물량배정(122), 물량이동(123), 반품입고(161) A등급수량
     */
    int updateInsiStoreEGdQty(WsnaItemStockItemizationDvo dvo);

    /**
     * 내부입고 - 입출고 유형 : 정상입고(121), 물량배정(122), 물량이동(123), 반품입고(161) A등급수량
     */
    int updateInsiStoreRGdQty(WsnaItemStockItemizationDvo dvo);

    /**
     * 외부입고 - 입출고 유형 : 반품외부입고(162), 재고조정입고(181) A등급
     */
    int updateOtsdStrAGdQty(WsnaItemStockItemizationDvo dvo);

    /**
     * 외부입고 - 입출고 유형 : 반품외부입고(162), 재고조정입고(181) B등급
     */
    int updateOtsdStrBGdQty(WsnaItemStockItemizationDvo dvo);

    /**
     * 외부입고 - 입출고 유형 : 반품외부입고(162), 재고조정입고(181) E등급
     */
    int updateOtsdStrEGdQty(WsnaItemStockItemizationDvo dvo);

    /**
     * 외부입고 - 입출고 유형 : 반품외부입고(162), 재고조정입고(181) R등급
     */
    int updateOtsdStrRGdQty(WsnaItemStockItemizationDvo dvo);

    /**
     * 내부출고 - 입출고 유형 : 정상출고(221), 물량배정(222), 물량이동(223), 반품출고(내부)(261), 반품출고(외부)(262), 외부출고(판매)(211), 외부출고(폐기)(212), 외부출고(작업)(213),
     *             외부출고(기타)(217), 외부출고(리퍼완료)(218), 재고조정출고(281), 외부출고(기타)(292) A등급
     */
    int updateInsiOstrAGdQty(WsnaItemStockItemizationDvo dvo);

    /**
     * 내부출고 - 입출고 유형 : 정상출고(221), 물량배정(222), 물량이동(223), 반품출고(내부)(261), 반품출고(외부)(262), 외부출고(판매)(211), 외부출고(폐기)(212), 외부출고(작업)(213),
     *             외부출고(기타)(217), 외부출고(리퍼완료)(218), 재고조정출고(281), 외부출고(기타)(292) B등급
     */
    int updateInsiOstrBGdQty(WsnaItemStockItemizationDvo dvo);

    /**
     * 내부출고 - 입출고 유형 : 정상출고(221), 물량배정(222), 물량이동(223), 반품출고(내부)(261), 반품출고(외부)(262), 외부출고(판매)(211), 외부출고(폐기)(212), 외부출고(작업)(213),
     *             외부출고(기타)(217), 외부출고(리퍼완료)(218), 재고조정출고(281), 외부출고(기타)(292) E등급
     */
    int updateInsiOstrEGdQty(WsnaItemStockItemizationDvo dvo);

    /**
     * 내부출고 - 입출고 유형 : 정상출고(221), 물량배정(222), 물량이동(223), 반품출고(내부)(261), 반품출고(외부)(262), 외부출고(판매)(211), 외부출고(폐기)(212), 외부출고(작업)(213),
     *             외부출고(기타)(217), 외부출고(리퍼완료)(218), 재고조정출고(281), 외부출고(기타)(292) R등급
     */
    int updateInsiOstrRGdQty(WsnaItemStockItemizationDvo dvo);

    /**
     * 이동재고처리 - 입출고유형 : 이동입고(991) A등급(작업구분이 D일경우)
     */
    int updateMovementAGdStock(WsnaItemStockItemizationDvo dvo);

    /**
     * 이동재고처리 - 입출고유형 : 이동입고(991) B등급(작업구분이 D일경우)
     */
    int updateMovementBGdStock(WsnaItemStockItemizationDvo dvo);

    /**
     * 이동재고처리 - 입출고유형 : 이동입고(991) E등급(작업구분이 D일경우)
     */
    int updateMovementEGdStock(WsnaItemStockItemizationDvo dvo);

    /**
     * 이동재고처리 - 입출고유형 : 이동입고(991) R등급(작업구분이 D일경우)
     */
    int updateMovementRGdStock(WsnaItemStockItemizationDvo dvo);

    /**
     * 이동재고처리 - 입출고유형 : 이동입고(991) A등급(작업구분이 A일경우)
     */
    int updateMovementAGdStockQty(WsnaItemStockItemizationDvo dvo);

    /**
     * 이동재고처리 - 입출고유형 : 이동입고(991) B등급(작업구분이 A일경우)
     */
    int updateMovementBGdStockQty(WsnaItemStockItemizationDvo dvo);

    /**
     * 이동재고처리 - 입출고유형 : 이동입고(991) E등급(작업구분이 A일경우)
     */
    int updateMovementEGdStockQty(WsnaItemStockItemizationDvo dvo);

    /**
     * 이동재고처리 - 입출고유형 : 이동입고(991) E등급(작업구분이 A일경우)
     */
    int updateMovementRGdStockQty(WsnaItemStockItemizationDvo dvo);

    /**
     * 이동재고처리(조회결과가 0 일경우) - 입출고유형 : 이동입고(991) A등급(작업구분이 A일경우)
     */
    int insertMovementAGdQty(WsnaItemStockItemizationDvo dvo);

    /**
     * 이동재고처리(조회결과가 0 일경우) - 입출고유형 : 이동입고(991) A등급(작업구분이 A일경우)
     */
    int insertMovementBGdQty(WsnaItemStockItemizationDvo dvo);

    /**
     * 이동재고처리(조회결과가 0 일경우) - 입출고유형 : 이동입고(991) A등급(작업구분이 A일경우)
     */
    int insertMovementEGdQty(WsnaItemStockItemizationDvo dvo);

    /**
     * 이동재고처리(조회결과가 0 일경우) - 입출고유형 : 이동입고(991) A등급(작업구분이 A일경우)
     */
    int insertMovementRGdQty(WsnaItemStockItemizationDvo dvo);
}
