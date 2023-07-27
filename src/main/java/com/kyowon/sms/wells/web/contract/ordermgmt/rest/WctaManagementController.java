package com.kyowon.sms.wells.web.contract.ordermgmt.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaManagementDto.*;
import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaContractRegStep5Service;
import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaManagementService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.response.SaveResponse;

import ch.qos.logback.classic.Logger;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "계약관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/contracts")
public class WctaManagementController {

    private final WctaManagementService service;
    private final WctaContractRegStep5Service cnfmService;
    private Logger log;

    @ApiOperation(value = "계약관리", notes = "계약관리내역을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrDv", value = "계약구분", paramType = "query"),
        @ApiImplicitParam(name = "cntrRcpStrtdt", value = "작성일자(시작일자)", paramType = "query"),
        @ApiImplicitParam(name = "cntrRcpEnddt", value = "작성일자(종료일자)", paramType = "query"),
        @ApiImplicitParam(name = "cntrwTpCd", value = "계약서구분(계약서유형코드)", paramType = "query"),
        @ApiImplicitParam(name = "alncmpCd", value = "계약서구분2(상조관련)", paramType = "query"),
        @ApiImplicitParam(name = "dgr2LevlOgId", value = "조직코드(지역단)", paramType = "query"),
        @ApiImplicitParam(name = "dgr3LevlOgId", value = "조직코드(지점)", paramType = "query"),
        @ApiImplicitParam(name = "cntrPrgsStatCd", value = "계약상태", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "cstKnm", value = "고객명", paramType = "query"),
        @ApiImplicitParam(name = "cntrCstNo", value = "고객번호", paramType = "query"),
        @ApiImplicitParam(name = "sellChnlSnrDv", value = "판매채널", paramType = "query"),
    })
    @GetMapping("/managements")
    public SearchRes getContractManagements(
        @Valid
        SearchKssOrdrListReq dto
    ) {
        return service.getContractManagements(dto);
    }

    @ApiOperation(value = "계약관리상세", notes = "계약관리 상세내역을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrwTpCd", value = "계약서유형코드", paramType = "query"),
        @ApiImplicitParam(name = "cntrPrgsStatCd", value = "계약진행상태", paramType = "query"),
    })
    @GetMapping("/managements/details")
    public SearchCntrDtlRes getContractMngtDtls(
        @Valid
        SearchLspyOrdrDtptListReq dto
    ) {
        return service.getContractMngtDtls(dto);
    }

    @ApiOperation(value = "확정승인요청", notes = "선택한 계약관리 조회 결과 확정승인을 요청")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "cnfmMsgYn", value = "확정승인메세지", paramType = "query"),
    })
    @PutMapping("/managements/confirm-approval")
    public SaveResponse saveConfirmApprovals(
        @RequestBody
        @NotEmpty
        List<SaveConfirmApprovalsReq> dtos
    ) {
        if (dtos.get(0).cnfmMsgYn().equals("Y")) {
            return SaveResponse.builder().key(service.saveAckdCnptMsg(dtos)).build();
        } else {
            return SaveResponse.builder().processCount(service.saveConfirmApprovals(dtos)).build();
        }
    }

    @ApiOperation(value = "계약관리", notes = "확정처리 전 계약번호의 계약진행상태코드 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
    })
    @GetMapping("/managements/status")
    public List<SearchOrderStatCdInfoRes> getContractStatus(
        @Valid
        SaveConfirmApprovalsReq dto
    ) {
        return service.getContractStatus(dto.cntrNo());
    }

    @ApiOperation(value = "확정요청", notes = "선택한 계약관리 조회 결과 확정을 요청")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
    })
    @PutMapping("/managements/confirm")
    public SaveResponse saveConfirms(
        @RequestBody
        @NotEmpty
        List<SaveConfirmApprovalsReq> dtos
    ) throws Exception {
        cnfmService.confirmContract(dtos.get(0).cntrNo());
        return SaveResponse.builder().processCount(0).build();
    }

    @ApiOperation(value = "알림톡 발송", notes = "선택한 계약관리 조회 결과 알림톡을 발송")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrDv", value = "계약상세구분", paramType = "query"),
    })
    @PutMapping("/managements/notification-talk-forwarding")
    public SaveResponse saveNotificationTalkFws(
        @RequestBody
        @NotEmpty
        List<SaveNotificationTalkFwsReq> dtos
    ) throws Exception {
        return SaveResponse.builder().processCount(service.saveNotificationTalkFws(dtos)).build();
    }
}
