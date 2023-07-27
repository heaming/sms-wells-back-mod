package com.kyowon.sms.wells.web.contract.risk.rest;

import static com.kyowon.sms.wells.web.contract.risk.dto.WctcSiteAuditSellDto.*;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.risk.service.WctcSiteAuditSellService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTC] 현장감사 판매리스트")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/site-audit")
public class WctcSiteAuditSellController {

    private final WctcSiteAuditSellService service;

    @ApiOperation(value = "현장감사 판매리스트 페이징 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "dgr1LevlOgCd", value = "총괄단조건(1차레벨조직코드)", paramType = "query"),
        @ApiImplicitParam(name = "dgr2LevlOgCd", value = "지역단조건(2차레벨조직코드)", paramType = "query"),
        @ApiImplicitParam(name = "dgr3LevlOgCd", value = "지점조건(3차레벨조직코드)", paramType = "query"),
        @ApiImplicitParam(name = "sellPrtnrNo", value = "판매파트너", paramType = "query"),
        @ApiImplicitParam(name = "ptrmDv", value = "접수일자/설치일자 구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "dtStrt", value = "시작일", paramType = "query", required = true),
        @ApiImplicitParam(name = "dtEnd", value = "종료일", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrStatCd", value = "상태구분(계약상세상태코드)", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매구분조건(판매유형코드)", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매유형조건(판매유형상세코드)", paramType = "query"),
        @ApiImplicitParam(name = "pdMclsfId", value = "제품군조건(상품중분류ID)", paramType = "query"),
    })
    @GetMapping("/sells")
    public PagingResult<SearchRes> getSiteAuditSellPages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getSiteAuditSellPages(dto, pageInfo);
    }

    @ApiOperation(value = "현장감사 판매리스트 페이징 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "dgr1LevlOgCd", value = "총괄단조건(1차레벨조직코드)", paramType = "query"),
        @ApiImplicitParam(name = "dgr2LevlOgCd", value = "지역단조건(2차레벨조직코드)", paramType = "query"),
        @ApiImplicitParam(name = "dgr3LevlOgCd", value = "지점조건(3차레벨조직코드)", paramType = "query"),
        @ApiImplicitParam(name = "sellPrtnrNo", value = "판매파트너", paramType = "query"),
        @ApiImplicitParam(name = "ptrmDv", value = "접수일자/설치일자 구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "dtStrt", value = "시작일", paramType = "query", required = true),
        @ApiImplicitParam(name = "dtEnd", value = "종료일", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrStatCd", value = "상태구분(계약상세상태코드)", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매구분조건(판매유형코드)", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매유형조건(판매유형상세코드)", paramType = "query"),
        @ApiImplicitParam(name = "pdMclsfId", value = "제품군조건(상품중분류ID)", paramType = "query"),
    })
    @GetMapping("/sells/excel-download")
    public List<SearchDetailRes> getSiteAuditSellsForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getSiteAuditSellsForExcelDownload(dto);
    }
}
