package com.kyowon.sms.wells.web.contract.common.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.common.dto.WctzContractNumberDto.SearchRes;
import com.kyowon.sms.wells.web.contract.common.service.WctzContractNumberService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WCTZ] 계약번호 채번")
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(CtContractConst.REST_URL_V1 + "/contracts/contract-numbers")
public class WctzContractNumberController {
    private final WctzContractNumberService service;

    @ApiOperation(value = "계약번호 채번", notes = "계약번호와 계약일련번호를 채번한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = false, example = "W20217674357"),
    })
    @GetMapping
    public SearchRes getContractNumber(
        @RequestParam(required = false)
        String cntrNo
    ) {
        return service.getContractNumber(cntrNo);
    }
}
