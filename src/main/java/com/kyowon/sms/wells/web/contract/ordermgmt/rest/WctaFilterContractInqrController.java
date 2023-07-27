package com.kyowon.sms.wells.web.contract.ordermgmt.rest;

import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaFilterContractInqrService;
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

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaFilterContractInqrDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaFilterContractInqrDto.SearchRes;

@Api(tags = "고객필터정보관리 - 필터계약정보 조회")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1)
public class WctaFilterContractInqrController {

    private final WctaFilterContractInqrService service;

    @ApiOperation(value = " 고객필터정보관리 - 필터계약정보 조회 페이징 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "qryCond", value = "조회조건구분", paramType = "query"),
        @ApiImplicitParam(name = "filterYrMn", value = "필터등록년월", paramType = "query"),
        @ApiImplicitParam(name = "svPdTpCd", value = "용도구분", paramType = "query"),
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약상세번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약상세순번", paramType = "query"),
    })
    @GetMapping("/filters/paging")
    public PagingResult<SearchRes> getFilterContractInqrPages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getFilterContractInqrPages(dto, pageInfo);
    }

}

