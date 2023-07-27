package com.kyowon.sms.wells.web.contract.interfaces.rest;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiRegularDeliveryPackageDto.FindReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiRegularDeliveryPackageDto.FindRes;
import com.kyowon.sms.wells.web.contract.interfaces.service.WctiRegularDeliveryPackageService;
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
public class WctiRegularDeliveryPackageInterfaceController {
    private final WctiRegularDeliveryPackageService service;

    @ApiOperation(value = "[EAI_WSSI1069] 변경가능 패키지 목록 조회", notes = "정기배송 상품변경 전 정보 조회 - 변경가능 패키지 목록 조회")
    @PostMapping("/regular-delivery-packages")
    public EaiWrapper getRegularDeliveryPackage(
        @Valid
        @RequestBody
        EaiWrapper<FindReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<FindRes> resWrapper = reqWrapper.newResInstance();

        // 서비스 메소드 호출 및 Response Body 세팅
        resWrapper.setBody(service.getRegularDeliveryPackage(reqWrapper.getBody()));

        return resWrapper;
    }
}
