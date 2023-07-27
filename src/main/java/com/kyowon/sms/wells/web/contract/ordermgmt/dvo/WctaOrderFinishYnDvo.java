package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaOrderFinishYnDvo {
    private String copnDvCd;
    private String cntrCanDt;
    private String cntrCanYn;
    private String fulpyYn;
    private String fulpyDt;
    private String dfaYn;
    private String eotDlqAmt;
    private String eotDlqDt;
    private String ucAmt;
}
