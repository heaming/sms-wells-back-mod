package com.kyowon.sms.wells.web.contract.changeorder.dto;

import io.swagger.annotations.ApiModel;

public class WctaMachineChangeInfoInqrDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WctaMachineChangeInfoInqrDto-SearchReq")
    public record SearchReq(
        String cntrNo,
        String cntrSn
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel("WctaMachineChangeInfoInqrDto-SearchRes")
    public record SearchRes(
        String basePdCd,
        String cntrDt2,
        String istDt,
        String canDt,
        String reqdDt2,
        String exnDt,
        String svTpCd,
        String copnDvCd,
        String dscApyTpCd,
        String dscApyDtlCd,
        String pmotCd,
        String sellEvCd,
        String pdBaseAmt,
        String mchnChYn,
        String mchnChTpCd,
        String svPrd,
        String pmotCd2,
        String pdTpCd
    ) {}
}
