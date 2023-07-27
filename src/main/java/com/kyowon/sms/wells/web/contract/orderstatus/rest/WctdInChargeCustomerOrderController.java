package com.kyowon.sms.wells.web.contract.orderstatus.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.orderstatus.dto.WctdInChargeCustomerOrderDto.SearchReq;
import com.kyowon.sms.wells.web.contract.orderstatus.dto.WctdInChargeCustomerOrderDto.SearchRes;
import com.kyowon.sms.wells.web.contract.orderstatus.service.WctdInChargeCustomerOrderService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTD] 담당 고객 및 주문 조회")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/myself-contracts")
public class WctdInChargeCustomerOrderController {

    private final WctdInChargeCustomerOrderService service;

    @ApiOperation(value = "담당 고객 및 주문 조회 페이징 조회", notes = "파트너 본인 담당의 고객 및 주문정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "cstKnm", value = "계약자명", paramType = "query"),
        @ApiImplicitParam(name = "cstNo", value = "고객번호", paramType = "query"),
        @ApiImplicitParam(name = "cralLocaraTno", value = "휴대지역전화번호", paramType = "query"),
        @ApiImplicitParam(name = "mexno", value = "휴대전화국번호", paramType = "query"),
        @ApiImplicitParam(name = "cralIdvTno", value = "휴대개별전화번호", paramType = "query"),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getInChargeCustomerOrderPages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getInChargeCustomerOrderPages(dto, pageInfo);
    }

    @ApiOperation(value = "담당 고객 및 주문 조회 페이징 조회 엑셀 다운로드", notes = "파트너 본인 담당의 고객 및 주문정보를 엑셀 다운로드한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "cstKnm", value = "계약자명", paramType = "query"),
        @ApiImplicitParam(name = "cstNo", value = "고객번호", paramType = "query"),
        @ApiImplicitParam(name = "cralLocaraTno", value = "휴대지역전화번호", paramType = "query"),
        @ApiImplicitParam(name = "mexno", value = "휴대전화국번호", paramType = "query"),
        @ApiImplicitParam(name = "cralIdvTno", value = "휴대개별전화번호", paramType = "query"),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getInChargeCustomerOrdersForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getInChargeCustomerOrdersForExcelDownload(dto);
    }
}
