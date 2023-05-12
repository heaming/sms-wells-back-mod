package com.kyowon.sms.wells.web.service.allocate.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncOutsourcedpdAsReceiptDto;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncOutsourcedpdAsReceiptDto.*;
import com.kyowon.sms.wells.web.service.allocate.service.WsncOutsourcedpdAsReceiptService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/outsourcedpd-as-receipts")
@Api(tags = "[WSNC] 외주상품 A/S 접수내역 및 접수처 조회 REST API")
@RequiredArgsConstructor
public class WsncOutsourcedpdAsReceiptController {

    private final WsncOutsourcedpdAsReceiptService service;

    @ApiOperation(value = "외주상품 A/S 접수내역 조회", notes = "조회조건에 일치하는 외주상품 A/S 접수 내역 정보를 조회한다.")
    @GetMapping("/itemization/paging")
    public PagingResult<SearchReceiptIzRes> getOutsourcedpdAsReceiptIzs(
        SearchReceiptIzReq dto, @Valid
        PageInfo pageInfo
    ) {
        return service.getOutsourcedpdAsReceiptIzs(dto, pageInfo);
    }

    @ApiOperation(value = "외주상품 A/S 접수내역 목록 엑셀 다운로드", notes = "검색조건을 입력 받아 엑셀 다운로드용 외주 상품 A/S 접수내역 목록을 조회한다.")
    @GetMapping("/itemization/excel-download")
    public List<SearchReceiptIzRes> getOutsourcedpdAsReceiptIzs(
        SearchReceiptIzReq dto
    ) {
        return service.getOutsourcedpdAsReceiptIzs(dto);
    }

    @ApiOperation(value = "외주상품 A/S 접수 내역 삭제", notes = "외주 상품 A/S 접수 내역 정보를 삭제한다.")
    @DeleteMapping("/itemization")
    public SaveResponse removeOutsourcedpdAsReceiptIzs(
        @RequestBody
        @Valid
        @NotEmpty
        List<RemoveReceiptIzReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.removeOutsourcedpdAsReceiptIzs(dtos))
            .build();
    }

    @ApiOperation(value = "외주상품 A/S 접수 내역 저장", notes = "외주 상품 A/S 접수처 정보를 고객에게 알림톡으로 발송한다.")
    @PostMapping("/itemization")
    public SaveResponse saveOutsourcedpdAsReceiptIzs(
        @RequestBody
        @Valid
        @NotEmpty
        List<SaveReceiptIzReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveOutsourcedpdAsReceiptIzs(dtos))
            .build();
    }

    @ApiOperation(value = "외주상품 A/S 접수처 알림톡 발송", notes = "외주 상품 A/S 접수처 정보를 고객에게 알림톡으로 발송한다.")
    @PostMapping("/biztalk")
    public SaveResponse sendAsReceiptBiztalk(
        @Valid
        @RequestBody
        WsncOutsourcedpdAsReceiptDto.BiztalkReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.sendAsReceiptBiztalk(dto))
            .build();
    }

    @ApiOperation(value = "외주상품 A/S 접수처 조회", notes = "조회조건에 일치하는 외주상품 A/S 접수처 정보를 조회한다.")
    @GetMapping("/business/paging")
    public PagingResult<SearchReceiptBzRes> getOutsourcedpdAsReceiptBzs(
        SearchReceiptBzReq dto, @Valid
        PageInfo pageInfo
    ) {
        return service.getOutsourcedpdAsReceiptBzs(dto, pageInfo);
    }

    @ApiOperation(value = "외주상품 A/S 접수처 목록 엑셀 다운로드", notes = "조회조건에 일치하는 외주상품 A/S 접수처 목록을 엑셀 다운로드한다.")
    @GetMapping("/business/excel-download")
    public List<SearchReceiptBzRes> getOutsourcedpdAsReceiptBzs(
        SearchReceiptBzReq dto
    ) {
        return service.getOutsourcedpdAsReceiptBzs(dto);
    }

    @ApiOperation(value = "외주상품 A/S 접수처 삭제", notes = "외주 상품 A/S 접수처 정보를 삭제한다.")
    @DeleteMapping("/business")
    public SaveResponse removeOutsourcedpdAsReceiptBzs(
        @RequestBody
        @Valid
        @NotEmpty
        List<RemoveReceiptBzReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.removeOutsourcedpdAsReceiptBzs(dtos))
            .build();
    }

    @ApiOperation(value = "외주상품 A/S 접수처 저장", notes = "외주 상품 A/S 접수처 정보를 저장한다.")
    @PostMapping("/business")
    public SaveResponse saveOutsourcedpdAsReceiptBzs(
        @RequestBody
        @Valid
        @NotEmpty
        List<SaveReceiptBzReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveOutsourcedpdAsReceiptBzs(dtos))
            .build();
    }
}
