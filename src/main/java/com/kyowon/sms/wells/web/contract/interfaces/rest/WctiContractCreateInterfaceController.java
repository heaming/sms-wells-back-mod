package com.kyowon.sms.wells.web.contract.interfaces.rest;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractCreateDto.CreateRentalReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractCreateDto.CreateRentalRes;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractCreateDto.CreateSinglePaymentReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractCreateDto.CreateSinglePaymentRes;
import com.kyowon.sms.wells.web.contract.interfaces.service.WctiContractCreateService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@InterfaceController
@Api(tags = "[WCTI] 인터페이스 계약생성")
@RequestMapping(value = CtContractConst.INTERFACE_URL_V1 + "/contracts")
@RequiredArgsConstructor
@Validated
public class WctiContractCreateInterfaceController {

    private final WctiContractCreateService service;

    @ApiOperation(value = "[EAI_WSSI1007] 일시불 주문 등록(K멤버스, 교원wells)", notes = "일시불 주문 등록(K멤버스, 교원wells)")
    @PostMapping("/single-payment")
    public EaiWrapper createContractForSinglePayment(
        @Valid
        @RequestBody
        EaiWrapper<CreateSinglePaymentReq> reqWrapper
    ) throws Exception {
        // Response용 EaiWrapper 생성
        EaiWrapper<CreateSinglePaymentRes> resWrapper = reqWrapper.newResInstance();

        // 서비스 메소드 호출
        CreateSinglePaymentRes res = service.createContractForSinglePayment(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }

    @ApiOperation(value = "[EAI_WSSI1001] 렌탈 주문 등록(K멤버스, 교원wells)", notes = "렌탈 주문 등록(K멤버스, 교원wells)")
    @PostMapping("/rental")
    public EaiWrapper createContractForRental(
        @Valid
        @RequestBody
        EaiWrapper<CreateRentalReq> reqWrapper
    ) throws Exception {
        // Response용 EaiWrapper 생성
        EaiWrapper<CreateRentalRes> resWrapper = reqWrapper.newResInstance();

        // 서비스 메소드 호출
        CreateRentalRes res = service.createContractForRental(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }
}
