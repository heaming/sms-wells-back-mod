package com.kyowon.sms.wells.web.contract.interfaces.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;

public class WctiContractProductDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 상품정보 Search Request Dto
    @ApiModel("WctiContractProductDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String CNTR_NO,
        @NotNull
        int CNTR_SN
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 상품정보 Search Result Dto
    @ApiModel("WctiContractProductDto-SearchRes")
    public record SearchRes(
        String BZ_HDQ_DV_CD, //사업구분코드
        String SELL_TP_CD, // 판매유형코드
        String SELL_TP_NM, // 판매유형명
        String PD_HCLSF_ID, // 상품대분류ID
        String PD_HCLSF_NM, // 상품대분류명
        String PD_MCLSF_ID, // 상품중분류ID
        String PD_MCLSF_NM, // 상품중분류명
        String PD_LCLSF_ID, // 상품소분류ID
        String PD_LCLSF_NM, // 상품소분류명
        String BASE_PD_CD, // 기준상품코드
        String BASE_PD_NM, // 기준상품명
        String HGR_PD_CD, // 상위상품코드
        String HGR_PD_NM // 상위상품
    ) {}
}
