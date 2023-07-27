package com.kyowon.sms.wells.web.contract.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;

public class WctiCustomerDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 고객 통합 리스트 Search Request Dto
    @ApiModel("WctiCustomerDto-SearchReq")
    public record SearchReq(
        @JsonProperty("CST_NM")
        String cstNm,
        @JsonProperty("CRAL_LOCARA_TNO")
        String cralLocaraTno,
        @JsonProperty("MEXNO")
        String mexno,
        @JsonProperty("CRAL_IDV_TNO")
        String cralIdvTno,
        @JsonProperty("LOCARA_TNO")
        String locaraTno,
        @JsonProperty("EXNO")
        String exno,
        @JsonProperty("IDV_TNO")
        String idvTno,
        @JsonProperty("CST_NO")
        String cstNo
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 고객 통합 리스트 Search Result Dto
    @ApiModel("WctiCustomerDto-SearchRes")
    public record SearchRes(
        @JsonProperty("CST_DV")
        String cstDv,
        @JsonProperty("CST_NO")
        String cstNo,
        @JsonProperty("ITG_CST_NO")
        String itgCstNo,
        @JsonProperty("CST_KNM")
        String cstKnm,
        @JsonProperty("CRAL_LOCARA_TNO")
        String cralLocaraTno,
        @JsonProperty("MEXNO")
        String mexno,
        @JsonProperty("CRAL_IDV_TNO")
        String cralIdvTno,
        @JsonProperty("LOCARA_TNO")
        String locaraTno,
        @JsonProperty("EXNO")
        String exno,
        @JsonProperty("IDV_TNO")
        String idvTno,
        @JsonProperty("EMADR")
        String emadr,
        @JsonProperty("BRYY_MMDD")
        String bryyMmdd,
        @JsonProperty("SEX_DV_CD")
        String sexDvCd
    ) {}
}
