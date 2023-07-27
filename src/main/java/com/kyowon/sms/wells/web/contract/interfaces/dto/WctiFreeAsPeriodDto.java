package com.kyowon.sms.wells.web.contract.interfaces.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;

public class WctiFreeAsPeriodDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 무상 AS 기간 Search Request Dto
    @ApiModel("WctiFreeAsPeriodDto-FindReq")
    public record FindReq(
        @NotBlank
        String CNTR_NO,
        @NotNull
        int CNTR_SN
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 무상 AS 기간 Search Result Dto
    @ApiModel("WctiFreeAsPeriodDto-FindRes")
    public record FindRes(
        Integer FRISU_AS_MCN,
        Integer SS_FRISU_AS_MCN
    ) {}
}
