package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-S-0088 물류 출고요청 데이터 생성 Response Dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-06-08
 */

@Getter
@Setter
public class WsnaLogisticsOutStorageAskResDvo {

    // TB_IFIN_ITM_OSTR_AK_SEND_ETXT - 품목출고요청송신전문 데이터 생성 건수
    private int akCnt;

    // TB_IFIN_OSTR_AK_DTL_SEND_ETXT - 출고요청상세송신전문 데이터 생성 건수
    private int akDtlCnt;

    // TB_IFIN_OSTR_AK_PCSV_SEND_ETXT - 출고요청택배송신전문 데이터 저장 건수
    private int pcsvCnt;

    // TB_IFIN_SPP_BAS_SEND_ETXT - 배송기본송신전문 데이터 생성 건수
    private int basCnt;

    // TB_IFIN_SPP_PD_SEND_ETXT - 배송상품송신전문 데이터 생성 건수
    private int pdCnt;

    // TB_IFIN_SPP_MAT_SEND_ETXT - 배송자재송신전문 데이터 생성 건수
    private int matCnt;
}
