package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaEmployeePrchsGcfMngtDvo {
    private String empNo; /* 사번 */
    private String fnm; /* 성명 */
    private String cralLocaraTno; /* 휴대지역전화번호 */
    @DBEncField
    @DBDecField
    private String mexnoEncr; /* 휴대전화국번호암호화 */
    private String cralIdvTno; /* 휴대개별전화번호 */
    private String cralTno; /* 휴대전화번호 */
    private String rsgnDt; /* 퇴사일 */
    private String sellInflwChnlDtlCd; /* 소속 */
    private String cntPre; /* 전월-설치 */
    private String cntPreC; /* 전월-취소 */
    private String cntPreFnl; /* 전월-최종 */
    private String cntCur; /* 당월-설치 */
    private String cntCurC; /* 당월-취소 */
    private String cntCurFnl; /* 당월-최종 */
    private String cnt01; /* 1월-설치 */
    private String cnt01C; /* 1월-취소 */
    private String cnt01Fnl; /* 1월-최종 */
    private String cnt02; /* 2월-설치 */
    private String cnt02C; /* 2월-취소 */
    private String cnt02Fnl; /* 2월-최종 */
    private String cnt03; /* 3월-설치 */
    private String cnt03C; /* 3월-취소 */
    private String cnt03Fnl; /* 3월-최종 */
    private String cnt04; /* 4월-설치 */
    private String cnt04C; /* 4월-취소 */
    private String cnt04Fnl; /* 4월-최종 */
    private String cnt05; /* 5월-설치 */
    private String cnt05C; /* 5월-취소 */
    private String cnt05Fnl; /* 5월-최종 */
    private String cnt06; /* 6월-설치 */
    private String cnt06C; /* 6월-취소 */
    private String cnt06Fnl; /* 6월-최종 */
    private String cnt07; /* 7월-설치 */
    private String cnt07C; /* 7월-취소 */
    private String cnt07Fnl; /* 7월-최종 */
    private String cnt08; /* 8월-설치 */
    private String cnt08C; /* 8월-취소 */
    private String cnt08Fnl; /* 8월-최종 */
    private String cnt09; /* 9월-설치 */
    private String cnt09C; /* 9월-취소 */
    private String cnt09Fnl; /* 9월-최종 */
    private String cnt10; /* 10월-설치 */
    private String cnt10C; /* 10월-취소 */
    private String cnt10Fnl; /* 10월-최종 */
    private String cnt11; /* 11월-설치 */
    private String cnt11C; /* 11월-취소 */
    private String cnt11Fnl; /* 11월-최종 */
    private String cnt12; /* 12월-설치 */
    private String cnt12C; /* 12월-취소 */
    private String cnt12Fnl; /* 12월-최종 */
    private String cntTot; /* 합계 */
}
