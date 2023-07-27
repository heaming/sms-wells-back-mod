package com.kyowon.sms.wells.web.contract.ordermgmt.rest;

import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaRentalProductChngPsService;
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
import java.util.List;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaRentalProductChngPsDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaRentalProductChngPsDto.SearchRes;

@Api(tags = "[WCTA] 렌탈제품 교체 현황")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/rental-change-products")
public class WctaRentalProductChngPsController {

    private final WctaRentalProductChngPsService service;

    @ApiOperation(value = "렌탈제품 교체 현황 페이징 조회", notes = "렌탈제품 교체 현황 페이징 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "jobCd", value = "업무구분", paramType = "query"),
        @ApiImplicitParam(name = "strtdt", value = "설치일자", paramType = "query"),
        @ApiImplicitParam(name = "enddt", value = "설치일자", paramType = "query"),
        @ApiImplicitParam(name = "basePdCd", value = "상품코드", paramType = "query"),
        @ApiImplicitParam(name = "sellOgTpCd", value = "판매구분", paramType = "query"),
        @ApiImplicitParam(name = "sellPrtnrNo", value = "등록담당", paramType = "query")
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getRentalProductChngPsPages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getRentalProductChngPsPages(dto, pageInfo);
    }

    @ApiOperation(value = "렌탈제품 교체 현황 엑셀 다운로드", notes = "렌탈제품 교체 현황 엑셀 다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "jobCd", value = "업무구분", paramType = "query"),
        @ApiImplicitParam(name = "strtdt", value = "설치일자", paramType = "query"),
        @ApiImplicitParam(name = "enddt", value = "설치일자", paramType = "query"),
        @ApiImplicitParam(name = "basePdCd", value = "상품코드", paramType = "query"),
        @ApiImplicitParam(name = "sellOgTpCd", value = "판매구분", paramType = "query"),
        @ApiImplicitParam(name = "sellPrtnrNo", value = "등록담당", paramType = "query")
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getRentalProductChngPss(
        @Valid
        SearchReq dto
    ) {
        return service.getRentalProductChngPssForExcelDownload(dto);
    }
}
