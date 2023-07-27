package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaPdPrcFnlDtlDvo {
    private String pdCd;
    private int svFeeAmt;
    private int svFeeAmt05;
    private int svFeeAmt10;
    private String verSn;
    private String rentalCheck;
}
