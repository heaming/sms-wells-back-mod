package com.kyowon.sms.wells.web.contract.changeorder.dto;

import io.swagger.annotations.ApiModel;

public class WcteSellLimitObjectDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WcteSellLimitObjectDto-SearchReq")
    public record SearchReq(
        String sellLmBzrNo
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel("WcteSellLimitObjectDto-SearchRes")
    public record SearchRes(
        String sellLmRsonCd,
        String sellLmRsonCn
    ) {}
}
