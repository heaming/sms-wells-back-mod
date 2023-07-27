package com.kyowon.sms.wells.web.contract.interfaces.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;

public class WctiContractInstallRelationDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 연관계약 건 Search Request Dto
    @ApiModel("WctiContractInstallRelationDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String CNTR_NO, //계약번호(필수)
        @NotNull
        int CNTR_SN //계약일련번호(필수)
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 연관계약 건 Search Result Dto
    @ApiModel("WctiContractInstallRelationDto-SearchRes")
    public record SearchRes(
        String DV_NM, //연관계약 구분명
        String SELL_TP_NM, // 판매유형명
        String RLTD_CNTR_NO, //관련계약번호
        Integer RLTD_CNTR_SN, //관련계약일련번호
        String PD_CD, //관련계약 상품코드
        String PD_NM //관련계약 상품명
    ) {}
}
