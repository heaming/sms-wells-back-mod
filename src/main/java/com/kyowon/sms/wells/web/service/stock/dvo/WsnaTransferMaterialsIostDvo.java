package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-S-0092 물량이동 수불데이터 생성 서비스 입출고 Dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-06-09
 */

@Getter
@Setter
public class WsnaTransferMaterialsIostDvo {

    // 품목출고번호
    private String itmOstrNo;
    // 출고일련번호
    private int ostrSn;
    // 품목입고번호
    private String itmStrNo;
    // 입고일련번호
    private int strSn;
    // 출고요청번호
    private String ostrAkNo;
    // 출고요청일련번호
    private Integer ostrAkSn;

    // 품목상품코드
    private String itmPdCd;
    // 품목등급코드
    private String itmGdCd;
    // 품목종류코드
    private String itmKndCd;
    // 품목코드
    private String itmCd;
    // 관리단위코드
    private String mngtUnitCd;
    // 박스단위수량
    private Integer boxUnitQty;
    // 출고요청수량
    private int ostrAkQty;

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

    // 출고사유코드
    private String ostrRsonCd;

    // 비고내용
    private String rmkCn;
}
