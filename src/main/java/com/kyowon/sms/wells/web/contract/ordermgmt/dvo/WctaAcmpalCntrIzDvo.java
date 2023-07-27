package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WctaAcmpalCntrIzDvo {
    private String acmpalCntrId; /* 관계사제휴계약ID */
    private String baseCntrNo; /* 기준계약번호 */
    private Integer baseCntrSn; /* 기준계약일련번호 */
    private String ojCntrNo; /* 대상계약번호 */
    private Integer ojCntrSn; /* 대상계약일련번호 */
    private String ojOrdNo; /* 대상주문번호 */
    private String sellTpCd; /* 판매유형코드 */
    private String alncmpCd; /* 제휴사코드 */
    private String alncStatTpCd; /* 제휴상태유형코드 */
    private String alncCntrDt; /* 제휴계약일자 */
    private String alncCanDt; /* 제휴취소일자 */
    private String alncCanRsonCd; /* 제휴취소사유코드 */
    private Integer alncJAcN; /* 제휴가입계좌수 */
    private String dtaDlYn; /* 데이터삭제여부 */
}
