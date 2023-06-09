package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-S-0092 물량이동 수불데이터 생성 서비스 상위처리 Dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-06-09
 */

@Getter
@Setter
public class WsnaTransferMaterialsHgrDvo {

    // 품목입고번호
    private String itmStrNo;
    // 입고유형코드
    private String strTpCd;
    // 품목등급코드
    private String itmGdCd;

    // 출고 상위 창고번호
    private String ostrHgrWareNo;
    // 출고 상위 창고구분코드
    private String ostrHgrDvCd;
    // 출고 상위 파트너번호
    private String ostrHgrPrtnrNo;
    // 출고 상위 파트너 조직유형코드
    private String ostrHgrPrtnrOgTpCd;

    // 입고 상위 창고번호
    private String strHgrWareNo;
    // 입고 상위 창고구분코드
    private String strHgrDvCd;
    // 입고 상위 파트너번호
    private String strHgrPrtnrNo;
    // 입고 상위 파트너 조직유형코드
    private String strHgrPrtnrOgTpCd;
}
