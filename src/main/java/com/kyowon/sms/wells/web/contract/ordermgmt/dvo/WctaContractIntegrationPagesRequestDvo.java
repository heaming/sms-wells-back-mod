package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import java.util.List;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaContractIntegrationPagesRequestDvo {
    private String cntrCnfmStrtDtm; /* 계약확정시작일시 */
    private String cntrCnfmFinsDtm; /* 계약확정종료일시 */
    private String plarDv; /* 플래너구분(판매자/관리자) */
    private String prtnrDv; /* 파트너내역구분(사번/성명/부서코드) */
    private String hmnrscEmpno; /* 인사사원번호 */
    private String ogTpCd; /* 조직유형코드 */
    private String ogCd; /* 부서코드(조직코드) */
    private String cntrCstSeltDv; /* 고객선택구분 */
    private String cntrCstNo; /* 고객번호(세이프키) */
    private String cntrCstNm; /* 고객명(계약자/설치자) */
    private String cntrCstMpno; /* 휴대전화번호(계약자/설치자) */
    private String cralLocaraTno; /* 휴대지역전화번호(계약자/설치자) */
    @DBEncField
    @DBDecField
    private String mexnoEncr; /* 휴대전화국번호암호화(계약자/설치자) */
    private String cralIdvTno; /* 휴대개별전화번호(계약자/설치자) */
    private String bzrno; /* 사업자번호 */
    private String sfkVal; /* 세이프키값 */
    private String cntrNo; /* 계약번호 */
    private String cntrSn; /* 계약일련번호 */
    private List<String> sellTpCd; /* 계약구분(판매유형코드) */
}
