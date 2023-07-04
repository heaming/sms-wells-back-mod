package com.kyowon.sms.wells.web.service.visit.rest;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbPaidServiceSettlementDto.SaveReq;
import com.kyowon.sms.wells.web.service.visit.service.WsnbPaidServiceSettlementService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/paid-service-settlements")
@Api(tags = "[WSNB] 유상 서비스 결제정보 생성 REST API")
@RequiredArgsConstructor
@Validated
public class WsnbPaidServiceSettlementController {

    private final WsnbPaidServiceSettlementService service;

    @ApiOperation(value = "유상서비스 결제정보 생성", notes = "수납기능에서 유상서비스 결제정보 생성 요청에 대해 서비스비용입금내역, 서비스비용신용카드처리내역 및 서비스비용가상계좌처리내역정보를 생성하는 서비스이다.")
    @PostMapping
    public SaveResponse savePaidServiceSettlements(
        @Valid
        @RequestBody
        SaveReq dto
    ) {
        return SaveResponse.builder().processCount(service.savePaidServiceSettlements(dto)).build();
    }

}
