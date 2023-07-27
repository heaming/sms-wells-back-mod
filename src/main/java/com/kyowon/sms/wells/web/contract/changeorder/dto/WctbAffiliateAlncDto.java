package com.kyowon.sms.wells.web.contract.changeorder.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctbAffiliateAlncDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    //  Search Request Dto
    @Builder
    @ApiModel("WctbAffiliateAlncDto-SaveReq")
    public record SaveReq(
        String alncmpCd,
        String cntrNo,
        int cntrSn,
        String klyear,
        String klcode,
        String alncCanDt,
        String alncCanRsonCd
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    //  Search Result Dto
    @ApiModel("WctbAffiliateAlncDto-SearchRes")
    public record SearchRes() {}
}
