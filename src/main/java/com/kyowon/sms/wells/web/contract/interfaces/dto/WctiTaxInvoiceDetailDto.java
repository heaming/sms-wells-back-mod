package com.kyowon.sms.wells.web.contract.interfaces.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WctiTaxInvoiceDetailDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 세금계산서 상세목록  Search Request Dto
    // request 파라미터는 String 이지만, 인터페이스 응답양식(key-value 형태)에 맞추기 위해 DTO 형식으로 선언
    @ApiModel("WctiTaxInvoiceDetailDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String TXINV_ID // 세금계산서ID
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 세금계산서 상세목록 Search Result Dto
    @ApiModel("WctiTaxInvoiceDetailDto-SearchRes")
    public record SearchRes(
        String TRD_DT, //거래일자
        String TXINV_PD_DV_NM, //세금계산서상품구분명
        String CNTR_NO, //계약번호
        Integer CNTR_SN, //계약일련번호
        String CNTRT_NM, //계약자명
        Integer PD_QTY, //상품수량
        Integer SPL_AMT, //공급금액
        Integer VAT_AMT, //부가가치세금액
        Integer SUM_AMT //합계금액
    ) {}
}
