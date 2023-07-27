package com.kyowon.sms.wells.web.contract.interfaces.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;

public class WctiRequidationDateDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 계약건의 철거 정보 Find Request Dto
    @ApiModel("WctiRequidationDateDto-FindReq")
    public record FindReq(
        @NotBlank
        String CNTR_NO,
        @NotNull
        int CNTR_SN
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 계약건의 철거 정보 Find Result Dto
    @ApiModel("WctiRequidationDateDto-FindRes")
    public record FindRes(
        String CNTR_NO,
        Integer CNTR_SN,
        String CNTR_DT,
        String IST_DT,
        String REQD_DT,
        Integer REQD_IST_DC_GAP
    ) {}
}
