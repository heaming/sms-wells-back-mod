package com.kyowon.sms.wells.web.service.allocate.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WsncBfsvcCrdovrAsnDto {

    // *********************************************************
    // Request Dto
    // *********************************************************

    @ApiModel(value = "WsncBfsvcCrdovrAsnDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String crdovrYm,
        @NotBlank
        String cstSvAsnNo
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel(value = "WsncBfsvcCrdovrAsnDto-SearchRes")
    public record SearchRes(
        String cstSvAsnNo
    ) {}

}
