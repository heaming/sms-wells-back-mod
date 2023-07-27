package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaOrderDetailCustomerBaseDvo {
    private String cntrDtlNo; /* 계약상세번호 */
    private String cntrNo; /* 계약번호 */
    private String cntrSn; /* 계약일련번호 */
    private String sellTpCd; /* 판매유형코드 */
    private String pdNm; /* 상품명 */
    private String cstKnm; /* 고객명 */
    private String cntrCstNo; /* 고객번호(교원키) */
    private String cstNo2; /* 개인:생년월일, 법인:사업자번호 */
    private String cntrCralLocaraTno; /* 계약자 휴대지역전화번호 */
    @DBEncField
    @DBDecField
    private String cntrMexnoEncr; /* 계약자 휴대전화국번호암호화 */
    private String cntrCralIdvTno; /* 계약자 휴대개별전화번호 */
    private String cralTno; /* 계약자 휴대지역전화번호 */
    private String cstGd; /* 고객등급 */
    private String sexDvNm; /* 성별 */
    private String aftnInfo; /* 계좌자동이체 상세정보 */
    private String dpTpCd; /* 입금유형코드 */
    @DBEncField
    @DBDecField
    private String acnoEncr; /* 계좌번호 */
    @DBEncField
    @DBDecField
    private String crcdnoEncr; /* 카드번호 */
    private String sfkVal; /* 세이프키값 */
    private String vacInfo; /* 가상계좌 */
    private String cntrtAdr; /* 계약자 주소 */
    private String rcgvpKnm; /* 수령자한글명 */
    private String istCralLocaraTno; /* 설치자 휴대지역전화번호 */
    @DBEncField
    @DBDecField
    private String istMexnoEncr; /* 설치자 휴대전화국번호암호화 */
    private String istCralIdvTno; /* 설치자 휴대개별전화번호 */
    private String rcgvpTno; /* 설치자 휴대전화번호 */
    private String rcgvpAdr; /* 수령자 주소 */
}
