package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaPifDestructionProcsDvo {
    private String cntrNo;
    @DBEncField
    private String space;
    private String rcgvpKnm;
    private String rcgvpEnm;
    private String adrId;
    private String cralLocaraTno;
    private String mexnoEncr;
    private String cralIdvTno;
    private String locaraTno;
    private String exnoEncr;
    private String idvTno;
    private String emadr;
    private String owrKnm;
    private String owrEnm;
    private String acnoEncr;
    private String crcdnoEncr;
    private String mvsDstcRcvryId;
    private String tblId;
    private String colId;
    private String mvsDstcRcvryOjRefkVal;
}
