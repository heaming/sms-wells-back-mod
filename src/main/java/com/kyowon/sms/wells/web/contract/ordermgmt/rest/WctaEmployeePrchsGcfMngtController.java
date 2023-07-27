package com.kyowon.sms.wells.web.contract.ordermgmt.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaEmployeePrchsGcfMngtDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaEmployeePrchsGcfMngtDto.SearchReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaEmployeePrchsGcfMngtDto.SearchRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaEmployeePrchsGcfMngtService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTA] 직원구매 상품권 지급 관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1)
public class WctaEmployeePrchsGcfMngtController {

    private final WctaEmployeePrchsGcfMngtService service;

    @ApiOperation(value = "직원구매 상품권 지급 관리", notes = "년 기준 이상 구매 직원에 대해 상품권 지급 현황을 관리한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "istDt", value = "귀속년도", paramType = "query"),
        @ApiImplicitParam(name = "emplDv", value = "직원구분", paramType = "query"),
        @ApiImplicitParam(name = "empNo", value = "사번", paramType = "query"),
        @ApiImplicitParam(name = "cntrtRelCd", value = "선택구분(계약자관계코드)", paramType = "query"),
        @ApiImplicitParam(name = "cralLocaraTno", value = "휴대지역전화번호", paramType = "query"),
        @ApiImplicitParam(name = "mexnoEncr", value = "휴대전화국번호암호화", paramType = "query"),
        @ApiImplicitParam(name = "cralIdvTno", value = "휴대개별전화번호", paramType = "query"),
    })
    @GetMapping("/contracts/employee-purchase-gcfs")
    public List<SearchRes> getEmployeePurchaseGcfs(
        @Valid
        SearchReq dto
    ) {
        return service.getEmployeePurchaseGcfs(dto);
    }

    @GetMapping("/contracts/employee-purchase-gcfs/excel-download")
    public List<SearchRes> getEmployeePurchaseGcfsExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getEmployeePurchaseGcfsExcelDownload(dto);
    }

    @ApiOperation(value = "직원구매 계약 목록 조회", notes = "메인화면에서 넘어온 파라미터(귀속년도, 사번)에 해당하는 직원구매 계약리스트 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "stYy", value = "적용기간 시작일", paramType = "query", required = true),
        @ApiImplicitParam(name = "colDv", value = "적용기간 종료일", paramType = "query", required = true),
        @ApiImplicitParam(name = "empno", value = "판매자사번", paramType = "query"),
        @ApiImplicitParam(name = "srchGbn", value = "취소,최종구분", paramType = "query"),
    })
    @GetMapping("/contracts/employee-purchases")
    public List<WctaEmployeePrchsGcfMngtDto.SearchCntrRes> getEmployeePurchases(
        @Valid
        WctaEmployeePrchsGcfMngtDto.SearchCntrReq dto
    ) {
        return service.getEmployeePurchases(dto);
    }
}
