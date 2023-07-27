package com.kyowon.sms.wells.web.contract.common.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctzRveDtlDvo {
    private String rveNo; /*수납번호*/
    private String rveSn; /*수납일련번호*/
    private String rveCd; /*수납코드*/
    private String procsDvCd; /*처리구분코드*/
    private String dpDvCd; /*입금구분코드*/
    private String dpTpCd; /*입금유형코드*/
    private String rveProcsYn; /*수납처리여부*/
    private String rveAmt; /*수납금액*/
    private String rveDt; /*수납일자*/
    private String perfDt; /*실적일자*/
    private String dpMesCd; /*입금수단코드*/
    private String dpCprcnfNo; /*입금대사번호*/
    private String dtaDlYn; /*데이터삭제여부*/
    private String dpAmt; /*입금금액*/
    private String prtnrNo; /*파트너번호*/
    private String rveCoCd; /*수납회사코드*/
    private String alncFee; /*제휴수수료*/
    private String pdCd; /*상품코드*/
    private String cntrNo; /*계약번호*/
    private String rveDvCd; /*수납구분코드*/
    private String cntrSn; /*계약일련번호*/
    private String itgDpNo; /*통합입금번호*/
    private String dpDt; /*입금일자*/
    private String rveAkSn; /*수납요청일련번호*/
    private String ogTpCd; /*조직유형코드*/
    private String rveAkNo; /*수납요청번호*/
    private String kwGrpCoCd; /*교원그룹회사코드*/
    private String mlgUseNo; /*마일리지사용번호*/
    private String orrveNo; /*원수납번호*/
    private String orrveSn; /*원수납일련번호*/
    private String chumRefDrmNo; /*채움몰참조식별번호*/
    private String rveBizDvCd; /*수납업무구분코드*/
    private String incmdcYn; /*소득공제여부*/
}
