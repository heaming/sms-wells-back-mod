package com.kyowon.sms.wells.web.contract.interfaces.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctiCustomerDvo {
    //request/response parameter
    private String cstNm;
    private String cralLocaraTno;
    @DBEncField
    @DBDecField
    private String mexno;
    private String cralIdvTno;
    private String locaraTno;
    @DBEncField
    @DBDecField
    private String exno;
    private String idvTno;
    private String cstNo;

    //response parameter
    private String cstDv;
    private String itgCstNo;
    private String cstKnm;
    private String emadr;
    private String bryyMmdd;
    private String sexDvCd;
}
