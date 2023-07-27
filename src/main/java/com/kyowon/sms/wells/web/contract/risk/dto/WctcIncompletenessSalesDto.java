package com.kyowon.sms.wells.web.contract.risk.dto;

import javax.validation.constraints.NotBlank;

import com.kyowon.sms.common.web.contract.zcommon.utils.CtContractUtils;
import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctcIncompletenessSalesDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 기기변경 부정행위 단건 조회 Search Request Dto
    @Builder
    @ApiModel("IncompletenessSalesDto-SearchByCntrNoReq")
    public record SearchByCntrNoReq(
        String baseCntrNo,
        String baseCntrSn,
        String ojCntrNo,
        String ojCntrSn
    ) {}

    // 기기변경 부정행위 Search Request Dto
    @Builder
    @ApiModel("IncompletenessSalesDto-SearchReq")
    public record SearchReq(
        String apyCls,
        String strtDt,
        String endDt,
        String strtYm,
        String endYm,
        String cntrNo,
        String dgr2LevlOgCd,
        String dgr3LevlOgCd,
        String prtnrKnm
    ) {}

    // 기기변경 부정행위 Save Request Dto
    @Builder
    @ApiModel("IncompletenessSalesDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String rowState,
        String icptSellId,
        String icptSellExrDt,
        String icptSellProcsTpCd,
        String icptSellRsonCn,
        String baseCntrNo,
        int baseCntrSn,
        String ojCntrNo,
        int ojCntrSn,
        String orglDtaDlYn,
        String fnlMdfcDtm
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 기기변경 부정행위 Search Result Dto
    @ApiModel("IncompletenessSalesDto-SearchRes")
    public record SearchRes(
        String icptSellId,
        String icptSellProcsTpCd,
        String icptSellRsonCn,
        String baseCntrNo,
        String baseCntrSn,
        String ojCntrNo,
        String ojCntrSn,
        String icptSellExrDt,
        String cntrPdStrtdt,
        String baseUsedCpsYn,
        String baseChdvcRerntYn,
        String baseRcgvpKnm,
        String baseIstDt,
        String baseReqdDt,
        String baseIstGapMm,
        String baseCralLocaraTno,
        String baseMexnoEncr,
        String baseCralIdvTno,
        String baseMpno,
        String basePdCd,
        String baseAdr,
        String prtnrKnm,
        String prtnrNo,
        String dgr2LevlOgCd,
        String dgr3LevlOgCd,
        String ojChdvcRerntYn,
        String ojRcgvpKnm,
        String ojIstDt,
        String ojReqdDt,
        String ojIstGapMm,
        String ojPdCd,
        String ojAdr,
        String fnlMdfcDtm
    ) {
        public SearchRes {
            baseMpno = CtContractUtils.buildTno(baseCralLocaraTno, DbEncUtil.dec(baseMexnoEncr), baseCralIdvTno);
        }
    }
}
