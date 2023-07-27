package com.kyowon.sms.wells.web.contract.interfaces.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiRegularDeliveryProductDto.SearchReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiRegularDeliveryProductDto.SearchRes;
import com.kyowon.sms.wells.web.contract.interfaces.service.WctiRegularDeliveryProductService;
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
public class WctiRegularDeliveryProductInterfaceController {
    private final WctiRegularDeliveryProductService service;

    @ApiOperation(value = "[EAI_WSSI1068] 정기배송 최근 배송제품 조회", notes = "계약번호, 계약일련번호에 대한 사은품 정보를 조회")
    @PostMapping("/regular-delivery-products")
    public EaiWrapper getRegularDeliveryProducts(
        @Valid
        @RequestBody
        EaiWrapper<SearchReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<SearchRes>> resWrapper = reqWrapper.newResInstance();

        // 서비스 메소드 호출 및 Response Body 세팅
        resWrapper.setBody(service.getRegularDeliveryProducts(reqWrapper.getBody()));

        return resWrapper;
    }
}
