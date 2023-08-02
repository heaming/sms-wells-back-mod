package com.kyowon.sms.wells.web.contract.changeorder.rest;

import static com.kyowon.sms.wells.web.contract.changeorder.dto.WctbRentalBulkChangeDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.changeorder.dto.WctbRentalBulkChangeDto.SearchRes;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbRentalBulkChangeDto;
import com.kyowon.sms.wells.web.contract.changeorder.service.WctbRentalBulkChangeService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTB] 멤버십 일괄변경 조회")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/changeorder")
public class WctbRentalBulkChangeController {

    private final WctbRentalBulkChangeService service;

    @ApiOperation(value = "멤버십 일괄변경 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/rental-bulk-change")
    public List<SearchRes> getRentalBulkChanges(
        @Valid
        SearchReq dto
    ) {
        return service.getRentalBulkChanges(dto);
    }

    @ApiOperation(value = "렌탈 일괄변경 계약정보 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true),
    })
    @GetMapping("/rental-change-contracts")
    public WctbRentalBulkChangeDto.SearchCntrRes getBulkChangeContractsInfs(
        @NotBlank
        String cntrNo,
        @NotBlank
        String cntrSn,
        @NotBlank
        String procsDv
    ) {
        return service.getBulkChangeContractsInfs(cntrNo, cntrSn, procsDv);
    }

    @ApiOperation(value = "렌탈 일괄변경 등록", notes = "")
    @PostMapping("/rental-bulk-change")
    public SaveResponse saveRentalBulkChange(
        @RequestBody
        @Valid
        WctbRentalBulkChangeDto.SaveReq dto
    ) {
        return SaveResponse.builder().processCount(service.saveRentalBulkChange(dto)).build();
    }
}
