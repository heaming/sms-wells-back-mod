package com.kyowon.sms.wells.web.service.common.rest;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.common.dto.WsnzOrganizationDto.SearchOrganizationRes;
import com.kyowon.sms.wells.web.service.common.dto.WsnzOrganizationDto.SearchPrtnrReq;
import com.kyowon.sms.wells.web.service.common.dto.WsnzOrganizationDto.SearchPrtnrRes;
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
    @GetMapping("/general-division")
    public List<SearchOrganizationRes> getGeneralDivisions() {
        return service.getGeneralDivisions();
    }

    @ApiOperation(value = "매니저 조직 지역단 조회", notes = "매니저 조직 지역단 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogId", value = "조직ID", paramType = "query"),
    })
    @GetMapping("/regional-group")
    public List<SearchOrganizationRes> getRegionalGroups(
        @RequestParam
        String ogId
    ) {
        return service.getRegionalGroups(ogId);
    }

    @ApiOperation(value = "매니저 조직 지점 조회", notes = "매니저 조직 지점 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogId", value = "조직ID", paramType = "query"),
    })
    @GetMapping("/branch")
    public List<SearchOrganizationRes> getBranchs(
        @RequestParam
        String ogId
    ) {
        return service.getBranchOgs(ogId);
    }

    @ApiOperation(value = "매니저 조회", notes = "매니저 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "dgr1LevlOgId", value = "1위계 조직ID", paramType = "query"),
        @ApiImplicitParam(name = "dgr2LevlOgId", value = "2위계 조직ID", paramType = "query"),
        @ApiImplicitParam(name = "dgr3LevlOgId", value = "3위계 조직ID", paramType = "query"),
    })
    @GetMapping("/manager")
    public List<SearchPrtnrRes> getManagers(SearchPrtnrReq dto) {
        return service.getManagers(dto);
    }
}
