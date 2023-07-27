package com.kyowon.sms.wells.web.contract.salesstatus.rest;

import static com.kyowon.sms.wells.web.contract.salesstatus.dto.WcteRcpIstDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.salesstatus.dto.WcteRcpIstDto.SearchRes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.salesstatus.service.WcteRcpIstService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTE] 접수 및 설치 현황 조회")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/receipt-installations")
public class WcteRcpIstController {

    private final WcteRcpIstService service;

    @ApiOperation(value = "접수 및 설치 현황 조회 페이징 조회", notes = "접수 및 설치 현황 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "inqrDv", value = "조회구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrCnfmDtFr", value = "조회시작일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrCnfmDtTo", value = "조회종료일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "pdDvs", value = "제품구분", allowMultiple = true, paramType = "query", required = true),
        @ApiImplicitParam(name = "cstDvCd", value = "고객구분", paramType = "query"),
        @ApiImplicitParam(name = "sellInflwChnlDtlCd", value = "조직구분", paramType = "query"),
        @ApiImplicitParam(name = "dgr1LevlOgId", value = "총괄ID", paramType = "query"),
        @ApiImplicitParam(name = "dgr2LevlOgId", value = "지역단ID", paramType = "query"),
        @ApiImplicitParam(name = "dgr3LevlOgId", value = "지점ID", paramType = "query"),
        @ApiImplicitParam(name = "incentiveYn", value = "인센티브여부", paramType = "query"),
        @ApiImplicitParam(name = "pdHclsfId", value = "상품대분류ID", paramType = "query"),
        @ApiImplicitParam(name = "pdMclsfId", value = "상품중분류ID", paramType = "query"),
        @ApiImplicitParam(name = "basePdCd", value = "기본상품코드", paramType = "query"),
        @ApiImplicitParam(name = "pdNm", value = "상품명", paramType = "query"),
        @ApiImplicitParam(name = "sppDuedt", value = "예정일", paramType = "query"),
        @ApiImplicitParam(name = "sppDuedtYn", value = "예정일여부", paramType = "query"),
        @ApiImplicitParam(name = "mngSv", value = "관리서비스", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형코드", paramType = "query"),

    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getRcpIstPages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getRcpIstPages(dto, pageInfo);
    }

    @ApiOperation(value = "접수 및 설치 현황 조회 엑셀 다운로드", notes = "접수 및 설치 현황 조회 리스트 엑셀 다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "inqrDv", value = "조회구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrCnfmDtFr", value = "조회시작일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrCnfmDtTo", value = "조회종료일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "pdDvs", value = "제품구분", allowMultiple = true, paramType = "query", required = true),
        @ApiImplicitParam(name = "cstDvCd", value = "고객구분", paramType = "query"),
        @ApiImplicitParam(name = "sellInflwChnlDtlCd", value = "조직구분", paramType = "query"),
        @ApiImplicitParam(name = "dgr1LevlOgId", value = "총괄ID", paramType = "query"),
        @ApiImplicitParam(name = "dgr2LevlOgId", value = "지역단ID", paramType = "query"),
        @ApiImplicitParam(name = "dgr3LevlOgId", value = "지점ID", paramType = "query"),
        @ApiImplicitParam(name = "incentiveYn", value = "인센티브여부", paramType = "query"),
        @ApiImplicitParam(name = "pdHclsfId", value = "상품대분류ID", paramType = "query"),
        @ApiImplicitParam(name = "pdMclsfId", value = "상품중분류ID", paramType = "query"),
        @ApiImplicitParam(name = "basePdCd", value = "기본상품코드", paramType = "query"),
        @ApiImplicitParam(name = "pdNm", value = "상품명", paramType = "query"),
        @ApiImplicitParam(name = "sppDuedt", value = "예정일", paramType = "query"),
        @ApiImplicitParam(name = "sppDuedtYn", value = "예정일여부", paramType = "query"),
        @ApiImplicitParam(name = "mngSv", value = "관리서비스", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형코드", paramType = "query"),

    })
    @GetMapping("/excel-download")
    public List<SearchRes> getRcpIstsForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getRcpIstsForExcelDownload(dto);
    }
}
