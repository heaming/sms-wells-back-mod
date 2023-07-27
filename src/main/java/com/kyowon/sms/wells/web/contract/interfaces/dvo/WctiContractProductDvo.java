package com.kyowon.sms.wells.web.contract.interfaces.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctiContractProductDvo {
    private String pdCd;
    private String pdHclsfId;
    private String pdMclsfId;
    private String pdLclsfId;
    private String pdDclsfId;
    private String sellTpCd;
    private String sellTpDtlCd;
    private String fnlVal;
    private String vat;
    private String sellFee;
    private String ackmtPerfAmt;
    private String ackmtPerfRt;
    private String cvtPerfAmt;
    private String pdBaseAmt;
    private String pdPrcFnlDtlId;
    private String verSn;
    private String ctrVal;
    private String fxamFxrtDvCd;
    private String pdPrcId;
}
