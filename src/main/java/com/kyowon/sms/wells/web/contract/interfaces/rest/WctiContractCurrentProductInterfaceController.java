package com.kyowon.sms.wells.web.contract.interfaces.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractCurrentProductDto.FindReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractCurrentProductDto.FindRes;
import com.kyowon.sms.wells.web.contract.interfaces.service.WctiContractCurrentProductService;
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
public class WctiContractCurrentProductInterfaceController {

    private final WctiContractCurrentProductService service;

    @ApiOperation(value = "[EAI_WSSI1080] 현재 상품 정보 조회", notes = "입력받은 계약번호, 계약일련번호에 대해 현재 상품관련 정보를 조회")
    @PostMapping("/contract-current-products")
    public EaiWrapper getContractCurrentProduct(
        @Valid
        @RequestBody
        EaiWrapper<FindReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<FindRes>> resWrapper = reqWrapper.newResInstance();

        // 서비스 메소드 호출
        List<FindRes> res = service.getContractCurrentProduct(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }
}
