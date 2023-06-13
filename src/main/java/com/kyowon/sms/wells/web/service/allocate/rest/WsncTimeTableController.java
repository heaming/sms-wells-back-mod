package com.kyowon.sms.wells.web.service.allocate.rest;

import java.text.ParseException;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableSalesDto;
import com.kyowon.sms.wells.web.service.allocate.service.WsncTimeTableService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/time-tables")
@Api(tags = "[WSNC] 타임테이블 일정선택 RESET API")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsncTimeTableController {

    private final WsncTimeTableService service;

    @ApiOperation(value = "타임테이블 조회(판매)")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "", paramType = "query"),
        @ApiImplicitParam(name = "chnlDvCd", value = "", paramType = "query"), // GB_CD
        @ApiImplicitParam(name = "inGb", value = "", paramType = "query"), // P_IN_GB
        @ApiImplicitParam(name = "svDvCd", value = "", paramType = "query"), // P_DATA_GB
        @ApiImplicitParam(name = "wrkDt", value = "", paramType = "query"), // P_WRK_DT
        @ApiImplicitParam(name = "dataStatCd", value = "", paramType = "query"), // P_DATA_STUS
        @ApiImplicitParam(name = "svBizDclsfCd", value = "", paramType = "query"), // P_WRK_TYP_DTL
        @ApiImplicitParam(name = "empId", value = "", paramType = "query"), // P_USER_ID
        @ApiImplicitParam(name = "basePdCd", value = "", paramType = "query"), // P_GDS_CD
        @ApiImplicitParam(name = "sellDate", value = "", paramType = "query"), // P_SELDATE
        @ApiImplicitParam(name = "mkCo", value = "", paramType = "query"), // P_MK_CO
        @ApiImplicitParam(name = "returnurl", value = "", paramType = "query"), // returnurl
    })
    @GetMapping("/sales")
    public WsncTimeTableSalesDto.FindRes getTmeAssignSales(WsncTimeTableSalesDto.FindReq req)
        throws ParseException {
        return service.getTmeAssignSales(req);
    }

}
