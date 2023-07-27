package com.kyowon.sms.wells.web.contract.ordermgmt.rest;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaTaxInvoiceInquiryDto.SaveReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaTaxInvoiceInquiryDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaTaxInvoiceInquiryService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTA] 주문상세페이지 내부 팝업_세금계산서, 계산서")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/contract-info/tax-Invoices")
public class WctaTaxInvoiceInquiryController {

    private final WctaTaxInvoiceInquiryService service;

    @ApiOperation(value = "주문상세페이지 내부 팝업_세금계산서, 계산서 조회", notes = "계약번호와 계약일련번호로 계산서를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true),
    })
    @GetMapping
    public WctaTaxInvoiceInquiryDvo getTaxInvoiceInquiry(
        @RequestParam
        String cntrNo,
        @RequestParam
        int cntrSn
    ) {
        return service.getTaxInvoiceInquiry(cntrNo, cntrSn);
    }

    @ApiOperation(value = "주문상세페이지 내부 팝업_세금계산서, 계산서 저장", notes = "계약번호와 계약일련번호로 계산서를 저장한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true),
    })
    @PostMapping
    public String saveTaxInvoice(
        @RequestBody
        SaveReq dto
    ) {
        return service.saveTaxInvoice(dto);
    }
}
