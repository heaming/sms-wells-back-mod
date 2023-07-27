package com.kyowon.sms.wells.web.contract.changeorder.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctbAffiliateAlncDvo {

    /* 라이프주문수신전문 check */
    private String klsley;
    private String klslem;
    private String klsled;
    private String klyear;
    private String klcode;
    private String alncCanDt;
    private String alncCanRsonCd;

    /* 계약 wells */
    private String cntrNo;
    private int cntrSn;
    private String acmpalCntrId;
    private String basePdCd;
    private String pmotCd;
    private String leaseYn;
    private int dscMcn;
    private int istmMcn;
    private int recapDutyPtrmN;
    private String cntrDt;
    private String sellDscDvCd;
    private String grpDv;
    private String prmApyDvCd;
    private String alncmpCd;

}
