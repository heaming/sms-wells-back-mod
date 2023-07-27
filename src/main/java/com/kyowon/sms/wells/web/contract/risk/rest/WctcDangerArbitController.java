package com.kyowon.sms.wells.web.contract.risk.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.contract.risk.dto.WctcDangerArbitDto.SaveReq;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcDangerArbitDto.SearchOrganizationRes;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcDangerArbitDto.SearchReq;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcDangerArbitDto.SearchRes;
import com.kyowon.sms.wells.web.contract.risk.service.WctcDangerArbitService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTC] wells 비정도영업 조치사항 관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/risk-audits/irregular-sales-actions")
public class WctcDangerArbitController {
    private final WctcDangerArbitService service;

    @ApiOperation(value = "비정도영업 조치사항 관리 조회", notes = "조회조건에 따른 비정도영업 조치사항 관리 목록을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "srchGbn", value = "조회구분", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "dangOcStrtdt", value = "조회시작날짜", paramType = "query", example = "20220101"),
        @ApiImplicitParam(name = "dangOcEnddt", value = "조회마지막날짜", paramType = "query", example = "20221231"),
        @ApiImplicitParam(name = "dangOcStrtMonth", value = "조회시작월", paramType = "query"),
        @ApiImplicitParam(name = "dangOcEndMonth", value = "조회마지막월", paramType = "query"),
        @ApiImplicitParam(name = "gnrdv", value = "총괄단", paramType = "query"),
        @ApiImplicitParam(name = "rgrp", value = "지역단", paramType = "query"),
        @ApiImplicitParam(name = "brch", value = "지점", paramType = "query"),
        @ApiImplicitParam(name = "dangOjPrtnrNo", value = "사번", paramType = "query"),
    })
    @GetMapping("/managerial-tasks")
    public List<SearchRes> getDangerArbitManagerial(
        @Valid
        SearchReq dto
    ) {
        return service.getDangerArbitManagerial(dto);
    }

    @ApiOperation(value = "조직정보조회(비정도 영업 조치사항)", notes = "행위자사번을 통해 조직정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "발생년월", paramType = "query"),
        @ApiImplicitParam(name = "prtnrNo", value = "행위자사번", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형코드", paramType = "query"),
    })
    @GetMapping("/organizations")
    public SearchOrganizationRes getOrganizationInfInqr(
        String baseYm,
        @NotEmpty
        String prtnrNo,
        String ogTpCd
    ) {
        return service.getOrganizationInfInqr(baseYm, prtnrNo, ogTpCd);
    }

    @ApiOperation(value = "비정도 영업 조치 사항 관리 삭제", notes = "비정도 영업 조치 사항 관리 삭제")
    @DeleteMapping("/managerial-tasks")
    public SaveResponse removeDangerArbitManagerial(
        @RequestBody
        @Valid
        @NotEmpty
        List<SaveReq> dtos
    ) {
        return SaveResponse.builder().processCount(service.removeDangerArbitManagerial(dtos)).build();
    }

    @ApiOperation(value = "비정도 영업 조치 사항 관리 저장", notes = "추가 / 수정한 비정도 영업 조치 사항 목록을 저장한다.")
    @PostMapping("/managerial-tasks")
    public SaveResponse saveDangerArbitManagerial(
        @RequestBody
        @Valid
        @NotEmpty
        List<SaveReq> dtos
    ) {
        return SaveResponse.builder().processCount(service.saveDangerArbitManagerial(dtos)).build();
    }
}
