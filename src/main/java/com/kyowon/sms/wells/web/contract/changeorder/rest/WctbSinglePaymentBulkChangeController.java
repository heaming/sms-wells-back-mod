package com.kyowon.sms.wells.web.contract.changeorder.rest;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbSinglePaymentBulkChangeDto;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbSinglePaymentBulkChangeDto.SearchRes;
import com.kyowon.sms.wells.web.contract.changeorder.service.WctbSinglePaymentBulkChangeService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "일시불 일괄변경 조회")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/changeorder")
public class WctbSinglePaymentBulkChangeController {
    private final WctbSinglePaymentBulkChangeService service;

    @ApiOperation(value = "일시불 일괄변경 조회", notes = "일시불 일괄변경 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "rfDt", value = "반영일자", paramType = "query"),
    })
    @GetMapping("/SinglePayment-bulk-changes")
    public List<SearchRes> getSinglePaymentBulkChangs(
        @RequestParam
        String cntrNo,
        @RequestParam
        String cntrSn,
        @RequestParam
        String rfDt
    ) {
        return service.getSinglePaymentBulkChangs(cntrNo, cntrSn, rfDt);
    }

    @ApiOperation(value = "일시불 일괄변경 계약 조회", notes = "일시불 일괄변경 계약 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
    })
    @GetMapping("/singlepayment-change-contracts")
    public WctbSinglePaymentBulkChangeDto.SearchRgstRes getSinglePaymentBulkChangsRgst(
        @RequestParam
        @NotBlank
        String cntrNo,
        @RequestParam
        @NotBlank
        String cntrSn
    ) {
        return service.getSinglePaymentBulkChangsRgst(cntrNo, cntrSn);
    }

    @ApiOperation(value = "일시불 일괄변경 등록", notes = "일시불 일괄변경 등록")
    @PostMapping("/singlepayment-bulk-changes")
    public SaveResponse saveSinglePaymentBulkChs(
        @RequestBody
        @Valid
        WctbSinglePaymentBulkChangeDto.SaveReq dto
    ) throws ParseException {
        return SaveResponse.builder().processCount(service.saveSinglePaymentBulkChs(dto)).build();
    }
}
