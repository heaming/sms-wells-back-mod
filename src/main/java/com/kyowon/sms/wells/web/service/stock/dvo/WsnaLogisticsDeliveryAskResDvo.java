package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-S-0091 물류 배송요청 서비스 (HQ 생성로직) Response Dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-06-08
 */

@Getter
@Setter
public class WsnaLogisticsDeliveryAskResDvo {

    // TB_IFIN_SPP_BAS_SEND_ETXT - 배송기본송신전문 데이터 생성 건수
    private int basCnt;

    // TB_IFIN_SPP_PD_SEND_ETXT - 배송상품송신전문 데이터 생성 건수
    private int pdCnt;

    // TB_IFIN_SPP_MAT_SEND_ETXT - 배송자재송신전문 데이터 생성 건수
    private int matCnt;

}
