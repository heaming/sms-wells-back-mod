package com.kyowon.sms.wells.web.contract.changeorder.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctbSodbtCanCstPsInqrDvo {
    private String ogCd; //소속
    private String hooPrtnrNm; // 본부장명
    private String cntrNo; // 계약번호
    private String cntrSn; // 계약 일련번호
    private String cstKnm; // 계약자 한글명
    private String istCralLocaraTno; // 설치자 휴대전화번호1
    @DBEncField
    @DBDecField
    private String istMexnoEncr; // 설치자 휴대전화번호2
    private String istCralIdvTno; // 설치자 휴대전화번호3
    private String istAdrZip; // 설치자정보 우편번호
    private String istRnadr; // 설치자정보 기준주소
    private String istRdadr; // 설치자정보 상세주소
    private String pdClsfNm; // 구분 - 중분류
    private String pdMclsfRnm; // 구분
    private String pdAbbrNm; // 상품약어명
    private String istDt; // 설치일자
    private String canDt; // 취소일자
    private String canCn; // 취소 내용
    private String sellInflwChnlDtlCd; // 판매유입채널상세코드
}
