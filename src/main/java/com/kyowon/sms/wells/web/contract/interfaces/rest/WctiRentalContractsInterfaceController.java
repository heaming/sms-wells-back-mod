package com.kyowon.sms.wells.web.contract.interfaces.rest;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiRentalContractsDto;
import com.kyowon.sms.wells.web.contract.interfaces.service.WctiRentalContractsService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@InterfaceController
@Api(tags = "[WCTI] 고객센터I/F")
@RequestMapping(value = CtContractConst.INTERFACE_URL_V1 + "/kyowonwells")
@RequiredArgsConstructor
@Validated
public class WctiRentalContractsInterfaceController {
    private final WctiRentalContractsService service;

    @ApiOperation(value = "[EAI_WSSI1004] 렌탈 계약정보 조회", notes = "렌탈 계약정보 조회")
    @PostMapping("/rental-contracts")
    public EaiWrapper getContractContacts(
        @Valid
        @RequestBody
        EaiWrapper<WctiRentalContractsDto.SearchReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<WctiRentalContractsDto.SearchRes> resWrapper = reqWrapper.newResInstance();

        // 서비스 메소드 호출 및 Response Body 세팅
        resWrapper.setBody(service.getRentalContracts(reqWrapper.getBody()));

        return resWrapper;
    }

}
