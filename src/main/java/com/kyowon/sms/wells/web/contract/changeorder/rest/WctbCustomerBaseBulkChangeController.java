package com.kyowon.sms.wells.web.contract.changeorder.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbCustomerBaseBulkChangeDto;
import com.kyowon.sms.wells.web.contract.changeorder.service.WctbCustomerBaseBulkChangeService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTB] wells 주문 상세 변경 이력 조회")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/changeorder")
public class WctbCustomerBaseBulkChangeController {

    private final WctbCustomerBaseBulkChangeService service;

    @ApiOperation(value = "고객기준 일괄변경 대상 조회(자동이체,설치자명,세금계산서발행여부)", notes = "고객기준 일괄변경 대상 조회(자동이체,설치자명,세금계산서발행여부)")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "sellTpCd", value = "업무구분", paramType = "query"),
        @ApiImplicitParam(name = "prcDvCd", value = "처리구분", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrCstNo", value = "고객번호", paramType = "query"),
        @ApiImplicitParam(name = "rcpDtFrom", value = "접수기간From", paramType = "query"),
        @ApiImplicitParam(name = "rcpDtTo", value = "접수기간To", paramType = "query"),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query"),
        @ApiImplicitParam(name = "emadr", value = "이메일", paramType = "query"),
        @ApiImplicitParam(name = "cardAccNo", value = "계좌/카드번호", paramType = "query"),
        @ApiImplicitParam(name = "ogCd", value = "조직코드", paramType = "query"),
    })
    @GetMapping("/change-automatic-fnts")
    public List<WctbCustomerBaseBulkChangeDto.SearchCustomerRes> getBulkChangeObjects(
        @Valid
        WctbCustomerBaseBulkChangeDto.SearchReq dto
    ) {
        return service.getBulkChangeObjects(dto);
    }

    @ApiOperation(value = "고객기준 일괄변경 대상 조회(플래너변경)", notes = "고객기준 일괄변경 대상 조회(플래너변경)")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "sellTpCd", value = "업무구분", paramType = "query"),
        @ApiImplicitParam(name = "prcDvCd", value = "처리구분", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrCstNo", value = "고객번호", paramType = "query"),
        @ApiImplicitParam(name = "rcpDtFrom", value = "접수기간From", paramType = "query"),
        @ApiImplicitParam(name = "rcpDtTo", value = "접수기간To", paramType = "query"),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query"),
        @ApiImplicitParam(name = "emadr", value = "이메일", paramType = "query"),
        @ApiImplicitParam(name = "cardAccNo", value = "계좌/카드번호", paramType = "query"),
        @ApiImplicitParam(name = "ogCd", value = "조직코드", paramType = "query"),
    })
    @GetMapping("/change-planers")
    public List<WctbCustomerBaseBulkChangeDto.SearchPartnerRes> getPlannerChanges(
        @Valid
        WctbCustomerBaseBulkChangeDto.SearchReq dto
    ) {
        return service.getPlannerChanges(dto);
    }

    @ApiOperation(value = "고객기준 일괄변경 대상 수정", notes = "고객기준 일괄변경 대상 수정")
    @PutMapping("/change-contract-infos")
    public SaveResponse saveCstBaseBulkChangeOjMdfcs(
        @Valid
        @RequestBody
        WctbCustomerBaseBulkChangeDto.SaveReq dto
    ) {
        return SaveResponse.builder()
            .processCount(service.saveCstBaseBulkChangeOjMdfcs(dto))
            .build();
    }
}
