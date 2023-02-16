package com.kyowon.sms.wells.web.service.allocate.rest;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncBfsvcCrdovrAsnDto;
import com.kyowon.sms.wells.web.service.allocate.service.WsncBfsvcCrdovrAsnService;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WSNC] 고객 정기BS 이월배정")
@RequiredArgsConstructor
@RestController
@RequestMapping(CommConst.REST_URL_V1 + "/sms/wells/service/crdovr-asns")
@Slf4j
public class WsncBfsvcCrdovrAsnController {

    private final WsncBfsvcCrdovrAsnService service;

    @ApiOperation(value = "고객 정기BS 이월배정", notes = "고객 정기BS 이월배정")
    @PostMapping
    public SaveResponse saveBfsvcCrdovrAsn(
        @Valid
        @RequestBody
        WsncBfsvcCrdovrAsnDto.SearchReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveBfsvcCrdovrAsn(dto))
            .build();
    }
}
