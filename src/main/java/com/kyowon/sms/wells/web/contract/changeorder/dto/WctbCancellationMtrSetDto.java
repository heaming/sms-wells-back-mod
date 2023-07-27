package com.kyowon.sms.wells.web.contract.changeorder.dto;

import io.swagger.annotations.ApiModel;

public class WctbCancellationMtrSetDto {
    // *********************************************************
    // Response Dto
    // *********************************************************
    @ApiModel(value = "WctbCancellationMtrSetDto-SearchRes")
    public record SearchRes(
        String cntrChRcpId,
        String cntrChSn,
        String cntrUnitTpCd,
        String procsDuedt,
        String procsFshDtm,
        String cntrChPrgsStatCd,
        String cntrChRcpDtm,
        String cntrChFshDtm,
        String cntrNo,
        String cntrSn,
        String cntrCanDtm,
        String cntrCanDtmNchk,
        String cntrChFshDtmNchk
    ) {}
    @ApiModel(value = "WctbCancellationMtrSetDto-SearchAcmpalCntrIzRes")
    public record SearchAcmpalCntrIzRes(
        String acmpalCntrId,
        String ojCntrNo
    ) {}
}
