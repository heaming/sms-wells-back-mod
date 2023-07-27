package com.kyowon.sms.wells.web.contract.changeorder.dto;

import io.swagger.annotations.ApiModel;

public class WcteAlreadyReceivedMphDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WcteAlreadyReceivedMphDto-SearchReq")
    public record SearchReq(
        String cntrNo,
        String cntCstNo
    ) {}
}
