package com.kyowon.sms.wells.web.contract.ordermgmt.rest;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailDepositIzDto.SearchRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaOrderDetailDepositIzService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTA] 주문상세페이지 내부 팝업_입금내역")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/contracts/order-details/deposit-itemization")
public class WctaOrderDetailDepositIzController {

    private final WctaOrderDetailDepositIzService service;

    @ApiOperation(value = "관리정보 조회", notes = "입력한 계약건에 대한 관리정보 목록을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형코드", paramType = "query"),
        @ApiImplicitParam(name = "cntrCstNo", value = "고객번호", paramType = "query"),
    })
    @GetMapping
    public SearchRes getOrderDetailDepositIz(
        @RequestParam
        String cntrNo,
        @RequestParam
        String cntrSn,
        @RequestParam
        String sellTpCd,
        @RequestParam
        String cntrCstNo
    ) {
        return service.getOrderDetailDepositIz(cntrNo, cntrSn, sellTpCd, cntrCstNo);
    }
}
