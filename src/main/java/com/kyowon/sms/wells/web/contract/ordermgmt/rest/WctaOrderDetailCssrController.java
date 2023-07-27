package com.kyowon.sms.wells.web.contract.ordermgmt.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailCssrDto.*;
import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaOrderDetailCssrService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTA] 주문상세페이지 내부 팝업_현금영수증")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/contracts")
public class WctaOrderDetailCssrController {

    private final WctaOrderDetailCssrService service;

    @ApiOperation(value = "주문상세페이지 내부 팝업 현금영수증 기본정보 조회", notes = "계약상세번호와 고객번호로 현금영수증 기본정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "cstNo", value = "고객번호", paramType = "query"),
    })
    @GetMapping("/order-details/cash-sales-receipts")
    public FindBaseRcpRes getContractBaseInformation(
        @Valid
        FindBaseRcpReq dto
    ) {
        return service.getContractBaseInformation(dto);
    }

    @ApiOperation(value = "주문상세페이지 내부 팝업 현금영수증 조회(발행내역)", notes = "계약상세번호의 현금영수증 발행내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
    })
    @GetMapping("/order-details/cash-sales-receipt-pbls")
    public List<SearchRcpRes> getCashSalesReceipts(
        @RequestParam
        String cntrNo,
        @RequestParam
        String cntrSn
    ) {
        return service.getCashSalesReceipts(cntrNo, cntrSn);
    }

    @ApiOperation(value = "주문상세페이지 내부 팝업 현금영수증 기본정보변경", notes = "현금영수증 기본정보를 변경한다.")
    @PostMapping("/order-details/cash-sales-receipts")
    public SaveResponse saveCashSalesReceipts(
        @RequestBody
        SaveRcpReq dto
    ) {
        return SaveResponse.builder()
            .processCount(service.saveCashSalesReceipt(dto))
            .build();

    }

    @ApiOperation(value = "주문상세페이지 내부 팝업 현금영수증 재발행", notes = "1회용 현금영수증을 재발행한다.")
    @PostMapping("/order-details/cash-sales-receipt-rpbls")
    public SaveResponse saveCashSalesReceiptRpbls(
        @RequestBody
        List<SaveRpblsReq> dto
    ) {
        return SaveResponse.builder()
            .processCount(service.saveCashSalesReceiptRpbls(dto))
            .build();

    }
}
