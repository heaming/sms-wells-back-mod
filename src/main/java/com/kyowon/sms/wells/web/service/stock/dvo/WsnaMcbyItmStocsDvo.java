package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 *  W-SV-S-0086 월별 품목재고내역 관리
 * </pre>
 *
 * @author SongTaeSong
 * @since 2023.04.05
 */

@Getter
@Setter
public class WsnaMcbyItmStocsDvo {
    String baseYm; /* 기준년월 */
    String wareNo; /* 창고번호 */
    String itmPdCd; /* 품목상품코드 */
    String wareMngtPrtnrNo; /* 창고관리파트너번호 */
    String ogTpCd; /* 조직유형코드 */
    String sftStocAGdQty; /* 안전재고A등급수량 */
    String sftStocBGdQty; /* 안전재고B등급수량 */
    String sftStocEGdQty; /* 안전재고E등급수량 */
    String sftStocRGdQty; /* 안전재고R등급수량 */
    String btdStocAGdQty; /* 기초재고A등급수량 */
    String btdStocBGdQty; /* 기초재고B등급수량 */
    String btdStocEGdQty; /* 기초재고E등급수량 */
    String btdStocRGdQty; /* 기초재고R등급수량 */
    String pitmStocAGdQty; /* 시점재고A등급수량 */
    String pitmStocEGdQty; /* 시점재고E등급수량 */
    String pitmStocRGdQty; /* 시점재고R등급수량 */
    String prchsStrQty; /* 구매입고수량 */
    String prchsStrBGdQty; /* 구매입고B등급수량 */
    String nomStrAGdQty; /* 정상입고A등급수량 */
    String nomStrEGdQty; /* 정상입고E등급수량 */
    String nomStrRGdQty; /* 정상입고R등급수량 */
    String qomAsnStrAGdQty; /* 물량배정입고A등급수량 */
    String qomAsnStrEGdQty; /* 물량배정입고E등급수량 */
    String qomAsnStrRGdQty; /* 물량배정입고R등급수량 */
    String qomMmtStrAGdQty; /* 물량이동입고A등급수량 */
    String qomMmtStrEGdQty; /* 물량이동입고E등급수량 */
    String qomMmtStrRGdQty; /* 물량이동입고R등급수량 */
    String rtngdStrInsiAGdQty; /* 반품입고내부A등급수량 */
    String rtngdStrInsiEGdQty; /* 반품입고내부E등급수량 */
    String rtngdStrInsiRGdQty; /* 반품입고내부R등급수량 */
    String rtngdStrOtsdAGdQty; /* 반품입고외부A등급수량 */
    String rtngdStrOtsdEGdQty; /* 반품입고외부E등급수량 */
    String rtngdStrOtsdRGdQty; /* 반품입고외부R등급수량 */
    String etcStrAGdQty; /* 기타입고A등급수량 */
    String etcStrEGdQty; /* 기타입고E등급수량 */
    String etcStrAGdQty1; /* 기타입고A등급수량1 */
    String etcStrBGdQty1; /* 기타입고B등급수량1 */
    String etcStrEGdQty1; /* 기타입고E등급수량1 */
    String etcStrRGdQty1; /* 기타입고R등급수량1 */
    String stocAcinspStrAGdQty; /* 재고실사입고A등급수량 */
    String stocAcinspStrEGdQty; /* 재고실사입고E등급수량 */
    String stocAcinspStrRGdQty; /* 재고실사입고R등급수량 */
    String nomOstrAGdQty; /* 정상출고A등급수량 */
    String nomOstrEGdQty; /* 정상출고E등급수량 */
    String nomOstrRGdQty; /* 정상출고R등급수량 */
    String qomAsnOstrAGdQty; /* 물량배정출고A등급수량 */
    String qomAsnOstrEGdQty; /* 물량배정출고E등급수량 */
    String qomAsnOstrRGdQty; /* 물량배정출고R등급수량 */
    String qomMmtOstrAGdQty; /* 물량이동출고A등급수량 */
    String qomMmtOstrEGdQty; /* 물량이동출고E등급수량 */
    String qomMmtOstrRGdQty; /* 물량이동출고R등급수량 */
    String rtngdOstrInsiAGdQty; /* 반품출고내부A등급수량 */
    String rtngdOstrInsiEGdQty; /* 반품출고내부E등급수량 */
    String rtngdOstrInsiRGdQty; /* 반품출고내부R등급수량 */
    String rtngdOstrOtsdAGdQty; /* 반품출고외부A등급수량 */
    String rtngdOstrOtsdEGdQty; /* 반품출고외부E등급수량 */
    String rtngdOstrOtsdRGdQty; /* 반품출고외부R등급수량 */
    String sellOstrQty; /* 판매출고수량 */
    String sellOstrBGdQty; /* 판매출고B등급수량 */
    String dsuOstrAGdQty; /* 폐기출고A등급수량 */
    String dsuOstrEGdQty; /* 폐기출고E등급수량 */
    String dsuOstrRGdQty; /* 폐기출고R등급수량 */
    String wkOstrAGdQty; /* 작업출고A등급수량 */
    String wkOstrEGdQty; /* 작업출고E등급수량 */
    String wkOstrRGdQty; /* 작업출고R등급수량 */
    String etcOstrAGdQty; /* 기타출고A등급수량 */
    String etcOstrEGdQty; /* 기타출고E등급수량 */
    String etcOstrRGdQty; /* 기타출고R등급수량 */
    String etcOstrAGdQty1; /* 기타출고A등급수량1 */
    String etcOstrEGdQty1; /* 기타출고E등급수량1 */
    String etcOstrRGdQty1; /* 기타출고R등급수량1 */
    String stocAcinspOstrAGdQty; /* 재고실사출고A등급수량 */
    String stocAcinspOstrEGdQty; /* 재고실사출고E등급수량 */
    String stocAcinspOstrRGdQty; /* 재고실사출고R등급수량 */
    String stocStatNomBadCtrQty; /* 재고상태정상불량조정수량 */
    String stocStatBadNomCtrQty; /* 재고상태불량정상조정수량 */
    String stocAcinspAGdQty; /* 재고실사A등급수량 */
    String stocAcinspEGdQty; /* 재고실사E등급수량 */
    String stocAcinspRGdQty; /* 재고실사R등급수량 */
    String mmtStocAGdQty; /* 이동재고A등급수량 */
    String mmtStocEGdQty; /* 이동재고E등급수량 */
    String mmtStocRGdQty; /* 이동재고R등급수량 */
    String freExpnStocAGdQty; /* 무료체험재고A등급수량 */
    String freExpnStocEGdQty; /* 무료체험재고E등급수량 */
    String freExpnStocRGdQty; /* 무료체험재고R등급수량 */
    String pitmStocBGdQty; /* 시점재고B등급수량 */
    String nomStrBGdQty; /* 정상입고B등급수량 */
    String qomAsnStrBGdQty; /* 물량배정입고B등급수량 */
    String qomMmtStrBGdQty; /* 물량이동입고B등급수량 */
    String rtngdStrInsiBGdQty; /* 반품입고내부B등급수량 */
    String rtngdStrOtsdBGdQty; /* 반품입고외부B등급수량 */
    String stocAcinspStrBGdQty; /* 재고실사입고B등급수량 */
    String nomOstrBGdQty; /* 정상출고B등급수량 */
    String qomAsnOstrBGdQty; /* 물량배정출고B등급수량 */
    String qomMmtOstrBGdQty; /* 물량이동출고B등급수량 */
    String rtngdOstrInsiBGdQty; /* 반품출고내부B등급수량 */
    String rtngdOstrOtsdBGdQty; /* 반품출고외부B등급수량 */
    String dsuOstrBGdQty; /* 폐기출고B등급수량 */
    String wkOstrBGdQty; /* 작업출고B등급수량 */
    String etcOstrBGdQty; /* 기타출고B등급수량 */
    String etcOstrBGdQty1; /* 기타출고B등급수량1 */
    String stocAcinspOstrBGdQty; /* 재고실사출고B등급수량 */
    String stocAcinspBGdQty; /* 재고실사B등급수량 */
    String mmtStocBGdQty; /* 이동재고B등급수량 */
    String freExpnStocBGdQty; /* 무료체험재고B등급수량 */
    String refrOstrAGdQty; /* 리퍼출고A등급수량 */
    String refrOstrBGdQty; /* 리퍼출고B등급수량 */
    String refrOstrEGdQty; /* 리퍼출고E등급수량 */
    String refrOstrRGdQty; /* 리퍼출고R등급수량 */
    String stocAcinspDt; /* 재고실사일자 */
    String dtaDlYn; /* 데이터삭제여부 */
}
