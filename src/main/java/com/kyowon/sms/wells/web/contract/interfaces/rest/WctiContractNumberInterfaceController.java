package com.kyowon.sms.wells.web.contract.interfaces.rest;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.contract.common.dto.WctzContractNumberDto.SearchRes;
import com.kyowon.sms.wells.web.contract.common.service.WctzContractNumberService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@InterfaceController
@Api(tags = "[WCTI] 계약번호 채번")
@RequestMapping(value = CtContractConst.INTERFACE_URL_V1 + "/contracts/contract-numbers")
@RequiredArgsConstructor
@Validated
public class WctiContractNumberInterfaceController {
    private final WctzContractNumberService service;

    @ApiOperation(value = "계약번호 채번", notes = "계약번호와 계약일련번호를 채번한다.")
    @PostMapping
    public EaiWrapper getContractNumber(
        @Valid
        @RequestBody
        EaiWrapper<String> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<SearchRes> resWrapper = reqWrapper.newResInstance();

        // 서비스 메소드 호출
        SearchRes res = service.getContractNumber(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }
}
