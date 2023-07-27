package com.kyowon.sms.wells.web.contract.interfaces.rest;

import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractProductDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractProductDto.SearchRes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.contract.interfaces.service.WctiContractProductService;
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
public class WctiContractProductInterfaceController {

    private final WctiContractProductService service;

    @ApiOperation(value = "상품정보 조회", notes = "입력받은 계약번호, 계약일련번호에 대한 기준 상품 정보 및 기타 상품분류 정보를 조회")
    @PostMapping("/contract-products")
    public EaiWrapper selectContractProduct(
        @Valid
        @RequestBody
        EaiWrapper<SearchReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<SearchRes>> resWrapper = reqWrapper.newResInstance();

        // 서비스 메소드 호출
        List<SearchRes> res = service.selectContractProduct(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }
}
