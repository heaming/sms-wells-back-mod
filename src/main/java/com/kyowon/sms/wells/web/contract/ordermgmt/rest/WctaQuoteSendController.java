package com.kyowon.sms.wells.web.contract.ordermgmt.rest;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaQuoteSendDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaQuoteSendService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTA] 주문상세페이지 내부 팝업 - 가상계좌 메세지 보내기")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/contracts")
public class WctaQuoteSendController {
    private final WctaQuoteSendService service;

    @ApiOperation(value = "견적서 발송 이력 조회", notes = "견적서 발송 이력 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
    })
    @GetMapping("/quote-sends")
    public List<WctaQuoteSendDto.SearchRes> getQuoteSendHists(
        WctaQuoteSendDto.SearchReq dto
    ) {
        return service.getQuoteSendHists(dto);
    }

    @ApiOperation(value = "견적서 발송 정보 조회", notes = "견적서 발송 정보 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
    })
    @GetMapping("/quote-sends/inf")
    public WctaQuoteSendDto.SearchInfRes getQuoteSendInf(
        WctaQuoteSendDto.SearchReq dto
    ) {
        return service.getQuoteSendInf(dto);
    }

    @ApiOperation(value = "견적서 발송", notes = "견적서 발송")
    @PostMapping("/quote-sends")
    public int saveQuoteSend(
        @RequestBody
        WctaQuoteSendDto.SaveReq dto
    ) throws Exception {
        return service.saveQuoteSend(dto);
    }
}
