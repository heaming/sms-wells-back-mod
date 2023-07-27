package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaDocumentRcpDtlInqrsDvo {
    private String cntrChRcpId; /* 접수번호 */
    private String cstKnm; /* 고객명 */
    private String cralLocaraTno; /* 휴대지역전화번호 */
    @DBDecField
    private String mexnoEncr; /* 휴대전화국번호암호화 */
    private String cralIdvTno; /* 휴대개별전화번호 */
    private String bryyMmdd; /* 생년월일 */
    private String cntrChPrgsMoCn; /* 상담사 메모 */
    private String cntrChTpCd; /* 계약변경유형코드명 */
    private String cntrChTpNm; /* 계약변경유형코드명 */
    private String dtlCntrNo; /* 계약번호 */
    private String dtlCntrSn; /* 계약일련번호 */
    private String cntrDtlNo; /* 계약상세번호 */
    private String sellTpCd; /* 판매유형코드(계약구분) */
    private String sellTpNm; /* 판매유형코드명(계약구분명) */
    private String cntrChRcpD; /* 접수일자 */
    private String cntrChRcpTm; /* 접수시간 */
    private String cntrChPrgsStatCd; /* 계약변경진행상태코드 */
    private String cntrChPrgsStatNm; /* 계약변경진행상태코드명 */
    private String fnlMdfcDtm; /* 최종수정일시 */
    private String cntrChAkCn; /* 재접수 사유 */
    private String cntrChRsonCd; /* 계약변경사유코드 */
    private String cntrChRsonNm; /* 계약변경사유코드명 */
}
