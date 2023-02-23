package com.kyowon.sms.wells.web.service.common.dto;

import io.swagger.annotations.ApiModel;

public class ZwsnzWellsCodeDto {
    @ApiModel(value = "ZwsnzWellsCodeDto-SearchWorkingEngineersReq")
    public record SearchWorkingEngineersReq() {}

    @ApiModel(value = "ZwsnzWellsCodeDto-SearchWorkingEngineersRes")
    public record SearchWorkingEngineersRes(
        String codeId,
        String codeNm,
        String codeNm1,
        String codeNm2,
        String cphonIdvTno,
        String ogCd,
        String ogNm,
        String empDvVal,
        String empRsbDvVal,
        String empTpVal,
        String entcoDt,
        String rsgnDt,
        String empRolDvVal,
        String empDvValNm,
        String empRolDvValNm,
        String hgrDeptCd
    ) {}

    @ApiModel(value = "ZwsnzWellsCodeDto-SearchWareHouseReq")
    public record SearchWareHouseReq(
        String startYm,
        String endYm,
        String wareDvCd,
        String wareIchrNo,
        String hgrWareNo
    ) {}

    @ApiModel(value = "ZwsnzWellsCodeDto-SearchWareHouseRes")
    public record SearchWareHouseRes(
        String wareNo,
        String wareNm
    ) {}

    @ApiModel(value = "ZwsnzWellsCodeDto-SearchMonthStockReq")
    public record SearchMonthStockReq(
        String wareMngtPrtnrNo,
        String apyYm
    ) {}

    @ApiModel(value = "ZwsnzWellsCodeDto-SearchMonthStockRes")
    public record SearchMonthStockRes(
        String codeId,
        String codeName,
        String wareMngtPrtnrNo,
        String wareIchrNo,
        String wareDvCd,
        String hgrWareNo,
        String apyYm,
        String parentWarNm,
        String parentWareMngtPrtnrNo,
        String parentWareLocaraCd,
        String parentWareDvCd
    ) {}

    @ApiModel(value = "ZwsnzWellsCodeDto-SearchServiceCenterOrgsReq")
    public record SearchServiceCenterOrgsReq() {}
    @ApiModel(value = "ZwsnzWellsCodeDto-SearchServiceCenterOrgsRes")
    public record SearchServiceCenterOrgsRes(
        String bldCd,
        String cloDt,
        String cloYn,
        String cnrLocaraDvCd,
        String cpsnDtrcOgId,
        String dgPrtnrNo,
        String dtrcSbrncOgId,
        String hgrOgId,
        String mngtWidaDvCd,
        String ogAbbrNm,
        String ogCd,
        String ogClsfCd,
        String ogId,
        String ogLevlDvCd,
        String ogNm,
        String ogTpCd,
        String opDt,
        String psicNm,
        String sbrncDvCd,
        String sbrncYn,
        String tempSaveYn,
        String vdtcrPdDvCd
    ) {}

    @ApiModel(value = "ZwsnzWellsCodeDto-SearchAllEngineersReq")
    public record SearchAllEngineersReq(
        String hgrDeptCd
    ) {}

    @ApiModel(value = "ZwsnzWellsCodeDto-SearchAllEngineersRes")
    public record SearchAllEngineersRes(
        String codeId,
        String codeNm,
        String codeNm1,
        String codeNm2,
        String cphonIdvTno,
        String ogCd,
        String ogNm,
        String empDvVal,
        String empRsbDvVal,
        String empTpVal,
        String entcoDt,
        String rsgnDt,
        String empRolDvVal,
        String empDvValNm,
        String empRolDvValNm,
        String hgrDeptCd,
        String ogTpCd
    ) {}
}
