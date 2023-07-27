package com.kyowon.sms.wells.web.contract.orderstatus.rest;

import com.kyowon.sms.wells.web.contract.orderstatus.service.WctdSelfConversionService;
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

import java.util.List;

import static com.kyowon.sms.wells.web.contract.orderstatus.dto.WctdSelfConversionDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.orderstatus.dto.WctdSelfConversionDto.SearchRes;

@Api(tags = "[WCTD]  자가전환 조회")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/contracts/self-conversion")
public class WctdSelfConversionController {

    private final WctdSelfConversionService service;

    @ApiOperation(value = " 자가전환 조회 페이징 조회", notes = "자가전환 조회 페이징 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "startDt", value = "시작일", paramType = "query", required = true),
        @ApiImplicitParam(name = "endDt", value = "종료일", paramType = "query", required = true),
        @ApiImplicitParam(name = "pdHclsfId", value = "상품대분류", paramType = "query"),
        @ApiImplicitParam(name = "pdMclsfId", value = "상품대분류", paramType = "query"),
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query"),
        @ApiImplicitParam(name = "pdNm", value = "상품명", paramType = "query"),
        @ApiImplicitParam(name = "strtOgCd", value = "시작조직코드", paramType = "query"),
        @ApiImplicitParam(name = "endOgCd", value = "끝조직코드", paramType = "query"),
        @ApiImplicitParam(name = "copnDvCd", value = "자료구분", paramType = "query"),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getSelfConversionPages(
        SearchReq dto,
        PageInfo pageInfo
    ) {
        return service.getSelfConversionPages(dto, pageInfo);
    }

    @ApiOperation(value = " 자가전환 조회 엑셀 다운로드", notes = "자가전환 조회 엑셀 다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "startDt", value = "시작일", paramType = "query", required = true),
        @ApiImplicitParam(name = "endDt", value = "종료일", paramType = "query", required = true),
        @ApiImplicitParam(name = "pdHclsfId", value = "상품대분류", paramType = "query"),
        @ApiImplicitParam(name = "pdMclsfId", value = "상품대분류", paramType = "query"),
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query"),
        @ApiImplicitParam(name = "pdNm", value = "상품명", paramType = "query"),
        @ApiImplicitParam(name = "ogCd", value = "조직코드", paramType = "query"),
        @ApiImplicitParam(name = "copnDvCd", value = "자료구분", paramType = "query"),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getSelfConversionsForExcelDownload(
        SearchReq dto
    ) {
        return service.getSelfConversionsForExcelDownload(dto);
    }
}
