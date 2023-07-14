package com.kyowon.sms.wells.web.service.allocate.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncBsPeriodChartDto;
import com.kyowon.sms.wells.web.service.allocate.service.WsncBsPeriodChartService;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNC] 정기 BS주기표를 생성")
@RequiredArgsConstructor
@RestController
@RequestMapping(CommConst.REST_URL_V1 + "/sms/wells/service/before-service-period-charts")
public class WsncBsPeriodChartController {

    private final WsncBsPeriodChartService service;

    @ApiOperation(value = "정기 BS주기표를 생성(멤버십)", notes = "조회조건에 따른 정기 BS주기표를 생성(멤버십)")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true),
    })
    @PostMapping("/membership")
    public SaveResponse processBSPeriodChartMembership(WsncBsPeriodChartDto.SearchReq dto) throws Exception {
        return SaveResponse.builder().processCount(service.processBsPeriodChartBs03(dto, true)).build();
    }

    @ApiOperation(value = "정기 BS주기표를 생성(렌탈)", notes = "조회조건에 따른 정기 BS주기표를 생성(렌탈)")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true),
    })
    @PostMapping("/rental")
    public SaveResponse processBSPeriodChartRental(WsncBsPeriodChartDto.SearchReq dto) throws Exception {
        return SaveResponse.builder().processCount(service.processBsPeriodChartBs03(dto, false)).build();
    }

    @ApiOperation(value = "삼성전자 에어컨의 정기 방문 주기 생성", notes = "삼성전자 에어컨의 정기 방문 주기 생성")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true),
    })
    @PostMapping("/samsung-air-conditioner")
    public SaveResponse processBSPeriodChartBs04(WsncBsPeriodChartDto.SearchReq dto) throws Exception {
        return SaveResponse.builder().processCount(service.processBsPeriodChartBs04(dto)).build();
    }

    @ApiOperation(value = "홈카페 캡슐 B/S주기표를 생성", notes = "홈카페 캡슐 B/S주기표를 생성")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true),
    })
    @PostMapping("/home-cafe")
    public SaveResponse processBSPeriodChartBs01(WsncBsPeriodChartDto.SearchReq dto) throws Exception {
        return SaveResponse.builder().processCount(service.processBsPeriodChartBs01(dto)).build();
    }

    @ApiOperation(value = "웰스팜 모종 BS주기표를 생성", notes = "웰스팜 모종 BS주기표를 생성")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true),
    })
    @PostMapping("/wells-farm")
    public SaveResponse processBSPeriodChartBs05(WsncBsPeriodChartDto.SearchReq dto) throws Exception {
        return SaveResponse.builder().processCount(service.processBsPeriodChartBs05(dto)).build();
    }






    @ApiOperation(value = "모종 일시불 구매 고객 정보 및 방문 스케쥴 인서트업데이트", notes = "모종 일시불 구매 고객 정보 및 방문 스케쥴 인서트업데이트")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true),
    })
    @PostMapping("/plant-schedule")
    public SaveResponse processSpMkPlantSchedule(WsncBsPeriodChartDto.SearchReq dto) throws Exception {
        return SaveResponse.builder().processCount(service.processSpMkPlantSchedule(dto)).build();
    }

    @ApiOperation(value = "홍삼 캡슐 정기구매 고객 스케쥴 인서트업데이트", notes = "홍삼 캡슐 정기구매 고객 스케쥴 인서트업데이트")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true),
    })
    @PostMapping("/redginseng-schedule")
    public SaveResponse processSpMkRedginsengSchedule(WsncBsPeriodChartDto.SearchReq dto) throws Exception {
        return SaveResponse.builder().processCount(service.processSpMkRedginsengSchedule(dto)).build();
    }


}
