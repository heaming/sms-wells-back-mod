package com.kyowon.sms.wells.web.service.visit.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbSafetyAccidentDto.*;
import com.kyowon.sms.wells.web.service.visit.service.WsnbSafetyAccidentService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/safety-accidents")
@Api(tags = "[WSNB] 안전사고 관리 API")
@RequiredArgsConstructor
public class WsnbSafetyAccidentController {

    private final WsnbSafetyAccidentService service;

    @ApiOperation(value = "안전사고 관리 내역 조회", notes = "조회조건에 일치하는 안전사고 관리 내역 정보를 조회한다.")
    @GetMapping("/paging")
    public PagingResult<SearchRes> getSafetyAccidents(
        SearchReq dto, @Valid
        PageInfo pageInfo
    ) {
        return service.getSafetyAccidents(dto, pageInfo);
    }

    @ApiOperation(value = "안전사고 관리 내역 엑셀다운로드", notes = "조회조건에 일치하는 엑셀다운로드용 안전사고 관리 내역 정보를 조회한다.")
    @GetMapping("/excel-download")
    public List<SearchRes> getSafetyAccidents(
        SearchReq dto
    ) {
        return service.getSafetyAccidents(dto);
    }

    @ApiOperation(value = "안전사고 상세 조회", notes = "선택한 안전사고의 상세정보를 조회한다.")
    @GetMapping("/{acdnRcpId}")
    public FindRes getSafetyAccident(
        @PathVariable
        String acdnRcpId
    ) {
        return service.getSafetyAccident(acdnRcpId);
    }

    @ApiOperation(value = "안전사고 결과 저장", notes = "안전사고 결과를 저장한다.")
    @PutMapping
    public SaveResponse editSafetyAccident(
        @RequestBody
        @Valid
        EditReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.editSafetyAccident(dto))
            .build();
    }

    @ApiOperation(value = "안전사고 합의서 서명 저장", notes = "안전사고 합의서 서명을 저장한다.")
    @PutMapping("/sign")
    public SaveResponse editSafetyAccidentSign(
        @RequestBody
        @Valid
        EditSignReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.editSafetyAccidentSign(dto))
            .build();
    }

    @ApiOperation(value = "안전사고 결과 알림톡 발송 및 저장", notes = "안전사고 결과를 알림톡으로 발송히고 결과를 저장한다.")
    @PostMapping("/biztalk")
    public SaveResponse sendSafetyAccidentBiztalk(
        @Valid
        @RequestBody
        BiztalkReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.sendSafetyAccidentBiztalk(dto))
            .build();
    }

}
