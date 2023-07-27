package com.kyowon.sms.wells.web.contract.interfaces.dto;

import javax.validation.constraints.NotBlank;

import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctiRestipulationDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    //  Search Request Dto
    @Builder
    @ApiModel("WctiRestipulationDto-SearchReq")
    public record SearchReq(
        @JsonProperty("STPL_RCP_DT_FR")
        @Validate
        @NotBlank
        String stplRcpDtFr, // 재계약일자 FROM
        @JsonProperty("STPL_RCP_DT_TO")
        @Validate
        @NotBlank
        String stplRcpDtTo, ///재계약일자 TO
        @JsonProperty("CTT_PSIC_ID")
        String cttPsicId // 컨택담당ID
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    //  Search Result Dto
    @ApiModel("WctiRestipulationDto-SearchRes")
    public record SearchRes(
        @JsonProperty("STPL_RCP_DT")
        String stplRcpDt, // 약정접수일자
        @JsonProperty("CNTR_NO")
        String cntrNo, // 계약번호
        @JsonProperty("CNTR_SN")
        int cntrSn, // 계약일련번호
        @JsonProperty("BASE_PD_CD")
        String basePdCd, // 기준상품코드
        @JsonProperty("PD_NM")
        String pdNm, // 상품명
        @JsonProperty("RCP_PRTNR_NO")
        String rcpPrtnrNo, // 접수파트너번호
        @JsonProperty("RCP_PRTNR_NM")
        String rcpPrtnrNm, // 접수파트너명
        @JsonProperty("CNTR_TAM")
        int cntrTam, // 계약총액
        @JsonProperty("ACKMT_PERF_RT")
        int ackmtPerfRt, // 인정실적율
        @JsonProperty("ACKMT_PERF_AMT")
        int ackmtPerfAmt // 인정실적금액
    ) {}
}
