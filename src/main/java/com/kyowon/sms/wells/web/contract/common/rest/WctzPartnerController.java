package com.kyowon.sms.wells.web.contract.common.rest;

import static com.kyowon.sms.wells.web.contract.common.dto.WctzPartnerDto.SearchEntrepreneurBaseRes;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.common.dto.WctzPartnerDto.SearchBranchDivisionsRes;
import com.kyowon.sms.wells.web.contract.common.dto.WctzPartnerDto.SearchGeneralDivisionsRes;
import com.kyowon.sms.wells.web.contract.common.dto.WctzPartnerDto.SearchRegionalDivisionsRes;
import com.kyowon.sms.wells.web.contract.common.service.WctzPartnerService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.common.utils.DateUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTZ] 파트너 계약공통")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/partners")
public class WctzPartnerController {

    private final WctzPartnerService service;

    @ApiOperation(value = "wells 사업자 가입제한 대상 관리 - 사업자 기본 정보 (WELLS)", notes = "사업자 기본 정보를 조회한다")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "bzrno", value = "사업자번호", paramType = "query", required = true),
    })
    @GetMapping
    public List<SearchEntrepreneurBaseRes> getEntrepreneurBases(
        @RequestParam
        String bzrno
    ) {
        return service.getEntrepreneurBases(bzrno);
    }

    @ApiOperation(value = "총괄단 조회", notes = "월조직내역(TB_OGBS_MM_OG_IZ)의 1차레벨 조직정보를 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query"),
    })
    @GetMapping("/general-divisions")
    public List<SearchGeneralDivisionsRes> getGeneralDivisions(
        @RequestParam(required = false)
        String baseYm
    ) {
        // 조회조건 : 기준년월 (기본값 : 현재년월)
        String paramDt = StringUtils.defaultString(baseYm, DateUtil.getNowDayString());
        return service.getGeneralDivisions(paramDt);
    }

    @ApiOperation(value = "지역단 조회", notes = "월조직내역(TB_OGBS_MM_OG_IZ)의 2차레벨 조직정보를 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query"),
    })
    @GetMapping("/regional-divisions")
    public List<SearchRegionalDivisionsRes> getRegionalDivisions(
        @RequestParam(required = false)
        String baseYm
    ) {
        // 조회조건 : 기준년월 (기본값 : 현재년월)
        String paramDt = StringUtils.defaultString(baseYm, DateUtil.getNowDayString());
        return service.getRegionalDivisions(paramDt);
    }

    @ApiOperation(value = "지점 조회", notes = "월조직내역(TB_OGBS_MM_OG_IZ)의 3차레벨 조직정보를 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query"),
    })
    @GetMapping("/branch-divisions")
    public List<SearchBranchDivisionsRes> getBranchDivisions(
        @RequestParam(required = false)
        String baseYm
    ) {
        // 조회조건 : 기준년월 (기본값 : 현재년월)
        String paramDt = StringUtils.defaultString(baseYm, DateUtil.getNowDayString());
        return service.getBranchDivisions(paramDt);
    }

    @ApiOperation(value = "파트너 휴업 여부 검사", notes = "통합계약 파트너 휴업여부를 검사한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query"),
    })
    @GetMapping("/business-closes")
    public boolean isPartnerStpa(
        @RequestParam
        String prtnrNo,
        @RequestParam
        String ogTpCd
    ) {
        return service.isPartnerStpa(prtnrNo, ogTpCd);
    }
}
