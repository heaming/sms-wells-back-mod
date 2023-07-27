package com.kyowon.sms.wells.web.contract.ordermgmt.rest;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractIntegrationDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractIntegrationDto.SearchRes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaContractIntegrationService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTA] 회원 통합 조회")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/contracts")
public class WctaContractIntegrationController {

    private final WctaContractIntegrationService service;

    @ApiOperation(value = "회원 통합 조회", notes = "회원통합내역을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrCnfmStrtDtm", value = "계약확정시작일시", paramType = "query"),
        @ApiImplicitParam(name = "cntrCnfmFinsDtm", value = "계약확정종료일시", paramType = "query"),
        @ApiImplicitParam(name = "plarDv", value = "플래너구분(판매자/관리자)", paramType = "query"),
        @ApiImplicitParam(name = "prtnrDv", value = "파트너내역구분(사번/성명/부서코드)", paramType = "query"),
        @ApiImplicitParam(name = "hmnrscEmpno", value = "인사사원번호", paramType = "query"),
        @ApiImplicitParam(name = "prtnrKnm", value = "성명", paramType = "query"),
        @ApiImplicitParam(name = "ogCd", value = "부서코드(조직코드)", paramType = "query"),
        @ApiImplicitParam(name = "cntrCstSeltDv", value = "고객선택구분", paramType = "query"),
        @ApiImplicitParam(name = "cntrCstNo", value = "고객번호(세이프키)", paramType = "query"),
        @ApiImplicitParam(name = "cntrCstNm", value = "고객명(계약자/설치자)", paramType = "query"),
        @ApiImplicitParam(name = "cntrCstMpno", value = "휴대전화번호(계약자/설치자)", paramType = "query"),
        @ApiImplicitParam(name = "cralLocaraTno", value = "휴대지역전화번호(계약자/설치자)", paramType = "query"),
        @ApiImplicitParam(name = "mexnoEncr", value = "휴대전화국번호암호화(계약자/설치자)", paramType = "query"),
        @ApiImplicitParam(name = "cralIdvTno", value = "휴대개별전화번호(계약자/설치자)", paramType = "query"),
        @ApiImplicitParam(name = "bzrno", value = "사업자번호", paramType = "query"),
        @ApiImplicitParam(name = "sfkVal", value = "세이프키값", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "계약구분(판매유형코드)", paramType = "query"),
    })
    @GetMapping("/details/paging")
    public PagingResult<SearchRes> getContractIntegrationsPages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getContractIntegrationsPages(dto, pageInfo);
    }

    @ApiOperation(value = "회원 통합 조회", notes = "회원통합내역을 조회 후 엑셀 다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrCnfmStrtDtm", value = "계약확정시작일시", paramType = "query"),
        @ApiImplicitParam(name = "cntrCnfmFinsDtm", value = "계약확정종료일시", paramType = "query"),
        @ApiImplicitParam(name = "plarDv", value = "플래너구분(판매자/관리자)", paramType = "query"),
        @ApiImplicitParam(name = "prtnrDv", value = "파트너내역구분(사번/성명/부서코드)", paramType = "query"),
        @ApiImplicitParam(name = "hmnrscEmpno", value = "인사사원번호", paramType = "query"),
        @ApiImplicitParam(name = "prtnrKnm", value = "성명", paramType = "query"),
        @ApiImplicitParam(name = "ogCd", value = "부서코드(조직코드)", paramType = "query"),
        @ApiImplicitParam(name = "cntrCstSeltDv", value = "고객선택구분", paramType = "query"),
        @ApiImplicitParam(name = "cntrCstNo", value = "고객번호(세이프키)", paramType = "query"),
        @ApiImplicitParam(name = "cntrCstNm", value = "고객명(계약자/설치자)", paramType = "query"),
        @ApiImplicitParam(name = "cntrCstMpno", value = "휴대전화번호(계약자/설치자)", paramType = "query"),
        @ApiImplicitParam(name = "cralLocaraTno", value = "휴대지역전화번호(계약자/설치자)", paramType = "query"),
        @ApiImplicitParam(name = "mexnoEncr", value = "휴대전화국번호암호화(계약자/설치자)", paramType = "query"),
        @ApiImplicitParam(name = "cralIdvTno", value = "휴대개별전화번호(계약자/설치자)", paramType = "query"),
        @ApiImplicitParam(name = "bzrno", value = "사업자번호", paramType = "query"),
        @ApiImplicitParam(name = "sfkVal", value = "세이프키값", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "계약구분(판매유형코드)", paramType = "query"),
    })
    @GetMapping("/details/excel-download")
    public List<SearchRes> getContractIntegrationsExcels(
        @Valid
        SearchReq dto
    ) {
        return service.getContractIntegrationsExcels(dto);
    }
}
