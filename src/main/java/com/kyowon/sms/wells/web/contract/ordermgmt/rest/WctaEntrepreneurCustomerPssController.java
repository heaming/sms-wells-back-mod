package com.kyowon.sms.wells.web.contract.ordermgmt.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaEntrepreneurCustomerPssDto.SearchReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaEntrepreneurCustomerPssDto.SearchRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaEntrepreneurCustomerPssService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "wells 법인(사업자) 고객현황(일시불 및 렌탈)")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/contracts/corporates")
public class WctaEntrepreneurCustomerPssController {
    private final WctaEntrepreneurCustomerPssService service;

    @ApiOperation(value = "법인(사업자) 고객 현황(일시불 및 렌탈)", notes = "법인(사업자) 고객 현황(일시불 및 렌탈) 데이터를 조회하고 결과를 반환한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "sellTpCd", value = "업무유형", paramType = "query"),
        @ApiImplicitParam(name = "perfGbn", value = "실적구분", paramType = "query"),
        @ApiImplicitParam(name = "empGbn", value = "직원구분", paramType = "query"),
        @ApiImplicitParam(name = "canGbn", value = "취소구분", paramType = "query"),
        @ApiImplicitParam(name = "dateGbn", value = "조회기간구분", paramType = "query"),
        @ApiImplicitParam(name = "fromDate", value = "조회시작날짜", paramType = "query"),
        @ApiImplicitParam(name = "toDate", value = "조회끝날짜", paramType = "query"),
        @ApiImplicitParam(name = "pdHclsf", value = "상품분류(대)", paramType = "query"),
        @ApiImplicitParam(name = "pdMclsf", value = "상품분류(중)", paramType = "query"),
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query"),
        @ApiImplicitParam(name = "pdNm", value = "상품명", paramType = "query"),
        @ApiImplicitParam(name = "cstrGbn", value = "고객구분", paramType = "query"),
        @ApiImplicitParam(name = "fntGbn", value = "이체구분", paramType = "query"),
        @ApiImplicitParam(name = "fromogCd", value = "조직코드(시작)", paramType = "query"),
        @ApiImplicitParam(name = "toOgCd", value = "조직코드(끝)", paramType = "query"),
        @ApiImplicitParam(name = "prtnrNo", value = "판매자사번", paramType = "query"),
        @ApiImplicitParam(name = "incentiveGbn", value = "인센티브 대상여부", paramType = "query"),
        @ApiImplicitParam(name = "dlpnrItemNm", value = "업종", paramType = "query"),
        @ApiImplicitParam(name = "dlpnrBzclNm", value = "업태", paramType = "query"),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getEntrepreneurCustomerPssPages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getEntrepreneurCustomerPssPages(dto, pageInfo);
    }

    @ApiOperation(value = "법인(사업자) 고객 현황(일시불 및 렌탈)", notes = "법인(사업자) 고객 현황(일시불 및 렌탈) 데이터를 조회하고 결과를 반환한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "sellTpCd", value = "업무유형", paramType = "query"),
        @ApiImplicitParam(name = "perfGbn", value = "실적구분", paramType = "query"),
        @ApiImplicitParam(name = "empGbn", value = "직원구분", paramType = "query"),
        @ApiImplicitParam(name = "canGbn", value = "취소구분", paramType = "query"),
        @ApiImplicitParam(name = "dateGbn", value = "조회기간구분", paramType = "query"),
        @ApiImplicitParam(name = "fromDate", value = "조회시작날짜", paramType = "query"),
        @ApiImplicitParam(name = "toDate", value = "조회끝날짜", paramType = "query"),
        @ApiImplicitParam(name = "fromRental", value = "조회시작렌탈차월", paramType = "query"),
        @ApiImplicitParam(name = "toRental", value = "조회끝렌탈차월", paramType = "query"),
        @ApiImplicitParam(name = "pdHclsf", value = "상품분류(대)", paramType = "query"),
        @ApiImplicitParam(name = "pdMclsf", value = "상품분류(중)", paramType = "query"),
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query"),
        @ApiImplicitParam(name = "pdNm", value = "상품명", paramType = "query"),
        @ApiImplicitParam(name = "cstrGbn", value = "고객구분", paramType = "query"),
        @ApiImplicitParam(name = "fntGbn", value = "이체구분", paramType = "query"),
        @ApiImplicitParam(name = "fromogCd", value = "조직코드(시작)", paramType = "query"),
        @ApiImplicitParam(name = "toOgCd", value = "조직코드(끝)", paramType = "query"),
        @ApiImplicitParam(name = "prtnrNo", value = "판매자사번", paramType = "query"),
        @ApiImplicitParam(name = "incentiveGbn", value = "인센티브 대상여부", paramType = "query"),
        @ApiImplicitParam(name = "dlpnrItemNm", value = "업종", paramType = "query"),
        @ApiImplicitParam(name = "dlpnrBzclNm", value = "업태", paramType = "query"),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getEntrepreneurCustomerPssForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getEntrepreneurCustomerPssForExcelDownload(dto);
    }
}
