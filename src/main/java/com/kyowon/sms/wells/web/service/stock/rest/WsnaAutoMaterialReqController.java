package com.kyowon.sms.wells.web.service.stock.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.service.WsnaAutoMaterialReqService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNA] 엔지니어 자재 자동 신청 SERVICE REST API")
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/auto-material-req")
public class WsnaAutoMaterialReqController {

    private final WsnaAutoMaterialReqService service;

    @ApiOperation(value = "엔지니어 자재 자동 신청", notes = "각 엔지니어창고의 안전재고 미만 제품들에 대하여 자동으로 자재를 신청하는 서비스")
    @PostMapping("/engineer")
    public SaveResponse createAutoMaterialReq() {
        return SaveResponse.builder()
            .processCount(service.createAutoMaterialReq())
            .build();
    }

    @ApiOperation(value = "서비스운영팀 자재자동신청 관련 데이터 이월")
    @PostMapping("/carry-over")
    public SaveResponse carryOver() {
        return SaveResponse.builder()
            .processCount(service.carryOver())
            .build();
    }
}
