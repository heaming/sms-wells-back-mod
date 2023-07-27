package com.kyowon.sms.wells.web.contract.changeorder.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;

public class WctbSodbtCanCstPsInqrDto {
    @ApiModel("WctbSodbtCanCstPsInqrDto-SearchRes")
    public record SearchRes(
        String ogCd, //소속
        String hooPrtnrNm, // 본부장명
        String cntrNo, // 계약번호
        String cntrSn, // 계약 일련번호
        String cstKnm, // 계약자 한글명
        String istCralLocaraTno, // 설치자 휴대전화번호1
        @DBDecField
        String istMexnoEncr, // 설치자 휴대전화번호2
        String istCralIdvTno, // 설치자 휴대전화번호3
        String istAdrZip, // 설치자정보 우편번호
        String istRnadr, // 설치자정보 기준주소
        String istRdadr, // 설치자정보 상세주소
        String pdClsfNm, // 구분 - 중분류
        String pdMclsfRnm, // 구분
        String pdAbbrNm, // 상품약어명
        String istDt, // 설치일자
        String canDt, // 취소일자
        String canCn, // 취소 내용
        String sellInflwChnlDtlCd // 판매유입채널상세코드
    ) {}
    @ApiModel("WctbSodbtCanCstPsInqrDto-SearchRes")
    public record SearchReq(
        @NotBlank
        @ValidDate
        String canStrtDt,
        @NotBlank
        @ValidDate
        String canEndDt,
        String pdGbn
    ) {}
}
