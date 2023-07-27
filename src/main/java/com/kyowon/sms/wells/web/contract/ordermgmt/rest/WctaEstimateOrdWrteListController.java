package com.kyowon.sms.wells.web.contract.ordermgmt.rest;

import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaEstimateOrdWrteListDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaEstimateOrdWrteListService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "견적 주문 작성 목록 조회")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/contracts")
public class WctaEstimateOrdWrteListController {
    private final WctaEstimateOrdWrteListService service;

    @ApiOperation(value = "견적 주문 작성 목록 가져오기", notes = "견적 주문 작성 목록 가져오기")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "fromEstDt", value = "견적일 From", paramType = "query"),
        @ApiImplicitParam(name = "toEstDt", value = "견적일 To", paramType = "query"),
        @ApiImplicitParam(name = "inqrDv", value = "조회구분구분", paramType = "query"),
        @ApiImplicitParam(name = "cntrt", value = "계약자명", paramType = "query"),
    })
    @GetMapping("/estimate-orders")
    public PagingResult<WctaEstimateOrdWrteListDto.SearchRes> getEstimateOrdWrteLists(
        WctaEstimateOrdWrteListDto.SearchReq dto,
        PageInfo pageInfo
    ) {
        return service.getEstimateOrdWrteLists(dto, pageInfo);
    }

    @ApiOperation(value = "견적 주문 작성 목록 삭제", notes = "견적 주문 작성 목록 삭제")
    @DeleteMapping("/estimate-orders")
    public SaveResponse removeIrgBznsArbitArtc(
        @RequestParam
        @NotEmpty
        String cntrNo,
        @RequestParam
        @NotEmpty
        String cntrSn
    ) {
        return SaveResponse.builder().processCount(service.removeEstOrdWrteListDl(cntrNo, cntrSn)).build();
    }
}
