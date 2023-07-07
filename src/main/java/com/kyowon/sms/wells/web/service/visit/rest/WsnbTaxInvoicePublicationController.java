package com.kyowon.sms.wells.web.service.visit.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbTaxInvoicePublicationDto.EditReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbTaxInvoicePublicationDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbTaxInvoicePublicationDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.service.WsnbTaxInvoicePublicationService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/tax-invoices")
@Api(tags = "[WSNB] (법인계약공통) 세금계산서발행요청 REST API")
@RequiredArgsConstructor
@Validated
public class WsnbTaxInvoicePublicationController {

    private final WsnbTaxInvoicePublicationService service;

    @ApiOperation(value = "세금계산서 렌탈료 합산청구 정보 조회", notes = "고객서비스배정번호와 파트너의 조직유형코드, 번호를 파라미터로 받아 렌탈료 합산청구 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cstSvAsnNos", value = "고객서비스배정번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query", required = true)
    })
    @GetMapping
    public List<SearchRes> getTaxInvoices(
        @Valid
        SearchReq dto
    ) {
        return this.service.getTaxInvoices(dto);
    }

    @ApiOperation(value = "사업자번호 등록 여부 조회", notes = "사업자번호 등록 여부를 조회한다.")
    @ApiImplicitParam(name = "cstSvAsnNos", value = "고객서비스배정번호", paramType = "query", required = true)
    @GetMapping("/business-number-check")
    public int getBusinessNumberCount(
        String bzrno
    ) {
        return this.service.getBusinessNumberCount(bzrno);
    }

    @ApiOperation(value = "세금계산서 발행 요청", notes = "세금계산서 발행 요청 여부 및 정보를 수정한다.")
    @PutMapping("/{csBilNo}")
    public SaveResponse editTaxInvoice(
        @PathVariable
        String csBilNo,
        @Valid
        @RequestBody
        EditReq dto
    ) {
        return SaveResponse.builder().processCount(this.service.editTaxInvoice(csBilNo, dto)).build();
    }

}
