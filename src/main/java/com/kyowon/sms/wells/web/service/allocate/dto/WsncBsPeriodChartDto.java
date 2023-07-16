package com.kyowon.sms.wells.web.service.allocate.dto;

import io.swagger.annotations.ApiModel;

public class WsncBsPeriodChartDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WsncBsPeriodChartDto-SearchReq")
    public record SearchReq(
        String cntrNo,
        String cntrSn
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
//    @ApiModel(value = "WsncBsPeriodChartDto-SearchBaseInfoRes")
//    public record SearchBaseInfoRes(
//        String cntrNo,
//        String cntrSn,
//        String bfsvcSppStpRsonCd,
//        String sellTpCd,
//        String svPdCd,
//        String pdctPdCd,
//        String istDt,
//        int chekInstMths,
//        String cntrPdStrtdt
//    ) {}
//
//    @ApiModel(value = "WsncBsPeriodChartDto-Search06Res")
//    public record Search06Res(
//        int vstNmnN,
//        int totStplMcn
//    ) {}
//
//    @ApiModel(value = "WsncBsPeriodChartDto-Search07Res")
//    public record Search07Res(
//        String vstMths,
//        String wrkTypDtl,
//        String chngStg,
//        String itemKnd,
//        String partCd,
//        int qty,
//        String vstGb
//    ) {}
}
