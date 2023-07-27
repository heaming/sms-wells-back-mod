package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaDocumentReceiptPssRequestDvo {
    private String cntrChRcpStrtDtm; /* 접수시작일자 */
    private String cntrChRcpFinsDtm; /* 접수종료일자 */
    private String cntrChPrgsStatCd; /* 접수현황 */
    private String cntrChTpCd; /* 접수유형 */
    private String cntrChRcpId; /* 접수번호 */
    private String cstKnm; /* 고객명 */
    private String cralLocaraTno; /* 휴대지역전화번호 */
    @DBEncField
    private String mexnoEncr; /* 휴대전화국번호암호화 */
    private String cralIdvTno; /* 휴대개별전화번호 */
}
