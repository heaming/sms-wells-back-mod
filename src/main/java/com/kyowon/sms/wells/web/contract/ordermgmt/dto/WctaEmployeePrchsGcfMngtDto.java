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
        String fnm,
        String cntrCralTno,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String cralTno,
        String rsgnDt,
        String sellInflwChnlDtlCd,
        String cntPre,
        String cntPreC,
        String cntPreFnl,
        String cntCur,
        String cntCurC,
        String cntCurFnl,
        String cnt01,
        String cnt01C,
        String cnt01Fnl,
        String cnt02,
        String cnt02C,
        String cnt02Fnl,
        String cnt03,
        String cnt03C,
        String cnt03Fnl,
        String cnt04,
        String cnt04C,
        String cnt04Fnl,
        String cnt05,
        String cnt05C,
        String cnt05Fnl,
        String cnt06,
        String cnt06C,
        String cnt06Fnl,
        String cnt07,
        String cnt07C,
        String cnt07Fnl,
        String cnt08,
        String cnt08C,
        String cnt08Fnl,
        String cnt09,
        String cnt09C,
        String cnt09Fnl,
        String cnt10,
        String cnt10C,
        String cnt10Fnl,
        String cnt11,
        String cnt11C,
        String cnt11Fnl,
        String cnt12,
        String cnt12C,
        String cnt12Fnl,
        String cntTot
    ) {}
    // *********************************************************
    // Request Dto
    // *********************************************************
    //직원구매 계약 목록 조회 Search Request Dto
    @ApiModel(value = "WctaEmployeePrchsGcfMngtDto-SearchCntrReq")
    public record SearchCntrReq(
        String stYy,
        String colDv,
        String empno,
        String srchGbn
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
