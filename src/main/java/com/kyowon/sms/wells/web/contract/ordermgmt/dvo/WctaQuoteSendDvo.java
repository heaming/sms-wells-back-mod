package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaQuoteSendDvo {
    private String fwTpCd;
    private String sendTelNo1;
    private String sendTelNo2;
    private String sendTelNo3;
    private String recpTelNo1;
    private String recpTelNo2;
    private String recpTelNo3;
    private String sendMail;
    private String recpMail;
    private String cntrCstNo;
    private String cntrCstNm;
    private String cntrCstCralLocaraTno;
    @DBDecField
    private String cntrCstMexnoEncr;
    private String cntrCstCralIdvTno;
    private String emadr;
    private String copnDvNm;
    private String basePdCd;
    private String basePdNm;
    private String sellDscTpCd;
    private String sellDscTpNm;
    private String stplPtrmUnitCd;
    private String stplPtrm;
    private String svPtrmUnitCd;
    private String svPrd;
    private String alncmpCdNm;
    private String cntrAmt;
    private String pdBaseAmt;
    private String fnlAmt;
    private String linkUrl;
    private String prtnrKnm;
    private String prtnrCralLocaraTno;
    @DBDecField
    private String prtnrMexnoEncr;
    private String prtnrCralIdvTno;
    private String cntrAprId;
    private String cntrNo;
    private String cntrSn;
}
