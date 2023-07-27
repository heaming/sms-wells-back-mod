package com.kyowon.sms.wells.web.contract.interfaces.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctiContractDetailDvo {
    private String cntrNo;
    private int cntrSn;
    private String cntrDtlStatCd;
    private String cntrDtlStatNm;
    private String pdHclsfId;
    private String pdHclsfNm;
    private String pdMclsfId;
    private String pdMclsfNm;
    private String pdLclsfId;
    private String pdLclsfNm;
    private String basePdCd;
    private String basePdNm;
    private String cntrtNm;
    private String cntrDt;
    private String cntrSellTpCd;
    private String cntrSellTpNm;
    private String cntrCralLocaraTno;
    @DBDecField
    private String cntrMexno;
    private String cntrCralIdvTno;
    private String cntrLocaraTno;
    @DBDecField
    private String cntrExno;
    private String cntrIdvTno;
    private String cntrAdrpcId;
    private String cntrAdr;
    private String cntrDtlAdr;
    private String cralLocaraTno;
    @DBDecField
    private String mexno;
    private String cralIdvTno;
    private String locaraTno;
    @DBDecField
    private String exno;
    private String idvTno;
    private String adrpcId;
    private String adr;
    private String dtlAdr;
    private String bryyMmdd;
    private String sexDvCd;
    private String istllNm;
}
