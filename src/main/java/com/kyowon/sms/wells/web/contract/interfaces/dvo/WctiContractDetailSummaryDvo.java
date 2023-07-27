package com.kyowon.sms.wells.web.contract.interfaces.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctiContractDetailSummaryDvo {
    private String cntrtNm;
    private String cntrtCralLocaraTno;
    private String cntrtMexno;
    private String cntrtCralIdvTno;
    private String istCstNm;
    private String istCralLocaraTno;
    @DBDecField
    private String istMexno;
    private String istCralIdvTno;
    private String istLocaraTno;
    @DBDecField
    private String istExno;
    private String istIdvTno;
    private String basePdCd;
    private String basePdNm;
}


