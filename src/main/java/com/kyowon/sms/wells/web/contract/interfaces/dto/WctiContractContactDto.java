package com.kyowon.sms.wells.web.contract.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctiContractContactDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 계약 컨택 현황 조회 Search Request Dto
    @Builder
    @ApiModel("WctiContractContactDto-SearchReq")
    public record SearchReq(
        @JsonProperty("CNTR_CNFM_DT_FR")
        @ValidDate
        String cntrCnfmDtFr,
        @JsonProperty("CNTR_CNFM_DT_TO")
        @ValidDate
        String cntrCnfmDtTo,
        @JsonProperty("SL_DT_FR")
        @ValidDate
        String slDtFr,
        @JsonProperty("SL_DT_TO")
        @ValidDate
        String slDtTo,
        @JsonProperty("CTT_PSIC_ID")
        String cttPsicId,
        @JsonProperty("SELL_TP_CD")
        String sellTpcd,
        @JsonProperty("ALNCMP_CD")
        String alncmpCd,
        @JsonProperty("ALOC_YN")
        String alocYn,
        @JsonProperty("CTT_RS_CD")
        String cttRsCd

    ) {}
    // *********************************************************
    // Result Dto
    // *********************************************************
    // 계약 컨택 현황 조회 Search Result Dto
    @ApiModel("WctiContractContactDto-SearchRes")
    public record SearchRes(
        @JsonProperty("CNTR_NO")
        String cntrNo,
        @JsonProperty("CNTR_SN")
        int cntrSn,
        @JsonProperty("CNTR_CNFM_DT")
        String cntrCnfmDt,
        @JsonProperty("SL_DT")
        String slDt,
        @JsonProperty("BASE_PD_CD")
        String basePdCd,
        @JsonProperty("PD_NM")
        String pdNm,
        @JsonProperty("CTT_RS_CD")
        String cttRsCd,
        @JsonProperty("CTT_RS_NM")
        String cttRsNm,
        @JsonProperty("CTT_PSIC_ID")
        String cttPsicId,
        @JsonProperty("CTT_PSIC_NM")
        String cttPsicNm,
        @JsonProperty("SELL_TP_CD")
        String sellTpCd,
        @JsonProperty("SELL_TP_NM")
        String sellTpNm,
        @JsonProperty("CNTR_CRAL_LOCARA_TNO")
        String cntrCralLocaraTno,
        @JsonProperty("CNTR_MEXNO")
        String cntrMexno,
        @JsonProperty("CNTR_CRAL_IDV_TNO")
        String cntrCralIdvTno,
        @JsonProperty("CNTR_LOCARA_TNO")
        String cntrLocaraTno,
        @JsonProperty("CNTR_EXNO")
        String cntrExno,
        @JsonProperty("CNTR_IDV_TNO")
        String cntrIdvTno,
        @JsonProperty("CNTR_ADRPC_ID")
        String cntrAdrpcId,
        @JsonProperty("CNTR_ADR")
        String cntrAdr,
        @JsonProperty("CNTR_DTL_ADR")
        String cntrDtlAdr,
        @JsonProperty("CRAL_LOCARA_TNO")
        String cralLocaraTno,
        @JsonProperty("MEXNO")
        String mexno,
        @JsonProperty("CRAL_IDV_TNO")
        String cralIdvTno,
        @JsonProperty("LOCARA_TNO")
        String locaraTno,
        @JsonProperty("EXNO")
        String exno,
        @JsonProperty("IDV_TNO")
        String idvTno,
        @JsonProperty("ADRPC_ID")
        String adrpcId,
        @JsonProperty("ADR")
        String adr,
        @JsonProperty("DTL_ADR")
        String dtlAdr,
        @JsonProperty("DSC_DV_NM")
        String dscDvNm
    ) {}
}
