package com.kyowon.sms.wells.web.contract.common.rest;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.common.dto.WctzProductDto.SearchRes;
import com.kyowon.sms.wells.web.contract.common.service.WctzProductService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTZ] 상품 계약공통")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/product")
public class WctzProductController {
    private final WctzProductService service;

    @ApiOperation(value = "상품대분류 조회", notes = "상품분류 기본(TB_PDBS_PD_CLSF_BAS)의 대분류를 조회하여 상품분류 목록 조회")
    @GetMapping("/high-classes")
    public List<SearchRes> getHighClasses() {
        return service.getHighClasses();
    }

    @ApiOperation(value = "상품중분류 조회", notes = "상품분류 기본(TB_PDBS_PD_CLSF_BAS)의 중분류를 조회하여 상품분류 목록 조회")
    @GetMapping("/middle-classes")
    public List<SearchRes> getMiddleClasses() {
        return service.getMiddleClasses();
    }
}
