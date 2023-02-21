package com.kyowon.sms.wells.web.service.allocate.rest;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncOutsourcedpdAsReceiptDto.BiztalkReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncOutsourcedpdAsReceiptDto.SearchRes;
import com.kyowon.sms.wells.web.service.allocate.service.WsncOutsourcedpdAsReceiptService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/outsourcedpd-as-receipts")
@Api(tags = "[WSNC] 외주상품 A/S 접수처 조회 REST API")
@RequiredArgsConstructor
public class WsncOutsourcedpdAsReceiptController {

    private final WsncOutsourcedpdAsReceiptService service;

    @ApiOperation(value = "외주상품 A/S 접수처 조회", notes = "외주 상품 A/S 접수처 정보 조회한다.")
    @GetMapping
    public List<SearchRes> getOutsourcedpdAsReceipts(
        @RequestParam(required = false)
        String searchParam
    ) {
        return service.getOutsourcedpdAsReceipts(searchParam);
    }

    @ApiOperation(value = "외주상품 A/S 접수처 알림톡 발신 내용 조회", notes = "외주상품 A/S 접수처 알림톡 발신 내용을 조회한다.")
    @GetMapping("/biztalk-content")
    public String getTemplateContent(
        BiztalkReq dto
    ) throws Exception {
        return service.getTemplateContent(dto);
    }

    @ApiOperation(value = "외주상품 A/S 접수처 알림톡 발송", notes = "외주 상품 A/S 접수처 정보를 고객에게 알림톡으로 발송한다.")
    @PostMapping
    public SaveResponse sendAsReceiptBiztalk(
        BiztalkReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.sendAsReceiptBiztalk(dto))
            .build();
    }
}
