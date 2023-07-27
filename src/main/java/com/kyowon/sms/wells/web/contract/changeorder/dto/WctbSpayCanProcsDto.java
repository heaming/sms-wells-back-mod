package com.kyowon.sms.wells.web.contract.changeorder.dto;

import io.swagger.annotations.ApiModel;

public class WctbSpayCanProcsDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WctbSpayCanProcsDto-SearchReq")
    public record SearchReq(
        String useCls,
        String cntrNo,
        String cntrSn
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel("WctbSpayCanProcsDto-SearchRes")
    public record SearchRes(
        String prcsRslt,
        String msg,
        String dtlMsg
    ) {}
    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel("WctbSpayCanProcsDto-SearchRes")
    public record SearchSsctCntrDtlRes(
        String cntrDtlStatCd,
        String basePdCd,
        String cntrPdStrtdt,
        String alncmpCd
    ) {}
}
