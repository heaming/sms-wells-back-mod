package com.kyowon.sms.wells.web.contract.orderstatus.rest;

import static com.kyowon.sms.wells.web.contract.orderstatus.dto.WctdConfirmMshPsInqrDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.orderstatus.dto.WctdConfirmMshPsInqrDto.SearchRes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.orderstatus.service.WctdConfirmMshPsInqrService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "[WCTD] wells 확정 멤버십 현황 조회")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/contracts/memberships")
public class WctdConfirmMshPsInqrController {

    private final WctdConfirmMshPsInqrService service;

    @ApiOperation(value = "확정 멤버십 현황 조회 페이징 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "rcpStrtDt", value = "접수일자 시작일", paramType = "query", required = true),
        @ApiImplicitParam(name = "rcpEndDt", value = "접수일자 종료일", paramType = "query", required = true),
        @ApiImplicitParam(name = "pdHclsfId", value = "대분류ID", paramType = "query"),
        @ApiImplicitParam(name = "pdMclsfId", value = "중분류ID", paramType = "query"),
        @ApiImplicitParam(name = "basePdCd", value = "상품코드", paramType = "query"),
        @ApiImplicitParam(name = "cnfmStrtDt", value = "확정일자 시작일", paramType = "query", required = true),
        @ApiImplicitParam(name = "cnfmEndDt", value = "확정일자 종료일", paramType = "query", required = true),
        @ApiImplicitParam(name = "pdNm", value = "상품명", paramType = "query"),
        @ApiImplicitParam(name = "sellOgTpCd", value = "판매구분(조직유형코드)", paramType = "query"),
        @ApiImplicitParam(name = "frisuMshCrtYn", value = "자동생성제외 여부", paramType = "query"),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너사번", paramType = "query")
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getConfirmMshPsInqrPages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getConfirmMshPsInqrPages(dto, pageInfo);
    }

    @ApiOperation(value = "확정 멤버십 현황 조회 엑셀 다운로드", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "rcpStrtDt", value = "접수일자 시작일", paramType = "query", required = true),
        @ApiImplicitParam(name = "rcpEndDt", value = "접수일자 종료일", paramType = "query", required = true),
        @ApiImplicitParam(name = "pdHclsfId", value = "대분류ID", paramType = "query"),
        @ApiImplicitParam(name = "pdMclsfId", value = "중분류ID", paramType = "query"),
        @ApiImplicitParam(name = "basePdCd", value = "상품코드", paramType = "query"),
        @ApiImplicitParam(name = "cnfmStrtDt", value = "확정일자 시작일", paramType = "query", required = true),
        @ApiImplicitParam(name = "cnfmEndDt", value = "확정일자 종료일", paramType = "query", required = true),
        @ApiImplicitParam(name = "pdNm", value = "상품명", paramType = "query"),
        @ApiImplicitParam(name = "sellOgTpCd", value = "판매구분(조직유형코드)", paramType = "query"),
        @ApiImplicitParam(name = "frisuMshCrtYn", value = "자동생성제외 여부", paramType = "query"),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너사번", paramType = "query")
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getConfirmMshPsInqrsForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getConfirmMshPsInqrsForExcelDownload(dto);
    }
}
