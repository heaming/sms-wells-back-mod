package com.kyowon.sms.wells.web.contract.interfaces.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;

/**
 * EAI INTERFACE 통신용 DTO
 * request/response의 body에 정의된 key 형식(snake case) 사용
 */
public class WctiFreeGiftDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 사은품 정보 Search Request Dto
    @ApiModel("WctiFreeGiftDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String CNTR_NO,
        @NotNull
        int CNTR_SN
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 사은품 정보 Search Response Dto
    @ApiModel("WctiFreeGiftDto-SearchRes")
    public record SearchRes(
        String FGPT_PD_CD, // 사은품상품코드
        String FGPT_PD_NM, // 사은품상품명
        Integer FGPT_QTY // 사은품수량
    ) {}
}
