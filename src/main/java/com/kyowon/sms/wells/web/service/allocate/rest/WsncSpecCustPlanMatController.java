package com.kyowon.sms.wells.web.service.allocate.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncSpecCustPlanMatDto;
import com.kyowon.sms.wells.web.service.allocate.service.WsncSpecCustPlanMatService;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNC] [W-SV-S-0029] 특정고객 예정자재 인서트")
@RequiredArgsConstructor
@RestController
@RequestMapping(CommConst.REST_URL_V1 + "/sms/wells/service/spec-cust-plan-mats")
public class WsncSpecCustPlanMatController {
    private final WsncSpecCustPlanMatService service;

    @ApiOperation(value = "특정고객 예정자재 인서트", notes = "특정고객 예정자재 인서트")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "asnOjYm", value = "배정년월", paramType = "query", required = false),
        @ApiImplicitParam(name = "prtnrNo", value = "작업담당자사번", paramType = "query", required = false),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = false),
    })
    @PostMapping
    public SaveResponse processSpecCustPlanMat(WsncSpecCustPlanMatDto.SaveProcessReq dto) throws Exception{
        return SaveResponse.builder()
            .processCount(service.processSpecCustPlanMat(dto))
            .build();
    }
}
