package com.kyowon.sms.wells.web.contract.interfaces.rest;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractDetailInfoDto.FindReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractDetailInfoDto.FindRes;
import com.kyowon.sms.wells.web.contract.interfaces.service.WctiContractDetailInfoService;
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
public class WctiContractDetailInfoInterfaceController {

    private final WctiContractDetailInfoService service;

    @ApiOperation(value = "[EAI_WSSI1047] 계약상세 정보 조회", notes = "계약상세 정보 조회를 위한 I/F 대상 프로그램")
    @PostMapping("/contract-detail-infos")
    public EaiWrapper getContractDetail(
        @Valid
        @RequestBody
        EaiWrapper<FindReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<FindRes> resWrapper = reqWrapper.newResInstance();

        // 서비스 메소드 호출 및 Response Body 세팅
        resWrapper.setBody(service.getContractDetail(reqWrapper.getBody()));

        return resWrapper;
    }
}
