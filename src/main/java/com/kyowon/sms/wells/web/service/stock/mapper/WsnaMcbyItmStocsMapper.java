package com.kyowon.sms.wells.web.service.stock.mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaMcbyItmStocsDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMcbyItmStocsDvo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WsnaMcbyItmStocsMapper {
    int selectCountMcbyStock(WsnaMcbyItmStocsDto.SaveReq dto);

    WsnaMcbyItmStocsDvo selectMcbyItmStocs(WsnaMcbyItmStocsDto.SaveReq dto);

    /**
     * 입력받은 구분값이 A일경우 시점재고 A등급 , 이동재고 A등급 을 조회해온다.
     *
     * @param dto
     * @return
     */
    WsnaMcbyItmStocsDvo selectPitmMmtStocAQty(WsnaMcbyItmStocsDto.SaveReq dto);

    /**
     * 입력받은 구분값이 B일경우 시점재고 B등급 , 이동재고 B등급 을 조회해온다.
     *
     * @param dto
     * @return
     */
    WsnaMcbyItmStocsDvo selectPitmMmtStocBQty(WsnaMcbyItmStocsDto.SaveReq dto);

    /**
     * 입력받은 구분값이 E일경우 시점재고 E등급 , 이동재고 E등급 을 조회해온다.
     *
     * @param dto
     * @return
     */
    WsnaMcbyItmStocsDvo selectPitmMmtStocEQty(WsnaMcbyItmStocsDto.SaveReq dto);

    /**
     * 입력받은 구분값이 A일경우 시점재고 R등급 , 이동재고 R등급 을 조회해온다.
     *
     * @param dto
     * @return
     */
    WsnaMcbyItmStocsDvo selectPitmMmtStocRQty(WsnaMcbyItmStocsDto.SaveReq dto);

    /**
     * 구매입고 유형에 따른 처리
     * <p>
     * 구매입고수량
     */

    int updatePurchaseAStr(WsnaMcbyItmStocsDvo dvo);

    /**
     * 구매입고 유형에 따른 처리
     * <p>
     * 구매입고수량 B등급 수량
     */
    int updatePurchaseBStr(WsnaMcbyItmStocsDvo dvo);

    /**
     * 기타입고 유형에 따른 처리
     * <p>
     * 117:기타입고 A등급 수량
     */
    int updateEtcAStr(WsnaMcbyItmStocsDvo dvo);

    /**
     * 기타입고 유형에 따른 처리
     * <p>
     * 117:기타입고 E등급 수량
     */
    int updateEtcEStr(WsnaMcbyItmStocsDvo dvo);

    /**
     * 내부입고 유형에 따른 처리
     * 정상입고 A등급 수량
     */
    int updateNomOstrAQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 내부입고 유형에 따른 처리
     * 정상입고 B등급 수량
     */
    int updateNomOstrBQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 내부입고 유형에 따른 처리
     * 정상입고 E등급 수량
     */
    int updateNomOstrEQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 내부입고 유형에 따른 처리
     * 정상입고 R등급 수량
     */
    int updateNomOstrRQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 물량배정 A등급 수량
     */

    int updateQomAsnStrAGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 물량배정 B등급 수량
     */
    int updateQomAsnStrBGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 물량배정 E등급 수량
     */
    int updateQomAsnStrEGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 물량배정 R등급 수량
     */
    int updateQomAsnStrRGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 123 : 물량이동 A등급 수량
     */
    int updateQomMmtStrAGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 123 : 물량이동 B등급 수량
     */
    int updateQomMmtStrBGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 123 : 물량이동 E등급 수량
     */
    int updateQomMmtStrEGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 123 : 물량이동 R등급 수량
     */
    int updateQomMmtStrRGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 161: 반품입고(내부) A등급 수량
     */
    int updateRtngdStrInsiAgdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 161: 반품입고(내부) B등급 수량
     */
    int updateRtngdStrInsiBgdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 161: 반품입고(내부) E등급 수량
     */
    int updateRtngdStrInsiEgdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 161: 반품입고(내부) R등급 수량
     */
    int updateRtngdStrInsiRgdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 162: 반품입고(외부) A등급 수량
     */
    int updateRtngStrOtsdAgdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 162: 반품입고(외부) B등급 수량
     */
    int updateRtngStrOtsdBgdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 162: 반품입고(외부) E등급 수량
     */
    int updateRtngStrOtsdEgdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 162: 반품입고(외부) R등급 수량
     */
    int updateRtngStrOtsdRgdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 181: 기타입고A등급수량1 A등급 수량
     */
    int updateEtcStrAGdQty1(WsnaMcbyItmStocsDvo dvo);

    /**
     * 181: 기타입고B등급수량1 A등급 수량
     */
    int updateEtcStrBGdQty1(WsnaMcbyItmStocsDvo dvo);

    /**
     * 181: 기타입고E등급수량1 A등급 수량
     */
    int updateEtcStrEGdQty1(WsnaMcbyItmStocsDvo dvo);

    /**
     * 181: 기타입고R등급수량1 A등급 수량
     */
    int updateEtcStrRGdQty1(WsnaMcbyItmStocsDvo dvo);

    /**
     * 221: 정상출고 A등급 수량
     */
    int updateNomOstrAGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 221: 정상출고 B등급 수량
     */
    int updateNomOstrBGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 221: 정상출고 E등급 수량
     */
    int updateNomOstrEGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 221: 정상출고 R등급 수량
     */
    int updateNomOstrRGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 222: 물량배정출고A등급수량
     * QOM_ASN_OSTR_A_GD_QTY
     */
    int updateQomAsnOstrAGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 222: 물량배정출고B등급수량
     * QOM_ASN_OSTR_B_GD_QTY
     */
    int updateQomAsnOstrBGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 222: 물량배정출고E등급수량
     * QOM_ASN_OSTR_E_GD_QTY
     */
    int updateQomAsnOstrEGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 222: 물량배정출고R등급수량
     * QOM_ASN_OSTR_R_GD_QTY
     */
    int updateQomAsnOstrRGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 223: 물량이동출고A등급수량
     * QOM_MMT_OSTR_A_GD_QTY
     */
    int updateQomMmtOstrAGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 223: 물량이동출고B등급수량
     * QOM_MMT_OSTR_B_GD_QTY
     */
    int updateQomMmtOstrBGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 223: 물량이동출고E등급수량
     * QOM_MMT_OSTR_E_GD_QTY
     */
    int updateQomMmtOstrEGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 223: 물량배정출고R등급수량
     * QOM_MMT_OSTR_R_GD_QTY
     */
    int updateQomMmtOstrRGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 261: 반품출고내부A등급수량
     * RTNGD_OSTR_INSI_A_GD_QTY
     */
    int updateRtngdOstrInsiAGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 261: 반품출고내부B등급수량
     * RTNGD_OSTR_INSI_B_GD_QTY
     */
    int updateRtngdOstrInsiBGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 261: 반품출고내부E등급수량
     * RTNGD_OSTR_INSI_E_GD_QTY
     */
    int updateRtngdOstrInsiEGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 261: 반품출고내부R등급수량
     * RTNGD_OSTR_INSI_R_GD_QTY
     */
    int updateRtngdOstrInsiRGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 262: 반품출고외부A등급수량
     * RTNGD_OSTR_OTSD_A_GD_QTY
     */
    int updateRtngdOstrOtsdAGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 262: 반품출고외부B등급수량
     * RTNGD_OSTR_OTSD_B_GD_QTY
     */
    int updateRtngdOstrOtsdBGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 262: 반품출고외부E등급수량
     * RTNGD_OSTR_OTSD_E_GD_QTY
     */
    int updateRtngdOstrOtsdEGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 262: 반품출고외부R등급수량
     * RTNGD_OSTR_OTSD_R_GD_QTY
     */
    int updateRtngdOstrOtsdRGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 211: 판매출고수량
     * SELL_OSTR_QTY
     */
    int updateSellOstrQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 211: 판매출고B등급수량
     * SELL_OSTR_B_GD_QTY
     */
    int updateSellOstrBGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 212: 폐기출고A등급수량
     * DSU_OSTR_A_GD_QTY
     */
    int updateDsuOstrAGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 212: 폐기출고B등급수량
     * DSU_OSTR_B_GD_QTY
     */
    int updateDsuOstrBGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 212: 폐기출고E등급수량
     * DSU_OSTR_E_GD_QTY
     */
    int updateDsuOstrEGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 212: 폐기출고R등급수량
     * DSU_OSTR_R_GD_QTY
     */
    int updateDsuOstrRGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 213: 작업출고A등급수량
     * WK_OSTR_A_GD_QTY
     */
    int updateWkOstrAGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 213: 작업출고B등급수량
     * WK_OSTR_B_GD_QTY
     */
    int updateWkOstrBGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 213: 작업출고E등급수량
     * WK_OSTR_E_GD_QTY
     */
    int updateWkOstrEGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 213: 작업출고R등급수량
     * WK_OSTR_R_GD_QTY
     */
    int updateWkOstrRGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 217: 기타출고A등급수량
     * ETC_OSTR_A_GD_QTY
     */
    int updateEtcOstrAGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 217: 기타출고B등급수량
     * ETC_OSTR_B_GD_QTY
     */
    int updateEtcOstrBGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 217: 기타출고E등급수량
     * ETC_OSTR_E_GD_QTY
     */
    int updateEtcOstrEGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 217: 기타출고R등급수량
     * ETC_OSTR_R_GD_QTY
     */
    int updateEtcOstrRGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 218: 리퍼비시출고A등급수량
     * REFR_OSTR_A_GD_QTY
     */
    int updateRefrOstrAGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 218: 리퍼비시출고B등급수량
     * REFR_OSTR_B_GD_QTY
     */
    int updateRefrOstrBGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 218: 리퍼비시출고E등급수량
     * REFR_OSTR_E_GD_QTY
     */

    int updateRefrOstrEGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 218: 리퍼비시출고R등급수량
     * REFR_OSTR_R_GD_QTY
     */
    int updateRefrOstrRGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 281: 기타출고A등급수량1
     * ETC_OSTR_A_GD_QTY1
     */
    int updateEtcOstrAGdQty1(WsnaMcbyItmStocsDvo dvo);

    /**
     * 281: 기타출고B등급수량1
     * ETC_OSTR_B_GD_QTY1
     */
    int updateEtcOstrBGdQty1(WsnaMcbyItmStocsDvo dvo);

    /**
     * 281: 기타출고E등급수량1
     * ETC_OSTR_E_GD_QTY1
     */
    int updateEtcOstrEGdQty1(WsnaMcbyItmStocsDvo dvo);

    /**
     * 281: 기타출고R등급수량1
     * ETC_OSTR_R_GD_QTY1
     */
    int updateEtcOstrRGdQty1(WsnaMcbyItmStocsDvo dvo);

    /**
     * 292: 재고실사출고A등급수량
     * STOC_ACINSP_OSTR_A_GD_QTY
     */

    int updateStocAcinspOstrAGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 292: 재고실사출고B등급수량
     * STOC_ACINSP_OSTR_B_GD_QTY
     */
    int updateStocAcinspOstrBGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 292: 재고실사출고E등급수량
     * STOC_ACINSP_OSTR_E_GD_QTY
     */
    int updateStocAcinspOstrEGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 292: 재고실사출고R등급수량
     * STOC_ACINSP_OSTR_R_GD_QTY
     */
    int updateStocAcinspOstrRGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 110 : 구매입고 INSERT
     */
    int insertNewPrchStrQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 110 : 구매입고 B등급 수량 INSERT
     */
    int insertNewPrchsStrBGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 121 : 정상입고 A등급수량 INSERT
     */
    int insertNomStrAGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 121 : 정상입고 B등급수량 INSERT
     */
    int insertNomStrBGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 121 : 정상입고 E등급수량 INSERT
     */
    int insertNomStrEGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 121 : 정상입고 R등급수량 INSERT
     */
    int insertNomStrRGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 122 : 물량배정입고A등급수량 INSERT
     */
    int insertQomAsnStrAGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 122 : 물량배정입고B등급수량 INSERT
     */
    int insertQomAsnStrBGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 122 : 물량배정입고E등급수량 INSERT
     */
    int insertQomAsnStrEGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 122 : 물량배정입고R등급수량 INSERT
     */
    int insertQomAsnStrRGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 123 : 물량이동입고A등급수량 INSERT
     */

    int insertQomMmtStrAGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 123 : 물량이동입고B등급수량 INSERT
     */
    int insertQomMmtStrBGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 123 : 물량이동입고E등급수량 INSERT
     */
    int insertQomMmtStrEGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 123 : 물량이동입고R등급수량 INSERT
     */
    int insertQomMmtStrRGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 161 : 반품입고내부A등급수량 INSERT
     */
    int insertRtngdStrInsiAGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 161 : 반품입고내부B등급수량 INSERT
     */
    int insertRtngdStrInsiBGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 161 : 반품입고내부E등급수량 INSERT
     */
    int insertRtngdStrInsiEGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 161 : 반품입고내부R등급수량 INSERT
     */
    int insertRtngdStrInsiRGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 117 : 기타입고A등급수량 INSERT
     */
    int insertEtcStrAGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 117 : 기타입고B등급수량 INSERT
     */
    int insertEtcStrEGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 162 : 반품입고외부A등급수량 INSERT
     */
    int insertRtngdStrOtsdAGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 162 : 반품입고외부B등급수량 INSERT
     */
    int insertRtngdStrOtsdBGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 162 : 반품입고외부E등급수량 INSERT
     */
    int insertRtngdStrOtsdEGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 162 : 반품입고외부R등급수량 INSERT
     */
    int insertRtngdStrOtsdRGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 181 : 기타입고A등급수량1 INSERT
     * ETC_STR_A_GD_QTY1
     */
    int insertEtcStrAGdQty1(WsnaMcbyItmStocsDvo dvo);

    /**
     * 181 : 기타입고B등급수량1 INSERT
     * ETC_STR_B_GD_QTY1
     */
    int insertEtcStrBGdQty1(WsnaMcbyItmStocsDvo dvo);

    /**
     * 181 : 기타입고E등급수량1 INSERT
     * ETC_STR_E_GD_QTY1
     */
    int insertEtcStrEGdQty1(WsnaMcbyItmStocsDvo dvo);

    /**
     * 181 : 기타입고R등급수량1 INSERT
     * ETC_STR_R_GD_QTY1
     */
    int insertEtcStrRGdQty1(WsnaMcbyItmStocsDvo dvo);

    /**
     * 넘어온 파라미터로 창고관리파트너번호를 조회한다.
     *
     * @param dto baseYm : 기준년월 , wareNo : 창고번호
     * @return
     */
    WsnaMcbyItmStocsDvo selectWareMngtPrtnrNo(WsnaMcbyItmStocsDto.CrdovrReq dto);

    /**
     * 넘어온 파라미터로 이월작업에 필요한 데이터를 조회한다.
     *
     * @param dto baseYm : 기준년월 , wareNo : 창고번호, itmPdCd : 품목상품코드
     * @return
     */
    WsnaMcbyItmStocsDvo selectMcbyItmStocCrdovrs(WsnaMcbyItmStocsDto.CrdovrReq dto);

    int selectCountMcbyItmStoc(WsnaMcbyItmStocsDto.CrdovrReq dto);

    /**
     * 앞에서 계산한 A등급 기초재고와 A등급 시점재고를 업데이트한다.
     */
    int updateCrdovrStocAGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 앞에서 계산한 E등급 기초재고와 E등급 시점재고를 업데이트한다.
     */
    int updateCrdovrStocEGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 앞에서 계산한 R등급 기초재고와 R등급 시점재고를 업데이트한다.
     */
    int updateCrdovrStocRGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 조회된 내역이 없을경우 신규로 A등급 수량에 대한 INSERT 진행
     */
    int insertCrdovrStocAGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 조회된 내역이 없을경우 신규로 E등급 수량에 대한 INSERT 진행
     */
    int insertCrdovrStocEGdQty(WsnaMcbyItmStocsDvo dvo);

    /**
     * 조회된 내역이 없을경우 신규로 R등급 수량에 대한 INSERT 진행
     */
    int insertCrdovrStocRGdQty(WsnaMcbyItmStocsDvo dvo);
}
