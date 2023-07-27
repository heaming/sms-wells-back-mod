package com.kyowon.sms.wells.web.contract.changeorder.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctbPaymentCancelNextdayDvo {
    private String prcsRslt; // 처리결과
    private String msg; // 메시지
    private String dtlMsg; // 상태메시지
    private String cntrDtlStatCd;
    private String basePdCd;
    private String cntrPdStrtdt;
    private String alncmpCd;

}
