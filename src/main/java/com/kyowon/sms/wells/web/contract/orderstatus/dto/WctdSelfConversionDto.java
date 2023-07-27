package com.kyowon.sms.wells.web.contract.orderstatus.dto;

import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst.PeriodType;
import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.system.config.validation.validator.ValidDate;
import io.swagger.annotations.ApiModel;
import org.apache.commons.lang.StringUtils;

import javax.validation.constraints.NotBlank;

public class WctdSelfConversionDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    //  자가전환 조회 Search Request Dto

    @ApiModel("WctdSelfConversionDto-SearchReq")
    public record SearchReq(
        PeriodType periodType,
        @NotBlank
        @ValidDate
        String startDt,

        @NotBlank
        @ValidDate
        String endDt,
        String pdHclsfId,
        String pdMclsfId,
        String pdCd,
        String pdNm,
        String strtOgCd,
        String endOgCd,
        String copnDvCd
    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    //  자가전환 조회 Search Result Dto
    @ApiModel("WctdSelfConversionDto-SearchRes")
    public record SearchRes(
        String cntrNo,
        int cntrSn,
        String copnDvCd,
        String istDt,
        String cntrCstNo,
        String cstKnm,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String rcgvpKnm,
        String rcgvpCralLocaraTno,
        String rcgvpMexnoEncr,
        String rcgvpCralIdvTno,
        String pdCd,
        String pdNm,
        String sdingPdNm,
        Integer fnlAmt,
        Integer stplPtrm,
        String stplPtrmUnitCd,
        String lastBsDt,
        Integer eotUcAmt,
        Integer eotDlqAmt,
        Integer dlqAcuMcn,
        String cntrPdStrtdt,
        String cntrPdEnddt,
        String mchnChYn,
        String reRetalYn,
        String mchnChTpCd,
        String chCntrNo,
        Integer chCntrSn,
        String chPdCd,
        String chPdNm,
        String stplRcpDtm,
        String stplStrtdt,
        String stplCanDtm
        ) {
        public SearchRes {
            mexnoEncr = StringUtils.isNotEmpty(mexnoEncr) ? DbEncUtil.dec(mexnoEncr) : mexnoEncr;
            rcgvpMexnoEncr = StringUtils.isNotEmpty(rcgvpMexnoEncr) ? DbEncUtil.dec(rcgvpMexnoEncr) : rcgvpMexnoEncr;
        }
    }
}
