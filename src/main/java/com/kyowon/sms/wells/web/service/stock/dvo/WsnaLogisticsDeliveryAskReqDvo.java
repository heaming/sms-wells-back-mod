package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-S-0091 물류 배송요청 서비스 (HQ 생성로직) Request Dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-06-08
 */

@Getter
@Setter
public class WsnaLogisticsDeliveryAskReqDvo {

    // 출고요청번호 (OSTR_AK_NO)
    private String ostrAkNo;
    // 출고요청일련번호 (OSTR_AK_SN)
    private int ostrAkSn;

    // 차수 (TCNT)
    private Integer tcnt;

}
