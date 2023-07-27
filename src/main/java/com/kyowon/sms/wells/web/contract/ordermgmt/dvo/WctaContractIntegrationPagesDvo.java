package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaContractIntegrationPagesDvo {
    private String ojSellTpNm; /* 계약구분 */
    private String cntrDtlNo; /* 계약상세번호 */
    private String cstKnm; /* 고객명 */
    private String rcgvpKnm; /* 설치고객 */
    private String cstGdCd; /* 고객등급 */
    private String pdNm; /* 상품명 */
    private String sellTpCd; /* 유형 */
    private String cntrCnfmDtm; /* 계약일 */
    private String istDt; /* 설치일 */
    private String cntrStat; /* 사용구분 */
    private String svPrd; /* 주기 */
    private String cancWtdrDt; /* 취소/탈퇴일 */
    private String rentalTn; /* 설치차월 */
    private String fmmbN; /* 가구수 */
    private String cntrTno; /* 계약자 전화번호 */
    private String cntrLocaraTno; /* 계약자 지역전화번호 */
    @DBEncField
    @DBDecField
    private String cntrExnoEncr; /* 계약자 전화국번호암호화 */
    private String cntrIdvTno; /* 계약자 개별전화번호 */
    private String cntrCralTno; /* 계약자 휴대전화번호 */
    private String cntrCralLocaraTno; /* 계약자 휴대지역전화번호 */
    @DBEncField
    @DBDecField
    private String cntrMexnoEncr; /* 계약자 휴대전화국번호암호화 */
    private String cntrCralIdvTno; /* 계약자 휴대개별전화번호 */
    private String istTno; /* 설치자 전화번호 */
    private String istLocaraTno; /* 설치자 지역전화번호 */
    @DBEncField
    @DBDecField
    private String istExnoEncr; /* 설치자 전화국번호암호화 */
    private String istIdvTno; /* 설치자 개별전화번호 */
    private String istCralTno; /* 설치자 휴대전화번호 */
    private String istCralLocaraTno; /* 설치자 휴대지역전화번호 */
    @DBEncField
    @DBDecField
    private String istMexnoEncr; /* 설치자 휴대전화국번호암호화 */
    private String istCralIdvTno; /* 설치자 휴대개별전화번호 */
    private String copnDvCd; /* 고객구분코드 */
    private String dpTpCd; /* 이체방식 납부방식유형코드 */
    private String dpTpNm; /* 이체방식 납부방식유형코드명 */
    private String mpyBsdt; /* 이체일 */
    private String bryyMmdd; /* 생년월일 */
    private String bzrno; /* 사업자번호 */
    private String txinvPblOjYn; /* 세금계산서 발행대상여부 */
    private String txinvPblD; /* 세금계산서 발행일 */
    private String sexDvNm; /* 성별 */
    private String cntrCstNo; /* 고객번호 */
    private String adrZip; /* 우편번호 */
    private String instAddr; /* 설치 주소 */
    private String sppOrdIvcNo; /* 송장번호 */
    private String sellOgNm; /* 소속코드 */
    private String istmMcn; /* 할부개월수 */
    private String pdctReqdRqdt; /* 제품철거요청일자 */
    private String reqdDt; /* 철거일자 */
    private String histStrtDtm; /* 이력시작일시 */
    private String alncmpCd; /* 제휴사코드 */
    private String rsltYn; /* 재약정여부 */
    private String sellPrtnrNo; /* 판매자 사번 */
    private String sellPrtnrKnm; /* 판매자명 */
    private String sellCralTno; /* 판매자 휴대전화번호 */
    private String sellCralLocaraTno; /* 판매자 휴대지역전화번호 */
    @DBEncField
    @DBDecField
    private String sellMexnoEncr; /* 판매자 휴대전화국번호암호화 */
    private String sellCralIdvTno; /* 판매자 휴대개별전화번호 */
    private String ogCd; /* 조직코드 */
    private String ogNm; /* 조직명 */
    private String bsPrtnrNo; /* 웰스매니저 사번 */
    private String bsPrtnrKnm; /* 웰스매니저명 */
    private String bsOgCd; /* 웰스매니저 조직코드 */
    private String bsOgNm; /* 웰스매니저 조직명 */
    private String bsCralTno; /* 웰스매니저 휴대전화번호 */
    private String bsCralLocaraTno; /* 웰스매니저 휴대지역전화번호 */
    @DBEncField
    @DBDecField
    private String bsMexnoEncr; /* 웰스매니저 휴대전화국번호암호화 */
    private String bsCralIdvTno; /* 웰스매니저 휴대개별전화번호 */
}
