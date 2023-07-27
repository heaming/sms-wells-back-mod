package com.kyowon.sms.wells.web.contract.salesstatus.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctePartnerAggregationDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 플래너별 계약 집계정보 조회 Search Request Dto
    @Builder
    @ApiModel("WctePartnerAggregationDto-SearchReq")
    public record SearchReq(
        String prtnrNo,
        String ogTpCd,
        String ogCd
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 플래너별 계약 집계정보 조회 Search Result Dto
    @ApiModel("WctePartnerAggregationDto-SearchRes")
    public record SearchRes(
        String prtnrKnm,
        String prtnrNo,
        String ogCd,
        int c20,
        int c40,
        int c60,
        int c50
    ) {}
}
