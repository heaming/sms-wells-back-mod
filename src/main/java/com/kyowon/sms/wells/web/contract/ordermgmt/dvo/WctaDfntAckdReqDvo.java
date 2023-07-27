package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaDfntAckdReqDvo {
    public String cntrNo; /* 계약번호 */
    public String isCurReq; /* 최신여부 */
    public String cancYn; /* 취소여부 */
    public String recvId; /* 수신사용자ID */
    public String aprvYn; /* 승인여부 */
}
