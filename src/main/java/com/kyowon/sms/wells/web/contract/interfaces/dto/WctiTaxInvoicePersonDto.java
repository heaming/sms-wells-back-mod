package com.kyowon.sms.wells.web.contract.interfaces.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WctiTaxInvoicePersonDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 세금계산서 담당자 Search Request Dto
    // request 파라미터는 String 이지만, 인터페이스 응답양식(key-value 형태)에 맞추기 위해 DTO 형식으로 선언
    @ApiModel("WctiTaxInvoicePersonDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String PSIC_NM // 담당자명
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 세금계산서 담당자 Search Result Dto
    @ApiModel("WctiTaxInvoicePersonDto-SearchRes")
    public record SearchRes(
        String PSIC_ID, //담당자ID
        String PSIC_NM, //담당자명
        String DEPT_NM  //부서명
    ) {}
}
