package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidMonth;

import io.swagger.annotations.ApiModel;

public class WctaRentalAccountMgtDto {
    //wells 확정 승인 기준 관리 - 확정 승인 기준 관리 조회 Search Result Dto
    @ApiModel("WctaRentalAccountMgtDto-SearchBpdRentalAccountRes")
    public record SearchBpdRentalAccountRes(
        String pdgrpNm,
        String pdNm,
        String basePdCd,
        String istDt,
        String rstlYn,
        String jCnt,
        String mchnChCnt,
        String reRentalCnt,
        String mshCnt,
        String keepRentalCnt,
        String sprExnCnt,
        String sprReqdCnt,
        String sprRat
    ) {}

    //wells 확정 승인 기준 관리 - 확정 승인 기준 관리 조회 Search Result Dto
    @ApiModel("WctaRentalAccountMgtDto-SearchBpdRentalAccountReq")
    public record SearchBpdRentalAccountReq(
        @NotBlank
        String srchGbn,
        @ValidMonth
        String istStartDt,
        @ValidMonth
        String istEndDt,
        @NotBlank
        String pdMclsfId,
        @NotBlank
        String basePdCd,
        @NotBlank
        String copnDvCd
    ) {}

    //wells 확정 승인 기준 관리 - 확정 승인 기준 관리 조회 Search Result Dto
    @ApiModel("WctaRentalAccountMgtDto-SearchByoRentalAccountRes")
    public record SearchByoRentalAccountRes(
        String dgr1LevlOgCd,
        String dgr2LevlOgCd,
        String istDt,
        String rstlYn,
        String jCnt,
        String mchnChCnt,
        String reRentalCnt,
        String mshCnt,
        String keepRentalCnt,
        String sprExnCnt,
        String sprReqdCnt,
        String sprRat
    ) {}

    //wells 확정 승인 기준 관리 - 확정 승인 기준 관리 조회 Search Result Dto
    @ApiModel("WctaRentalAccountMgtDto-SearchByoRentalAccountReq")
    public record SearchByoRentalAccountReq(
        @NotBlank
        String srchGbn,
        @ValidMonth
        String istStartDt,
        @ValidMonth
        String istEndDt,
        @NotBlank
        String dgr1LevlOgCd,
        @NotBlank
        String dgr2LevlOgCd,
        @NotBlank
        String copnDvCd
    ) {}
}
