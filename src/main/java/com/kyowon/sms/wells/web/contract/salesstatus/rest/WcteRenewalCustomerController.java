package com.kyowon.sms.wells.web.contract.salesstatus.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.salesstatus.dto.WcteRenewalCustomerDto;
import com.kyowon.sms.wells.web.contract.salesstatus.service.WcteRenewalCustomerService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTE] 만료/재약정 고객대상 컨택 배정관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/contracts/renewal-customers")
public class WcteRenewalCustomerController {

    private final WcteRenewalCustomerService service;

    @ApiOperation(value = "만료 재약정 고객 컨택 배정 조회", notes = "만료 재약정 고객 컨택 배정 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "asnDvCd", value = "처리상세", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "dlqMcn", value = "연체개월", paramType = "query", example = "3"),
        @ApiImplicitParam(name = "dlqYn", value = "연체제외", paramType = "query", example = "Y"),
        @ApiImplicitParam(name = "srchPeriodGbn", value = "조회기간구분", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "cntrPdEnddtFrom", value = "만료일자From", paramType = "query", example = "20230404"),
        @ApiImplicitParam(name = "cntrPdEnddtTo", value = "만료일자To", paramType = "query", example = "20230407"),
        @ApiImplicitParam(name = "istNmnN", value = "렌탈차월", paramType = "query", example = "3"),
        @ApiImplicitParam(name = "hcsfVal", value = "대분류", paramType = "query", example = "01"),
        @ApiImplicitParam(name = "mcsfVal", value = "중분류", paramType = "query", example = "01003"),
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query", example = "pdCd"),
        @ApiImplicitParam(name = "pdNm", value = "상품명", paramType = "query", example = "입력한상품명"),
        @ApiImplicitParam(name = "dgr1LevlOgIds", value = "총괄단", paramType = "query"),
        @ApiImplicitParam(name = "dgr2LevlOgIds", value = "지역단", paramType = "query"),
        @ApiImplicitParam(name = "dgr3LevlOgIds", value = "지점", paramType = "query"),
        @ApiImplicitParam(name = "copnDvCd", value = "자료구분", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "cstAgYn", value = "마케팅동의여부", paramType = "query", example = "Y"),
    })
    @GetMapping("/paging")
    public PagingResult<WcteRenewalCustomerDto.SearchRes> getExnRstlCstContactAssigns(
        @Valid
        WcteRenewalCustomerDto.SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getExnRstlCstContactAssigns(dto, pageInfo);
    }

    @ApiOperation(value = "엑셀다운로드용 만료 재약정 고객 컨택 배정 조회 ", notes = "엑셀다운로드용 만료 재약정 고객 컨택 배정 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "asnDvCd", value = "처리상세", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "dlqMcn", value = "연체개월", paramType = "query", example = "3"),
        @ApiImplicitParam(name = "dlqYn", value = "연체제외", paramType = "query", example = "Y"),
        @ApiImplicitParam(name = "srchPeriodGbn", value = "조회기간구분", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "cntrPdEnddtFrom", value = "만료일자From", paramType = "query", example = "20230404"),
        @ApiImplicitParam(name = "cntrPdEnddtTo", value = "만료일자To", paramType = "query", example = "20230407"),
        @ApiImplicitParam(name = "istNmnN", value = "렌탈차월", paramType = "query", example = "3"),
        @ApiImplicitParam(name = "hcsfVal", value = "대분류", paramType = "query", example = "01"),
        @ApiImplicitParam(name = "mcsfVal", value = "중분류", paramType = "query", example = "01003"),
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query", example = "pdCd"),
        @ApiImplicitParam(name = "pdNm", value = "상품명", paramType = "query", example = "입력한상품명"),
        @ApiImplicitParam(name = "dgr1LevlOgIds", value = "총괄단", paramType = "query"),
        @ApiImplicitParam(name = "dgr2LevlOgIds", value = "지역단", paramType = "query"),
        @ApiImplicitParam(name = "dgr3LevlOgIds", value = "지점", paramType = "query"),
        @ApiImplicitParam(name = "copnDvCd", value = "자료구분", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "cstAgYn", value = "마케팅동의여부", paramType = "query", example = "Y"),
    })
    @GetMapping("/excel-download")
    public List<WcteRenewalCustomerDto.SearchRes> getExnRstlCstContactAssignsForExcelDownload(
        @Valid
        WcteRenewalCustomerDto.SearchReq dto
    ) {
        return service.getExnRstlCstContactAssignsForExcelDownload(dto);
    }
}
