package com.kyowon.sms.wells.web.contract.orderstatus.rest;

import static com.kyowon.sms.wells.web.contract.orderstatus.dto.WctdExpiredRetentionCntrDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.orderstatus.dto.WctdExpiredRetentionCntrDto.SearchRes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.orderstatus.service.WctdExpiredRetentionCntrService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTD] wells 렌탈 설치 약정만료 현황 조회")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/expired-retention-contracts")
public class WctdExpiredRetentionCntrController {

    private final WctdExpiredRetentionCntrService service;

    @ApiOperation(value = "wells 렌탈 설치 약정만료 현황 조회", notes = "아래 2가지의 데이터를 조회하여 반환한다. \n1) 렌탈 계약중 만료된 계약, \n2) 렌탈 계약중 만료되었으나, 멤버십 계약으로 연결된 계약")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrPdEnddtStrtdt", value = "계약상품종료일자 시작일", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrPdEnddtEnddt", value = "계약상품종료일자 종료일", paramType = "query", required = true),
        @ApiImplicitParam(name = "pdHclsfId", value = "상품대분류ID", paramType = "query"),
        @ApiImplicitParam(name = "pdMclsfId", value = "상품중분류ID", paramType = "query"),
        @ApiImplicitParam(name = "basePdCd", value = "기준상품코드", paramType = "query"),
        @ApiImplicitParam(name = "pdNm", value = "상품명", paramType = "query"),
        @ApiImplicitParam(name = "isExcdCan", value = "취소제외", paramType = "query"),
    })
    @GetMapping
    public List<SearchRes> getExpiredRetentionCntrs(
        @Valid
        SearchReq dto
    ) {
        return service.getExpiredRetentionCntrs(dto);
    }
}
