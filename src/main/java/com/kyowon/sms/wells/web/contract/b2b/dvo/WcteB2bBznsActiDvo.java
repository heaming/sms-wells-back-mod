package com.kyowon.sms.wells.web.contract.b2b.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WcteB2bBznsActiDvo {
    private String rowState;
    private String leadCstId;
    private String leadCstRlpplId;
    private String prjNm;
    private String opptId;
    private String fstRgstDt;
    private String dgr3LevlOgCd;
    private String opptIchrPrtnrNo;
    private String prtnrKnm;
    private String prjFomCd;
    private String bzrno;
    private String leadCstNm;
    private String leadCstRlpplNm;
    private String locaraTno;
    @DBDecField
    private String exnoEncr;
    private String idvTno;
    private String cralLocaraTno;
    @DBDecField
    private String mexnoEncr;
    private String cralIdvTno;
    private String emadrCn;
    private String crdrVal;
    private String etBiddDt;
    private String opptCntrFomCd;
    private String totQty;
    private String wrfrQty;
    private String arcleQty;
    private String bdtQty;
    private String etcQty;
    private String etRcvodQty;
    private String etRcvodRat;
    private String rcvodDt;
    private String lossDt;
    private String etCntrStrtdt;
    private String maxStplPtrm;
    private String biddBzsNm;
    private String unuitmCn;
    private String fnlMdfcDt;
    private String opptSn;
    private String pdClsfNm;
    private String pdNm;
    private String basePdCd;
    private String pdQty;
    private String fnlAmt;
    private String ogTpCd;
}
