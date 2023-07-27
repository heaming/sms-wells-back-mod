package com.kyowon.sms.wells.web.contract.interfaces.rest;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiRequidationDateDto.FindReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiRequidationDateDto.FindRes;
import com.kyowon.sms.wells.web.contract.interfaces.service.WctiRequidationDateService;
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
public class WctiRequidationDateInterfaceController {

    private final WctiRequidationDateService service;

    @ApiOperation(value = "[EAI_WSSI1084] 철거 정보 조회", notes = "라이프 제휴로 발생한 wells 계약건의 철거 정보 조회")
    @PostMapping("/requidation-dates")
    public EaiWrapper getRequidationDate(
        @Valid
        @RequestBody
        EaiWrapper<FindReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<FindRes> resWrapper = reqWrapper.newResInstance();

        // 서비스 메소드 호출 및 Response Body 세팅
        resWrapper.setBody(service.getRequidationDate(reqWrapper.getBody()));

        return resWrapper;
    }
}
