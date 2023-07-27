package com.kyowon.sms.wells.web.contract.interfaces.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctiRentalContractsDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 렌탈 계약 정보 조회 Search Request Dto
    @Builder
    @ApiModel("WctiRentalContractsDto-SearchReq")
    public record SearchReq(
        @JsonProperty("CNTR_NO")
        @NotBlank
        String cntrNo,
        @JsonProperty("CNTR_SN")
        int cntrSn
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 렌탈 계약 정보 조회 Search Result Dto
    @ApiModel("WctiRentalContractsDto-SearchRes")
    public record SearchRes(
        @JsonProperty("SCS_YN")
        String scsYn,
        @JsonProperty("CNTR_NO")
        String cntrNo,
        @JsonProperty("CNTR_SN")
        int cntrSn,
        @JsonProperty("CST_KNM")
        String cstKnm,
        @JsonProperty("BRYY_MMDD")
        String bryyMmdd,
        @JsonProperty("LOCARA_TNO")
        String locaraTno,
        @JsonProperty("EXNO_ENCR")
        String exnoEncr,
        @JsonProperty("IDV_TNO")
        String idvTno,
        @JsonProperty("CRAL_LOCARA_TNO")
        String cralLocaraTno,
        @JsonProperty("MEXNO_ENCR")
        String mexnoEncr,
        @JsonProperty("CRAL_IDV_TNO")
        String cralIdvTno,
        @JsonProperty("ADR")
        String adr,
        @JsonProperty("DTL_ADR")
        String dtlAdr,
        @JsonProperty("PD_NM")
        String pdNm,
        @JsonProperty("PD_QTY")
        int pdQty,
        @JsonProperty("STPL_PTRM")
        int stplPtrm,
        @JsonProperty("CNTR_AMT")
        int cntrAmt,
        @JsonProperty("CNTRAM_DSC_AMT")
        int cntramDscAmt,
        @JsonProperty("PD_BASE_AMT")
        int pdBaseAmt,
        @JsonProperty("FNL_AMT")
        int fnlAmt,
        @JsonProperty("SV_PRD")
        int svPrd,
        @JsonProperty("SV_PD_TP_CD")
        String svPdTpCd,
        @JsonProperty("CNTR_PTRM")
        int cntrPtrm,
        @JsonProperty("FNT_DV_CD")
        String fntDvCd,
        @JsonProperty("BNK_CRD_NM")
        String bnkCrdNm,
        @JsonProperty("ACHLDR_NM")
        String achldrNm,
        @JsonProperty("BNK_CRD_NO")
        String bnkCrdNo,
        @JsonProperty("FNT_DT")
        String fntDt,
        @JsonProperty("EXPDT_YM")
        String expdtYm,
        @JsonProperty("TXINV_PBL_YN")
        String txinvPblYn,
        @JsonProperty("EMADR")
        String emadr,
        @JsonProperty("DSC_MCN")
        int dscMcn,
        @JsonProperty("MM_DSC_AMT")
        int mmDscAmt,
        @JsonProperty("BZRNO")
        String bzrno,
        @JsonProperty("ALNC_P_AMT")
        int alncPAmt,
        @JsonProperty("RGLR_SPP_COMBI_YN")
        String rglrSppCombiYn,
        @JsonProperty("RGLR_SPP_CNTR_NO")
        String rglrSppCntrNo,
        @JsonProperty("RGLR_SPP_CNTR_SN")
        int rglrSppCntrSn,
        @JsonProperty("RGLR_SPP_CST_KNM")
        String rglrSppCstKnm,
        @JsonProperty("RGLR_SPP_COPN_DV_CD")
        String rglrSppCopnDvCd,
        @JsonProperty("RGLR_SPP_BRYY_MMDD")
        String rglrSppBryyMmdd,
        @JsonProperty("RGLR_SPP_BZRNO")
        String rglrSppBzrno,
        @JsonProperty("RGLR_SPP_LOCARA_TNO")
        String rglrSppLocaraTno,
        @JsonProperty("RGLR_SPP_EXNO_ENCR")
        String rglrSppExnoEncr,
        @JsonProperty("RGLR_SPP_IDV_TNO")
        String rglrSppIdvTno,
        @JsonProperty("RGLR_SPP_CRAL_LOCARA_TNO")
        String rglrSppCralLocaraTno,
        @JsonProperty("RGLR_SPP_MEXNO_ENCR")
        String rglrSppMexnoEncr,
        @JsonProperty("RGLR_SPP_CRAL_IDV_TNO")
        String rglrSppCralIdvTno,
        @JsonProperty("RGLR_SPP_ADR")
        String rglrSppAdr,
        @JsonProperty("RGLR_SPP_DTL_ADR")
        String rglrSppDtlAdr,
        @JsonProperty("RGLR_SPP_PRD")
        int rglrSppPrd,
        @JsonProperty("RGLR_SPP_PD_NM")
        String rglrSppPdNm,
        @JsonProperty("RGLR_SPP_SELL_AMT")
        int rglrSppSellAmt,
        @JsonProperty("RGLR_SPP_MM_BILL_AMT")
        int rglrSppMmBillAmt,
        @JsonProperty("RGLR_SPP_FNT_DV_CD")
        String rglrSppFntDvCd,
        @JsonProperty("RGLR_SPP_BNK_CRD_NM")
        String rglrSppBnkCrdNm,
        @JsonProperty("RGLR_SPP_ACHLDR_NM")
        String rglrSppAchldrNm,
        @JsonProperty("RGLR_SPP_BNK_CRD_NO")
        String rglrSppBnkCrdNo,
        @JsonProperty("RGLR_SPP_FNT_DT")
        String rglrSppFntDt,
        @JsonProperty("RGLR_SPP_EXPDT_YM")
        String rglrSppExpdtYm
    ) {}
}
