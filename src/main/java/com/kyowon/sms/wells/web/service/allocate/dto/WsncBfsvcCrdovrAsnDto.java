package com.kyowon.sms.wells.web.service.allocate.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WsncBfsvcCrdovrAsnDto {

    // *********************************************************
    // Request Dto
    // *********************************************************

    @ApiModel(value = "WsncBfsvcCrdovrAsnDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String crdovrYm,
        @NotBlank
        String cstSvAsnNo
    ) {}

}
