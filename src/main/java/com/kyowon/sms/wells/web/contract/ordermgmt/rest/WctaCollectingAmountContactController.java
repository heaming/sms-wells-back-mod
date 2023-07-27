package com.kyowon.sms.wells.web.contract.ordermgmt.rest;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaCollectingAmountContactDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaCollectingAmountContactService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTA] 주문상세페이지 내부 팝업_집금컨택내용 관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/contracts/order-details/collecting-amount-contact")
public class WctaCollectingAmountContactController {

    private final WctaCollectingAmountContactService service;

    @ApiOperation(value = "집금컨택내용 조회", notes = "입력한 계약건에 대한 집금컨택내용 목록을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
    })
    @GetMapping
    public List<WctaCollectingAmountContactDto.SearchRes> getCollectingAmountContacts(
        @RequestParam
        String cntrNo,
        @RequestParam
        String cntrSn
    ) {
        return service.getCollectingAmountContacts(cntrNo, cntrSn);
    }
}
