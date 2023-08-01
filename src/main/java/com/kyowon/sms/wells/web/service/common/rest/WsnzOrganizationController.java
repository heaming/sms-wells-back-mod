package com.kyowon.sms.wells.web.service.common.rest;

import static com.kyowon.sms.wells.web.service.common.dto.WsnzOrganizationDto.*;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.common.service.WsnzOrganizationService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNZ] 파트너")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/organizations")
public class WsnzOrganizationController {
    private final WsnzOrganizationService service;

    @ApiOperation(value = "매니저 조직 총괄단 조회", notes = "매니저 조직 총괄단 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "authYn", value = "권한적용여부", paramType = "query")
    })
    @GetMapping("/general-division")
    public List<SearchManagerOgRes> getGeneralDivisions(
        @RequestParam(defaultValue = "Y")
        String authYn
    ) {
        return service.getGeneralDivisions(authYn);
    }

    @ApiOperation(value = "매니저 조직 지역단 조회", notes = "매니저 조직 지역단 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogId", value = "조직ID", paramType = "query"),
        @ApiImplicitParam(name = "authYn", value = "권한적용여부", paramType = "query")
    })
    @GetMapping("/regional-group")
    public List<SearchManagerOgRes> getRegionalGroups(
        @RequestParam
        String ogId,
        @RequestParam(defaultValue = "Y")
        String authYn
    ) {
        return service.getRegionalGroups(ogId, authYn);
    }

    @ApiOperation(value = "매니저 조직 지점 조회", notes = "매니저 조직 지점 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogId", value = "조직ID", paramType = "query"),
        @ApiImplicitParam(name = "authYn", value = "권한적용여부", paramType = "query")
    })
    @GetMapping("/branch")
    public List<SearchManagerOgRes> getBranchs(
        @RequestParam
        String ogId,
        @RequestParam(defaultValue = "Y")
        String authYn
    ) {
        return service.getBranchs(ogId, authYn);
    }

    @ApiOperation(value = "매니저 조회", notes = "매니저 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "dgr1LevlOgId", value = "1위계 조직ID", paramType = "query"),
        @ApiImplicitParam(name = "dgr2LevlOgId", value = "2위계 조직ID", paramType = "query"),
        @ApiImplicitParam(name = "dgr3LevlOgId", value = "3위계 조직ID", paramType = "query"),
        @ApiImplicitParam(name = "authYn", value = "권한적용여부", paramType = "query")
    })
    @GetMapping("/manager")
    public List<SearchManagerRes> getManagers(SearchPrtnrReq dto) {
        return service.getManagers(dto);
    }

    @ApiOperation(value = "엔지니어 조직 센터 조회", notes = "엔지니어 조직 센터 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "authYn", value = "권한적용여부", paramType = "query")
    })
    @GetMapping("/service-center")
    public List<SearchEngineerOgRes> getServiceCenters(
        @RequestParam(defaultValue = "Y")
        String authYn
    ) {
        return service.getServiceCenters(authYn);
    }

    @ApiOperation(value = "엔지니어 조회", notes = "엔지니어 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "dgr1LevlOgId", value = "1위계 조직ID", paramType = "query"),
        @ApiImplicitParam(name = "authYn", value = "권한적용여부", paramType = "query")
    })
    @GetMapping("/engineer")
    public List<SearchEngineerRes> getEngineers(SearchPrtnrReq dto) {
        return service.getEngineers(dto);
    }
}
