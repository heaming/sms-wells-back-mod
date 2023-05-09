package com.kyowon.sms.wells.web.service.stock.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.service.WsnaAutoMaterialReqService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNA] 엔지니어 자재 자동 신청 SERVICE API")
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/auto-material-req")
public class WsnaAutoMaterialReqController {

    private final WsnaAutoMaterialReqService service;

    @ApiOperation(value = "엔지니어 자재 자동 신청", notes = "각 엔지니어창고의 안전재고 미만 제품들에 대하여 자동으로 자재를 신청하는 서비스")
    @ApiImplicitParams(value = {})
    @PostMapping
    public int createAutoMaterialReq() {
        return service.createAutoMaterialReq();
    }
}
