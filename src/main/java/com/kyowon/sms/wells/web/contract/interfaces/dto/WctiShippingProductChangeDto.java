package com.kyowon.sms.wells.web.contract.interfaces.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctiShippingProductChangeDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 커피원두 상품 변경 Save Request Dto
    @ApiModel("WctiShippingProductChangeDto-SaveReq")
    public record SaveReq(
        @JsonProperty("CNTR_NO")
        @NotBlank
        String cntrNo,
        @JsonProperty("CNTR_SN")
        @NotNull
        int cntrSn,
        @JsonProperty("BFCH_PDCT_PD_CD")
        @NotBlank
        String bfchPdctPdCd,
        @JsonProperty("AFCH_PDCT_PD_CD")
        @NotBlank
        String afchPdctPdCd,
        @JsonProperty("CH_RCST_ID")
        @NotBlank
        String chRcstId
    ) {}

    @ApiModel("WctiShippingProductChangeDto-SaveCntrChRcpBas")
    public record SaveCntrChRcpBas(
        String cntrChRcpId,
        String chRqrNm,
        String cstNo,
        String cntrChAkCn,
        String cntrChPrgsStatCd,
        String chRcpUsrId,
        String aprUsrId
    ) {}


    // *********************************************************
    // Result Dto
    // *********************************************************
    // 커피원두 상품 변경 Save Result Dto
    @ApiModel("WctiShippingProductChangeDto-SaveRes")
    @Builder
    public record SaveRes(
        @JsonProperty("SCS_YN")
        String scsYn
    ) {}
}

