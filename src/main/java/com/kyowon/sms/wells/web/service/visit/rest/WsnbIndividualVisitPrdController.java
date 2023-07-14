package com.kyowon.sms.wells.web.service.visit.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbIndividualVisitPrdDto;
import com.kyowon.sms.wells.web.service.visit.service.WsnbIndividualVisitPrdService;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WSNB] 개인별 방문주기 생성")
@RequiredArgsConstructor
@RestController
@RequestMapping(CommConst.REST_URL_V1 + "/sms/wells/service/individual-visit-prds")
@Slf4j
public class WsnbIndividualVisitPrdController {

    private final WsnbIndividualVisitPrdService service;

    @ApiOperation(value = "개인별 방문주기 생성 - 고객정보 조회", notes = "개인별 방문주기 생성 - 고객정보 조회")
    @GetMapping("/customer-infos")
    public WsnbIndividualVisitPrdDto.SearchRes getCustomerInfo(WsnbIndividualVisitPrdDto.SearchReq dto) {
        return service.getCustomerInfo(dto);
    }

    @ApiOperation(value = "개인별 방문주기 생성 - 방문현황 조회", notes = "개인별 방문주기 생성 - 방문현황 조회")
    @GetMapping("/visits")
    public List<WsnbIndividualVisitPrdDto.SearchVstRes> getVisits(WsnbIndividualVisitPrdDto.SearchReq dto) {
        return service.getVisits(dto);
    }

    @ApiOperation(value = "개인별 방문주기 생성 - 주기표 조회", notes = "개인별 방문주기 생성 - 주기표 조회")
    @GetMapping("/periods")
    public List<WsnbIndividualVisitPrdDto.SearchPeriodRes> getPeriods(WsnbIndividualVisitPrdDto.SearchPeriodReq dto) {
        return service.getPeriods(dto);
    }

    @ApiOperation(value = "개인별 방문주기 생성 - B/S 배정", notes = "개인별 방문주기 생성 - B/S 배정")
    @PostMapping("/bs-assigns")
    public SaveResponse processBsAssign(
        @Valid
        @RequestBody
        WsnbIndividualVisitPrdDto.SearchProcessReq dto
    ) throws Exception {
        return SaveResponse.builder().processCount(service.processBsAssign(dto)).build();
    }

    @ApiOperation(value = "개인별 방문주기 생성 - B/S 배정 이월", notes = "개인별 방문주기 생성 - B/S 배정 이월")
    @PostMapping("/carried-forwards")
    public SaveResponse processCarriedForward(
        @Valid
        @RequestBody
        WsnbIndividualVisitPrdDto.SearchProcessReq dto
    ) throws Exception {
        return SaveResponse.builder().processCount(service.processCarriedForward(dto)).build();
    }

    @ApiOperation(value = "개인별 방문주기 생성 - B/S 배정 삭제", notes = "개인별 방문주기 생성 - B/S 배정 삭제")
    @DeleteMapping("/bs-deletes")
    public SaveResponse processBsDelete(
        WsnbIndividualVisitPrdDto.SearchProcessReq dto
    ) throws Exception {
        return SaveResponse.builder().processCount(service.processBsDelete(dto)).build();
    }

    @ApiOperation(value = "개인별 방문주기 생성 - B/S 강제배정", notes = "개인별 방문주기 생성 - B/S 강제배정")
    @PostMapping("/bs-force-assigns")
    public SaveResponse processBsForceAssign(
        @Valid
        @RequestBody
        WsnbIndividualVisitPrdDto.SearchProcessReq dto
    ) throws Exception {
        return SaveResponse.builder().processCount(service.processBsForceAssign(dto)).build();
    }

    @ApiOperation(value = "개인별 방문주기 생성 - 방문주기 삭제", notes = "개인별 방문주기 생성 - 방문주기 삭제")
    @DeleteMapping("/visit-period-deletes")
    public SaveResponse processVisitPeriodDelete(
        WsnbIndividualVisitPrdDto.SearchProcessReq dto
    ) throws Exception {
        return SaveResponse.builder().processCount(service.processVisitPeriodDelete(dto)).build();
    }

    @ApiOperation(value = "개인별 방문주기 생성 - 방문주기 재생성", notes = "개인별 방문주기 생성 - 방문주기 재생성")
    @PostMapping("/visit-period-regens")
    public SaveResponse processVisitPeriodRegen(
        @Valid
        @RequestBody
        WsnbIndividualVisitPrdDto.SearchProcessReq dto
    ) throws Exception {
        return SaveResponse.builder().processCount(service.processVisitPeriodRegen(dto)).build();
    }

}
