package com.kyowon.sms.wells.web.contract.changeorder.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaBogoCustomerCheckResultDvo {
    private String cntrNo;
    private String cntrCstNo;
    private String sellPrtnrNo;
    private String cntrCnfmDtm;
    private String sellTpCd;
    private String dscApyTpCd;
    private String dscApyDtlCd;
    private String pdCd;
    private String pdTpCd;
    private String pdClsfCd;
    private String refPdClsfVal;
    private String frisuBfsvcPtrmN;
    private String pmotCd;
}
