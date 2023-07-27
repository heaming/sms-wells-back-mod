package com.kyowon.sms.wells.web.contract.orderstatus.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctdInChargeCustomerOrderDvo {
    //request param
    private String cntrNo; /* 계약번호 */
    private String cntrSn; /* 계약일련번호 */
    private String cstKnm; /* 계약자명 */
    private String cstNo; /* 고객번호 */
    private String cralLocaraTno; /* 휴대지역전화번호 */
    @DBEncField
    @DBDecField
    private String mexno; /* 휴대전화국번호 */
    private String cralIdvTno; /* 휴대개별전화번호 */

    //response param
    private String mpNo; /* 휴대전화번호 */
    private String sellTpNm; /* 계약구분 */
    private String cntrDtlStatNm; /* 상태 */
    private String pdNm; /* 상품명 */
    private String adr; /* 기준주소 */
    private String dtlAdr; /* 상세주소 */
    private String cntrPrgsStatCd;
}
