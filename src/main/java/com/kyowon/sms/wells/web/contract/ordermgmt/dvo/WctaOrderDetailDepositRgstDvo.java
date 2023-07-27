package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaOrderDetailDepositRgstDvo {
    private String rveDt; /* 수납일자[수납일] */
    private String perfDt; /* 실적일자[실적일] */
    private String cntrNo; /* 계약번호 */
    private String cntrSn; /* 계약일련번호 */
    private String rveAmt; /* 금액 */
    private String dpMesCd; /* 유형(입금수단코드) */
    private String dpMesNm; /* 유형명(입금수단코드명) */
    private String cdcoCd; /* 카드(은행사) 코드 */
    private String cdcoNm; /* 카드(은행사) */
    @DBEncField
    @DBDecField
    private String crcdnoEncr; /* 카드번호(가상계좌) */
    private String crdcdIstmMcn; /* 할부개월 */
    private String fnlMdfcPrgId; /* 모듈 */
    private String stat; /* 상태 */
}
