package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WctaContractStlmRelDvo {
    private String cntrStlmRelId; /* 계약결제관계ID */
    private String vlStrtDtm; /* 유효시작일시 */
    private String vlEndDtm; /* 유효종료일시 */
    private String cntrUnitTpCd; /* 계약단위유형코드 */
    private String cntrStlmId; /* 계약결제ID */
    private String cntrNo; /* 계약번호 */
    private String dtlCntrNo; /* 상세계약번호 */
    private Integer dtlCntrSn; /* 상세계약일련번호 */
    private String dpTpCd; /* 입금유형코드 */
    private String rveDvCd; /* 수납구분코드 */
    private String islndIncmdcTpCd; /* 도서지역소득공제유형코드 */
    private Long stlmAmt; /* 결제금액 */
    private String dtaDlYn; /* 데이터삭제여부 */
}
