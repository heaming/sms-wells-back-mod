package com.kyowon.sms.wells.web.service.stock.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * W-SV-S-0091 물류 배송요청 서비스 (HQ 생성로직)
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-05-31
 */

public class WsnaLogisticsDeliveryAskDto {

    @Builder
    @ApiModel("WsnaLogisticsDeliveryAskDto-CreateReq")
    public record CreateReq(
        // 출고요청번호 (OSTR_AK_NO)
        @NotBlank
        String ostrAkNo,

        // 차수 (TCNT)
        @Positive
        int tcnt
    ) {}

    @Builder
    @ApiModel("WsnaLogisticsDeliveryAskDto-CreateRes")
    public record CreateRes(
        // TB_IFIN_SPP_BAS_SEND_ETXT - 배송기본송신전문 데이터 생성 건수
        int basCnt,

        // TB_IFIN_SPP_PD_SEND_ETXT - 배송상품송신전문 데이터 생성 건수
        int pdCnt,

        // TB_IFIN_SPP_MAT_SEND_ETXT - 배송자재송신전문 데이터 생성 건수
        int matCnt
    ) {}

}
