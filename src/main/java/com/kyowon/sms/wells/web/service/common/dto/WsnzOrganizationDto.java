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
    // 매니저 조직 Search Result Dto
    @ApiModel(value = "WsnzOrganizationDto-SearchManagerOgRes")
    public record SearchManagerOgRes(
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
    @ApiModel(value = "WsnzOrganizationDto-SearchManagerRes")
    public record SearchManagerRes(
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

    // 엔지니어 조직 Search Result Dto
    @ApiModel(value = "WsnzOrganizationDto-SearchEngineerOgRes")
    public record SearchEngineerOgRes(
        String ogId,
        String ogTpCd,
        String ogCd,
        String ogNm,
        String ogCdNm,
        String hgrOgId
    ) {}

    // 엔지니어 Search Result Dto
    @ApiModel(value = "WsnzOrganizationDto-SearchEngineerRes")
    public record SearchEngineerRes(
        String ogTpCd,
        String prtnrNo,
        String prtnrNm,
        String prtnrNoNm,
        String pstnDvCd,
        String rsbDvCd,
        String rolDvCd,
        String ogId,
        String ogCd,
        String ogNm
    ) {}
}
