package com.kyowon.sms.wells.web.contract.ordermgmt.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaKakaotalksDto.SearchReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaKakaotalksDto.SearchRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaKakaotalksService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTA] 카카오톡 발송 내역 조회")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/kakaotalks")
public class WctaKakaotalksController {

    private final WctaKakaotalksService service;

    @ApiOperation(value = "카카오톡 발송 내역 조회", notes = "계약번호와 계약구분별로 카카오톡 발송내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "concDiv", value = "계약구분", paramType = "query"),
    })
    @GetMapping("/kakaotalk-forwarding-itemizations")
    public List<SearchRes> getKakaotalkFwIzs(
        @Valid
        SearchReq dto
    ) {
        return service.getKakaotalkFwIzs(dto);
    }
}
