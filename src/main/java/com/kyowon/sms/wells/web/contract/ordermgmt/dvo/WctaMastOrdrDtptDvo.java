package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaMastOrdrDtptDvo {
    private String cntrNo; /* 계약번호 */
    private String cntrSn; /* 계약일련번호 */
    private String cntrDtlNo; /* 계약상세번호 */
    private String cntrwTpCd; /* 계약서구분(계약서유형코드) */
    private String cntrPrgsStatCd; /* 계약진행상태코드 */
    private String cntrRcpFshDt; /* 접수일자 */
    private String cntrCralLocaraTno; /* 계약자 휴대지역전화번호 */
    @DBEncField
    @DBDecField
    private String cntrMexnoEncr; /* 계약자 휴대전화국번호암호화 */
    private String cntrCralIdvTno; /* 계약자 휴대개별전화번호 */
    private String sellTpCd; /* 판매유형코드 */
    private String cstKnm; /* 고객명 */
    private String cntrCnt; /* 그룹주문 건수 */
    private String basePdCd; /* 상품코드 */
    private String pdNm; /* 상품명 */
    private String sellTpDtlCd; /* 상품각사속성상세 */
    private String svAlncBzsCd; /* 서비스제휴업체 */
    private String sppDuedt; /* 배송일자 */
    private String sowDuedt; /* 파종예정일자 */
}
