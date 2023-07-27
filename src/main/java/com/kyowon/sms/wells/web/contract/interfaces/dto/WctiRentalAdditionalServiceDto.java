package com.kyowon.sms.wells.web.contract.interfaces.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;

public class WctiRentalAdditionalServiceDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 렌탈 부가서비스 Search Request Dto
    @ApiModel("WctiRentalAdditionalServiceDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String CNTR_NO, //계약번호(필수)
        @NotNull
        int CNTR_SN //계약일련번호(필수)
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 렌탈 부가서비스 Search Result Dto
    @ApiModel("WctiRentalAdditionalServiceDto-SearchRes")
    public record SearchRes(
        String IST_DT,
        String REQD_DT,
        Integer ADN_SV_CS_AMT,
        Integer RENTAL_AMT,
        Integer TOT_AMT
    ) {}
}


