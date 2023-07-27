package com.kyowon.sms.wells.web.contract.ordermgmt.rest;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractDocumentMailDto.SaveReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaContractDocumentMailService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTA] 계약서 메일발송")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/contracts/order-details/documentary-evidence-mails")
public class WctaContractDocumentMailController {

    private final WctaContractDocumentMailService service;

    @ApiOperation(value = "고객명 조회", notes = "계약번호와 일련번호로 고객명을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true),
    })
    @GetMapping
    public String getContractCstNm(
        @RequestParam
        String cntrNo,
        @RequestParam
        int cntrSn
    ) {
        return service.getContractCstNm(cntrNo, cntrSn);
    }

    @ApiOperation(value = "증빙서류 메일 전송", notes = "빙서류 메일 전송한다.")
    @PostMapping
    public int saveDcevdnMlTrss(
        @RequestBody
        SaveReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveDcevdnMlTrss(dto))
            .build()
            .getProcessCount();
    }
}
