package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaEmployeePrchsGcfMngtRequestDvo {
    private String istDt; /* 귀속년도 */
    private String emplDv; /* 직원구분 */
    private String empNo; /* 사번 */
    private String cntrtRelCd; /* 선택구분(계약자관계코드) */
    private String cralLocaraTno; /* 휴대지역전화번호 */
    @DBDecField
    private String mexnoEncr; /* 휴대전화국번호암호화 */
    private String cralIdvTno; /* 휴대개별전화번호 */
}
