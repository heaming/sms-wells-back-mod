package com.kyowon.sms.wells.web.contract.interfaces.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctiRegularDeliveryProductDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 정기배송 최근 배송제품 Search Request Dto
    @Builder
    @ApiModel("WctiRegularDeliveryProductDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String CNTR_NO,
        @NotNull
        int CNTR_SN
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 정기배송 최근 배송제품 Search Result Dto
    @ApiModel("WctiRegularDeliveryProductDto-SearchRes")
    public record SearchRes(
        String CNTR_NO, /* 본문루트 */
        Integer CNTR_SN, /* 계약번호 */
        String BASE_PD_CD, /* 계약일련번호 */
        Integer TOT_PD_QTY, /* 기준상품코드 */
        String PDCT_PD_CD, /* 총상품수량 */
        String PDCT_PD_NM, /* 제품상품코드 */
        String RGLR_SPP_DV /* 제품상품명 */
    ) {}
}
