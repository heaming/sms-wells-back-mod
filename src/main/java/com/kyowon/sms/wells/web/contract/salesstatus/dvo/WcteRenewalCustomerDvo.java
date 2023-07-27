package com.kyowon.sms.wells.web.contract.salesstatus.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WcteRenewalCustomerDvo {
    private String cntrNo; // 계약번호
    private String cntrSn; // 계약일련번호
    private String cntrNoSn; // [계약상세번호]
    private String cstKnm; // [계약자명] 계약자정보
    private String rcgvpKnm; // [설치자명] 설치자명
    private String cntrCralLocaraTno; // [휴대전화번호1] 계약자 휴대폰번호1
    @DBDecField
    private String cntrMexnoEncr; // [휴대전화번호2] 계약자 휴대폰번호2
    private String cntrCralIdvTno; // [휴대전화번호3] 계약자 휴대폰번호3
    private String pdClsfNm; // [상품분류] 상품분류(대분류 > 중분류)
    private String pdCd; // [상품코드] 상품코드
    private String pdNm; // [상품명] 상품명
    private String ogCd; // [조직코드] 조직코드(판매자의조직코드)
    private String cstAgYn; // [마케팅동의여부] 마케팅동의여부
    private String slRcogDt; // [매출일] 매출인식일자
    private String asnDvCd; // [배정구분] 배정구분
    private String asnDvNm; // [배정구분명] 배정구분명
    private String asnDt; // [배정일] 배정일자
    private String canDt; // [취소일] 이력시작일시
    private String reqdDt; // [철거일] 철거일자
    private String taskNm; // [테스크명] 테스크명
    private String psicNo; // [담당자사번] 담당자 사번
    private String psicNm; // [담당자명] 담당자 명
    private String cttCd; // [컨택코드] 컨택코드
    private String cttNm; // [컨택명] 컨택명
    private String dgr1LevlOgId; // 조회조건: 총괄단 - 조직(1차레벨조직ID)
    private String dgr2LevlOgId; // 조회조건: 지역단 - 조직(2차레벨조직ID)
    private String dgr3LevlOgId; // 조회조건: 지점 - 조직(3차레벨조직ID)
    private String refPdClsfVal; // 상품분류코드 참조키(대분류+중분류가 포함되어 있음 EX) 02001
    private String copnDvCd; // 개인법인 구분
    private String cntrPdEnddt; // 계약상품종료일자 : 만료일자
    private String istNmnN; // 설치차월 : INLTNOMNT
    private String eotDlqAmt; // 연체금액-기말연체금액
    private String dlqAcuMcn; // 연체개월-연체누적개월수
}
