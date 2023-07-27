package com.kyowon.sms.wells.web.contract.ordermgmt.rest;

import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaMembershipService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaMembershipDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaMembershipDto.SearchRes;

@Api(tags = "[WCTA] 멤버쉽 관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/membership")
public class WctaMembershipController {

    private final WctaMembershipService service;

    @ApiOperation(value = "멤버쉽 대상자 페이징 조회", notes = "멤버쉽 대상자 페이징 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "copnDvCd", value = "법인격구분코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "cstKnm", value = "고객명", paramType = "query"),
        @ApiImplicitParam(name = "cralLocaraTno", value = "전화번호1", paramType = "query"),
        @ApiImplicitParam(name = "mexnoEncr", value = "전화번호2", paramType = "query"),
        @ApiImplicitParam(name = "cralIdvTno", value = "전화번호3", paramType = "query"),
    })
    @GetMapping("/customers/paging")
    public PagingResult<SearchRes> getMembershipCustomerPages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getMembershipCustomerPages(dto, pageInfo);
    }

    @ApiOperation(value = "멤버쉽 대상자 수 조회", notes = "멤버쉽 대상자 수 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "copnDvCd", value = "법인격구분코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "cstKnm", value = "고객명", paramType = "query"),
        @ApiImplicitParam(name = "cralLocaraTno", value = "전화번호1", paramType = "query"),
        @ApiImplicitParam(name = "mexnoEncr", value = "전화번호2", paramType = "query"),
        @ApiImplicitParam(name = "cralIdvTno", value = "전화번호3", paramType = "query"),
    })
    @GetMapping("/customers/counts")
    public Integer getMembershipCustomerCount(
        @Valid
        SearchReq dto
    ) {
        return service.getMembershipCustomersCounts(dto);
    }
}
