package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-S-0092 물량이동 수불데이터 생성 Request Dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-06-09
 */

@Getter
@Setter
public class WsnaTransferMaterialsReqDvo {

    // 출고요청번호
    private String ostrAkNo;

    // 출고대상창고
    private String ostrOjWareNo;

    // 입고대상창고
    private String strOjWareNo;

}
