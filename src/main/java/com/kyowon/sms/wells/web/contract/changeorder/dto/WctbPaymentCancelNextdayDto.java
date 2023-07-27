package com.kyowon.sms.wells.web.contract.changeorder.dto;

import io.swagger.annotations.ApiModel;

public class WctbPaymentCancelNextdayDto {
    // *********************************************************
    // Result Dto
    // *********************************************************
    //  Search Result Dto
    @ApiModel("WctbPaymentCancelNextdayDto-SearchRes")
    public record SearchRes(
        String prcsRslt,
        String msg,
        String dtlMsg
    ) {}
}
