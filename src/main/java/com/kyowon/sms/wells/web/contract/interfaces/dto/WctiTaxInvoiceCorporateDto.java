package com.kyowon.sms.wells.web.contract.interfaces.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WctiTaxInvoiceCorporateDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 세금계산서 사업자번호 조회 Search Request Dto
    // request 파라미터는 String 이지만, 인터페이스 응답양식(key-value 형태)에 맞추기 위해 DTO 형식으로 선언
    @ApiModel("WctiTaxInvoiceCorporateDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String DLPNR_NM // 거래처명
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 세금계산서 사업자번호 조회 Search Result Dto
    @ApiModel("WctiTaxInvoiceCorporateDto-SearchRes")
    public record SearchRes(
        String DLPNR_CD,    //거래처코드
        String DLPNR_NM,    //거래처명
        String BZRNO,       //사업자등록번호
        String DLGPS_NM     //대표자명
    ) {}
}
