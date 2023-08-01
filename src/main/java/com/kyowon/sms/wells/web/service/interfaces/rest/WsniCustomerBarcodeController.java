package com.kyowon.sms.wells.web.service.interfaces.rest;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCustomerBarcodeDto.SearchReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCustomerBarcodeDto.SearchRes;
import com.kyowon.sms.wells.web.service.interfaces.service.WsniCustomerBarcodeService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@InterfaceController
@Api(tags = SnServiceConst.REST_INTERFACE_DOC_V1 + ": 고객코드, 바코드 일치 확인 API 조회")
@RequestMapping(SnServiceConst.REST_INTERFACE_URL_V1 + "/customercode-barcode")
@RequiredArgsConstructor
@Validated
public class WsniCustomerBarcodeController {

    private final WsniCustomerBarcodeService service;

    @ApiOperation(value = "고객코드, 바코드 일치 확인 API 조회", notes = "요청한 고객코드와 바코드가 일치하는지를 검증하는 인터페이스 API로서 바코드에 등록되어 있는 고객정보와 요청한 고객코드가 동일한지 확인 검증 후 결과를 제공한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "CNTR_NO", value = "계약번호", paramType = "query", example = "W20232237423", required = true),
        @ApiImplicitParam(name = "BC_NO", value = "바코드번호", paramType = "query", example = "WM5WAFD01Q22800332", required = true)
    })
    @PostMapping
    public EaiWrapper getCustomerBarcodes(
        @Valid
        @RequestBody
        EaiWrapper<SearchReq> reqEaiWrapper
    ) {
        EaiWrapper<SearchRes> resEaiWrapper = reqEaiWrapper.newResInstance();
        resEaiWrapper.setBody(service.getCustomerBarcodes(reqEaiWrapper.getBody()));
        return resEaiWrapper;
    }

}
