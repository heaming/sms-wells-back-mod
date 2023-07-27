package com.kyowon.sms.wells.web.contract.interfaces.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;

public class WctiTaxInvoiceBaseDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 세금계산서 목록 Search Request Dto
    @ApiModel("WctiTaxInvoiceBaseDto-SearchReq")
    public record SearchReq(
        String TXINV_PBL_MTHD_CD,	//세금계산서발행방식코드
        @NotBlank
        @ValidDate
        String SPLR_WRTE_STRTDT,    //공급자작성시작일자
        @NotBlank
        @ValidDate
        String SPLR_WRTE_ENDDT,     //공급자작성종료일자
        String BZRNO,               //사업자등록번호
        String APLC_PSIC_ID         //신청담당자ID
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 세금계산서 목록 Search Result Dto
    @ApiModel("WctiTaxInvoiceBaseDto-SearchRes")
    public record SearchRes(
        String APLC_DT, //신청일자
        String TXINV_ID, //세금계산서ID
        String SPLR_WRTE_DT, //공급자작성일자
        String TXINV_PBL_MTHD_CD, //세금계산서발행방식코드
        String TXINV_PBL_MTHD_NM, //세금계산서발행방식명
        String TXINV_BIL_DV_CD, //세금계산서청구구분코드
        String TXINV_BIL_DV_NM, //세금계산서청구구분명
        String APLCNS_NM, //신청자명
        String DLPNR_NM, //거래처명
        String CNTR_NO, //계약번호
        String CNTR_SN, //계약일련번호
        Integer PLB_AMT //발행금
    ) {}
}
