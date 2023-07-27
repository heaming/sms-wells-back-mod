package com.kyowon.sms.wells.web.contract.risk.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctcIcptSellChHistDvo {
    private String icptSellId;
    private String histStrtDtm;
    private String histEndDtm;
    private String icptSellExrDt;
    private String baseCntrNo;
    private int baseCntrSn;
    private String cntrDtlStatCd;
    private String icptSellTpCd;
    private String icptSellRsonCn;
    private String icptSellOjPrtnrNo;
    private String ojCntrNo;
    private int ojCntrSn;
    private String icptSellProcsTpCd;
    private String icptSellProcsDtm;
    private int icptSellProcsTn;
    private String rfndProcsTpCd;
    private String dtaDlYn;
}
