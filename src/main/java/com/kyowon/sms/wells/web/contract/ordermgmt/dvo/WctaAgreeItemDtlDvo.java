package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import lombok.*;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class WctaAgreeItemDtlDvo {
    private String cstAgId; /* 고객동의ID*/
    private String agAtcDvCd; /* 동의 항목 구분 코드*/
    private String pdCd; /* 상품코드 */
    private String prvDocId; /* 약관문서ID */
    private String agStatCd; /* 동의상태코드: 01 동의 02 거절 03 미결정 */
    private String agStatdt; /* 동의시작일자 */
    private String agExnDuedt; /* 동의만료예정일자 */
    private String agExnPrcsdt; /* 동의만료처리일자 */
    private String agExnProcsRsonCd; /* 동의만료처리사유코드 */

    private String mndtYn; /* 필수 여부 있어야 하지 않나 */
}
