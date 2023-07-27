package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctaContractDocumentMailDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 계약서 메일발송 Search Request Dto
    @Builder
    @ApiModel("WctaContractDocumentMailDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String docDvCd, // 발행구분(증빙서류 구분)
        @NotBlank
        String recpMail, // 이메일
        @NotBlank
        String cstKnm, // 고객명
        @ValidDate
        @NotBlank
        String startDt, // 시작일
        @ValidDate
        @NotBlank
        String endDt, // 종료일
        @ValidDate
        @NotBlank
        String pblDt, // 발행일
        @NotBlank
        String cstGubun, // 구분
        @NotBlank
        String title, // 제목
        @NotBlank
        String titleStr, // 분류

        List<Contract> cstList // 계약리스트
    ) {}

    public record Contract(
        @NotBlank
        String cntrDtlNo, // 계약상세번호

        @NotBlank
        String cntrNo,
        @NotBlank
        String cntrSn
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
}
