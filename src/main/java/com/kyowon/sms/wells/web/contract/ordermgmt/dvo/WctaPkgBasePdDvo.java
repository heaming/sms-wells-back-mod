package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaPkgBasePdDvo {
    private String pdCd;
    private String sellTpDtlCd;
    private int totQty;
    private int totKndQty;
}
