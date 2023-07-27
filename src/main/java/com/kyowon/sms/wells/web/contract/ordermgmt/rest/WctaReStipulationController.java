package com.kyowon.sms.wells.web.contract.ordermgmt.rest;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaReStipulationDto.*;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaContractRestipulationCntrRegDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaRentalRstlIzDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaReStipulationService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTA] 재약정 관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/re-stipulation")
public class WctaReStipulationController {

    private final WctaReStipulationService service;

    @ApiOperation(value = "재약정 대상자 페이징 조회", notes = "재약정 대상자 페이징 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "copnDvCd", value = "법인격구분코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "cstKnm", value = "고객명", paramType = "query"),
        @ApiImplicitParam(name = "cralLocaraTno", value = "전화번호1", paramType = "query"),
        @ApiImplicitParam(name = "mexnoEncr", value = "전화번호2", paramType = "query"),
        @ApiImplicitParam(name = "cralIdvTno", value = "전화번호3", paramType = "query"),
    })
    @GetMapping("/customers/paging")
    public PagingResult<SearchRes> getReStipulationCustomerPages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getReStipulationCustomerPages(dto, pageInfo);
    }

    @ApiOperation(value = "재약정 대상자 수 조회", notes = "재약정 대상자 수 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "copnDvCd", value = "법인격구분코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "cstKnm", value = "고객명", paramType = "query"),
        @ApiImplicitParam(name = "cralLocaraTno", value = "전화번호1", paramType = "query"),
        @ApiImplicitParam(name = "mexnoEncr", value = "전화번호2", paramType = "query"),
        @ApiImplicitParam(name = "cralIdvTno", value = "전화번호3", paramType = "query"),
    })
    @GetMapping("/customers/counts")
    public Integer getReStipulationCustomerCounts(
        @Valid
        SearchReq dto
    ) {
        return service.getReStipulationCustomerCounts(dto);
    }

    @ApiOperation(value = "재약정 기준정보 조회", notes = "재약정 기준정보 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약상세일련번호", paramType = "query", required = true)
    })
    @GetMapping("/standard-info")
    public List<BasInfoRes> getRestipulationStandardInfo(
        @Valid
        String cntrNo,
        @Valid
        Integer cntrSn
    ) {
        return service.getReStipulationStandardInfo(cntrNo, cntrSn);
    }

    @ApiOperation(value = "재약정계약 조회", notes = "재약정계약 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약상세일련번호", paramType = "query", required = true)
    })
    @GetMapping("/contract")
    public WctaRentalRstlIzDvo getReltalReStipulation(
        @Valid
        String cntrNo,
        @Valid
        Integer cntrSn
    ) {
        return service.getReltalReStipulation(cntrNo, cntrSn);
    }

    @ApiOperation(value = "재약정 대상계약 상세조회", notes = "재약정 대상계약 상세조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약상세일련번호", paramType = "query", required = true)
    })
    @GetMapping("/contract-info")
    public ContractRes getRestipulationContractInfo(
        @Valid
        String cntrNo,
        @Valid
        Integer cntrSn
    ) {
        return service.getRestipulationContractInfo(cntrNo, cntrSn);
    }

    @ApiOperation(value = "재약정 계약 저장", notes = "재약정 계약 저장")
    @PostMapping("/save-contract")
    public SaveResponse saveRestipulationContractReg(
        @RequestBody
        @Valid
        WctaContractRestipulationCntrRegDvo dvo
    ) {
        return SaveResponse.builder().key(service.saveRestipulationContractReg(dvo)).build();
    }
}
