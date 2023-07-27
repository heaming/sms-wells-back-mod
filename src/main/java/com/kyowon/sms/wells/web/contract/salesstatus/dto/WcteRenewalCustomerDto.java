package com.kyowon.sms.wells.web.contract.salesstatus.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WcteRenewalCustomerDto {
    @Builder
    @ApiModel("WcteRenewalCustomerDto-SearchReq")
    public record SearchReq(
        String asnDvCd, // 처리상세. 렌탈만료(1),재약정(2)
        String dlqMcn, // 연체개월. 선택안함(),1개월(1),2개월(2),3개월(3),4개월(4),5개월(5),6개월(6),7개월이상(7)
        String dlqYn, // 연체제외. Y,N
        @NotBlank
        String srchPeriodGbn, // 조회기간구분. 만료일자(1),렌탈차월(2)
        @ValidDate
        String cntrPdEnddtFrom, // 만료일자From
        @ValidDate
        String cntrPdEnddtTo, // 만료일자To
        String istNmnN, // 렌탈차월
        String hcsfVal, // 대분류
        String mcsfVal, // 중분류
        String pdCd, // 상품코드
        String pdNm, // 상품명
        List<String> dgr1LevlOgIds, // 총괄단
        List<String> dgr2LevlOgIds, // 지역단
        List<String> dgr3LevlOgIds, // 지점
        String copnDvCd, // 자료구분. 전체(),개인(1),법인(2)
        String cstAgYn // 마케팅동의여부. Y,N
    ) {}

    @ApiModel("WcteRenewalCustomerDto-SearchRes")
    public record SearchRes(
        String cntrNo, // 계약번호
        String cntrSn, // 계약일련번호
        String cntrNoSn, // [계약상세번호]
        String cstKnm, // [계약자명] 계약자정보
        String rcgvpKnm, // [설치자명] 설치자명
        String cntrCralLocaraTno, // [휴대전화번호1] 계약자 휴대폰번호1
        String cntrMexnoEncr, // [휴대전화번호2] 계약자 휴대폰번호2
        String cntrCralIdvTno, // [휴대전화번호3] 계약자 휴대폰번호3
        String pdClsfNm, // [상품분류] 상품분류(대분류 > 중분류)
        String pdCd, // [상품코드] 상품코드
        String pdNm, // [상품명] 상품명
        String ogCd, // [조직코드] 조직코드(판매자의조직코드)
        String cstAgYn, // [마케팅동의여부] 마케팅동의여부
        String slRcogDt, // [매출일] 매출인식일자
        String asnDvCd, // [배정구분] 배정구분
        String asnDvNm, // [배정구분명] 배정구분명
        String asnDt, // [배정일] 배정일자
        String canDt, // [취소일] 이력시작일시
        String reqdDt, // [철거일] 철거일자
        String taskNm, // [테스크명] 테스크명
        String psicNo, // [담당자사번] 담당자 사번
        String psicNm, // [담당자명] 담당자 명
        String cttCd, // [컨택코드] 컨택코드
        String cttNm, // [컨택명] 컨택명
        String dgr1LevlOgId, // 조회조건: 총괄단 - 조직(1차레벨조직ID)
        String dgr2LevlOgId, // 조회조건: 지역단 - 조직(2차레벨조직ID)
        String dgr3LevlOgId, // 조회조건: 지점 - 조직(3차레벨조직ID)
        String refPdClsfVal, // 상품분류코드 참조키(대분류+중분류가 포함되어 있음 EX) 02001
        String copnDvCd, // 개인법인 구분
        String cntrPdEnddt, // 계약상품종료일자 : 만료일자
        String istNmnN, // 설치차월 : INLTNOMNT
        String eotDlqAmt, // 연체금액-기말연체금액
        String dlqAcuMcn // 연체개월-연체누적개월수
    ) {}
}
