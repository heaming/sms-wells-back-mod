package com.kyowon.sms.wells.web.contract.ordermgmt.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailDto.*;
import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaOrderDetailService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTA] wells 주문 상세 조회")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/contracts")
public class WctaOrderDetailController {

    private final WctaOrderDetailService service;

    @ApiOperation(value = "wells 연관계약 리스트 조회", notes = "고객상세조회 화면 內 1+1 접수정보 조회 기능으로 적용")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
    })
    @GetMapping("/related-contracts")
    public WctaOrderDetailDto.SearchRes getRelatedContracts(
        @Valid
        SearchReq dto
    ) {
        return service.getRelatedContracts(dto);
    }

    @ApiOperation(value = "wells 주문 상세(상세계약목록)", notes = "주문 상세(상세계약목록)정보 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrCstNo", value = "고객번호", paramType = "query"),
    })
    @GetMapping("/order-details/customer-bases/contract-lists")
    public List<SearchContractListsRes> getContractLists(
        @Valid
        SearchReq dto
    ) {
        return service.getContractLists(dto);
    }

    @ApiOperation(value = "wells 주문 상세(고객기본정보)", notes = "주문 상세(고객기본정보)정보 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
    })
    @GetMapping("/order-details/customer-bases")
    public List<SearchCustomerBaseRes> getCustomerBase(
        @Valid
        SearchReq dto
    ) {
        return service.getCustomerBase(dto);
    }

    @ApiOperation(value = "wells 주문 상세(거래명세서목록조회-입금내역서)", notes = "주문 상세(거래명세서목록-입금내역서)정보 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrCstNo", value = "고객번호", paramType = "query"),
    })
    @GetMapping("/order-details/specification/deposit-itemizations")
    public List<SearchDepositItemizationsRes> getDepositItemizations(
        @Valid
        SearchTradeSpecificationSheetReq dto
    ) {
        return service.getDepositItemizations(dto);
    }

    @ApiOperation(value = "wells 주문 상세(거래명세서목록조회-거래명세서)", notes = "주문 상세(거래명세서목록-거래명세서)정보 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrCstNo", value = "고객번호", paramType = "query"),
    })
    @GetMapping("/order-details/specification/trade-specification")
    public List<SearchTradeSpecificationSheetRes> getTradeSpcshs(
        @Valid
        SearchTradeSpecificationSheetReq dto
    ) {
        return service.getTradeSpcshs(dto);
    }

    @ApiOperation(value = "wells 주문 상세(거래명세서목록조회-카드매출전표)", notes = "주문 상세(거래명세서목록-카드매출전표)정보 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrCstNo", value = "고객번호", paramType = "query"),
    })
    @GetMapping("/order-details/specification/card-sales-slips")
    public List<SearchCardSalesSlipsRes> getCardSalesSlips(
        @Valid
        SearchTradeSpecificationSheetReq dto
    ) {
        return service.getCardSalesSlips(dto);
    }

    @ApiOperation(value = "wells 주문 상세(거래명세서목록조회-계약사항)", notes = "주문 상세(거래명세서목록-계약사항)정보 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrCstNo", value = "고객번호", paramType = "query"),
    })
    @GetMapping("/order-details/specification/contract-articles")
    public List<SearchContractArticlesRes> getContractArticles(
        @Valid
        SearchTradeSpecificationSheetReq dto
    ) {
        return service.getContractArticles(dto);
    }

    @ApiOperation(value = "wells 주문 상세(거래명세서목록조회-계약목록)", notes = "주문 상세(거래명세서목록-계약목록)정보 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrCstNo", value = "고객번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrCnfmStrtDt", value = "기간(시작일자)", paramType = "query"),
        @ApiImplicitParam(name = "cntrCnfmEndDt", value = "기간(종료일자)", paramType = "query"),
    })
    @GetMapping("/order-details/specification/contracts")
    public List<SearchContractsRes> getContracts(
        @Valid
        SearchTradeSpecificationSheetReq dto
    ) {
        return service.getContracts(dto);
    }
}
