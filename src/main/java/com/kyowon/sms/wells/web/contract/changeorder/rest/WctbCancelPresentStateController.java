package com.kyowon.sms.wells.web.contract.changeorder.rest;

import static com.kyowon.sms.wells.web.contract.changeorder.dto.WctbCancelPresentStateDto.*;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.changeorder.service.WctbCancelPresentStateService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTB] 취소현황")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = CtContractConst.REST_URL_V1 + "/changeorder")
public class WctbCancelPresentStateController {

    private final WctbCancelPresentStateService service;

    @ApiOperation(value = "렌탈 취소현황 조회", notes = "렌탈 취소배정 관리에서 등록이 된 렌탈 취소건을 조회하는 화면")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogCd", value = "소속구분", paramType = "query"),
        @ApiImplicitParam(name = "cancelFromDt", value = "시작일", paramType = "query"),
        @ApiImplicitParam(name = "cancelToDt", value = "종료일", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련변호", paramType = "query"),
        @ApiImplicitParam(name = "sellOgTpCd", value = "판매구분", paramType = "query"),
        @ApiImplicitParam(name = "basePdCd", value = "상품콛", paramType = "query"),
        @ApiImplicitParam(name = "pdGdCd", value = "상품등급", paramType = "query"),
        @ApiImplicitParam(name = "rgstUsrEpNo", value = "등록담당자사번", paramType = "query"),
        @ApiImplicitParam(name = "clctamPrtnrNo", value = "집금담당", paramType = "query"),
        @ApiImplicitParam(name = "printDiv", value = "출력구분", paramType = "query"),
        @ApiImplicitParam(name = "copnDvCd", value = "계약구분", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매세부", paramType = "query"),
        @ApiImplicitParam(name = "alncmpCd", value = "상조취소", paramType = "query"),
        @ApiImplicitParam(name = "cntrStatChRsonCd", value = "취소유형", paramType = "query"),
        @ApiImplicitParam(name = "reqdDiv", value = "철거구분", paramType = "query"),
    })
    @GetMapping("/rental-cancels")
    public List<SearchRentalRes> getRentalCancelPresentStates(
            @Valid
            SearchReq dto
    ) {
        return service.getRentalCancelPresentStates(dto);
    }

    @ApiOperation(value = "정기배송 취소현황 조회", notes = "정기배송 취소현황 - 취소현황 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogCd", value = "소속구분", paramType = "query"),
        @ApiImplicitParam(name = "cancelFromDt", value = "시작일", paramType = "query"),
        @ApiImplicitParam(name = "cancelToDt", value = "종료일", paramType = "query"),
        @ApiImplicitParam(name = "sellOgTpCd", value = "판매구분", paramType = "query"),
        @ApiImplicitParam(name = "rgstUsrEpNo", value = "등록담당자사번", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련변호", paramType = "query"),
        @ApiImplicitParam(name = "cntrStatChRsonCd", value = "취소유형", paramType = "query"),
        @ApiImplicitParam(name = "wellsFarmCancelDiv", value = "웰스팜취소구분", paramType = "query"),
    })
    @GetMapping("/regular-shipping-cancels")
    public List<SearchRegularShippingRes> getRegularShippingCancelPresentStates(
            @Valid
            SearchReq dto
    ) {
        return service.getRegularShippingCancelPresentStates(dto);
    }

    @ApiOperation(value = "일시불 취소현황 조회", notes = "일시불 취소현황 - 취소현황 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogCd", value = "소속구분", paramType = "query"),
        @ApiImplicitParam(name = "dtDiv", value = "일자검색구분", paramType = "query"),
        @ApiImplicitParam(name = "cancelFromDt", value = "시작일", paramType = "query"),
        @ApiImplicitParam(name = "cancelToDt", value = "종료일", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련변호", paramType = "query"),
        @ApiImplicitParam(name = "reqdDiv", value = "철거구분", paramType = "query"),
    })
    @GetMapping("/single-payment-cancels")
    public List<SearchSinglePaymentRes> getSinglePaymentCancelPresentStates(
        @Valid
        SearchReq dto
    ) {
        return service.getSinglePaymentCancelPresentStates(dto);
    }

    @ApiOperation(value = "멤버십 취소현황 조회", notes = "멤버십 취소현황 - 취소현황 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogCd", value = "소속구분", paramType = "query"),
        @ApiImplicitParam(name = "dtDiv", value = "일자검색구분", paramType = "query"),
        @ApiImplicitParam(name = "cancelFromDt", value = "시작일", paramType = "query"),
        @ApiImplicitParam(name = "cancelToDt", value = "종료일", paramType = "query"),
        @ApiImplicitParam(name = "sellOgTpCd", value = "판매구분", paramType = "query"),
        @ApiImplicitParam(name = "pdHclsfId", value = "상품대분류", paramType = "query"),
        @ApiImplicitParam(name = "pdMclsfId", value = "상품중분류", paramType = "query"),
        @ApiImplicitParam(name = "basePdCd", value = "상품코드", paramType = "query"),
        @ApiImplicitParam(name = "rgstUsrEpNo", value = "등록담당자사번", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매세부", paramType = "query"),
        @ApiImplicitParam(name = "rgstUsrEpNo", value = "등록담당자사번", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련변호", paramType = "query"),
        @ApiImplicitParam(name = "cntrStatChRsonCd", value = "취소유형", paramType = "query"),
        @ApiImplicitParam(name = "copnDvCd", value = "고객구분", paramType = "query"),
    })
    @GetMapping("/membership-cancels")
    public List<SearchMembershipRes> getMembershipCancelPresentStates(
            @Valid
            SearchReq dto
    ) {
        return service.getMembershipCancelPresentStates(dto);
    }
}





