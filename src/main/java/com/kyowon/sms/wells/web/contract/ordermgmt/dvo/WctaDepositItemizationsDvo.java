package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaDepositItemizationsDvo {
    private String cntrNo; /* 계약번호 */
    private String cntrSn; /* 계약일련번호 */
    private String cntrDtlNo; /* 계약상세번호 */
    private String sellTpCd; /* 판매유형코드 */
    private String rveNoFull; /* 입금번호 */
    private String rveDt; /* 수납일자 */
    private String perfDt; /* 실적일자 */
    private String pymDt; /* 지급일자 */
    private String rveDvNm; /* 수납구분 */
    private String rveAmt; /* 입금금액 */
    private String dpTpCd; /* 입금유형코드 */
    private String dpTpNm; /* 입금유형(구분) */
    private String cdcoNm; /* 카드사 */
    @DBEncField
    @DBDecField
    private String crcdno; /* 신용카드번호 */
    private String crdcdAprno; /* 카드승인번호 */
    private String crdcdIstmMcn; /* 신용카드할부 */
    private String prmYn; /* 선납유무 */
    private String ozgub1; /* 입금수단코드 */
    private String lcamt1vat; /* 부가세 */
    private String lcamt1sply; /* 공급가액 */
    private String ozlcinst; /* 수납일자 */
    private String dpCprcnfYn; /* 대사여부 */
    private String fnlMdfcPrgId; /* 입금대사 */
    private String cwigub; /* 입금구분 */
    private String cwijub; /* 수납구분 */
    private String dtlview; /* DTLVIEW */
    private String pdNm; /* 상품명 */
}
