package com.kyowon.sms.wells.web.contract.interfaces.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiTaxInvoiceCorporateDto.SearchReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiTaxInvoiceCorporateDto.SearchRes;
import com.kyowon.sms.wells.web.contract.interfaces.service.WctiTaxInvoiceCorporateService;
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
public class WctiTaxInvoiceCorporateInterfaceController {
    private final WctiTaxInvoiceCorporateService service;

    @ApiOperation(value = "[EAI_WSSI1090] 세금계산서 사업자번호 조회", notes = "세금계산서 사업자번호 조회")
    @PostMapping("/tax-invoice-corporates")
    public EaiWrapper getTaxInvoiceCorporates(
        @Valid
        @RequestBody
        EaiWrapper<SearchReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<SearchRes>> resWrapper = reqWrapper.newResInstance();

        // 서비스 메소드 호출
        List<SearchRes> res = service.getTaxInvoiceCorporates(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }
}
