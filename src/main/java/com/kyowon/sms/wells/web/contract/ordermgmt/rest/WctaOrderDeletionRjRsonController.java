package com.kyowon.sms.wells.web.contract.ordermgmt.rest;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDeletionRjRsonDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaOrderDeletionRjRsonService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTA] wells 주문삭제 요청 반려 사유 등록")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/contracts")
public class WctaOrderDeletionRjRsonController {
    private final WctaOrderDeletionRjRsonService service;

    @ApiOperation(value = "wells 주문삭제 요청 반려 사유 등록", notes = "wells 주문삭제 요청 반려 사유 등록")
    @PostMapping("/reject-reasons")
    public SaveResponse sendContractEmail(
        @RequestBody
        @Valid
        WctaOrderDeletionRjRsonDto.SaveReq dto
    ) {
        return SaveResponse.builder().processCount(service.saveRejectReasonRgst(dto)).build();
    }
}
