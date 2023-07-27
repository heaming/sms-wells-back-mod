package com.kyowon.sms.wells.web.contract.risk.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.contract.risk.dto.WctcRiskAuditDto.SearchReq;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcRiskAuditDto.SearchRes;
import com.kyowon.sms.wells.web.contract.risk.service.WctcRiskAuditService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTC] wells 비정도영업 조치사항 조회")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/risk-audits")
public class WctcRiskAuditController {
    private final WctcRiskAuditService service;

    @ApiOperation(value = "비정도영업 조치사항 조회", notes = "조회조건에 따른 비정도영업 조치사항 목록을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "srchGbn", value = "조회구분", paramType = "query", required = true, example = "1"),
        @ApiImplicitParam(name = "dangOcStrtdt", value = "조회시작날짜", paramType = "query", example = "20220101"),
        @ApiImplicitParam(name = "dangOcEnddt", value = "조회마지막날짜", paramType = "query", example = "20221231"),
        @ApiImplicitParam(name = "dangOcStrtMonth", value = "조회시작월", paramType = "query"),
        @ApiImplicitParam(name = "dangOcEndMonth", value = "조회마지막월", paramType = "query"),
        @ApiImplicitParam(name = "gnrdv", value = "총괄단", paramType = "query"),
        @ApiImplicitParam(name = "rgrp", value = "지역단", paramType = "query"),
        @ApiImplicitParam(name = "brch", value = "지점", paramType = "query"),
        @ApiImplicitParam(name = "dangOjPrtnrNo", value = "사번", paramType = "query"),
        @ApiImplicitParam(name = "dangMngtPrtnrNo", value = "직위구분", paramType = "query"),
    })
    @GetMapping("/irregular-sales-actions")
    public List<SearchRes> getIrregularBznsInqr(
        @Valid
        SearchReq dto
    ) {
        return service.getIrregularBznsInqr(dto);
    }

    @ApiOperation(value = "비정도 영업 조치 사항 삭제", notes = "비정도 영업 조치 사항 삭제")
    @DeleteMapping("/irregular-sales-actions")
    public SaveResponse removeIrgBznsArbitArtc(
        @RequestParam
        @NotEmpty
        List<String> dangChkIds
    ) {
        return SaveResponse.builder().processCount(service.removeIrgBznsArbitArtc(dangChkIds)).build();
    }
}
