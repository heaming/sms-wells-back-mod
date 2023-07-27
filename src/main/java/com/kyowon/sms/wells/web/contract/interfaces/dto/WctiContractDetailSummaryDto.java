package com.kyowon.sms.wells.web.contract.interfaces.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;

public class WctiContractDetailSummaryDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 계약상세 요약 정보 Search Request Dto
    @ApiModel("WctiContractDetailSummaryDto-FindReq")
    public record FindReq(
        @NotBlank
        String CNTR_NO,
        @NotNull
        int CNTR_SN
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 계약상세 요약 정보 Search Result Dto
    @ApiModel("WctiContractDetailSummaryDto-FindRes")
    public record FindRes(
        String CNTRT_NM,
        String CNTRT_CRAL_LOCARA_TNO,
        String CNTRT_MEXNO,
        String CNTRT_CRAL_IDV_TNO,
        String IST_CST_NM,
        String IST_CRAL_LOCARA_TNO,
        String IST_MEXNO,
        String IST_CRAL_IDV_TNO,
        String IST_LOCARA_TNO,
        String IST_EXNO,
        String IST_IDV_TNO,
        String BASE_PD_CD,
        String BASE_PD_NM
    ) {}
}
