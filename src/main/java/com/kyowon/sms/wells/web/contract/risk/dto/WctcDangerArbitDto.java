package com.kyowon.sms.wells.web.contract.risk.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;
import com.sds.sflex.system.config.validation.validator.ValidMonth;

import io.swagger.annotations.ApiModel;

public class WctcDangerArbitDto {
    //*********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WctcDangerArbitDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String srchGbn,
        @ValidDate
        String dangOcStrtdt,
        @ValidDate
        String dangOcEnddt,
        @ValidMonth
        String dangOcStrtMonth,
        @ValidMonth
        String dangOcEndMonth,
        String gnrdv,
        String rgrp,
        String brch,
        String dangOjPrtnrNo
    ) {}
    // *********************************************************
    // Response Dto
    // *********************************************************
    @ApiModel(value = "WctcDangerArbitDto-SearchRes")
    public record SearchRes(
        String dangOjPrtnrNo,
        String dangOcStrtmm,
        String dangOjOgId,
        String dangOjPrtnrNm,
        String dangOjPstnDvCd,
        String dgr1LevlDgPrtnrNo,
        String dgr1LevlDgPrtnrNm,
        String dgr2LevlDgPrtnrNo,
        String dgr2LevlDgPrtnrNm,
        String bznsSpptPrtnrNo,
        String bznsSpptPrtnrNm,
        String dgr3LevlDgPrtnrNo,
        String dgr3LevlDgPrtnrNm,
        String dangChkNm,
        String dangArbitCd,
        String dangUncvrCt,
        String dangArbitLvyPc,
        String dangArbitOgId,
        String fstRgstUsrId,
        String fstRgstDt
    ) {}
    // *********************************************************
    // Response Dto
    // *********************************************************
    @ApiModel(value = "WctcDangerArbitDto-SearchOrganizationRes")
    public record SearchOrganizationRes(
        String prtnrNo,
        String prtnrKnm,
        String pstnDvCd,
        String pstnDvNm,
        String dgr1LevlOgCd,
        String dgr1LevlOgNm,
        String dgr1LevlDgPrtnrNo,
        String dgr1LevlDgPrtnrNm,
        String dgr1LevlOgId,
        String dgr1LevlDgPstnDvCd,
        String dgr2LevlOgCd,
        String dgr2LevlOgNm,
        String dgr2LevlDgPrtnrNo,
        String dgr2LevlDgPrtnrNm,
        String dgr2LevlOgId,
        String dgr2LevlDgPstnDvCd,
        String dgr3LevlOgCd,
        String dgr3LevlOgNm,
        String dgr3LevlDgPrtnrNo,
        String dgr3LevlDgPrtnrNm,
        String dgr3LevlOgId,
        String dgr3LevlDgPstnDvCd,
        String dgr4LevlOgCd,
        String dgr4LevlOgNm,
        String dgr4LevlDgPrtnrNo,
        String dgr4LevlDgPrtnrNm,
        String dgr4LevlOgId,
        String dgr4LevlDgPstnDvCd,
        String dgr5LevlOgCd,
        String dgr5LevlOgNm,
        String dgr5LevlDgPrtnrNo,
        String dgr5LevlOgId,
        String bizSpptPrtnrNo,
        String ogUpbrngPrtnrNo,
        String ogCd,
        String ogId,
        String ogNm,
        String baseYm,
        String ogTpCd
    ) {}

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 비정도 영업 조치 사항 등록 Save Request Dto
    @ApiModel(value = "WctcDangerArbitDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String rowState, /* 로우상태(created, updated) */
        @NotBlank
        String dangOjPrtnrNo, /* 사번(행위자) */
        @NotBlank
        String dangOcStrtmm, /* 발생년월(행위자) */
        String dangOjOgId, /* 소속(행위자) */
        String dangOjPrtnrNm, /* 성명(행위자) */
        String dangOjPstnDvCd, /* 직급(행위자) */
        String dgr1LevlDgPrtnrNo, /* 총괄단(소속) */
        String dgr1LevlDgPrtnrNm, /* 총괄단(소속)명 */
        String dgr2LevlDgPrtnrNo, /* 지역단(소속) */
        String dgr2LevlDgPrtnrNm, /* 지역단(소속)명 */
        String bznsSpptPrtnrNo, /* 영업지원파트너(BM)(소속) */
        String bznsSpptPrtnrNm, /* 영업지원파트너(BM)(소속) */
        String dgr3LevlDgPrtnrNo, /* 지점(소속) */
        String dgr3LevlDgPrtnrNm, /* 지점(소속) */
        @NotBlank
        String dangChkNm, /* 부과내역(벌점) */
        String dangArbitCd, /* 조치항목(벌점) */
        @NotBlank
        String dangUncvrCt, /* 부과대상건수(벌점) */
        @NotBlank
        String dangArbitLvyPc, /* 조치결과부과점수(벌점) */
        @NotBlank
        String dangArbitOgId, /* 조치부서(벌점) */
        String fstRgstUsrId, /* 등록자 */
        String fstRgstDt, /* 등록일자 */
        String ogTpCd
    ) {}
}
