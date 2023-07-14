package com.kyowon.sms.wells.web.service.allocate.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncSpecCustAsnDto;
import com.kyowon.sms.wells.web.service.allocate.service.WsncSpecCustAsnService;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNC] [W-SV-S-0027] 특정고객 배정 인서트")
@RequiredArgsConstructor
@RestController
@RequestMapping(CommConst.REST_URL_V1 + "/sms/wells/service/spec-cust-asns")
public class WsncSpecCustAsnController {

    private final WsncSpecCustAsnService service;

    @ApiOperation(value = "특정고객 배정 인서트", notes = "특정고객 배정 인서트")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "asnOjYm", value = "배정년월", paramType = "query", required = false),
        @ApiImplicitParam(name = "prtnrNo", value = "작업담당자사번", paramType = "query", required = false),
    })
    @PostMapping
    public SaveResponse processRegularBfsvcAsn(WsncSpecCustAsnDto.SaveProcessReq dto) throws Exception{
        return SaveResponse.builder()
            .processCount(service.processSpecCustAsn(dto))
            .build();
    }
}
