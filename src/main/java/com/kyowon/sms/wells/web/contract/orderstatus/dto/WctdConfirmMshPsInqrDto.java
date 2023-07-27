package com.kyowon.sms.wells.web.contract.orderstatus.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;

public class WctdConfirmMshPsInqrDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 확정 멤버십 현황 조회 Search Request Dto
    @ApiModel("WctdConfirmMshPsInqrDto-SearchReq")
    public record SearchReq(
        @NotBlank
        @ValidDate
        String rcpStrtDt,
        @NotBlank
        @ValidDate
        String rcpEndDt,
        String pdHclsfId,
        String pdMclsfId,
        String basePdCd,
        @NotBlank
        @ValidDate
        String cnfmStrtDt,
        @NotBlank
        @ValidDate
        String cnfmEndDt,
        String pdNm,
        String sellOgTpCd,
        @Pattern(regexp = "^$|[YN]")
        String frisuMshCrtYn,
        String prtnrNo
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 확정 멤버십 현황 조회 Search Result Dto
    @ApiModel("WctdConfirmMshPsInqrDto-SearchRes")
    public record SearchRes(
        String cntrDtlNo,
        String cstKnm,
        String sellTpNm,
        String sellOgTpNm,
        String pdHclsfNm,
        String pdMclsfNm,
        String basePdCd,
        String pdNm,
        String frisuBfsvcPtrmN,
        String frisuMshCrtYn,
        String nationSptYn,
        String feeYn,
        String prmYn,
        String ogCd,
        String sellPrtnrNo,
        String prtnrKnm,
        String rsbDvNm,
        String cntrRcpFshDt,
        String cntrCnfmYn,
        String cntrCnfmDt,
        String cntrPdStrtdt,
        String cntrPdEnddt,
        String cntrCanDt
    ) {}
}
