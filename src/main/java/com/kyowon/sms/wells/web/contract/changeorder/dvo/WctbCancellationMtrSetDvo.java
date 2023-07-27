package com.kyowon.sms.wells.web.contract.changeorder.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctbCancellationMtrSetDvo {
    private String cntrChRcpId;
    private String cntrChSn;
    private String cntrUnitTpCd;
    private String procsDuedt;
    private String procsFshDtm;
    private String cntrChPrgsStatCd;
    private String cntrChRcpDtm;
    private String cntrChFshDtm;
    private String cntrNo;
    private String cntrSn;
    private String cntrCanDtm;
    private String cntrCanDtmNchk;
    private String cntrChFshDtmNchk;
}
