package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-S-0092 물량이동 수불데이터 생성 서비스 Data Dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-06-09
 */

@Getter
@Setter
public class WsnaTransferMaterialsDataDvo {

    // 출고대상 창고번호
    private String ostrOjWareNo;
    // 출고창고구분코드
    private String ostrWareDvCd;
    // 출고창고 파트너번호
    private String ostrPrtnrNo;
    // 출고창고 파트너 조직유형코드
    private String ostrPrtnrOgTpCd;

    // 출고 상위 창고번호
    private String ostrHgrWareNo;
    // 출고 상위 창고구분코드
    private String ostrHgrDvCd;
    // 출고 상위 파트너번호
    private String ostrHgrPrtnrNo;
    // 출고 상위 파트너 조직유형코드
    private String ostrHgrPrtnrOgTpCd;

    // 품목상품코드
    private String itmPdCd;
    // 품목등급코드
    private String itmGdCd;
    // 출고요청수량
    private int ostrAkQty;

    // 입고대상 창고번호
    private String strOjWareNo;
    // 입고창고구분코드
    private String strWareDvCd;
    // 입고 창고 파트너번호
    private String strPrtnrNo;
    // 입고 창고 파트너 조직유형코드
    private String strPrtnrOgTpCd;

    // 입고 상위 창고번호
    private String strHgrWareNo;
    // 입고 상위 창고구분코드
    private String strHgrDvCd;
    // 입고 상위 파트너번호
    private String strHgrPrtnrNo;
    // 입고 상위 파트너 조직유형코드
    private String strHgrPrtnrOgTpCd;
}
