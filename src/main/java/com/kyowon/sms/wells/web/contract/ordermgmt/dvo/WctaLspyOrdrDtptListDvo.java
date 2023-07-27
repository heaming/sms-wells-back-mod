package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaLspyOrdrDtptListDvo {
    private String cntrNo; /* 계약번호 */
    private String cntrSn; /* 계약일련번호 */
    private String cntrRcpFshDt; /* 접수완료일 */
    private String cntrPrgsStatCd; /* 계약진행상태코드 */
    private String cntrwTpCd; /* 계약서유형코드 */
    private String cntrPrgsStatNm; /* 계약진행상태코드명 */
    private String sellOgTpCd; /* 판매조직유형코드 */
    private String sellPrtnrNo; /* 판매자사번 */
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
    private String svPdTpCd; /* 용도 */
    private String svPdTpNm; /* 용도명 */
    private String svPrd; /* 서비스주기 */
    private String svPrdNm; /* 서비스주기명 */
    private String frisuBfsvcPtrmN; /* 무상개월 BS */
    private String mshJYn; /* 무상여부 */
    private String frisuAsPtrmN; /* 무상개월 AS */
    private String nocmDvCd; /* 무상구분코드 */
    private String fnlAmt; /* 상품금액 */
    private String cntrAmt; /* 계약금 총액 */
    private String cshStlmAmt; /* 현금결제 */
    private String cardStlmAmt; /* 카드결제 */
    private String crpUcAmt; /* 법인미수 */
    private String istmPcamAmt; /* 할부원금 */
    private String istmMcn; /* 할부개월 */
    private String istmIntAmt; /* 할부수수료 */
    private String mmIstmAmt; /* 월납부액 */
    private String aftnDvCd; /* 자동이체 */
    private String mshYn; /* 멤버십여부 */
    private String mshSspcs; /* 멤버십회비 */
    private String dpTpNm; /* 멤버십자동이체 */
    private String mchnChYn; /* 기기변경 */
    private String mchnChCntrNo; /* 기변 기준계약번호 */
    private String mchnChCntrSn; /* 기변 기준계약일련번호 */
    private String sellEvCd; /* 행사코드 */
    private String sellEvNm; /* 행사코드명 */
    private String alncmpCd; /* 제휴사코드 */
    private String alncmpNm; /* 제휴사코드명 */
    private String alncJAcN; /* 제휴 구좌수 */
    private String alncCntrNo; /* 제휴계약번호 */
}
