package com.kyowon.sms.wells.web.service.visit.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbInstallationOrderDto.SaveReq;
import com.kyowon.sms.wells.web.service.visit.service.WsnbInstallationOrderService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

// [AS-IS] LC_ASREGN_API_000
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/installation-works")
@Api(tags = "[WSNB] 타시스템(wells)에서 설치/AS/BS/홈케어 서비스 작업 오더 API")
@RequiredArgsConstructor
public class WsnbInstallationOrderController {

    private final WsnbInstallationOrderService service;

    @ApiOperation(value = "설치 오더 생성", notes = "타시스템(Wells)에서 설치/AS/BS/홈케어 서비스 작업 오더 생성을 위해 사용한다.")
    @PostMapping
    public SaveResponse saveInstallationOrder(
        @Valid
        @RequestBody
        @NotEmpty
        List<SaveReq> dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(1)
            .data(service.saveInstallationOrder(dto))
            .build();
    }

}
