package com.kyowon.sms.wells.web.contract.changeorder.dto;

import io.swagger.annotations.ApiModel;

public class WctaBogoCustomerCheckDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WctaBogoCustomerCheckDto-SearchReq")
    public record SearchReq(
        String rcpDt,
        String baseCntrNo,
        String baseCntrDtlNo,
        String cntrCstNo,
        String pdCd,
        String sellPrtnrNo,
        String ojCntrNo,
        String ojCntrDtlNo,
        String dscGubn,
        String userGubn
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel("WctaBogoCustomerCheckDto-SearchRes")
    public record SearchRes(
        String existYn
    ) {}
}
