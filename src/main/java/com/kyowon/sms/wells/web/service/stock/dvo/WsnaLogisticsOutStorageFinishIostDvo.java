package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 물류센터 출고완료 처리 서비스 입출고내역 dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-06-14
 */

@Getter
@Setter
public class WsnaLogisticsOutStorageFinishIostDvo {

    // 품목출고번호
    private String itmOstrNo;
    // 출고일련번호
    private Integer ostrSn;
    // 품목입고번호
    private String itmStrNo;
    // 입고일련번호
    private int strSn;
    // 출고요청번호
    private String ostrAkNo;
    // 출고요청일련번호
    private Integer ostrAkSn;
    // 출고요청유형코드
    private String ostrAkTpCd;

    // 출고대상 창고번호
    private String ostrOjWareNo;
    // 출고창고구분코드
    private String ostrWareDvCd;
    // 출고창고 파트너번호
    private String ostrPrtnrNo;
    // 출고창고 파트너 조직유형코드
    private String ostrPrtnrOgTpCd;
    // 출고유형코드
    private String ostrTpCd;
    // 출고일자
    private String ostrDt;

    // 입고대상 창고번호
    private String strOjWareNo;
    // 입고창고구분코드
    private String strWareDvCd;
    // 입고 창고 파트너번호
    private String strPrtnrNo;
    // 입고 창고 파트너 조직유형코드
    private String strPrtnrOgTpCd;
    // 입고유형코드
    private String strTpCd;

    // 품목상품코드
    private String itmPdCd;
    // 품목등급코드
    private String itmGdCd;
    // 품목종류코드
    private String itmKndCd;
    // 관리단위코드
    private String mngtUnitCd;
    // 박스단위수량
    private Integer boxUnitQty;
    // 입고수량
    private int strQty;
    // 입고희망일자
    private String strHopDt;

    // 비고내용
    private String rmkCn;
}
