package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaContractExOjBasDvo {
    private String exProcsId;
    private String vlStrtDtm;
    private String vlEndDtm;
    private String exProcsCd;
    private String exProcsOjDrmTpCd;
    private String exProcsOjDrmVal;
    private String exProcsApyLvTpCd;
    private String ogTpCd;
    private String exProcsDtlCn;
    private String dtaDlYn;
    private String orglFnlMdfcDtm;
}
