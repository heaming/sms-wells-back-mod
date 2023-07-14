package com.kyowon.sms.wells.web.service.allocate.dto;

import io.swagger.annotations.ApiModel;

public class WsncSpecCustAsnDto {

    // *********************************************************
    // Request Dto
    // *********************************************************

    @ApiModel(value = "WsncSpecCustAsnDto-SaveProcessReq")
    public record SaveProcessReq(
        String asnOjYm,
        String prtnrNo,
        String cntrNo,
        String cntrSn
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
}
