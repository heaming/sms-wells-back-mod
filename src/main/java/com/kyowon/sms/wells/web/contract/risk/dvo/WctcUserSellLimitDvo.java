package com.kyowon.sms.wells.web.contract.risk.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctcUserSellLimitDvo {
    private String rowState;
    private String sellBaseId;
    private String sellBaseSn;
    private String sellBaseCd;
    private String vlStrtDtm;
    private String vlEndDtm;
    private String sellBasePsicId;
    private String sellBaseTpCd;
    private String sellPrmitDvCd;
    private String sellBaseApyCn;
    private String sellBaseChval;
    private String sellBaseNuval;
}
