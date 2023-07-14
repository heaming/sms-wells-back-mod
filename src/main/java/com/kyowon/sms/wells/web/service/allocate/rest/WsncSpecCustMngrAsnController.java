package com.kyowon.sms.wells.web.service.allocate.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncSpecCustMngrAsnDto;
import com.kyowon.sms.wells.web.service.allocate.service.WsncSpecCustMngrAsnService;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNC] [W-SV-S-0031] 특정고객 담당자 지정 BS 오더 생성")
@RequiredArgsConstructor
@RestController
@RequestMapping(CommConst.REST_URL_V1 + "/sms/wells/service/spec-cust-mngr-asns")
public class WsncSpecCustMngrAsnController {

    private final WsncSpecCustMngrAsnService service;

    @ApiOperation(value = "특정고객 담당자 지정 BS 오더 생성", notes = "특정고객 담당자 지정 BS 오더 생성")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "asnOjYm", value = "배정년월", paramType = "query", required = false),
        @ApiImplicitParam(name = "prtnrNo", value = "작업담당자사번", paramType = "query", required = false),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = false),
    })
    @PostMapping
    public SaveResponse processSpecCustPlanMat(WsncSpecCustMngrAsnDto.SaveProcessReq dto) throws Exception{
        return SaveResponse.builder()
            .processCount(service.processSpecCustAsn(dto))
            .build();
    }
}
