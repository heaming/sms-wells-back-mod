package com.kyowon.sms.wells.web.service.common.rest;

import com.kyowon.sms.common.web.zcommon.constants.CommonConst;
import com.kyowon.sms.wells.web.service.common.dto.ZwsnzWellsCodeDto.*;
import com.kyowon.sms.wells.web.service.common.service.ZwsnzWellsCodeService;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(CommonConst.REST_URL_WELLS_COMMON + "/sms-wells-codes")
@Api(tags = "[WSNZ] 코드조회 RESET API")
@RequiredArgsConstructor
@Validated
@Slf4j
public class ZwsnzWellsCodeController {

    private final ZwsnzWellsCodeService service;

    @ApiOperation(value = "서비스센터 근무 엔지니어(Wells서비스관리팀 또는 Wells고객서비스부문) 조회")
    @GetMapping("/working-engineers")
    public List<SearchWorkingEngineersRes> getWorkingEngineers(
        SearchWorkingEngineersReq req
    ) {
        return service.getWorkingEngineers(req);
    }

    @ApiOperation(value = "창고조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "startYm", value = "적용년월시작", paramType = "query"),
        @ApiImplicitParam(name = "endYm", value = "적용년월종료", paramType = "query"),
        @ApiImplicitParam(name = "wareDvCd", value = "창고구분코드", paramType = "query"),
        @ApiImplicitParam(name = "wareIchrNo", value = "창고담당번호", paramType = "query"),
        @ApiImplicitParam(name = "hgrWareNo", value = "상위창고번호", paramType = "query"),
    })
    @GetMapping("/ware-houses")
    public List<SearchWareHouseRes> getWareHouses(
        SearchWareHouseReq req
    ) {
        return service.getWareHouses(req);
    }

    @ApiOperation(value = "월별 창고내역 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "wareMngtPrtnrNo", value = "창고관리자", paramType = "query"),
        @ApiImplicitParam(name = "apyYm", value = "적용일자", paramType = "query"),
    })
    @GetMapping("/month-stocks")
    public List<SearchMonthStockRes> getMonthStocks(
        SearchMonthStockReq req
    ) {
        return service.getMonthStocks(req);
    }

    @ApiOperation(value = "서비스센터 조직 조회")
    @GetMapping("/service-center-orgs")
    public List<SearchServiceCenterOrgsRes> getServiceCenterOrgs(
        SearchServiceCenterOrgsReq req
    ) {
        return service.getServiceCenterOrgs(req);
    }

    @ApiOperation(value = "서비스센터 엔지니어 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "hgrDeptCd", value = "상위부서코드", paramType = "query", example = "00810"),
    })
    @GetMapping("/all-engineers")
    public List<SearchAllEngineersRes> getAllEngineers(
        SearchAllEngineersReq req
    ) {
        return service.getAllEngineers(req);
    }
}
