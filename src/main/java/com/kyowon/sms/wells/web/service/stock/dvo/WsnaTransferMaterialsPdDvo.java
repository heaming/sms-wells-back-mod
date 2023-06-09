package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-S-0092 물량이동 수불데이터 생성 서비스 상품 Dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-06-09
 */

@Getter
@Setter
public class WsnaTransferMaterialsPdDvo {

    // 품목종류코드
    private String itmKndCd;
    // 품목상품코드
    private String itmPdCd;
    // 품목코드
    private String itmCd;
    // 관리단위코드
    private String mngtUnitCd;
    // 박스단위수량
    private Integer boxUnitQty;

}
