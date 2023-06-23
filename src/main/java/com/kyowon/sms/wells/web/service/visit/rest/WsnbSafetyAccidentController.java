package com.kyowon.sms.wells.web.service.visit.rest;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbSafetyAccidentDto.EditReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbSafetyAccidentDto.FindRes;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbSafetyAccidentDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbSafetyAccidentDto.SearchRes;
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

    @ApiOperation(value = "안전사고 상세 조회", notes = "선택한 안전사고의 상세정보를 조회한다.")
    @GetMapping("/detail")
    public FindRes getSafetyAccident(
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
}
