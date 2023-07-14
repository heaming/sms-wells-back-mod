package com.kyowon.sms.wells.web.service.allocate.dto;

import io.swagger.annotations.ApiModel;

public class WsncRegularBfsvcAsnDto {
    // *********************************************************
    // Request Dto
    // *********************************************************

    @ApiModel(value = "WsncRegularBfsvcAsnDto-SaveProcessReq")
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
