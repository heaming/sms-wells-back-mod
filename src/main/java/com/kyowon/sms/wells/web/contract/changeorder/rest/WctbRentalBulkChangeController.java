package com.kyowon.sms.wells.web.contract.changeorder.rest;

import static com.kyowon.sms.wells.web.contract.changeorder.dto.WctbRentalBulkChangeDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.changeorder.dto.WctbRentalBulkChangeDto.SearchRes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.changeorder.service.WctbRentalBulkChangeService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;

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
}
