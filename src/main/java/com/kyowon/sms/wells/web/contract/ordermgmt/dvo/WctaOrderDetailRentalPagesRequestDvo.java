package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import java.util.List;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaOrderDetailRentalPagesRequestDvo {
    private String prdEnqry; /* 기간조회 */
    private String strtDt; /* 시작일자 */
    private String endDt; /* 종료일자 */
    private String rentalNmn; /* 렌탈차월 */
    private String hcsfVal; /* 상품분류(대분류) */
    private String hcsfMcsfVal; /* 상품분류(소분류) */
    private String pdCd; /* 상품코드 */
    private String pdNm; /* 상품명 */
    private String alncmpCd; /* 제휴코드 */
    private String sellEvCd; /* 행사코드 */
    private String sellPrtnrNo; /* 파트너코드 */
    private List<String> dgr1LevlOgId; /* 조직코드(총괄단) */
    private List<String> dgr2LevlOgId; /* 조직코드(지역단) */
    private List<String> dgr3LevlOgId; /* 조직코드(지점) */
    private String cndtSellTpCd; /* 판매유형상세 */
    private List<String> sellOgTpCd; /* 조직구분 */
    private String booSellYn; /* 자료구분(예약자료) */
    private String canYn; /* 자료구분(취소제외) */
    private String slYn; /* 자료구분(매출생성) */
    private String cntrNo; /* 계약번호 */
    private String cntrSn; /* 계약일련번호 */
    private String cntrCstNo; /* 계약고객번호 */
    private String istCralTno; /* 휴대지역전화번호 */
    private String cralLocaraTno; /* 설치자 휴대지역전화번호 */
    @DBEncField
    @DBDecField
    private String mexnoEncr; /* 설치자 휴대전화국번호암호화 */
    private String cralIdvTno; /* 설치자 휴대개별전화번호 */
    private String rcgvpKnm; /* 설치자명 */
}
