package com.kyowon.sms.wells.web.contract.interfaces.rest;

import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiShippingProductHistoryDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiShippingProductHistoryDto.SearchRes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.contract.interfaces.service.WctiShippingProductHistoryService;
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
public class WctiShippingProductHistoryInterfaceController {

    private final WctiShippingProductHistoryService service;

    @ApiOperation(value = "[EAI_WSSI1081] 커피원두 변경 이력 조회", notes = "입력받은 계약번호, 계약일련번호에 대한 정기배송 제품 변경이력을 조회한다.")
    @PostMapping("/shipping-product-histores")
    public EaiWrapper getShippingProductHistories(
        @Valid
        @RequestBody
        EaiWrapper<SearchReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<SearchRes>> resWrapper = reqWrapper.newResInstance();

        // 서비스 메소드 호출 및 Response Body 세팅
        resWrapper.setBody(service.getShippingProductHistories(reqWrapper.getBody()));

        return resWrapper;
    }
}
