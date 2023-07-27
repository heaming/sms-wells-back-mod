package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;

public class WctaFilterCntrInfDtlInqrDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 필터계약정보 상세조회(계약상세번호 단위) Search Request Dto
    @Builder
    @ApiModel("WctaFilterCntrInfDtlInqrDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String cntrNo,
        String cntrSn
    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 필터계약정보 상세조회(계약상세번호 단위) Search Result Dto
    @ApiModel("WctaFilterCntrInfDtlInqrDto-SearchRes")
    public record SearchRes(
        @NotBlank
        String cntrNo,
        String cntrSn,
        String svPdTpCd,
        String basePdCd,
        String pdNm,
        String pdQty,
        String frisuYn,
        String fnlAmt
    ) {
    }
}
