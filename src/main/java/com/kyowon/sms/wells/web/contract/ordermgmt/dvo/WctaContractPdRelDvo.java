package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WctaContractPdRelDvo {
    private String cntrPdRelId; /* 계약상품관계ID */
    private String vlStrtDtm; /* 유효시작일시 */
    private String vlEndDtm; /* 유효종료일시 */
    private String cntrNo; /* 계약번호 */
    private Integer cntrSn; /* 계약일련번호 */
    private String pdRelId; /* 상품관계ID */
    private String basePdCd; /* 기준상품코드 */
    private String ojPdCd; /* 대상상품코드 */
    private String pdRelTpCd; /* 상품관계유형코드 */
    private String rltgSppOjYn; /* 실물배송대상여부 */
    private Long pdQty; /* 상품수량 */
    private String cntrChRcpId; /* 계약변경접수ID */
    private String dtaDlYn; /* 데이터삭제여부 */
}
