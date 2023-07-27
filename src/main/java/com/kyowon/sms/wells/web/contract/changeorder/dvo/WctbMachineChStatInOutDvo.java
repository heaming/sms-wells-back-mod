package com.kyowon.sms.wells.web.contract.changeorder.dvo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WctbMachineChStatInOutDvo {
    // *********************************************************
    // Request param
    // *********************************************************
    private String cntrNo; // 기존 계약번호
    private String cntrSn; // 기존 계약 일련번호
    private String cstNo; // 계약자 고객번호
    private String indvCrpDv; // 법인격구분코드(1.개인, 2.법인)
    private String pdCd; // 현재 진행 중인 기준상품코드
    private String dscDv; // 할인적용유형코드
    private String dscTp; // 할인적용상세코드
    private String sellTpCd; // 판매유형코드(1.일시불/할부, 2.렌탈)
    private String alncmpCd; // 제휴사코드
    private String ogTpCd; // session의 조직유형코드
    private String rctDt; // 접수일자

    // *********************************************************
    // Result param
    // *********************************************************
    private String workFlag; // 기기변경유형
    private String pdNm;     // 이전기기 상품명
    private String resultDvCheck; // 결과구분CHECK
    private String resultMessage;
    private int finalPerfRt; // 최종실적율
    private String ptyCopnDvCd; // 상대법인격구분코드
    private int rentalNmnN; // 렌탈차월
    private String rerntPsbDt; // 재렌탈가능일
    private String stplDutyStrtDt; // 약정의무시작일
    private String stplDutyEndDt; // 약정의무종료일
    private String rstlDutyStrtDt; // 재약정의무시작일
    private String rstlDutyEndDt; // 재약정의무종료일
    private String ownrsExnDt; // 소유권이전종료일
    private int dlqAmt; // 연체금액
    private int ucAmt; // 미수금액
    private int recapDutyPtrmN; // 의무기간 수(월)
}
