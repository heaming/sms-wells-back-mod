package com.kyowon.sms.wells.web.contract.common.dvo;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WctzCntrDtlStatChangeHistDvo {
    private String cntrNo;
    private int cntrSn;
    private String histStrtDtm;
    private String histEndDtm;
    private String cntrDtlStatCd;
    private String cntrDtlStatChRsonCd;
    private String cntrDtlStatChMoCn;
    private String dtaDlYn;
}
