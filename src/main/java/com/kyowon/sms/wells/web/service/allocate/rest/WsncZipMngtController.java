package com.kyowon.sms.wells.web.service.allocate.rest;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.response.SaveResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncZipMngtDto;
import com.kyowon.sms.wells.web.service.allocate.service.WsncZipMngtService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Api(tags = "[WSNC] 우편번호 관리")
@RequiredArgsConstructor
@RestController
@RequestMapping(CommConst.REST_URL_V1 + "/sms/wells/service")
public class WsncZipMngtController {

    private final WsncZipMngtService wsncZipMngtService;

    @ApiOperation(value = "우편번호 관리 화면 - 우편번호 목록 조회", notes = "조회조건에 따른 우편번호 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "zipFrom", value = "우편번호(From)", paramType = "query", required = false),
        @ApiImplicitParam(name = "zipTo", value = "우편번호(To)", paramType = "query", required = false),
        @ApiImplicitParam(name = "ctpvCtctyEmdDvCd", value = "주소구분", paramType = "query", required = false),
        @ApiImplicitParam(name = "ctpvCtctyEmdNm", value = "주소", paramType = "query", required = false),
        @ApiImplicitParam(name = "mngrDvCd", value = "관리구분(1 : 매니저, 2 : 엔지니어)", paramType = "query", required = false),
        @ApiImplicitParam(name = "vstPrdVal", value = "방문주기", paramType = "query", required = false),
        @ApiImplicitParam(name = "mngerRglvlDvCd", value = "급지구분(1: 1급지, 2: 2급지, 3: 3급지)", paramType = "query", required = false)
    })
    @GetMapping("/zip-assignments/paging")
    public PagingResult<WsncZipMngtDto.SearchZipCodeRes> getZipAssignments(
        WsncZipMngtDto.SearchZipCodeReq dto, @Valid
        PageInfo pageInfo
    ) {
        return wsncZipMngtService.getZipAssignments(dto, pageInfo);
    }

    @ApiOperation(value = "우편번호 관리 화면 - 우편번호 등록 및 수정", notes = "우편번호별 관리정보 저장")
    @PostMapping("/zip-assignments")
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
