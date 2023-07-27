package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaDocumentReceiptPssDvo {
    private String cntrChRcpId; /* 접수번호 */
    private String reCntrChRcpId; /* 재접수번호 */
    private String cntrChRcpD; /* 접수일 */
    private String cntrChRcpTm; /* 접수시간 */
    private String cntrChPrgsStatCd; /* 접수현황코드 */
    private String cntrChPrgsStatNm; /* 접수현황코드명 */
    private String cntrChPrgsStatCdEnd; /* 기타종료코드 */
    private String cntrChPrgsStatNmEnd; /* 기타종료코드명*/
    private String cstKnm; /* 고객명 */
    private String cralLocaraTno; /* 휴대지역전화번호 */
    @DBDecField
    private String mexnoEncr; /* 휴대전화국번호암호화 */
    private String cralIdvTno; /* 휴대개별전화번호 */
    private String cntrChTpCd; /* 접수유형 */
    private String cntrChTpNm; /* 접수유형명 */
    private String fnlMdfcDtm; /* 최종수정일시 */
}
