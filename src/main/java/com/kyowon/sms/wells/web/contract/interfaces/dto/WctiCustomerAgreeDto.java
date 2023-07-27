package com.kyowon.sms.wells.web.contract.interfaces.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctiCustomerAgreeDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 개인정보 동의 현황 조회 Search Request Dto
    @Builder
    @ApiModel("WctiCustomerAgreeDto-SearchReq")
    public record SearchReq(
        @JsonProperty("CST_NO")
        String cstNo,
        @JsonProperty("CNTR_NO")
        String cntrNo

    ) {}
    // *********************************************************
    // Result Dto
    // *********************************************************
    // 개인정보 동의 현황 조회 Search Result Dto
    @ApiModel("WctiCustomerAgreeDto-SearchRes")
    public record SearchRes(
        @JsonProperty("CNTR_NO")
        String cntrNo,
        @JsonProperty("CNTR_CST_NO")
        String cntrCstNo,
        @JsonProperty("CNTR_CST_NM")
        String cntrCstNm,
        @JsonProperty("SELL_PRTNR_NO")
        String sellPrtnrNo,
        @JsonProperty("AG_PROCS_DTM")
        String agProcsDtm,
        @JsonProperty("AG_ATC_DV_CD")
        String agAtcDvCd,
        @JsonProperty("AG_ATC_DV_NM")
        String agAtcDvNm,
        @JsonProperty("AG_STAT_CD")
        String agStatCd,
        @JsonProperty("AG_STAT_NM")
        String agStatNm,
        @JsonProperty("PRV_DOC_ID")
        String prvDocId
    ) {}
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 개인정보 동의 내역 저장 Save Request Dto
    @Builder
    @ApiModel("WctiCustomerAgreeDto-SaveReq")
    public record SaveReq(
        @JsonProperty("CNTR_NO")
        @NotBlank
        String cntrNo,
        @JsonProperty("AG_ATC_DV_CD")
        @NotBlank
        String agAtcDvCd,
        @JsonProperty("AG_STAT_CD")
        String agStatCd,
        @JsonProperty("AZ_PSIC_ID")
        String azPsicId
    ) {}
    // *********************************************************
    // Result Dto
    // *********************************************************
    // 개인정보 동의 내역 저장 Save Result Dto
    @ApiModel("WctiCustomerAgreeDto-SaveRes")
    public record SaveRes(
        @JsonProperty("SCS_YN")
        @NotBlank
        String scsYn
    ) {}
}
