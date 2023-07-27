package com.kyowon.sms.wells.web.contract.interfaces.rest;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiFreeAsPeriodDto.FindReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiFreeAsPeriodDto.FindRes;
import com.kyowon.sms.wells.web.contract.interfaces.service.WctiFreeAsPeriodService;
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
public class WctiFreeAsPeriodInterfaceController {

    private final WctiFreeAsPeriodService service;

    @ApiOperation(value = "[EAI_WSSI1059] 삼성제품 무상 AS 기간 조회", notes = "계약예외처리를 통한 삼성 무상 AS 건 및 기간 및 상품에 따른 무상 AS 기간 조회")
    @PostMapping("/free-as-periods")
    public EaiWrapper getFreeAsPeriod(
        @Valid
        @RequestBody
        EaiWrapper<FindReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<FindRes> resWrapper = reqWrapper.newResInstance();

        // 서비스 메소드 호출 및 Response Body 세팅
        resWrapper.setBody(service.getFreeAsPeriod(reqWrapper.getBody()));

        return resWrapper;
    }
}
