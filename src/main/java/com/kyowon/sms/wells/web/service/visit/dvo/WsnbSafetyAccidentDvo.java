package com.kyowon.sms.wells.web.service.visit.dvo;

import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WsnbSafetyAccidentDvo {

    String acdnRcpId;
    String acdnRcpNm;
    String cntrNo;
    String cntrSn;
    String cstNm;
    String acdnDtm;
    String agrDocRcvYn;
    String pdNm;
    String istAdr;
    String istDtlAdr;
    String istReferAdr;
    String cpsPrgsCd;
    String fstRgstUsrId;
    String fshDt;
    String vstDt;
    String locaraTno;
    @DBEncField
    String exnoEncr;
    String idvTno;
    String cralLocaraTno;
    @DBEncField
    String mexnoEncr;
    String cralIdvTno;
    String slDt;
    String fstRgstDtm;
    String rcpMoCn;
    String acdnCausCn;
    String cstDmdCn;
    String acdnRsCn;
    String fnlMdfcDtm;
    String rptrNm;
    String svCnrOgId;
    String brchNm;
    String imptaRsonCd;
    int totCpsAmt;
    int kwCpsAmt;
    int insrcoCpsAmt;
    String rcpdt;
    String svCnrNm;
    String cnrldNm;
    String istLctDtlCn;
    String imptaRsonNm;
    String agrDocFwYn;
    String psicNm;
    String vstIz;
    String damgIz;
    String estIz;
    String agrIz;
    int totRduAmt;
}
