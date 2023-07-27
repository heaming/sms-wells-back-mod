package com.kyowon.sms.wells.web.contract.interfaces.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctiContractInstallHistoryDvo {
    private String cntrNo;
    private int cntrSn;
    private String cralLocaraTno;
    @DBDecField
    private String mexno;
    private String cralIdvTno;
    private String locaraTno;
    @DBDecField
    private String exno;
    private String idvTno;
    private String addrId;
    private String addr;
    private String dtlAddr;
    private String chDtm;
    private String fnlMdfcUsrId;
    private String fnlMdfcUsrNm;
}
