package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctaOrderDetailVirtualAcSmsDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 주문상세페이지 내부 팝업_가상계좌 메세지 보내기 Search Request Dto

    @Builder
    @ApiModel("WctaOrderDetailVirtualAcSmsDto-SearchReq")
    public record SearchReq(

        @NotBlank
        String cntrNo,
        @NotNull
        int cntrSn
    ) {}
    @Builder
    @ApiModel("WctaOrderDetailVirtualAcSmsDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String cntrNo,
        @NotNull
        int cntrSn,
        @NotBlank
        String cstNm,
        @NotBlank
        String msgTit,
        @NotBlank
        String template,
        @NotBlank
        String cralLocaraTno,
        @NotBlank
        String mexnoEncr,
        @NotBlank
        String cralIdvTno
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
}
