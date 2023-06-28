package com.kyowon.sms.wells.web.service.visit.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
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
    String pdCd;
    String pdNm;
    String istAdr;
    String istDtlAdr;
    String istReferAdr;
    String cpsPrgsCd;
    String fstRgstUsrId;
    String fshDt;
    String vstDt;
    String locaraTno;
    @DBDecField
    @DBEncField
    String exnoEncr;
    String idvTno;
    String cralLocaraTno;
    @DBDecField
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
    String cpsDvCd;
    String fmlRelDvCd1;
    String etcCn1;
    String fmlRelDvCd2;
    String etcCn2;
    String maasFnm;
    String maasBirthdate;
    String maasMpno;
    String rfndAcnoEncr;
    String rfndBnkCd;
    String rfndAcownNm;
    String mpno;
    byte[] signCn;
    String cstSignCn;
    String fmlRelDvNm1;
    String rfndBnkNm;
    String fmlRelDvNm2;
    String wrteDt;
}
