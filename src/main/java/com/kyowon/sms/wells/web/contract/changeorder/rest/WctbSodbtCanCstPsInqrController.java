package com.kyowon.sms.wells.web.contract.changeorder.rest;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbSodbtCanCstPsInqrDto;
import com.kyowon.sms.wells.web.contract.changeorder.service.WctbSodbtCanCstPsInqrService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "wells 총판 취소고객 현황 조회")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/contracts/soledistributor-cancel-contracts")
public class WctbSodbtCanCstPsInqrController {
    private final WctbSodbtCanCstPsInqrService service;

    @ApiOperation(value = "wells 총판 취소고객 현황 조회", notes = "wells 총판 취소고객 현황 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "canStrtDt", value = "취소시작일자", paramType = "query"),
        @ApiImplicitParam(name = "canEndDt", value = "취소끝일자", paramType = "query"),
        @ApiImplicitParam(name = "pdGbn", value = "구분", paramType = "query"),
    })
    @GetMapping("/paging")
    public PagingResult<WctbSodbtCanCstPsInqrDto.SearchRes> getCancelContractsPaging(
        @Valid
        WctbSodbtCanCstPsInqrDto.SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getCancelContractsPaging(dto, pageInfo);
    }
}
