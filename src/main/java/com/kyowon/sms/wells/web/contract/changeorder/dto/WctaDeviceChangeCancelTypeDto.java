package com.kyowon.sms.wells.web.contract.changeorder.dto;

import io.swagger.annotations.ApiModel;

public class WctaDeviceChangeCancelTypeDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WctaDeviceChangeCancelTypeDto-SearchReq")
    public record SearchReq(
        String ojCntrNo,
        String ojCntrSn
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel("WctaDeviceChangeCancelTypeDto-SearchRes")
    public record SearchRes(
        String baseCntrNo,
        String baseCntrSn,
        String sellInflwChnlDtlCd,
        String sellTpCd,
        String mchnChTpCd,
        String mchnCpsApyr,
        String baseCntrRcpdt,
        String baseCntrSlDt,
        String mchnChYn
    ) {}
}
