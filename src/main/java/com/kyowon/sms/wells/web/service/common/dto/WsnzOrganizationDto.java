package com.kyowon.sms.wells.web.service.common.dto;

import io.swagger.annotations.ApiModel;

public class WsnzOrganizationDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 매니저 Search Request Dto
    @ApiModel(value = "WsnzOrganizationDto-SearchPrtnrReq")
    public record SearchPrtnrReq(
        String dgr1LevlOgId,
        String dgr2LevlOgId,
        String dgr3LevlOgId
    ) {}
    // *********************************************************
    // Result Dto
    // *********************************************************
    // 조직 Search Result Dto
    @ApiModel(value = "WsnzOrganizationDto-SearchOrganizationRes")
    public record SearchOrganizationRes(
        String ogId,
        String ogTpCd,
        String ogCd,
        String ogNm,
        String ogCdNm,
        String hooOgTpCd,
        String hooPrtnrNo,
        String hooPrtnrNm,
        String hgrOgId,
        String hgrOgNm,
        String bizIchrNo,
        String bizIchrNm
    ) {}

    // 매니저 Search Result Dto
    @ApiModel(value = "WsnzOrganizationDto-SearchPrtnrRes")
    public record SearchPrtnrRes(
        String ogTpCd,
        String prtnrNo,
        String prtnrNm,
        String prtnrNoNm,
        String brnhId,
        String brnhCd,
        String bizId,
        String bizCd,
        String adminId,
        String adminCd
    ) {}
}
