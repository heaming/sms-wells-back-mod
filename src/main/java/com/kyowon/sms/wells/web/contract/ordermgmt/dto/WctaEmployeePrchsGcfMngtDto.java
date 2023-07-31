package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import io.swagger.annotations.ApiModel;

public class WctaEmployeePrchsGcfMngtDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    //직원구매 상품권 지급 관리 조회 Search Request Dto
    @ApiModel(value = "WctaEmployeePrchsGcfMngtDto-SearchReq")
    public record SearchReq(
        String istDt,
        String emplDv,
        String empNo,
        String cntrtRelCd,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    //직원구매 상품권 지급 관리 조회 Search Result Dto
    @ApiModel("WctaEmployeePrchsGcfMngtDto-SearchRes")
    public record SearchRes(
        String empNo,
        String empDv,
        String prtnrKnm,
        String cntrCralTno,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String cralTno,
        String rsgnDt,
        String sellInflwChnlDtlCd,
        String bfIstCn,
        String bfCanCn,
        String bfFnlCn,
        String thmIstCn,
        String thmCanCn,
        String thmFnlCn,
        String mth01IstCn,
        String mth01CanCn,
        String mth01FnlCn,
        String mth02IstCn,
        String mth02CanCn,
        String mth02FnlCn,
        String mth03IstCn,
        String mth03CanCn,
        String mth03FnlCn,
        String mth04IstCn,
        String mth04CanCn,
        String mth04FnlCn,
        String mth05IstCn,
        String mth05CanCn,
        String mth05FnlCn,
        String mth06IstCn,
        String mth06CanCn,
        String mth06FnlCn,
        String mth07IstCn,
        String mth07CanCn,
        String mth07FnlCn,
        String mth08IstCn,
        String mth08CanCn,
        String mth08FnlCn,
        String mth09IstCn,
        String mth09CanCn,
        String mth09FnlCn,
        String mth10IstCn,
        String mth10CanCn,
        String mth10FnlCn,
        String mth11IstCn,
        String mth11CanCn,
        String mth11FnlCn,
        String mth12IstCn,
        String mth12CanCn,
        String mth12FnlCn,
        String totFnlCn
    ) {}
    // *********************************************************
    // Request Dto
    // *********************************************************
    //직원구매 계약 목록 조회 Search Request Dto
    @ApiModel(value = "WctaEmployeePrchsGcfMngtDto-SearchCntrReq")
    public record SearchCntrReq(
        String stYy, // 연도(YYYY)
        String colDv, // 월구분. 전월(pre), 1~12월(01~12), 전체(tot)
        String empno, // 사번
        String srchGbn // 설치구분. 설치(null), 취소(cncl), 최종(fnl)
    ) {}

    // *********************************************************
    // Response Dto
    // *********************************************************
    //직원구매 계약 목록 조회 Search Response Dto
    @ApiModel(value = "WctaEmployeePrchsGcfMngtDto-SearchCntrRes")
    public record SearchCntrRes(
        String hmnrscEmpno,
        String prtnrKnm,
        String ogNm,
        String cntrDtlNo,
        String cstKnm,
        String bryyMmdd,
        String pdNm,
        String pdTpCd,
        String cntrCnfmDtm,
        String sppDuedt,
        String istDt,
        String cntrCanDtm,
        String stplPtrm
    ) {}
}
