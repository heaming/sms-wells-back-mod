package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaRentOrdrDtptListDvo {
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
    private String sellDscDvCd; /* 판매할인구분코드 */
    private String sellDscDvNm; /* 판매할인구분코드명 */
    private String sellDscTpCd; /* 판매할인유형코드 */
    private String sellDscTpNm; /* 판매할인유형코드명 */
    private String svPdTpCd; /* 용도 */
    private String svPdTpNm; /* 용도명 */
    private String svPrd; /* 서비스주기 */
    private String svPrdNm; /* 서비스주기명 */
    private String pdBaseAmt; /* 렌탈료(기본약정) */
    private String sellAmt; /* 할인적용가격 */
    private String sellEvCd; /* 행사코드 */
    private String sellEvNm; /* 행사코드명 */
    private String alncmpCd; /* 제휴사코드 */
    private String alncmpNm; /* 제휴사코드명 */
    private String mchnChYn; /* 기기변경 */
    private String pmotCd; /* 할인유형(프로모션코드) */
    private String pmotNm; /* 할인유형(프로모션코드명) */
    private String aftnDvCd; /* 자동이체 */
    private String prmYn; /* 선납여부 */
    private String dcevdnDocId; /* 첨부파일(증빙서류문서ID) */
}
