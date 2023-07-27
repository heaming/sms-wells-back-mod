package com.kyowon.sms.wells.web.contract.interfaces.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctiRegularDeliveryPackageDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 변경가능 패지키 목록 Find Request Dto
    @ApiModel("WctiRegularDeliveryPackageDto-FindReq")
    public record FindReq(
        @JsonProperty("DV_CD")
        @NotBlank
        @Pattern(regexp="1|2")
        String dvCd, // 구분코드(1.신규, 2.변경)
        @JsonProperty("CNTR_NO")
        @NotBlank
        String cntrNo, // 계약번호(필수)
        @JsonProperty("CNTR_SN")
        @NotNull
        int cntrSn, // 계약일련번호(필수)
        @JsonProperty("PRC_DV_CD")
        @NotBlank
        @Pattern(regexp="1|2")
        String prcDvCd // 가격구분코드/ 결합여부(1.결합, 2.분리)
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 변경가능 패지키 목록 Find Result Dto
    @Builder
    @ApiModel("WctiRegularDeliveryPackageDto-FindRes")
    public record FindRes(
        @JsonProperty("PKG_PD_CD")
        String pkgPdCd, // 패키지상품코드(기준상품코드)
        @JsonProperty("PKG_PD_NM")
        String pkgPdNm, // 패키지상품코드명
        @JsonProperty("PKG_PD_AMT")
        Integer pkgPdAmt, // 패키지상품금액
        @JsonProperty("PDCT_LIST")
        List<Product> pdctList
    ) {}

    @ApiModel("WctiRegularDeliveryPackageDto-Product")
    public record Product(
        @JsonProperty("PDCT_CD")
        String pdctCd, // 패키지 구성 제품코드
        @JsonProperty("PDCT_NM")
        String pdctNm, // 제품코드명
        @JsonProperty("PDCT_QTY")
        Integer pdctQty, // 제품수량
        @JsonProperty("PDCT_AMT")
        Integer pdctAmt // 제품금액
    ) {}
}





