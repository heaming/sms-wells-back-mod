package com.kyowon.sms.wells.web.contract.interfaces.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;

public class WctiContractDetailDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 계약상세 목록 Search Request Dto
    @ApiModel("WctiContractDetailDto-SearchReq")
    public record SearchReq(
        @JsonProperty("CST_NO")
        @NotBlank
        String cstNo,
        @JsonProperty("PD_NM")
        String pdNm,
        @JsonProperty("SELL_TP_CD")
        String sellTpCd
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 계약상세 목록 Search Response Dto
    @ApiModel("WctiContractDetailDto-SearchRes")
    public record SearchRes(
        @JsonProperty("CNTR_NO")
        String cntrNo, // 계약번호
        @JsonProperty("CNTR_SN")
        int cntrSn, // 계약일련번호
        @JsonProperty("CNTR_DTL_STAT_CD")
        String cntrDtlStatCd, // 계약상세상태코드
        @JsonProperty("CNTR_DTL_STAT_NM")
        String cntrDtlStatNm, // 계약상세상태코드명
        @JsonProperty("PD_HCLSF_ID")
        String pdHclsfId, // 상품대분류ID
        @JsonProperty("PD_HCLSF_NM")
        String pdHclsfNm, // 상품대분류명
        @JsonProperty("PD_MCLSF_ID")
        String pdMclsfId, // 상품중분류ID
        @JsonProperty("PD_MCLSF_NM")
        String pdMclsfNm, // 상품중분류명
        @JsonProperty("PD_LCLSF_ID")
        String pdLclsfId, // 상품소분류ID
        @JsonProperty("PD_LCLSF_NM")
        String pdLclsfNm, // 상품소분류명
        @JsonProperty("BASE_PD_CD")
        String basePdCd, // 기준상품코드
        @JsonProperty("BASE_PD_NM")
        String basePdNm, // 기준상품명
        @JsonProperty("CNTRT_NM")
        String cntrtNm, // 계약자명
        @JsonProperty("CNTR_DT")
        String cntrDt, // 계약일자
        @JsonProperty("CNTR_SELL_TP_CD")
        String cntrSellTpCd, // 판매유형코드
        @JsonProperty("CNTR_SELL_TP_NM")
        String cntrSellTpNm, // 판매유형코드명
        @JsonProperty("CNTR_CRAL_LOCARA_TNO")
        String cntrCralLocaraTno, // 휴대지역전화번호
        @JsonProperty("CNTR_MEXNO")
        String cntrMexno, // 휴대전화국번호
        @JsonProperty("CNTR_CRAL_IDV_TNO")
        String cntrCralIdvTno, // 휴대개별전화번호
        @JsonProperty("CNTR_LOCARA_TNO")
        String cntrLocaraTno, // 지역전화번호
        @JsonProperty("CNTR_EXNO")
        String cntrExno, // 전화국번호
        @JsonProperty("CNTR_IDV_TNO")
        String cntrIdvTno, // 개별전화번호
        @JsonProperty("CNTR_ADRPC_ID")
        String cntrAdrpcId, // 계약주소지ID
        @JsonProperty("CNTR_ADR")
        String cntrAdr, // 계약주소
        @JsonProperty("CNTR_DTL_ADR")
        String cntrDtlAdr, // 계약상세주소
        @JsonProperty("CRAL_LOCARA_TNO")
        String cralLocaraTno, // 휴대지역전화번호
        @JsonProperty("MEXNO")
        String mexno, // 휴대전화국번호
        @JsonProperty("CRAL_IDV_TNO")
        String cralIdvTno, // 휴대개별전화번호
        @JsonProperty("LOCARA_TNO")
        String locaraTno, // 지역전화번호
        @JsonProperty("EXNO")
        String exno, // 전화국번호
        @JsonProperty("IDV_TNO")
        String idvTno, // 개별전화번호
        @JsonProperty("ADRPC_ID")
        String adrpcId, // 계약주소지ID
        @JsonProperty("ADR")
        String adr, // 계약주소
        @JsonProperty("DTL_ADR")
        String dtlAdr, // 계약상세주소
        @JsonProperty("BRYY_MMDD")
        String bryyMmdd, // 생년월일
        @JsonProperty("SEX_DV_CD")
        String sexDvCd, // 성별구분코드
        @JsonProperty("ISTLL_NM")
        String istllNm // 설지차명
    ) {}
}
