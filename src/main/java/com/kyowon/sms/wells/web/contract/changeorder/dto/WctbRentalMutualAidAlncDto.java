package com.kyowon.sms.wells.web.contract.changeorder.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctbRentalMutualAidAlncDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    //  Search Request Dto
    @Builder
    @ApiModel("WctbRentalMutualAidAlncDto-SearchReq")
    public record SearchReq(
        String indvGubun, //개인구분
        String rcpDt, //접수일자
        String allnPrdtCd, //제휴상품코드
        @NotBlank
        String rentalDscDvCd, //(렌탈)할인구분코드
        @NotBlank
        String rentalDscTpCd, //(렌탈)할인유형코드
        @NotBlank
        String vstPrd, //방문주기
        @NotBlank
        String svPdTpCd, //서비스상품유형코드
        @NotBlank
        String mchnChYn, //기변여부
        @NotBlank
        String alncmpCd, //제휴사코드
        String ptyIndvGubun, //상대개인구분
        String ptySellTpCd, //상대판매유형코드
        String ptyPdCd, //상대상품코드
        @NotBlank
        String mchnChTpCd //기기변경유형코드
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    //  Search Result Dto
    @ApiModel("WctbRentalMutualAidAlncDto-SearchRes")
    public record SearchRes() {}
}
