package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaMbOrdrDtptListDvo {
    private String cntrNo; /* 계약번호 */
    private String cntrSn; /* 계약일련번호 */
    private String cntrPrgsStatCd; /* 계약진행상태코드 */
    private String cntrPrgsStatNm; /* 계약진행상태코드명 */
    private String rcgvpKnm; /* 이름(설치자명) */
    private String istCralTno; /* 휴대전화번호 */
    private String istCralLocaraTno; /* 휴대지역전화번호 */
    @DBEncField
    @DBDecField
    private String istMexnoEncr; /* 휴대전화국번호암호화 */
    private String istCralIdvTno; /* 휴대개별전화번호 */
    private String istAdrZip; /* 우편번호 */
    private String istAdr; /* 설치처 정보 */
    private String istRnadr; /* 기준주소 */
    private String istRdadr; /* 상세주소 */
    private String basePdCd; /* 상품코드 */
    private String pdNm; /* 상품명 */
    private String pdQty; /* 상품수량 */
    private String mshSspcs; /* 멤버십회비 */
    private String aftnDvCd; /* 자동이체 */
    private String prmYn; /* 선납여부 */
}
