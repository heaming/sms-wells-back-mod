package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaMonthlyItemStocksDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMonthlyItemStocksDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMonthlyItemStocksReqDvo;

@Mapper
public interface WsnaMonthlyItemStocksMapper {
    @Deprecated
    int selectCountMcbyStock(WsnaMonthlyItemStocksDto.SaveReq dto);

    int selectCountMcbyStock(WsnaMonthlyItemStocksReqDvo dvo);

    @Deprecated
    WsnaMonthlyItemStocksDvo selectMcbyItmStocs(WsnaMonthlyItemStocksDto.SaveReq dto);

    WsnaMonthlyItemStocksDvo selectMcbyItmStocs(WsnaMonthlyItemStocksReqDvo dvo);

    /**
     * 입력받은 구분값이 A일경우 시점재고 A등급 , 이동재고 A등급 을 조회해온다.
     *
     * @param dto
     * @return
     */
    @Deprecated
    WsnaMonthlyItemStocksDvo selectPitmMmtStocAQty(WsnaMonthlyItemStocksDto.SaveReq dto);

    Optional<WsnaMonthlyItemStocksDvo> selectPitmMmtStocAQty(WsnaMonthlyItemStocksReqDvo dvo);

    /**
     * 입력받은 구분값이 B일경우 시점재고 B등급 , 이동재고 B등급 을 조회해온다.
     *
     * @param dto
     * @return
     */
    @Deprecated
    WsnaMonthlyItemStocksDvo selectPitmMmtStocBQty(WsnaMonthlyItemStocksDto.SaveReq dto);

    Optional<WsnaMonthlyItemStocksDvo> selectPitmMmtStocBQty(WsnaMonthlyItemStocksReqDvo dvo);

    /**
     * 입력받은 구분값이 E일경우 시점재고 E등급 , 이동재고 E등급 을 조회해온다.
     *
     * @param dto
     * @return
     */
    @Deprecated
    WsnaMonthlyItemStocksDvo selectPitmMmtStocEQty(WsnaMonthlyItemStocksDto.SaveReq dto);

    Optional<WsnaMonthlyItemStocksDvo> selectPitmMmtStocEQty(WsnaMonthlyItemStocksReqDvo dvo);

    /**
     * 입력받은 구분값이 A일경우 시점재고 R등급 , 이동재고 R등급 을 조회해온다.
     *
     * @param dto
     * @return
     */
    @Deprecated
    WsnaMonthlyItemStocksDvo selectPitmMmtStocRQty(WsnaMonthlyItemStocksDto.SaveReq dto);

    Optional<WsnaMonthlyItemStocksDvo> selectPitmMmtStocRQty(WsnaMonthlyItemStocksReqDvo dvo);

    /**
     * 구매입고 유형에 따른 처리
     * <p>
     * 구매입고수량
     */

    int updatePurchaseAStr(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 구매입고 유형에 따른 처리
     * <p>
     * 구매입고수량 B등급 수량
     */
    int updatePurchaseBStr(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 기타입고 유형에 따른 처리
     * <p>
     * 117:기타입고 A등급 수량
     */
    int updateEtcAStr(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 기타입고 유형에 따른 처리
     * <p>
     * 117:기타입고 E등급 수량
     */
    int updateEtcEStr(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 내부입고 유형에 따른 처리
     * 정상입고 A등급 수량
     */
    int updateNomOstrAQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 내부입고 유형에 따른 처리
     * 정상입고 B등급 수량
     */
    int updateNomOstrBQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 내부입고 유형에 따른 처리
     * 정상입고 E등급 수량
     */
    int updateNomOstrEQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 내부입고 유형에 따른 처리
     * 정상입고 R등급 수량
     */
    int updateNomOstrRQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 물량배정 A등급 수량
     */

    int updateQomAsnStrAGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 물량배정 B등급 수량
     */
    int updateQomAsnStrBGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 물량배정 E등급 수량
     */
    int updateQomAsnStrEGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 물량배정 R등급 수량
     */
    int updateQomAsnStrRGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 123 : 물량이동 A등급 수량
     */
    int updateQomMmtStrAGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 123 : 물량이동 B등급 수량
     */
    int updateQomMmtStrBGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 123 : 물량이동 E등급 수량
     */
    int updateQomMmtStrEGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 123 : 물량이동 R등급 수량
     */
    int updateQomMmtStrRGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 161: 반품입고(내부) A등급 수량
     */
    int updateRtngdStrInsiAgdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 161: 반품입고(내부) B등급 수량
     */
    int updateRtngdStrInsiBgdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 161: 반품입고(내부) E등급 수량
     */
    int updateRtngdStrInsiEgdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 161: 반품입고(내부) R등급 수량
     */
    int updateRtngdStrInsiRgdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 162: 반품입고(외부) A등급 수량
     */
    int updateRtngStrOtsdAgdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 162: 반품입고(외부) B등급 수량
     */
    int updateRtngStrOtsdBgdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 162: 반품입고(외부) E등급 수량
     */
    int updateRtngStrOtsdEgdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 162: 반품입고(외부) R등급 수량
     */
    int updateRtngStrOtsdRgdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 181: 기타입고A등급수량1 A등급 수량
     */
    int updateEtcStrAGdQty1(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 181: 기타입고B등급수량1 A등급 수량
     */
    int updateEtcStrBGdQty1(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 181: 기타입고E등급수량1 A등급 수량
     */
    int updateEtcStrEGdQty1(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 181: 기타입고R등급수량1 A등급 수량
     */
    int updateEtcStrRGdQty1(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 221: 정상출고 A등급 수량
     */
    int updateNomOstrAGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 221: 정상출고 B등급 수량
     */
    int updateNomOstrBGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 221: 정상출고 E등급 수량
     */
    int updateNomOstrEGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 221: 정상출고 R등급 수량
     */
    int updateNomOstrRGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 222: 물량배정출고A등급수량
     * QOM_ASN_OSTR_A_GD_QTY
     */
    int updateQomAsnOstrAGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 222: 물량배정출고B등급수량
     * QOM_ASN_OSTR_B_GD_QTY
     */
    int updateQomAsnOstrBGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 222: 물량배정출고E등급수량
     * QOM_ASN_OSTR_E_GD_QTY
     */
    int updateQomAsnOstrEGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 222: 물량배정출고R등급수량
     * QOM_ASN_OSTR_R_GD_QTY
     */
    int updateQomAsnOstrRGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 223: 물량이동출고A등급수량
     * QOM_MMT_OSTR_A_GD_QTY
     */
    int updateQomMmtOstrAGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 223: 물량이동출고B등급수량
     * QOM_MMT_OSTR_B_GD_QTY
     */
    int updateQomMmtOstrBGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 223: 물량이동출고E등급수량
     * QOM_MMT_OSTR_E_GD_QTY
     */
    int updateQomMmtOstrEGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 223: 물량배정출고R등급수량
     * QOM_MMT_OSTR_R_GD_QTY
     */
    int updateQomMmtOstrRGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 261: 반품출고내부A등급수량
     * RTNGD_OSTR_INSI_A_GD_QTY
     */
    int updateRtngdOstrInsiAGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 261: 반품출고내부B등급수량
     * RTNGD_OSTR_INSI_B_GD_QTY
     */
    int updateRtngdOstrInsiBGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 261: 반품출고내부E등급수량
     * RTNGD_OSTR_INSI_E_GD_QTY
     */
    int updateRtngdOstrInsiEGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 261: 반품출고내부R등급수량
     * RTNGD_OSTR_INSI_R_GD_QTY
     */
    int updateRtngdOstrInsiRGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 262: 반품출고외부A등급수량
     * RTNGD_OSTR_OTSD_A_GD_QTY
     */
    int updateRtngdOstrOtsdAGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 262: 반품출고외부B등급수량
     * RTNGD_OSTR_OTSD_B_GD_QTY
     */
    int updateRtngdOstrOtsdBGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 262: 반품출고외부E등급수량
     * RTNGD_OSTR_OTSD_E_GD_QTY
     */
    int updateRtngdOstrOtsdEGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 262: 반품출고외부R등급수량
     * RTNGD_OSTR_OTSD_R_GD_QTY
     */
    int updateRtngdOstrOtsdRGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 211: 판매출고수량
     * SELL_OSTR_QTY
     */
    int updateSellOstrQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 211: 판매출고B등급수량
     * SELL_OSTR_B_GD_QTY
     */
    int updateSellOstrBGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 212: 폐기출고A등급수량
     * DSU_OSTR_A_GD_QTY
     */
    int updateDsuOstrAGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 212: 폐기출고B등급수량
     * DSU_OSTR_B_GD_QTY
     */
    int updateDsuOstrBGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 212: 폐기출고E등급수량
     * DSU_OSTR_E_GD_QTY
     */
    int updateDsuOstrEGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 212: 폐기출고R등급수량
     * DSU_OSTR_R_GD_QTY
     */
    int updateDsuOstrRGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 213: 작업출고A등급수량
     * WK_OSTR_A_GD_QTY
     */
    int updateWkOstrAGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 213: 작업출고B등급수량
     * WK_OSTR_B_GD_QTY
     */
    int updateWkOstrBGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 213: 작업출고E등급수량
     * WK_OSTR_E_GD_QTY
     */
    int updateWkOstrEGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 213: 작업출고R등급수량
     * WK_OSTR_R_GD_QTY
     */
    int updateWkOstrRGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 217: 기타출고A등급수량
     * ETC_OSTR_A_GD_QTY
     */
    int updateEtcOstrAGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 217: 기타출고B등급수량
     * ETC_OSTR_B_GD_QTY
     */
    int updateEtcOstrBGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 217: 기타출고E등급수량
     * ETC_OSTR_E_GD_QTY
     */
    int updateEtcOstrEGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 217: 기타출고R등급수량
     * ETC_OSTR_R_GD_QTY
     */
    int updateEtcOstrRGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 218: 리퍼비시출고A등급수량
     * REFR_OSTR_A_GD_QTY
     */
    int updateRefrOstrAGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 218: 리퍼비시출고B등급수량
     * REFR_OSTR_B_GD_QTY
     */
    int updateRefrOstrBGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 218: 리퍼비시출고E등급수량
     * REFR_OSTR_E_GD_QTY
     */

    int updateRefrOstrEGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 218: 리퍼비시출고R등급수량
     * REFR_OSTR_R_GD_QTY
     */
    int updateRefrOstrRGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 281: 기타출고A등급수량1
     * ETC_OSTR_A_GD_QTY1
     */
    int updateEtcOstrAGdQty1(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 281: 기타출고B등급수량1
     * ETC_OSTR_B_GD_QTY1
     */
    int updateEtcOstrBGdQty1(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 281: 기타출고E등급수량1
     * ETC_OSTR_E_GD_QTY1
     */
    int updateEtcOstrEGdQty1(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 281: 기타출고R등급수량1
     * ETC_OSTR_R_GD_QTY1
     */
    int updateEtcOstrRGdQty1(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 292: 재고실사출고A등급수량
     * STOC_ACINSP_OSTR_A_GD_QTY
     */

    int updateStocAcinspOstrAGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 292: 재고실사출고B등급수량
     * STOC_ACINSP_OSTR_B_GD_QTY
     */
    int updateStocAcinspOstrBGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 292: 재고실사출고E등급수량
     * STOC_ACINSP_OSTR_E_GD_QTY
     */
    int updateStocAcinspOstrEGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 292: 재고실사출고R등급수량
     * STOC_ACINSP_OSTR_R_GD_QTY
     */
    int updateStocAcinspOstrRGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 110 : 구매입고 INSERT
     */
    int insertNewPrchStrQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 110 : 구매입고 B등급 수량 INSERT
     */
    int insertNewPrchsStrBGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 121 : 정상입고 A등급수량 INSERT
     */
    int insertNomStrAGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 121 : 정상입고 B등급수량 INSERT
     */
    int insertNomStrBGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 121 : 정상입고 E등급수량 INSERT
     */
    int insertNomStrEGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 121 : 정상입고 R등급수량 INSERT
     */
    int insertNomStrRGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 122 : 물량배정입고A등급수량 INSERT
     */
    int insertQomAsnStrAGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 122 : 물량배정입고B등급수량 INSERT
     */
    int insertQomAsnStrBGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 122 : 물량배정입고E등급수량 INSERT
     */
    int insertQomAsnStrEGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 122 : 물량배정입고R등급수량 INSERT
     */
    int insertQomAsnStrRGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 123 : 물량이동입고A등급수량 INSERT
     */

    int insertQomMmtStrAGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 123 : 물량이동입고B등급수량 INSERT
     */
    int insertQomMmtStrBGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 123 : 물량이동입고E등급수량 INSERT
     */
    int insertQomMmtStrEGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 123 : 물량이동입고R등급수량 INSERT
     */
    int insertQomMmtStrRGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 161 : 반품입고내부A등급수량 INSERT
     */
    int insertRtngdStrInsiAGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 161 : 반품입고내부B등급수량 INSERT
     */
    int insertRtngdStrInsiBGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 161 : 반품입고내부E등급수량 INSERT
     */
    int insertRtngdStrInsiEGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 161 : 반품입고내부R등급수량 INSERT
     */
    int insertRtngdStrInsiRGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 117 : 기타입고A등급수량 INSERT
     */
    int insertEtcStrAGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 117 : 기타입고B등급수량 INSERT
     */
    int insertEtcStrEGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 162 : 반품입고외부A등급수량 INSERT
     */
    int insertRtngdStrOtsdAGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 162 : 반품입고외부B등급수량 INSERT
     */
    int insertRtngdStrOtsdBGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 162 : 반품입고외부E등급수량 INSERT
     */
    int insertRtngdStrOtsdEGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 162 : 반품입고외부R등급수량 INSERT
     */
    int insertRtngdStrOtsdRGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 181 : 기타입고A등급수량1 INSERT
     * ETC_STR_A_GD_QTY1
     */
    int insertEtcStrAGdQty1(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 181 : 기타입고B등급수량1 INSERT
     * ETC_STR_B_GD_QTY1
     */
    int insertEtcStrBGdQty1(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 181 : 기타입고E등급수량1 INSERT
     * ETC_STR_E_GD_QTY1
     */
    int insertEtcStrEGdQty1(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 181 : 기타입고R등급수량1 INSERT
     * ETC_STR_R_GD_QTY1
     */
    int insertEtcStrRGdQty1(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 넘어온 파라미터로 창고관리파트너번호를 조회한다.
     *
     * @param dto baseYm : 기준년월 , wareNo : 창고번호
     * @return
     */
    WsnaMonthlyItemStocksDvo selectWareMngtPrtnrNo(WsnaMonthlyItemStocksDto.CrdovrReq dto);

    /**
     * 넘어온 파라미터로 이월작업에 필요한 데이터를 조회한다.
     *
     * @param dto baseYm : 기준년월 , wareNo : 창고번호, itmPdCd : 품목상품코드
     * @return
     */
    WsnaMonthlyItemStocksDvo selectMcbyItmStocCrdovrs(WsnaMonthlyItemStocksDto.CrdovrReq dto);

    int selectCountMcbyItmStoc(WsnaMonthlyItemStocksDto.CrdovrReq dto);

    /**
     * 앞에서 계산한 A등급 기초재고와 A등급 시점재고를 업데이트한다.
     */
    int updateCrdovrStocAGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 앞에서 계산한 E등급 기초재고와 E등급 시점재고를 업데이트한다.
     */
    int updateCrdovrStocEGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 앞에서 계산한 R등급 기초재고와 R등급 시점재고를 업데이트한다.
     */
    int updateCrdovrStocRGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 조회된 내역이 없을경우 신규로 A등급 수량에 대한 INSERT 진행
     */
    int insertCrdovrStocAGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 조회된 내역이 없을경우 신규로 E등급 수량에 대한 INSERT 진행
     */
    int insertCrdovrStocEGdQty(WsnaMonthlyItemStocksDvo dvo);

    /**
     * 조회된 내역이 없을경우 신규로 R등급 수량에 대한 INSERT 진행
     */
    int insertCrdovrStocRGdQty(WsnaMonthlyItemStocksDvo dvo);
}
