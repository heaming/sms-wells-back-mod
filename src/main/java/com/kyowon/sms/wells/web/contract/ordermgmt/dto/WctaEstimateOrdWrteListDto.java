package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidMonth;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctaEstimateOrdWrteListDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @Builder
    @ApiModel("WctaEstimateOrdWrteListDto-SearchReq")
    public record SearchReq(
        @NotBlank
        @ValidMonth
        String fromEstDt,
        @NotBlank
        @ValidMonth
        String toEstDt,
        String inqrDv,
        String cntrt

    ) {}
    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel("WctaEstimateOrdWrteListDto-SearchRes")
    public record SearchRes(

        String cntrNo,
        String cntrSn,
        String sellTpCd,
        String sellTpCdNm,
        String copnDvCd,
        String copnDvCdNm,
        String cstKnm,
        String bryyMmddBzrno,
        String cntrTempSaveDt,
        String sndDt,
        String pdNm,
        String basePdCd,
        String sellAmt
    ) {}
}
