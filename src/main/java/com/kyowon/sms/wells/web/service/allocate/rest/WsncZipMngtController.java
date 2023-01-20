package com.kyowon.sms.wells.web.service.allocate.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncZipMngtDto;
import com.kyowon.sms.wells.web.service.allocate.service.WsncZipMngtService;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNC] 우편번호 관리")
@RequiredArgsConstructor
@RestController
@RequestMapping(CommConst.REST_URL_V1 + "/sms/wells/service/zip-assignments")
public class WsncZipMngtController {

    private final WsncZipMngtService wsncZipMngtService;

    @ApiOperation(value = "우편번호 관리 화면 - 우편번호 목록 조회", notes = "조회조건에 따른 우편번호 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "zipFrom", value = "우편번호(From)", paramType = "query", required = false),
        @ApiImplicitParam(name = "zipTo", value = "우편번호(To)", paramType = "query", required = false),
        @ApiImplicitParam(name = "ctpvCtctyEmdDvCd", value = "주소구분", paramType = "query", required = false),
        @ApiImplicitParam(name = "ctpvCtctyEmdNm", value = "주소", paramType = "query", required = false),
        @ApiImplicitParam(name = "mngrDvCd", value = "관리구분(1 : 매니저, 2 : 엔지니어)", paramType = "query", required = false),
        @ApiImplicitParam(name = "mngerRglvlDvCd", value = "급지구분(1: 1급지, 2: 2급지, 3: 3급지)", paramType = "query", required = false)
    })
    @GetMapping("/paging")
    public PagingResult<WsncZipMngtDto.SearchZipCodeRes> getZipAssignments(
        WsncZipMngtDto.SearchZipCodeReq dto, @Valid
        PageInfo pageInfo
    ) {
        return wsncZipMngtService.getZipAssignments(dto, pageInfo);
    }

    @ApiOperation(value = "우편번호 관리 화면 - 우편번호 등록 및 수정", notes = "우편번호별 관리정보 저장")
    @PostMapping
    public SaveResponse saveZipAssignments(
        @Valid
        @RequestBody
        @NotEmpty
        List<WsncZipMngtDto.SaveZipCodeReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(wsncZipMngtService.saveZipAssignments(dtos))
            .build();
    }
}
