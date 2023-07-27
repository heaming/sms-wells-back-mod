package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import io.swagger.annotations.ApiModel;

public class WctaProductEmpPrchsCountDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel("WctaProductEmpPrchsCountDto-SearchReq")
    public record SearchReq(
        String cntrNo,
        String cntrSn,
        String sellTpCd,
        String startDate,
        String endDate
    ) {}
}
