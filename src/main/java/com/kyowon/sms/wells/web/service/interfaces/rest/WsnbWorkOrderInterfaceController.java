package com.kyowon.sms.wells.web.service.interfaces.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsnbWorkOrderInterfaceDto.CreateOrderReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsnbWorkOrderInterfaceDto.CreateOrderRes;
import com.kyowon.sms.wells.web.service.interfaces.service.WsnbWorkOrderInterfaceService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@Api(tags = SnServiceConst.REST_INTERFACE_DOC_V1 + ": A/S, 분리, 재설치 및 설치정보 변경 등록")
@RequestMapping(SnServiceConst.REST_INTERFACE_URL_V1 + "/work-orders")
@RequiredArgsConstructor
@Validated
public class WsnbWorkOrderInterfaceController {

    private final WsnbWorkOrderInterfaceService service;

    @ApiOperation(value = "A/S, 분리, 재설치 및 설치정보 변경 등록 작업 오더 생성", notes = "타시스템(교원웰스, 고객센터, KMEMBERS)에서 다건의 A/S, 분리, 재설치 서비스 작업 오더 생성을 위해 사용한다.")
    public EaiWrapper createMultipleTaskOrders(
        @Valid
        @RequestBody
        EaiWrapper<List<CreateOrderReq>> reqEaiWrapper
    ) throws Exception {
        EaiWrapper<List<CreateOrderRes>> resEaiWrapper = reqEaiWrapper.newResInstance();
        resEaiWrapper.setBody(service.createMultipleTaskOrders(reqEaiWrapper.getBody()));
        return resEaiWrapper;
    }

}
