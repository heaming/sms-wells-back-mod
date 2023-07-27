package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaCardSalesSlipsDvo {
    private String cntrNo; /* 계약번호 */
    private String cntrSn; /* 계약일련번호 */
    private String cntrDtlNo; /* 계약상세번호 */
    private String basePdCd; /* 상품코드 */
    private String pdNm; /* 상품명 */
    private String sellTpCd; /* 판매유형코드(주문유형) */
    private String rveOjDrmNo; /* 수납대상식별번호 */
    private String cardgbn; /* 카드구분 */
    private String edcgbn; /* 승인취소구분 */
    private String crdcdIstmMcn; /* 할부개월 */
    private String dpstdt; /* 입금일자 */
    private String dpsthm; /* 입금일시 */
    private String dpcndt; /* 취소일자 */
    private String dpcnhm; /* 취소일시 */
    private String cardvndr; /* 카드사명 */
    @DBEncField
    @DBDecField
    private String dpcdnoFull; /* 카드번호 */
    private String crdcdAprno; /* 신용카드승인번호 */
    private String dpAmt; /* 입금금액 */
    private String lcetccnt; /* 입금대사건수 */
    private String affno; /* 가맹점번호 */
    private String cntrCstNo; /* 고객번호 */
}
