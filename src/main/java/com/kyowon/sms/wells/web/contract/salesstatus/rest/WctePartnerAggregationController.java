package com.kyowon.sms.wells.web.contract.salesstatus.rest;

import static com.kyowon.sms.wells.web.contract.salesstatus.dto.WctePartnerAggregationDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.salesstatus.dto.WctePartnerAggregationDto.SearchRes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.salesstatus.service.WctePartnerAggregationService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTE] wells 플래너별 계약 집계정보 조회")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/partner-aggregations")
public class WctePartnerAggregationController {

    private final WctePartnerAggregationService service;

    @ApiOperation(value = "wells 플래너별 계약 집계정보 조회", notes = "wells 플래너별 계약 집계정보 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query"),
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형코드", paramType = "query"),
        @ApiImplicitParam(name = "ogCd", value = "조직코드", paramType = "query"),
    })
    @GetMapping
    public List<SearchRes> getPartnerAggregations(
        @Valid
        SearchReq dto
    ) {
        return service.getPartnerAggregations(dto);
    }
}
