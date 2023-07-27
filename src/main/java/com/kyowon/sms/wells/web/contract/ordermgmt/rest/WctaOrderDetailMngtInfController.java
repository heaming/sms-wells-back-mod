package com.kyowon.sms.wells.web.contract.ordermgmt.rest;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailMngtInfDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaOrderDetailMngtInfService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTA] 주문상세페이지 내부 팝업_관리정보")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/contracts")
public class WctaOrderDetailMngtInfController {

    private final WctaOrderDetailMngtInfService service;

    @ApiOperation(value = "관리정보 조회", notes = "입력한 계약건에 대한 관리정보 목록을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형코드", paramType = "query"),
    })
    @GetMapping("/order-details/management-information")
    public List<WctaOrderDetailMngtInfDto.SearchRes> getOrderDetailMngtInqr(
        @RequestParam
        String cntrNo,
        @RequestParam
        String cntrSn,
        @RequestParam
        String sellTpCd
    ) {
        return service.getOrderDetailMngtInqr(cntrNo, cntrSn, sellTpCd);
    }

    @ApiOperation(value = "관리정보(프로모션) 조회", notes = "입력한 계약건에 대한 관리정보 프로모션 목록을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
    })
    @GetMapping("/order-details/apply-promotions")
    public WctaOrderDetailMngtInfDto.SearchPmotFgptRes getPromotions(
        @RequestParam
        String cntrNo,
        @RequestParam
        String cntrSn
    ) {
        return service.getPromotions(cntrNo, cntrSn);
    }
}
