package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctaMembershipJoinTcntDto {
    // *********************************************************
    // Rsponse Dto
    // *********************************************************
    // 멤버십 가입 차수 조회 Search Rsponse Dto
    @Builder
    @ApiModel("WctaMembershipJoinTcntDto-SearchRes")
    public record SearchRes(
        String baseDtlCntrNo,
        String baseDtlCntrSn
    ) {}
}
