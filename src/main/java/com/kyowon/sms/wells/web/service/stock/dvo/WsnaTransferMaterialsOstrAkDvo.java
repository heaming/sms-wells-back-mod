package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-S-0092 물량이동 수불데이터 생성 서비스 출고요청 Dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-06-09
 */

@Getter
@Setter
public class WsnaTransferMaterialsOstrAkDvo {

    // 출고요청번호
    private String ostrAkNo;
    // 출고요청일련번호
    private int ostrAkSn;
    // 출고요청유형코드
    private String ostrAkTpCd;

    // 출고대상창고번호
    private String ostrOjWareNo;
    // 출고대상창고구분코드
    private String ostrOjWareDvCd;
    // 출고창고관리파트너번호
    private String ostrWareMngtPrtnrNo;
    // 출고창고관리파트너조직유형코드
    private String ostrWareMngtPrtnrOgTpCd;

    // 입고대상창고번호
    private String strOjWareNo;
    // 출고요청창고구분코드
    private String ostrAkWareDvCd;
    // 창고관리파트너번호
    private String wareMngtPrtnrNo;
    // 창고관리파트너조직유형코드
    private String wareMngtPrtnrOgTpCd;

    // 입고수량
    private int strQty;
    // 품목종류코드
    private String itmKndCd;
    // 품목상품코드
    private String itmPdCd;
    // 품목코드
    private String itmCd;
    // 품목등급코드
    private String itmGdCd;
    // 관리단위코드
    private String mngtUnitCd;
    // 박스단위수량
    private Integer boxUnitQty;

    // 비고내용
    private String rmkCn;

}
