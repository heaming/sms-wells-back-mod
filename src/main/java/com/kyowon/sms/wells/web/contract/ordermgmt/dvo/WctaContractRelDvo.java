package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WctaContractRelDvo {
    private String cntrRelId; /* 계약관계ID */
    private String vlStrtDtm; /* 유효시작일시 */
    private String vlEndDtm; /* 유효종료일시 */
    private String cntrUnitTpCd; /* 계약단위유형코드 */
    private String cntrRelTpCd; /* 계약관계유형코드 */
    private String cntrRelDtlCd; /* 계약관계상세코드 */
    private String baseCntrNo; /* 기준계약번호 */
    private String ojCntrNo; /* 대상계약번호 */
    private String baseDtlCntrNo; /* 기준상세계약번호 */
    private Integer baseDtlCntrSn; /* 기준상세계약일련번호 */
    private String ojDtlCntrNo; /* 대상상세계약번호 */
    private Integer ojDtlCntrSn; /* 대상상세계약일련번호 */
    private String cntrCstGrpId; /* 계약고객그룹ID */
    private String cntrRelDtlCn; /* 계약관계상세내용 */
    private String otsdLkDrmVal; /* 외부연계식별값 */
    private String dtaDlYn; /* 데이터삭제여부 */
}
