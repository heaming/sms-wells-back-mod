package com.kyowon.sms.wells.web.customer.contract.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WcsaCustomerDvo {
    private String cstNo;               /* 고객번호 */
    private String copnDvCd;            /* 법인격구분코드 */
    private String copnDvNm;            /* 법인격구분명 */
    private String cstGdCd;             /* 고객등급코드 */
    private String cstGdNm;             /* 고객등급명 */
    private String cstNm;               /* 고객한글명 */
    private String bryyMmdd;            /* 생년월일 */
    private String sexDvCd;             /* 성별구분코드 */
    private String sexDvNm;             /* 성별구분명 */
    private String zip;                 /* 우편번호 */
    private String basAdr;              /* 기본주소 */
    private String dtlAdr;              /* 상세주소 */
    private String adr;                 /* 주소 */
    private String cralLocaraTno;       /* 휴대전화번호 - 지역전화번호 */
    @DBDecField
    private String mexnoEncr;           /* 휴대전화번호 - 전화국번호 */
    private String cralIdvTno;          /* 휴대전화번호 - 개별전화번호 */
    private String mpno;                /* 휴대전화번호 */
    private String hpLocaraTno;         /* 자택전화번호 - 지역전화번호 */
    @DBDecField
    private String hpEncr;              /* 자택전화번호 - 전화국번호 */
    private String hpIdvTno;            /* 자택전화번호 - 개별전화번호 */
    private String hpno;                /* 자택전화번호 */
    private String safeKeyDupYn;        /* 세이프키 중복여부 */
}
