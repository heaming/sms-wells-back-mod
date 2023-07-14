package com.kyowon.sms.wells.web.service.allocate.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WsncVisitPeriodRecrtDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WsncVisitPeriodRecrtDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String cntrNo,
        @NotBlank
        String cntrSn
    ) {}
}
