package com.kyowon.sms.wells.web.contract.orderstatus.dto;

import javax.validation.constraints.Pattern;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctdHoldingCustomerDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 보류고객 관리 Search Request Dto
    @Builder
    @ApiModel("WctdHoldingCustomerDto-SearchReq")
    public record SearchReq(
        @ValidDate
        String startDt,
        @ValidDate
        String endDt,
        @Pattern(regexp = "^$|[YN]")
        String cttYn,
        String cntrNo,
        Integer cntrSn
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 보류고객 관리 Search Result Dto
    @ApiModel("WctdHoldingCustomerDto-SearchRes")
    public record SearchRes(
        String cntrDtlNo, /*계약상세번호*/
        String cntrCnfmDtm, /*계약확정일시 cut of time in front*/
        String cttYn, /*컨택여부*/
        String pdNm, /*상품명*/
        String cttRsCd, /*컨택결과코드: LC_CTT_RS_CD*/
        String cttMoCn, /* 컨택메모내용 */
        String ogCd, /*본부장 조직코드*/
        String prtnrKnm, /*본부장 한글명*/
        String cstKnm /* 고객명 */
    ) {}
}
