package com.kyowon.sms.wells.web.contract.interfaces.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;

public class WctiContractCurrentProductDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 현재 상품 Find Request Dto
    @ApiModel("WctiContractCurrentProductDto-FindReq")
    public record FindReq(
        @NotBlank
        String CNTR_NO,
        @NotNull
        int CNTR_SN
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 현재 상품 조회 Find Result Dto
    @ApiModel("WctiContractCurrentProductDto-FindRes")
    public record FindRes(
        String PD_HCLSF_ID,
        String PD_HCLSF_NM,
        String PD_MCLSF_ID,
        String PD_MCLSF_NM,
        String PD_LCLSF_ID,
        String PD_LCLSF_NM,
        String BASE_PD_CD,
        String BASE_PD_NM,
        String HGR_PD_CD,
        String HGR_PD_NM,
        String AS_PSB_YN,
        Integer FRISU_AS_PTRM_N
    ) {}
}
