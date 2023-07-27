package com.kyowon.sms.wells.web.contract.changeorder.dvo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WctbRentalMutualAidAlncDvo {
    private String alncmpCd; // 제휴사코드
    private String sellTpCd; // 판매유형코드
    private String pdCd; // 상품코드
    private String svPdTpCd; // 서비스상품유형코드
    private String vstPrd; // 방문주기
    private String rentalDscDvCd; // (렌탈)할인구분코드
    private String rentalDscTpCd; // (렌탈)할인유형코드

    private String indvGubun; //개인구분
    private String rcpDt; //접수일자
    private String allnPrdtCd; //제휴상품코드
    private String mchnChYn; //기변여부
    private String ptyIndvGubun; //상대개인구분
    private String ptySellTpCd; //상대판매유형코드
    private String ptyPdCd; //상대상품코드
    private String mchnChTpCd; //기기변경유형코드

    private String checkYn; // 체크여부
}
