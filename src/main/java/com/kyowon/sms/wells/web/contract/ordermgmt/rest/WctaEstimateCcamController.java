package com.kyowon.sms.wells.web.contract.ordermgmt.rest;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaEstimateCcamDto.SearchReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaEstimateCcamDto.SearchRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaEstimateCcamService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTA] 주문상세페이지 내부 팝업_관리정보_위약금내역")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1)
public class WctaEstimateCcamController {

    private final WctaEstimateCcamService service;

    @ApiOperation(value = "위약금 내역 조회", notes = "입력한 계약건에 대한 위약금 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "slClYm", value = "기준년월", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
    })
    @GetMapping("/prepayment-sales-info")
    public List<SearchRes> getEstimateCcam(SearchReq dto) {
        return service.getEstimateCcam(dto);
    }
}
