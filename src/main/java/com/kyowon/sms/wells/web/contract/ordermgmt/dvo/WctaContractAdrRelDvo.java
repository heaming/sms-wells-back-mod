package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WctaContractAdrRelDvo {
    private String cntrAdrRelId; /* 계약주소관계ID */
    private String vlStrtDtm; /* 유효시작일시 */
    private String vlEndDtm; /* 유효종료일시 */
    private String adrpcTpCd; /* 주소지유형코드 */
    private String cntrUnitTpCd; /* 계약단위유형코드 */
    private String cntrNo; /* 계약번호 */
    private String dtlCntrNo; /* 상세계약번호 */
    private Integer dtlCntrSn; /* 상세계약일련번호 */
    private String cntrAdrpcId; /* 계약주소지ID */
    private String vstRqdt; /* 방문요청일자 */
    private String vstAkStrtHm; /* 방문요청시작시분 */
    private String vstAkEndHm; /* 방문요청종료시분 */
    private String urgtOjYn; /* 긴급대상여부 */
    private String cntrwPosndTpCd; /* 계약서발송처유형코드 */
    private String sppMthdTpCd; /* 배송방식유형코드 */
    private String sppIchrAoffceCd; /* 배송담당사무소코드 */
    private String sppIchrUsrId; /* 배송담당사용자ID */
    private String cntrChRcpId; /* 계약변경접수ID */
    private String dtaDlYn; /* 데이터삭제여부 */

    /* STEP3 */
    private Integer adrpcIdx; /* 중복제거된 adrpcs의 idx */
}
