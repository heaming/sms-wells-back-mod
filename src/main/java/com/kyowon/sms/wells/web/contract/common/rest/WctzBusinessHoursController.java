package com.kyowon.sms.wells.web.contract.common.rest;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.common.service.WctzBusinessHoursService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WCTZ] 영업마감시간 조회")
@Validated
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(CtContractConst.REST_URL_V1 + "/business-hours")
public class WctzBusinessHoursController {

    private final WctzBusinessHoursService service;

    @ApiOperation(value = "영업마감시간 여부 조회", notes = "현재 기준의 영업시간 마감여부를 조회한다.")
    @GetMapping("/is-business-closed-hours")
    public Boolean isBusinessClosedHours() {
        return service.getIsBusinessClosedHours();
    }

}
