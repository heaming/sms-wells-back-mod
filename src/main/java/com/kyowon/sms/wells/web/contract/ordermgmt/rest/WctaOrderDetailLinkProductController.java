package com.kyowon.sms.wells.web.contract.ordermgmt.rest;

import com.sds.sflex.system.config.datasource.PageInfo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailLinkProductDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaOrderDetailLinkProductService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

@Api(tags = "[WCTA] 연계상품 리스트 조회")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/contracts/link-products")
public class WctaOrderDetailLinkProductController {

    private final WctaOrderDetailLinkProductService service;

    @ApiOperation(value = "연계상품 리스트 조회", notes = "입력한 계약건에 대한 연계상품 목록을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
    })
    @GetMapping("/paging")
    public PagingResult<WctaOrderDetailLinkProductDto.SearchRes> getLinkProducts(
        @RequestParam
        String cntrNo,
        @RequestParam
        String cntrSn,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getLinkProducts(cntrNo, cntrSn, pageInfo);
    }
}
