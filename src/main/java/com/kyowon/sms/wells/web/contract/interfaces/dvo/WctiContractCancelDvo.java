package com.kyowon.sms.wells.web.contract.interfaces.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctiContractCancelDvo {
    // 계약정보
    private String cntrNo;
    private String cntrSn;
    private String sellTpCd;
    private String cntrExnDt;
    private String canDt;

    // 멤버십계약취소 시 조정/공제금액
    private String slCtrAmt;
    private String dscDdctam;
    private String filtDdctam;

    // 기타
    String rgstUsrId;
}
