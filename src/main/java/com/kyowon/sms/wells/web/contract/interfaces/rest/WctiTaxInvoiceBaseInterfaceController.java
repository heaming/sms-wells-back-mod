package com.kyowon.sms.wells.web.contract.interfaces.rest;

import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiTaxInvoiceBaseDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiTaxInvoiceBaseDto.SearchRes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.contract.interfaces.service.WctiTaxInvoiceBaseService;
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
public class WctiTaxInvoiceBaseInterfaceController {
    private final WctiTaxInvoiceBaseService service;

    @ApiOperation(value = "[EAI_WSSI1087] 세금계산서 목록 조회", notes = "입력받은 조건에 대한 세금계산서 목록을 조회")
    @PostMapping("/tax-invoice-bases")
    public EaiWrapper getTaxInvoices(
        @Valid
        @RequestBody
        EaiWrapper<SearchReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<SearchRes>> resWrapper = reqWrapper.newResInstance();

        // 서비스 메소드 호출
        List<SearchRes> res = service.getTaxInvoices(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }
}
