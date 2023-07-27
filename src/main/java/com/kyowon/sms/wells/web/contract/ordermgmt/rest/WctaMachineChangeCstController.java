package com.kyowon.sms.wells.web.contract.ordermgmt.rest;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaMachineChangeCstDto.FindReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaMachineChangeCstDto.FindRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaMachineChangeCstService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTA] 기기변경 고객")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/contracts")
public class WctaMachineChangeCstController {

    private final WctaMachineChangeCstService service;

    @ApiOperation(value = "기기변경 고객 조회", notes = "기기변경을 수행할 건 조회 / 제약 및 실적적용율 등을 계산")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseCntrNo", value = "현재 진행중인 계약번호", paramType = "query"),
        @ApiImplicitParam(name = "baseCntrSn", value = "현재 진행중인 계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "cstNo", value = "계약자 고객번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "indvCrpDv", value = "법인격구분코드(1.개인, 2.법인)", paramType = "query", required = true),
        @ApiImplicitParam(name = "pdCd", value = "기준상품코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "dscDv", value = "할인적용유형코드", paramType = "query"),
        @ApiImplicitParam(name = "dscTp", value = "할인적용유형코드", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "alncmpCd", value = "제휴사코드", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "기기변경을 수행할 계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "기기변경을 수행할 계약일련번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "rgstMdfcDv", value = "1.등록, 2.수정", paramType = "query", required = true),
    })
    @GetMapping("/machine-changes")
    public FindRes getMachineChangeCst(
        @Valid
        FindReq dto
    ) throws Exception {
        return service.getMachineChangeCst(dto);
    }
}
