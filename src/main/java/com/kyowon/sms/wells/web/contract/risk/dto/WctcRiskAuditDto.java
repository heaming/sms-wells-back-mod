package com.kyowon.sms.wells.web.contract.risk.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;
import com.sds.sflex.system.config.validation.validator.ValidMonth;

import io.swagger.annotations.ApiModel;

public class WctcRiskAuditDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WctcRiskAuditDto-SearchReq")
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
        String dangOjPrtnrNo,
        String dangMngtPrtnrNo
    ) {}
    // *********************************************************
    // Response Dto
    // *********************************************************
    @ApiModel(value = "WctcRiskAuditDto-SearchRes")
    public record SearchRes(
        String dangChkId,
        String wellsOjPstnRankNm,
        String dangMngtPntnrOgNm,
        String dangMngtPntnrOgCd,
        String ogLevlDvCd,
        String dangMngtPntnrNm,
        String dangMngtPrtnrNo,
        String dangOjPrtnrNm,
        String dangOjPrtnrNo,
        String dangOjPrtnrPstnDvCd,
        String dangOjPrtnrPstnDvNm,
        String dangOcStrtmm,
        String dangArbitOgNm,
        String dangChkNm,
        String dangArbitCdNm,
        String dangUncvrCt,
        String dangArbitLvyPc,
        String dangArbitLvyPcSum,
        String fstRgstUsrId,
        String fstRgstDt,
        String dangMngtPstnDvCd,
        String dangArbitOgId,
        String dangArbitCd,
        String dangArbitLvyPcOrg,
        String fstRgstDtm,
        String dgr1LevlOgCd,
        String dgr2LevlOgCd,
        String dgr3LevlOgCd,
        String ogId,
        String pstnDvCd,
        String dgrLevlOgCd,
        String hgrOgId,
        String ogTpCd
    ) {}
}
