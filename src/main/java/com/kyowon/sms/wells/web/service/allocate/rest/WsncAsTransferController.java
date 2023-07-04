package com.kyowon.sms.wells.web.service.allocate.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncAsTransferDto.SaveReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncAsTransferDto.SearchReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncAsTransferDto.SearchRes;
import com.kyowon.sms.wells.web.service.allocate.service.WsncAsTransferService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/as-transfers")
@Api(tags = "[WSNC] A/S 담당자 이관 REST API")
@RequiredArgsConstructor
@Validated
public class WsncAsTransferController {

    private final WsncAsTransferService service;

    @ApiOperation(value = "A/S 담당자 이관 조회", notes = "조회조건에 일치하는 A/S 접수 정보를 조회하여 담당자를 이관 처리한다.")
    @GetMapping("/paging")
    public PagingResult<SearchRes> getAsTransferPages(
        SearchReq dto, @Valid
        PageInfo pageInfo
    ) {
        return service.getAsTransferPages(dto, pageInfo);
    }

    @ApiOperation(value = "A/S 담당자 이관 목록 엑셀 다운로드", notes = "검색조건을 입력 받아 엑셀 다운로드용 A/S 담당자 이관 목록을 조회한다.")
    @GetMapping("/excel-download")
    public List<SearchRes> getAsTransferPagesExcelDownload(
        SearchReq dto
    ) {
        return service.getAsTransferPagesExcelDownload(dto);
    }

    @ApiOperation(value = "A/S 담당자 이관 저장", notes = "A/S 담당자 이관을 저장한다.")
    @PostMapping
    public SaveResponse saveAsTransfers(
        @Valid
        @RequestBody
        List<SaveReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveAsTransfers(dtos))
            .build();
    }

}
