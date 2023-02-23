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
}
