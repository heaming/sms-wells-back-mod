package com.kyowon.sms.wells.web.contract.changeorder.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctbPrepaymentChCstPsDvo {
    private String ogCd; /*조직코드 AKDDPT*/
    private String cntrDtlNo; /*계약번호(-)계약일련번호 LCCODE02*/
    private String cstKnm; /*고객명 LCCNAM*/
    private String cralLocaraTno; /* 휴대지역전화번호 */
    @DBEncField
    @DBDecField
    private String mexnoEncr; /* 전화국번호암호화 */
    private String cralIdvTno; /* 개별전화번호 */
    private String pdNm; /*상품 LC31.LCICDE KA11.KAINAM*/
    private String pdCd; /*상품 LC31.LCICDE KA11.KAINAM*/
    private String pdClsfNm; /* 상품분류 */
    private String prmPtrm; /*선납기간 LCPSED*/
    private int prmMcn; /*선납개월 LCPMON*/
    private int prmDscr; /*선납할인(율) LCPRAT*/
    private String prmPtrmThm; /*선납기간 LCPSED02*/
    private int prmMcnThm; /*선납개월 LCPMON02*/
    private int prmDscrThm; /*선납할인(율) LCPRAT02*/
}
