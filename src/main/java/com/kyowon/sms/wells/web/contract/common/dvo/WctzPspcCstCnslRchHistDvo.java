package com.kyowon.sms.wells.web.contract.common.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctzPspcCstCnslRchHistDvo extends WctzPspcCstCnslRcmdIzDvo {
    private String histStrtDtm;
    private String histEndDtm;
}
