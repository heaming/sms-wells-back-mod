package com.kyowon.sms.wells.web.contract.interfaces.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiMachineChangeDto;
import com.kyowon.sms.wells.web.contract.interfaces.service.WctiMachineChangeService;
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
public class WctiMachineChangeInterfaceController {
    private final WctiMachineChangeService service;

    @ApiOperation(value = "[EAI_WSSI1057] 기기변경 정보 조회", notes = "기기변경 대상 계약번호, 계약일련번호를 입력받아 기기변경 대상 계약 정보 등을 조회")
    @PostMapping("/machine-changes")
    public EaiWrapper getMachineChanges(
        @Valid
        @RequestBody
        EaiWrapper<WctiMachineChangeDto.SearchReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<WctiMachineChangeDto.SearchRes>> resWrapper = reqWrapper.newResInstance();

        // 서비스 메소드 호출 및 Response Body 세팅
        resWrapper.setBody(service.getMachineChanges(reqWrapper.getBody()));

        return resWrapper;
    }
}

