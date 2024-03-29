package com.kyowon.sms.wells.web.service.visit.rest;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbWorkOrderDto.SaveReq;
import com.kyowon.sms.wells.web.service.visit.service.WsnbWorkOrderService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/work-orders")
@Api(tags = "[WSNB] 다건 작업오더, 정보변경 처리")
@RequiredArgsConstructor
@Slf4j
public class WsnbWorkOrderController {
    private final WsnbWorkOrderService service;

    @ApiOperation(value = "다건 작업오더, 정보변경 처리", notes = "다건에 대한 작업오더 생성, 수정, 삭제, 정보 변경처리를 한다.")
    @PostMapping
    public SaveResponse saveWorkOrders(
        @Valid
        @RequestBody
        SaveReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .data(service.saveWorkOrders(dto))
            .processCount(1)
            .build();
    }

}
