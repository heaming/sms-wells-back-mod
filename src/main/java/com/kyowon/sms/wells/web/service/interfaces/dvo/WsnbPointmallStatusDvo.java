package com.kyowon.sms.wells.web.service.interfaces.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsnbPointmallStatusDvo {

    String wkAcpteStatCd; /* 작업수락상태코드 */
    String wkAcpteDt; /* 작업수락일자 */
    String wkAcpteHh; /* 작업수락시간 */
    String vstCnfmdt; /* 방문확정일자 */
    String vstCnfmHh; /* 방문확적시간 */
    String wkExcnDt; /* 작업수행일자 */
    String wkExcnHh; /* 작업수행시간 */
    String wkPrgsStatCd; /* 작업진행상태코드 */
    String rtngdYn; /* 반품여부 */

}
