package com.kyowon.sms.wells.web.service.allocate.dto;

import io.swagger.annotations.ApiModel;

public class WsncSpecCustMngrAsnDto {
    // *********************************************************
    // Request Dto
    // *********************************************************

    @ApiModel(value = "WsncSpecCustMngrAsnDto-SaveProcessReq")
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
