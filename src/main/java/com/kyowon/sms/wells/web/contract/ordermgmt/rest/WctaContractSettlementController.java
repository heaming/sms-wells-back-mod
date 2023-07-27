package com.kyowon.sms.wells.web.contract.ordermgmt.rest;

import com.kyowon.sflex.common.system.service.QueryStringEncService;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractSettelmentDto.*;
import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaContractRegStep5Service;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.exception.BizException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "[ECTA] EDU 통합계약 고객동의 및 결재정보 관리")
@Validated
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(CtContractConst.REST_URL_V1 + "/contracts/settlements")
public class WctaContractSettlementController {

    private final WctaContractRegStep5Service service;
    private final QueryStringEncService queryStringEncService;

    @ApiOperation(value = "계약기초 정보 조회", notes = "계약번호를 받아 계약자 인증을 위한 기본 제공 정보를 조회함")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약자명", paramType = "query"),
    })
    @GetMapping("/basic-info")
    public FindBasicInfoRes getCntrBasicInfo(
        @Valid
        SearchBasicInfoReq req
    ) {
        return service.getCntrBasicInfo(req);
    }

    @ApiOperation(value = "계약자 인증", notes = "법인 구분 코드에 따라 다른 값으로 계약자임을 확인")
    @PostMapping("/login")
    public Authorization getAuthByCntr(
        @RequestBody
        @Valid
        AuthenticationReq req
    ) {
        return service.authorize(req);
    }

    @ApiOperation(value = "Contract For Settlement 단건 조회", notes = "외부연계민감정보 포함 요청으로 post 사용")
    @PostMapping("/contract")
    public FindContractForStlmRes getContractForSettlement(
        @RequestBody
        @Valid
        AuthenticationReq req
    ) {
        return service.getContractForSettlements(req);
    }

    @ApiOperation(value = "신용카드 카드사 코드 조회", notes = "신용카드 카드사 코드 조회")
    @GetMapping("/finance-code")
    public String getFnitCd(
        String encCrcdnoPrefix
    ) {
        String decCrcdno;
        try {
            decCrcdno = queryStringEncService.decrypt(encCrcdnoPrefix);
        } catch (Exception e) {
            throw new BizException("조회되지 않은 금융기관 카드번호입니다.");
        }

        return service.getFnitCd(decCrcdno);
    }

    @ApiOperation(value = "입금유형 별 선택 가능 자동이체일 목록 조회", notes = "입금유형 별 선택 가능 자동이체일 목록 조회")
    @GetMapping("/regular-fund-transfers-day-options/{dpTpCd}")
    public List<Integer> getRegularFundTransfersDayOptions(
        @PathVariable
        String dpTpCd
    ) {
        return service.getRegularFundTransfersDayOptions(dpTpCd);
    }

    @ApiOperation(value = "계약 확정 요청", notes = "최종 동의 시 날릴 요청")
    @PostMapping("/confirm")
    public SaveRes saveContractSettlements(
        @RequestBody
        @Valid
        SaveReq req
    ) {
        return service.saveContractSettlements(req);
    }

    @ApiOperation(value = "신용카드 결제 서비스", notes = "신용카드 승인 서비스")
    @PostMapping("/credit-card-spay")
    public CreditRes requestCreditCardApproval(
        @RequestBody @Valid CreditReq req
    ) {
        return service.requestCreditCardApproval(req);
    }

    @ApiOperation(value = "신용카드 승인 취소", notes = "신용카드 승인 취소 요청")
    @PostMapping("/credit-card-cancel")
    public SaveRes requestCancelCreditCardApproval(
        @RequestBody @Valid CreditReq req
    ) {
        return SaveRes.builder().result(service.requestCancelCardApproval(req)).build();
    }

    @ApiOperation(value = "가상계좌 생성 요청 서비스", notes = "가상계좌 생성 요청 서비스")
    @PostMapping("/virtual-account")
    public VacIsRveAskRes requestVacIsRveAsk(
        @RequestBody @Valid VacIsRveAskReq req
    ) {
        return service.requestVacIsRveAsk(req);
    }

}
