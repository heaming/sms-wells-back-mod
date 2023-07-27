package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import lombok.Getter;
import lombok.Setter;

/* 사전접수 dvo*/
@Getter
@Setter
public class WctaPriorBizBsdtDvo {
    private String wkYm;	/*작업년월*/
    private String rcpStrtdt;	/*접수시작일자*/
    private String rcpEnddt;	/*접수종료일자*/
    private String cnfmStrtdt;	/*확정시작일자*/
    private String cnfmEnddt;	/*확정종료일자*/
}
