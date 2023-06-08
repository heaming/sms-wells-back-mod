package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-S-0089 물류 반품요청 데이터 생성 Response Dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-06-08
 */

@Getter
@Setter
public class WsnaLogisticsInStorageAskResDvo {

    // TB_IFIN_PD_RTNGD_AK_SEND_ETXT - 상품반품요청송신전문 데이터 저장 건수
    private int akCnt;

    // TB_IFIN_RTNGD_AK_DTL_SEND_ETXT - 반품요청상세송신전문 데이터 저장 건수
    private int akDtlCnt;
}
