package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctaContractExceptionDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 예외 처리 관리 Search Request Dto
    @Builder
    @ApiModel("WctaContractExceptionDto-SearchReq")
    public record SearchReq(
        @ValidDate
        String strtDt,
        @ValidDate
        String endDt,
        String prtnrNo,
        String cstNo,
        String cntrNo,
        String exProcsCd
    ) {}

    //  Save Request Dto
    @Builder
    @ApiModel("WctaContractExceptionDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String rowState,
        String exProcsId,
        String exProcsCd,
        String exProcsOjDrmTpCd,
        String cstNo,
        String prtnrNo,
        String cntrNo,
        String ogTpCd,
        String dtaDlYn,
        String exProcsDtlCn,
        String vlStrtDtm,
        String vlEndDtm
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 예외 처리 관리 Search Result Dto
    @ApiModel("WctaContractExceptionDto-SearchRes")
    public record SearchRes(
        String exProcsId,
        String exProcsCd,
        String exProcsOjDrmTpCd,
        String cstNo,
        String prtnrNo,
        String cntrNo,
        String ogTpCd,
        String dtaDlYn,
        String exProcsDtlCn,
        String vlStrtDtm,
        String vlEndDtm,
        String fstRgstUsrId,
        String fstRgstUsrNm,
        String fstRgstDtm,
        String fnlMdfcUsrId,
        String fnlMdfcUsrNm,
        String fnlMdfcDtm
    ) {}
}
