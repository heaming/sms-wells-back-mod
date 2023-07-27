package com.kyowon.sms.wells.web.contract.interfaces.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractContactDto.SearchReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractContactDto.SearchRes;
import com.kyowon.sms.wells.web.contract.interfaces.service.WctiContractContactService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@InterfaceController
@Api(tags = "[WCTI] 고객센터I/F")
@RequestMapping(value = CtContractConst.INTERFACE_URL_V1 + "/customer-centers")
@RequiredArgsConstructor
@Validated
public class WctiContractContactInterfaceController {
    private final WctiContractContactService service;

    @ApiOperation(value = "[EAI_WSSI1074] 계약 컨택 현황 조회", notes = "고객센터 아웃바운드운영팀에서 계약 컨택을 진행하는 건들을 조회")
    @PostMapping("/contract-contact-lists")
    public EaiWrapper getContractContacts(
        @Valid
        @RequestBody
        EaiWrapper<SearchReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<SearchRes>> resWrapper = reqWrapper.newResInstance();

        // 서비스 메소드 호출 및 Response Body 세팅
        resWrapper.setBody(service.getContractContacts(reqWrapper.getBody()));

        return resWrapper;
    }
}
