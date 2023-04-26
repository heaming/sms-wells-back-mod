package com.kyowon.sms.wells.web.service.visit.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbOutsourcedpdAsReceiptDto.BiztalkReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbOutsourcedpdAsReceiptDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbOutsourcedpdAsReceiptDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.service.WsnbOutsourcedpdAsReceiptService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/outsourcedpd-as-receipts")
@Api(tags = "[WSNB] 외주상품 A/S 접수처 조회 REST API")
@RequiredArgsConstructor
public class WsnbOutsourcedpdAsReceiptController {

    private final WsnbOutsourcedpdAsReceiptService service;

    @ApiOperation(value = "외주상품 A/S 접수처 조회", notes = "조회조건에 일치하는외주상품 A/S 접수처 정보를 조회한다.")
    @GetMapping("/paging")
    public PagingResult<SearchRes> getOutsourcedpdAsReceipts(
        SearchReq dto, @Valid
        PageInfo pageInfo
    ) {
        return service.getOutsourcedpdAsReceipts(dto, pageInfo);
    }

    @ApiOperation(value = "외주상품 A/S 접수처 목록 엑셀 다운로드", notes = "검색조건을 입력 받아 엑셀 다운로드용 외주 상품 A/S 접수처 목록을 조회한다.")
    @GetMapping("/excel-download")
    public List<SearchRes> getOutsourcedpdAsReceipts(
        @Valid
        SearchReq dto
    ) {
        return service.getOutsourcedpdAsReceipts(dto);
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
