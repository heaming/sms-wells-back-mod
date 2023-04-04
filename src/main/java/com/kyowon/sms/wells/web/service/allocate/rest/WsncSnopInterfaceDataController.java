package com.kyowon.sms.wells.web.service.allocate.rest;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.allocate.service.WsncSnopInterfaceDataService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/snop-interfaces")
@Api(tags = "[WSNC] S&OP 인터페이스 자료 생성")
@RequiredArgsConstructor
@Validated
public class WsncSnopInterfaceDataController {

    private final WsncSnopInterfaceDataService service;

    @ApiOperation(value = "S&OP 인터페이스 자료 생성", notes = "기준년월 소요자재 현황을 조회하기 위한 S&OP 인터페이스 자료를 생성한다.")
    @PostMapping
    public SaveResponse saveSnopInterfaceDatas() {
        return SaveResponse.builder().processCount(service.saveSnopInterfaceDatas()).build();
    }

}
