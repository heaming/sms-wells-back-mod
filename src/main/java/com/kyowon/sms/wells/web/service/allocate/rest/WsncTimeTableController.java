package com.kyowon.sms.wells.web.service.allocate.rest;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableDto.*;
import com.kyowon.sms.wells.web.service.allocate.service.WsncTimeTableService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/time-table")
@Api(tags = "[WSNC] 타임테이블 일정선택 RESET API")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsncTimeTableController {

    private final WsncTimeTableService service;

    @ApiOperation(value = "타임테이블 일정 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm,", value = "기준월", paramType = "query"),
        @ApiImplicitParam(name = "prevTag,", value = "조회구분", paramType = "query"),
        @ApiImplicitParam(name = "gbCd,", value = "", paramType = "query"),
        @ApiImplicitParam(name = "dataGb,", value = "", paramType = "query"),
        @ApiImplicitParam(name = "prtnrNo,", value = "파트너번호", paramType = "query"),
        @ApiImplicitParam(name = "selDate,", value = "방문예정일자", paramType = "query"),
        @ApiImplicitParam(name = "saleCd,", value = "상품코드", paramType = "query"),
        @ApiImplicitParam(name = "ordDt,", value = "", paramType = "query"),
        @ApiImplicitParam(name = "ordSeq,", value = "", paramType = "query"),
        @ApiImplicitParam(name = "wrkTypDtl,", value = "작업구분", paramType = "query"),
        @ApiImplicitParam(name = "zipno,", value = "우편번호코드", paramType = "query"),
        @ApiImplicitParam(name = "cntrCd,", value = "", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo,", value = "제품코드", paramType = "query"),
        @ApiImplicitParam(name = "mojongCd", value = "모종코드", paramType = "query"),
    })
    @GetMapping
    public SearchRes getTimeTable(
        SearchReq req
    ) {
        log.debug("----------------------------------- 타임테이블 일정 조회 -----------------------------------------");
        log.debug("baseYm: {}", req.baseYm());
        log.debug("prtnrNo:  {}", req.prtnrNo());
        log.debug("prevTag:  {}", req.prevTag());
        log.debug("dataGb:  {}", req.dataGb());
        log.debug("dataStus:  {}", req.dataStus());
        log.debug("cntrNo:  {}", req.cntrNo());
        log.debug("saleCd:  {}", req.saleCd());
        log.debug("wrkTypDtl:  {}", req.wrkTypDtl());
        return service.getTimeTable(req);
    }

}
