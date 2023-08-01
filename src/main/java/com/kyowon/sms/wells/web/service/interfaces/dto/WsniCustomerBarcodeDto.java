package com.kyowon.sms.wells.web.service.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * W-SV-I-0013 고객코드, 바코드 일치 확인 API 조회
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.04.26
 */
public class WsniCustomerBarcodeDto {
    @ApiModel(value = "WsniCustomercodeBarcodeEqualDto-SearchReq")
    public record SearchReq(
        @JsonProperty("CNTR_NO")
        String cntrNo,
        @JsonProperty("BC_NO")
        String bcNo
    ) {}

    @ApiModel(value = "WsniCustomercodeBarcodeEqualDto-SearchRes")
    public record SearchRes(
        @JsonProperty("RESPONSE_CODE")
        String responseCode,
        @JsonProperty("RESPONSE_CSMR_NM")
        String responseCsmrNm,
        @JsonProperty("RESPONSE_MSG")
        String responseMsg
    ) {}
}
