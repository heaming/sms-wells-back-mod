package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaEmployeePrchsGcfMngtDvo {
    private String empNo; /* 사번 */
    private String empDv; /* 사번구분 */
    private String prtnrKnm; /* 성명 */
    private String cralLocaraTno; /* 휴대지역전화번호 */
    @DBEncField
    @DBDecField
    private String mexnoEncr; /* 휴대전화국번호암호화 */
    private String cralIdvTno; /* 휴대개별전화번호 */
    private String cralTno; /* 휴대전화번호 */
    private String rsgnDt; /* 퇴사일 */
    private String sellInflwChnlDtlCd; /* 소속 */
    private String bfIstCn; /* 전월-설치 */
    private String bfCanCn; /* 전월-취소 */
    private String bfFnlCn; /* 전월-최종 */
    private String thmIstCn; /* 당월-설치 */
    private String thmCanCn; /* 당월-취소 */
    private String thmFnlCn; /* 당월-최종 */
    private String mth01IstCn; /* 1월-설치 */
    private String mth01CanCn; /* 1월-취소 */
    private String mth01FnlCn; /* 1월-최종 */
    private String mth02IstCn; /* 2월-설치 */
    private String mth02CanCn; /* 2월-취소 */
    private String mth02FnlCn; /* 2월-최종 */
    private String mth03IstCn; /* 3월-설치 */
    private String mth03CanCn; /* 3월-취소 */
    private String mth03FnlCn; /* 3월-최종 */
    private String mth04IstCn; /* 4월-설치 */
    private String mth04CanCn; /* 4월-취소 */
    private String mth04FnlCn; /* 4월-최종 */
    private String mth05IstCn; /* 5월-설치 */
    private String mth05CanCn; /* 5월-취소 */
    private String mth05FnlCn; /* 5월-최종 */
    private String mth06IstCn; /* 6월-설치 */
    private String mth06CanCn; /* 6월-취소 */
    private String mth06FnlCn; /* 6월-최종 */
    private String mth07IstCn; /* 7월-설치 */
    private String mth07CanCn; /* 7월-취소 */
    private String mth07FnlCn; /* 7월-최종 */
    private String mth08IstCn; /* 8월-설치 */
    private String mth08CanCn; /* 8월-취소 */
    private String mth08FnlCn; /* 8월-최종 */
    private String mth09IstCn; /* 9월-설치 */
    private String mth09CanCn; /* 9월-취소 */
    private String mth09FnlCn; /* 9월-최종 */
    private String mth10IstCn; /* 10월-설치 */
    private String mth10CanCn; /* 10월-취소 */
    private String mth10FnlCn; /* 10월-최종 */
    private String mth11IstCn; /* 11월-설치 */
    private String mth11CanCn; /* 11월-취소 */
    private String mth11FnlCn; /* 11월-최종 */
    private String mth12IstCn; /* 12월-설치 */
    private String mth12CanCn; /* 12월-취소 */
    private String mth12FnlCn; /* 12월-최종 */
    private String totFnlCn; /* 합계 */
}
