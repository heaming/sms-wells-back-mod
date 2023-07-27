package com.kyowon.sms.wells.web.contract.ordermgmt.rest;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaVirtualAccountMailDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaVirtualAccountMailService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTA] 가상계좌 확인서 메일 전송")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/contracts/order-details/virtual-account-document")
public class WctaVirtualAccountMailController {

    private final WctaVirtualAccountMailService service;

    @ApiOperation(value = "가상계좌 확인서 메일 전송", notes = "가상계좌 확인서 메일을 전송한다.")
    @PostMapping
    public SaveResponse saveVirtualAccountMail(
        @RequestBody
        @Valid
        WctaVirtualAccountMailDto.SaveReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveVirtualAccountMail(dto))
            .build();
    }
}
