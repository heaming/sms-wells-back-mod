package com.kyowon.sms.wells.web.service.allocate.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsncAsTransferDvo {

    String svCnrOgId; /* 서비스센터조직ID */
    String ichrPrtnrNo; /* 담당파트너번호 */
    String assignDateFrom; /* 배정일자From */
    String assignDateTo; /* 배정일자To */
    String vstCnfmdt; /* 방문확정일 */
    String svBizHclsfCd; /* 작업구분코드 */

}
