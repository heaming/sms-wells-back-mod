package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaEmployeeSellQuantityDvo {
    private String rcpMm;
    private String contractFromYm;
    private String contractToYm;
    private String sellPartnerNo;
    private String contractCustomerNo;
    private String baseProductCd;

    private String pdQty;
    private String dsnCntrQty;
    private String istDt;
    private String slDt;
}
