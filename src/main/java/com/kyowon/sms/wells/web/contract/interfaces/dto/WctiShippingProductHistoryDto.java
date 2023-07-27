package com.kyowon.sms.wells.web.contract.interfaces.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;

public class WctiShippingProductHistoryDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 커피원두 변경 이력 Search Request Dto
    @ApiModel("WctiShippingProductHistoryDto-SearchReq")
    public record SearchReq(
        @JsonProperty("CNTR_NO")
        @NotBlank
        String cntrNo,
        @JsonProperty("CNTR_SN")
        @NotNull
        int cntrSn
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 커피원두 변경 이력 Search Result Dto
    @ApiModel("WctiShippingProductHistoryDto-SearchRes")
    public record SearchRes(
        @JsonProperty("CNTR_NO")
        String cntrNo,
        @JsonProperty("CNTR_SN")
        int cntrSn,
        @JsonProperty("SELL_TP_CD")
        String sellTpCd,
        @JsonProperty("SL_YM")
        String slYm,
        @JsonProperty("CNTR_PD_REL_ID")
        String cntrPdRelId,
        @JsonProperty("BFCH_PDCT_PD_CD")
        String bfchPdctPdCd,
        @JsonProperty("BFCH_PDCT_PD_NM")
        String bfchPdctPdNm,
        @JsonProperty("AFCH_PDCT_PD_CD")
        String afchPdctPdCd,
        @JsonProperty("AFCH_PDCT_PD_NM")
        String afchPdctPdNm,
        @JsonProperty("FNL_MDFC_DTM")
        String fnlMdfcDtm,
        @JsonProperty("FNL_MDFC_USR_ID")
        String fnlMdfcUsrId,
        @JsonProperty("FNL_MDFC_USR_NM")
        String fnlMdfcUsrNm
    ) {}
}

