package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaFilterContractInqrDvo {
    private String cntrNo;
    private String cstKnm;
    private String cralLocaraTno;
    @DBDecField
    private String mexnoEncr;
    private String cralIdvTno;
    private String filterPdCd1;
    private String filterPdNm1;
    private String filterPdCd2;
    private String filterPdNm2;
    private String filterPdCd3;
    private String filterPdNm3;
    private String filterPdCd4;
    private String filterPdNm4;
    private String filterPdCd5;
    private String filterPdNm5;
    private String svPdTpCd;
    private String svPdTpNm;
    private String svPrd;
    private String sellTpCd;
    private String sellTpNm;
    private String mbGbn;
    private String sldt;
    private String cttDt;
    private String istGbn;
    private String filterAmt;
    private String filterQty;
    private String cttRsCd;
    private String reCttYn;
    private String cttPsicId;
    private String sconCn;
    private String istAkArtcMoCn;
}

