package com.kyowon.sms.wells.web.service.allocate.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-S-0024 S&OP 인터페이스 자료 생성
 * </pre>
 *
 * @author hyewon.kim 김혜원
 * @since 2023.04.03
 */
@Setter
@Getter
public class WsncSnopInterfaceDataDvo {

    String plantCd;
    String strgCd;
    String itemCd;
    String yymmdd;
    String invQty;
    String grQty;
    String qtyUnitCd;

}
