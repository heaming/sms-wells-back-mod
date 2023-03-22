package com.kyowon.sms.wells.web.service.visit.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsnbPointmallStatusInfDvo {

    String WK_ACPTE_STAT_CD; /* 작업수락상태코드 */
    String WK_ACPTE_DT; /* 작업수락일자 */
    String WK_ACPTE_HH; /* 작업수락시간 */
    String VST_CNFMDT; /* 방문확정일자 */
    String VST_CNFM_HH; /* 방문확적시간 */
    String WK_EXCN_DT; /* 작업수행일자 */
    String WK_EXCN_HH; /* 작업수행시간 */
    String WK_PRGS_STAT_CD; /* 작업진행상태코드 */
    String RTNGD_YN; /* 반품여부 */

}
