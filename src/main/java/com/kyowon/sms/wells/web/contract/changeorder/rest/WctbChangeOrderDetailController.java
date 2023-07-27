package com.kyowon.sms.wells.web.contract.changeorder.rest;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbChangeOrderDetailDto.SearchReq;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbChangeOrderDetailDto.SearchRes;
import com.kyowon.sms.wells.web.contract.changeorder.service.WctbChangeOrderDetailService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTB] wells 주문 상세 변경 이력 조회")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/changeorders")
public class WctbChangeOrderDetailController {

    private final WctbChangeOrderDetailService service;

    @ApiOperation(value = "wells 주문 상세 변경 이력 조회", notes = "주문 변경에 대한 이력을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
    })
    @GetMapping("/order-change-history")
    public SearchRes getContractChangeArtcs(
        @Valid
        SearchReq dto
    ) {
        return service.getContractChangeArtcs(dto);
    }
}
