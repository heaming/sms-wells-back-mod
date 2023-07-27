package com.kyowon.sms.wells.web.contract.ordermgmt.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailDepositRgstDto.SearchReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailDepositRgstDto.SearchRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaOrderDetailDepositRgstService;
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
public class WctaOrderDetailDepositRgstController {

    private final WctaOrderDetailDepositRgstService service;

    @ApiOperation(value = "주문상세페이지 내부 팝업_입금등록", notes = "입금등록 내역조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "rveDtFr", value = "입금일자(시작)", paramType = "query"),
        @ApiImplicitParam(name = "rveDtTo", value = "입금일자(종료)", paramType = "query"),
    })
    @GetMapping("/order-details/deposit-registration/itemizations")
    public List<SearchRes> getDepositRgstIzs(
        @Valid
        SearchReq dto
    ) {
        return service.getDepositRgstIzs(dto);
    }
}
