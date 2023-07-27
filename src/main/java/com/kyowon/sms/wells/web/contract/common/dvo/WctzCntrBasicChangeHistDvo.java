package com.kyowon.sms.wells.web.contract.common.dvo;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WctzCntrBasicChangeHistDvo {
    private String cntrNo; /* 계약번호 */
    private String histStrtDtm; /* 이력시작일시 */
    private String histEndDtm; /* 이력종료일시 */
    private String cntrCstNo; /* 계약고객번호 */
    private String copnDvCd; /* 법인격구분코드 */
    private String sellInflwChnlDtlCd; /* 판매유입채널상세코드 */
    private String SsellOgTpCd; /* 판매조직유형코드 */
    private String sellPrtnrNo; /* 판매파트너번호 */
    private String cntrTpCd; /* 계약유형코드 */
    private String cntrNatCd; /* 계약국가코드 */
    private String cntrPrgsStatCd; /* 계약진행상태코드 */
    private String cstStlmInMthCd; /* 고객결제입력방법코드 */
    private String prrRcpCntrYn; /* 사전접수계약여부 */
    private String cntrTempSaveDtm; /* 계약임시저장일시 */
    private String cntrRcpFshDtm; /* 계약접수완료일시 */
    private String cntrStlmFshDtm; /* 계약결제완료일시 */
    private String cntrCnfmAprAkDtm; /* 계약확정승인요청일시 */
    private String cntrCnfmAprDtm; /* 계약확정승인일시 */
    private String cntrCnfmDtm; /* 계약확정일시 */
    private String cntrCanDtm; /* 계약취소일시 */
    private String cntrCanRsonCd; /* 계약취소사유코드 */
    private String cntrPrgsStatMoCn; /* 계약진행상태메모내용 */
    private String dsbGurTpCd; /* 지급보증유형코드 */
    private String cntrInflwPhDvCd; /* 계약유입경로구분코드 */
    private String pspcCstId; /* 가망고객ID */
    private String cntrChRcpId; /* 계약변경접수ID */
    private String dcevdnDocId; /* 증빙서류문서ID */
    private String dtaDlYn; /* 데이터삭제여부 */
}
