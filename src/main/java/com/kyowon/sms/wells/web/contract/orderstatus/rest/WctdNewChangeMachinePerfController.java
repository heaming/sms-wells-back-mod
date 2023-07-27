package com.kyowon.sms.wells.web.contract.orderstatus.rest;

import static com.kyowon.sms.wells.web.contract.orderstatus.dto.WctdNewChangeMachinePerfDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.orderstatus.dto.WctdNewChangeMachinePerfDto.SearchRes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.orderstatus.service.WctdNewChangeMachinePerfService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTD] 신규/기변 실적 조회")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/contracts/new-machine-changes")
public class WctdNewChangeMachinePerfController {

    private final WctdNewChangeMachinePerfService service;

    @ApiOperation(value = "신규/기변 실적 조회 페이징 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfStrtDt", value = "실적 시작일", paramType = "query", defaultValue = "20200101", required = true),
        @ApiImplicitParam(name = "perfEndDt", value = "실적 종료일", paramType = "query", defaultValue = "99991231", required = true),
        @ApiImplicitParam(name = "perfDv", value = "실적구분", paramType = "query", defaultValue = "T", required = true),
        @ApiImplicitParam(name = "optnDv", value = "가동구분", paramType = "query"),
        @ApiImplicitParam(name = "inqrDv", value = "조회구분", paramType = "query", defaultValue = "P", required = true),
        @ApiImplicitParam(name = "dgr1LevlOgId", value = "총괄단", paramType = "query"),
        @ApiImplicitParam(name = "dgr1LevlOgId", value = "지역단", paramType = "query"),
        @ApiImplicitParam(name = "dgr1LevlOgId", value = "지점", paramType = "query"),
        @ApiImplicitParam(name = "sellOgTpCd", value = "판매조직구분", defaultValue = "W01", paramType = "query")
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getNewChangeMachinePerfPages(
            @Valid
            SearchReq dto,
            @Valid
            PageInfo pageInfo
    ) {
        return service.getNewChangeMachinePerfPages(dto, pageInfo);
    }


    @ApiOperation(value = "신규/기변 실적 조회 합계 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfStrtDt", value = "실적 시작일", paramType = "query", defaultValue = "20200101", required = true),
        @ApiImplicitParam(name = "perfEndDt", value = "실적 종료일", paramType = "query", defaultValue = "99991231", required = true),
        @ApiImplicitParam(name = "perfDv", value = "실적구분", paramType = "query", defaultValue = "T", required = true),
        @ApiImplicitParam(name = "optnDv", value = "가동구분", paramType = "query"),
        @ApiImplicitParam(name = "inqrDv", value = "조회구분", paramType = "query", defaultValue = "P", required = true),
        @ApiImplicitParam(name = "dgr1LevlOgId", value = "총괄단", paramType = "query"),
        @ApiImplicitParam(name = "dgr1LevlOgId", value = "지역단", paramType = "query"),
        @ApiImplicitParam(name = "dgr1LevlOgId", value = "지점", paramType = "query"),
        @ApiImplicitParam(name = "sellOgTpCd", value = "판매조직구분", defaultValue = "W01", paramType = "query")
    })
    @GetMapping("/summary")
    public SearchRes getNewChangeMachinePerfSummary(
            @Valid
            SearchReq dto
    ) {
        return service.getNewChangeMachinePerfSumm(dto);
    }

    @ApiOperation(value = "신규/기변 실적 조회 엑셀 다운로드", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getNewChangeMachinePerfsForExcelDownload(
            @Valid
            SearchReq dto
    ) {
        return service.getNewChangeMachinePerfsForExcelDownload(dto);
    }
}
