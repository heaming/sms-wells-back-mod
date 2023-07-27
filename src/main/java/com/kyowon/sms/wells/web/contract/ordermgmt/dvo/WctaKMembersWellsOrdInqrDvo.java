package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaKMembersWellsOrdInqrDvo {
    private String cmnSfkVal;
    private String cntrNo;
    private String cntrSn;
    private String basePdCd;
    private String pdNm;
    private String prchsPh;
    private String sellTpCd;
    private String sellTpDtlCd;
    private String fnlAmt;
    private String cntrPdStrtdt;
    private String recapDutyPtrmN;
    private String mmIstmAmt;
    private String istRnadr;
    private String istRdadr;
    private String cntrDtlStat;
    private String istDt;
    private String rentalTn;
    private String welsMnger;
    private String welsMngerCralLocaraTno;
    @DBEncField
    @DBDecField
    private String welsMngerMexnoEncr;
    private String welsMngerCralIdvTno;
    private String mpyMthdTp;
    private String fnitNm;
    @DBEncField
    @DBDecField
    private String acnoCrdcdEncr;
    private String owrKnm;
    private String mpyBsdt;
}
