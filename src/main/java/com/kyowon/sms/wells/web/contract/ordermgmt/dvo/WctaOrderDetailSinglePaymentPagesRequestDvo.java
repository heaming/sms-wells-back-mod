package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import java.util.List;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaOrderDetailSinglePaymentPagesRequestDvo {
    private String searchGbn;
    private String bryyMmdd;
    private String bzrno;
    private String sexGbn;
    private String cstKnm;
    private String cralLocaraTno; /* 휴대지역전화번호 */
    @DBEncField
    @DBDecField
    private String mexnoEncr; /* 휴대전화국번호암호화 */
    private String cralIdvTno; /* 휴대개별전화번호 */
    private String cntrCstNo; /* 계약고객번호 */
    private String cntrCanYn;
    private String cntrNo; /* 계약번호 */
    private String cntrSn; /* 계약일련번호 */
    private String prdEnqry; /* 기간조회 */
    private String strtDt; /* 시작일자 */
    private String endDt; /* 종료일자 */
    private String hcsfVal; /* 상품분류(대분류) */
    private String hcsfMcsfVal; /* 상품분류(소분류) */
    private String pdCd; /* 상품코드 */
    private String pdNm; /* 상품명 */
    private String alncmpCd; /* 제휴코드 */
    private String sellEvCd; /* 행사코드 */
    private List<String> etcDv; /* 기타 */
    private String sellPrtnrNo; /* 파트너코드 */
    private List<String> dgr1LevlOgId; /* 조직코드(총괄단) */
    private List<String> dgr2LevlOgId; /* 조직코드(지역단) */
    private List<String> dgr3LevlOgId; /* 조직코드(지점) */
    private List<String> sellOgTpCd; /* 조직구분 */
}
