package com.kyowon.sms.wells.web.contract.risk.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.contract.risk.dto.WctcUserSellLimitMgtDto.SaveReq;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcUserSellLimitMgtDto.SearchReq;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcUserSellLimitMgtDto.SearchRes;
import com.kyowon.sms.wells.web.contract.risk.service.WctcUserSellLimitMgtService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTC] wells 사용자판매제한 관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/sales-limits")
public class WctcUserSellLimitMgtController {
    private final WctcUserSellLimitMgtService service;

    @ApiOperation(value = "사용자판매제한 관리", notes = "조회조건에 따른 사용자판매제한 관리 목록을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "startDate", value = "조회시작날짜", paramType = "query"),
        @ApiImplicitParam(name = "endDate", value = "조회마지막날짜", paramType = "query"),
        @ApiImplicitParam(name = "sell", value = "판매", paramType = "query"),
        @ApiImplicitParam(name = "channel", value = "채널", paramType = "query"),
        @ApiImplicitParam(name = "organization", value = "조직", paramType = "query"),
        @ApiImplicitParam(name = "user", value = "사용자", paramType = "query"),
        @ApiImplicitParam(name = "productName", value = "상품명", paramType = "query"),
        @ApiImplicitParam(name = "sellType", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellLimit", value = "판매제한", paramType = "query"),
    })
    @GetMapping("/users")
    public List<SearchRes> getSellLimitLists(
        @Valid
        SearchReq dto
    ) {
        return service.getSellLimitLists(dto);
    }

    @ApiOperation(value = "사용자판매제한관리 저장", notes = "추가 / 수정한 사용자 판매 제한 관리 목록을 저장한다.")
    @PostMapping("/users")
    public SaveResponse saveSellBase(
        @RequestBody
        @Valid
        @NotEmpty
        List<SaveReq> dtos
    ) {
        return SaveResponse.builder().processCount(service.saveSellBase(dtos)).build();
    }

    @ApiOperation(value = "사용자판매제한관리 삭제", notes = "사용자 판매 제한 관리 목록을 삭제")
    @DeleteMapping("/users")
    public SaveResponse removeSellBase(
        @RequestParam
        @NotEmpty
        List<String> sellBaseIds
    ) {
        return SaveResponse.builder().processCount(service.removeSellBase(sellBaseIds)).build();
    }
}
