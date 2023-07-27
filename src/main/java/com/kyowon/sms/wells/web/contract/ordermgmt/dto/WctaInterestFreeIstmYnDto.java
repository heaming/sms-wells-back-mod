package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;

public class WctaInterestFreeIstmYnDto {
    // *********************************************************
    // Request Dto
    // *********************************************************

    // 홈케어 계약 Search Request Dto
    @ApiModel("WctaInterestFreeIstmYnDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String pdCd, // 상품코드
        @ValidDate
        String rcpDt, // 접수일자
        int pdQty, // 상품수량
        String crpDscDvCd, // 법인할인구분코드
        int cshBlam, // 현금잔액
        int istmMcn // 할부개월
    ) {}

}
