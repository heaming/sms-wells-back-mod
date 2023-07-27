package com.kyowon.sms.wells.web.contract.orderstatus.dto;

import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.kyowon.sms.common.web.contract.zcommon.constants.CtContractConst;
import com.kyowon.sms.common.web.contract.zcommon.utils.CtContractUtils;
import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctdExpiredRetentionCntrDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // wells 렌탈 설치 약정만료 현황 조회 Search Request Dto
    @Builder
    @ApiModel("WctdExpiredRetentionCntrDto-SearchReq")
    public record SearchReq(
        @NotBlank
        @ValidDate
        String cntrPdEnddtStrtdt,
        @NotBlank
        @ValidDate
        String cntrPdEnddtEnddt,
        String pdHclsfId,
        String pdMclsfId,
        String basePdCd,
        String pdNm,
        String isExcdCan,
        List<String> canDtlCds
    ) {
        public SearchReq {
            canDtlCds = Arrays.asList(CtContractConst.CntrStatCd.CANCELLATION.getDtlCds());
        }
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // wells 렌탈 설치 약정만료 현황 조회 Search Result Dto
    @ApiModel("WctdExpiredRetentionCntrDto-SearchRes")
    public record SearchRes(
        String exnDt,
        String cntrNo,
        String cntrSn,
        String cntrCstNo,
        String cstKnm,
        String pdClsf,
        String basePdCd,
        String pdNm,
        String istmMcn,
        String stplPtrm,
        String canDt,
        String canCstDutyUseExprYn,
        String slClDt,
        String dutyUseDt,
        String mshCntrNo,
        String mshCntrSn,
        String mshCntrDt,
        String mshJoinDt,
        String mshCanDt,
        String mshWdwalDt,
        String cntrtCralLocaraTno,
        String cntrtMexnoEncr,
        String cntrtCralIdvTno,
        String cntrtMpno,
        String istllCralLocaraTno,
        String istllMexnoEncr,
        String istllCralIdvTno,
        String istllMpno
    ) {
        public SearchRes {
            cntrtMpno = CtContractUtils.buildTno(cntrtCralLocaraTno, DbEncUtil.dec(cntrtMexnoEncr), cntrtCralIdvTno);
            istllMpno = CtContractUtils.buildTno(istllCralLocaraTno, DbEncUtil.dec(istllMexnoEncr), istllCralIdvTno);
        }
    }
}
