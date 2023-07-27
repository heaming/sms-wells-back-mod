package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaOrderDetailMembershipPagesRequestDvo {
    private String rcpDateDv; /* 접수일자선텍 */
    private String strtDt; /* 시작일자 */
    private String endDt; /* 종료일자 */
    private String dateSeltDv; /* 일자선택 */
    private String choStrtDt; /* 일자선택 시작일자 */
    private String choEndDt; /* 일자선택 종료일자 */
    private String sellTpDtlCd; /* 계약구분(판매유형상세코드) */
    private String cntrwTpCd; /* 멤버십구분 */
    private String sellInflwChnlDtlCd; /* 판매구분 */
    private String hcsfVal; /* 상품분류(대분류) */
    private String hcsfMcsfVal; /* 상품분류(소분류) */
    private String pdCd; /* 상품코드 */
    private String pdNm; /* 상품명 */
    private String sellPrtnrNo; /* 파트너코드 */
    private String cntrRcpFshDtYn; /* 미가입자만 조회 */
    private String cntrNo; /* 계약번호 */
    private String cntrSn; /* 계약일련번호 */
    private String bryyMmddEntrpNoCbno; /* 생년월일/사업자/법인등록번호 */
    private String bryyMmdd; /* 생년월일 */
    private String sexDvCd; /* 성별 */
    private String bzrno; /* 사업자번호 */
    private String cstKnm; /* 계약자명 */
    private String cntrCralTno; /* 계약자 휴대전화번호 */
    private String cralLocaraTno; /* 계약자 휴대지역전화번호 */
    @DBEncField
    @DBDecField
    private String mexnoEncr; /* 계약자 휴대전화국번호암호화 */
    private String cralIdvTno; /* 계약자 휴대개별전화번호 */
    private String cntrCstNo; /* 계약고객번호 */
    private String cntrPdEnddtYn; /* 탈퇴제외 */
}
