package com.kyowon.sms.wells.web.contract.interfaces.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctiRerentalOnplusoneDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // wells 재렌탈, 1+1 정보 조회 Search Request Dto
    @Builder
    @ApiModel("WctiRerentalOnplusoneDto-SearchReq")
    public record SearchReq(

        @JsonProperty("CNTR_NO")
        @NotBlank
        String cntrNo,
        @JsonProperty("CNTR_SN")
        @NotNull
        int cntrSn

    ) {}
    // *********************************************************
    // Result Dto
    // *********************************************************
    // wells 재렌탈, 1+1 정보 조회 Search Result Dto
    @ApiModel("WctiRerentalOnplusoneDto-SearchRes")
    public record SearchRes(
        @JsonProperty("CNTR_NO")
        String cntrNo, // 계약번호
        @JsonProperty("CNTR_SN")
        int cntrSn, // 계약일련번호
        @JsonProperty("SELL_TP_CD")
        String sellTpCd, // 판매유형코드
        @JsonProperty("SELL_TP_NM")
        String sellTpNm, // 판매유형명
        @JsonProperty("CNTR_DTL_STAT_CD")
        String cntrDtlStatCd, // 계약상세상태코드
        @JsonProperty("CNTR_DTL_STAT_NM")
        String cntrDtlStatNm, // 계약상세상태명
        @JsonProperty("BASE_PD_CD")
        String basePdCd, // 기준상품코드
        @JsonProperty("BASE_PD_NM")
        String basePdNm, // 기준상품명
        @JsonProperty("CNTR_CNFM_DT")
        String cntrCnfmDt, // 계약확정일시
        @JsonProperty("IST_DT")
        String istDt, // 설치일자
        @JsonProperty("KEEP_PTRM_ST_DT")
        String keepPtrmStDt, // 유지기간시작일자
        @JsonProperty("KEEP_PTRM_ED_DT")
        String keepPtrmEdDt, // 유지기간종료일자
        @JsonProperty("PRGS_NMN")
        int prgsNmn, // 진행차월
        @JsonProperty("SELL_AMT")
        int sellAmt, // 판매금액
        @JsonProperty("DSC_AMT")
        int dscAmt, // 할인금액
        @JsonProperty("TOT_DSC_AMT")
        int totDscAmt, // 총할인금액
        @JsonProperty("FNL_AMT")
        int fnlAmt // 최종금액
    ) {}
}
