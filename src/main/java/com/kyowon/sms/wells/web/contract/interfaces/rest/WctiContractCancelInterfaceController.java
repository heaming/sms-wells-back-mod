package com.kyowon.sms.wells.web.contract.interfaces.rest;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractCancelDto.CancelMembershipContractReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractCancelDto.CancelMembershipContractRes;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractCancelDto.CancelRentalContractReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractCancelDto.CancelRentalContractRes;
import com.kyowon.sms.wells.web.contract.interfaces.service.WctiContractCancelService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@InterfaceController
@Api(tags = "[WCTI] 인터페이스 계약취소")
@RequestMapping(value = CtContractConst.INTERFACE_URL_V1)
@RequiredArgsConstructor
@Validated
public class WctiContractCancelInterfaceController {

    private final WctiContractCancelService service;

    @ApiOperation(value = "[EAI_WSSI1018] 렌탈 접수취소 등록", notes = "렌탈 접수취소 등록")
    @PostMapping("/cancel-rental-contract")
    public EaiWrapper cancelRentalContract(
        @Valid
        @RequestBody
        EaiWrapper<CancelRentalContractReq> reqWrapper
    ) throws Exception {
        // Response용 EaiWrapper 생성
        EaiWrapper<CancelRentalContractRes> resWrapper = reqWrapper.newResInstance();

        // 서비스 메소드 호출
        CancelRentalContractRes res = service.cancelRentalContract(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }

    @ApiOperation(value = "[EAI_WSSI1021] 정기배송해약등록", notes = "정기배송해약등록")
    @PostMapping("/cancel-membership-contract")
    public EaiWrapper cancelMembershipContract(
        @Valid
        @RequestBody
        EaiWrapper<CancelMembershipContractReq> reqWrapper
    ) throws Exception {
        // Response용 EaiWrapper 생성
        EaiWrapper<CancelMembershipContractRes> resWrapper = reqWrapper.newResInstance();

        // 서비스 메소드 호출
        CancelMembershipContractRes res = service.cancelMembershipContract(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }
}
