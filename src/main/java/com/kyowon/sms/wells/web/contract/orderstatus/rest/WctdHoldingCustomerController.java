package com.kyowon.sms.wells.web.contract.orderstatus.rest;

import static com.kyowon.sms.wells.web.contract.orderstatus.dto.WctdHoldingCustomerDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.orderstatus.dto.WctdHoldingCustomerDto.SearchRes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.orderstatus.service.WctdHoldingCustomerService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTD] 보류고객 관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/holding-customers")
public class WctdHoldingCustomerController {

    private final WctdHoldingCustomerService service;

    @ApiOperation(value = "보류고객 관리 페이징 조회", notes = "보류고객 페이징 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "StartDt", value = "접수시작일자", paramType = "query"),
        @ApiImplicitParam(name = "endDt", value = "접수종료일자", paramType = "query"),
        @ApiImplicitParam(name = "cttYn", value = "컨택여부: 미설치: Y, 미컨택: N", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getHoldingCustomerPages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getHoldingCustomerPages(dto, pageInfo);
    }

    @ApiOperation(value = "보류고객 관리 엑셀 다운로드", notes = "보류고객 관리 엑셀 다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "StartDt", value = "접수시작일자", paramType = "query"),
        @ApiImplicitParam(name = "endDt", value = "접수종료일자", paramType = "query"),
        @ApiImplicitParam(name = "cttYn", value = "컨택여부: 미설치: Y, 미컨택: N", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
    })
    @GetMapping
    public List<SearchRes> getHoldingCustomersForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getHoldingCustomers(dto);
    }
}
