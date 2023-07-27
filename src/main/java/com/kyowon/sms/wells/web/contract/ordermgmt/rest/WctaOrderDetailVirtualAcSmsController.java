package com.kyowon.sms.wells.web.contract.ordermgmt.rest;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailVirtualAcSmsDto.SaveReq;
import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailVirtualAcSmsDto.SearchReq;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaOrderDetailVirtualAcSmsDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaOrderDetailVirtualAcSmsService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTA] 주문상세페이지 내부 팝업 - 가상계좌 메세지 보내기")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/contracts/order-details/virtual-account-message")
public class WctaOrderDetailVirtualAcSmsController {

    private final WctaOrderDetailVirtualAcSmsService service;

    @ApiOperation(value = "가상계좌번호 템플릿 조회", notes = "ID에 맞는 템플릿을 가져온다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true),
    })
    @GetMapping
    public WctaOrderDetailVirtualAcSmsDvo getVirtualAccountTemplate(
        @Valid
        SearchReq dto
    ) throws Exception {
        return service.getVirtualAccountTemplate(dto);
    }

    @ApiOperation(value = "가상계좌 메세지 보내기", notes = "입력한 휴대전화번호에 SMS를 발송한다.")
    @PostMapping
    public SaveResponse saveVirtualAccountMessages(
        @RequestBody
        SaveReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveVirtualAccountMessages(dto))
            .build();
    }
}
