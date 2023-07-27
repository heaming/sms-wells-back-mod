package com.kyowon.sms.wells.web.contract.ordermgmt.rest;

import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaFilterCntrInfDtlInqrService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaFilterCntrInfDtlInqrDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaFilterCntrInfDtlInqrDto.SearchRes;

@Api(tags = "[] 필터계약정보 상세조회(계약상세번호 단위)")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1)
public class WctaFilterCntrInfDtlInqrController {

    private final WctaFilterCntrInfDtlInqrService service;

    @ApiOperation(value = "필터계약정보 상세조회(계약상세번호 단위) 페이징 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "", paramType = "query", required = true),
    })
    @GetMapping("/filter-details/paging")
    public PagingResult<SearchRes> getFilterCntrInfDtlInqrPages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getFilterCntrInfDtlInqrPages(dto, pageInfo);
    }

}
