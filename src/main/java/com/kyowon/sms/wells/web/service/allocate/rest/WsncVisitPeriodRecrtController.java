package com.kyowon.sms.wells.web.service.allocate.rest;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncVisitPeriodRecrtDto;
import com.kyowon.sms.wells.web.service.allocate.service.WsncVisitPeriodRecrtService;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WSNC] 방문주기 재생성")
@RequiredArgsConstructor
@RestController
@RequestMapping(CommConst.REST_URL_V1 + "/sms/wells/service/visit-period-recrts")
@Slf4j
public class WsncVisitPeriodRecrtController {

    private final WsncVisitPeriodRecrtService service;

    @ApiOperation(value = "방문주기 재생성", notes = "방문주기 재생성")
    @PostMapping
    public SaveResponse saveVisitPeriodRecrt(
        @Valid
        @RequestBody
        WsncVisitPeriodRecrtDto.SaveReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveVisitPeriodRecrt(dto))
            .build();
    }

}
