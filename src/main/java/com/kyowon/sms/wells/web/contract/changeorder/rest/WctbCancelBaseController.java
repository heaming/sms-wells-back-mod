package com.kyowon.sms.wells.web.contract.changeorder.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbCancelBaseDto.*;
import com.kyowon.sms.wells.web.contract.changeorder.service.WctbCancelBaseService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTB] 취소등록")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = CtContractConst.REST_URL_V1 + "/changeorder")
public class WctbCancelBaseController {

    private final WctbCancelBaseService service;

    @ApiOperation(value = "취소등록 - 기본 정보 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "cstNo", value = "고객번호", paramType = "query"),
        @ApiImplicitParam(name = "dm", value = "조회년월", paramType = "query", required = true),
    })
    @GetMapping("/cancel-base-infos")
    public List<SearchRes> getCntrBases(
        @Valid
        SearchReq dto
    ) {
        return service.getCntrBases(dto);
    }

    @ApiOperation(value = "취소등록 - 위약금 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "reqDt", value = "요청일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "cancelDt", value = "취소일자", paramType = "query", required = true),
    })
    @GetMapping("/breach-promises")
    public FindSubDetailRes getCancelBases(
        @Valid
        SearchReq dto
    ) {
        return service.getCancelBases(dto);
    }

    @ApiOperation(value = "취소등록 - 저장", notes = "")
    @PostMapping("/cancel-registrations")
    public SaveResponse saveCancel(
        @RequestBody
        List<SaveReq> dtos
    ) {
        return SaveResponse.builder()
            .processCount(service.saveCancel(dtos))
            .build();
    }

    @ApiOperation(value = "취소등록 - 단건 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "cstNo", value = "고객번호", paramType = "query"),
        @ApiImplicitParam(name = "dm", value = "조회년월", paramType = "query", required = true),
    })
    @GetMapping("/cancel-infos")
    public FindDetailRes getCancelBase(
        @Valid
        SearchReq dto
    ) {
        return service.getCancelInfo(dto);
    }
}
