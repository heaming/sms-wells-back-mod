package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WctaContractPrtnrRelDvo {
    private String cntrPrtnrRelId; /* 계약파트너관계ID */
    private String vlStrtDtm; /* 유효시작일시 */
    private String vlEndDtm; /* 유효종료일시 */
    private String cntrNo; /* 계약번호 */
    private String ogTpCd; /* 조직유형코드 */
    private String prtnrNo; /* 파트너번호 */
    private String cntrPrtnrTpCd; /* 계약파트너유형코드 */
    private String cntrPrtnrTpDrmVal; /* 계약파트너유형식별값 */
    private String ogId; /* 조직ID */
    private String rcpAoffceCd; /* 접수사무소코드 */
    private String prrBizRgrYn; /* 사전업무등록자여부 */
    private String alncPrtnrDrmVal; /* 제휴파트너식별값 */
    private String alncPrtnrIdnrNm; /* 제휴파트너식별자명 */
    private String alncPrtnrDrmDvCd; /* 제휴파트너식별구분코드 */
    private String dtaDlYn; /* 데이터삭제여부 */

    private String prtnrKnm;
    private String dgr1LevlOgCd; /* 1차레벨조직코드 */
    private String dgr1LevlOgNm; /* 1차레벨조직명 */
    private String dgr2LevlOgCd; /* 2차레벨조직코드 */
    private String dgr2LevlOgNm; /* 2차레벨조직명 */
    private String dgr3LevlOgCd; /* 3차레벨조직코드 */
    private String dgr3LevlOgNm; /* 3차레벨조직명 */
    private String dgr4LevlOgCd; /* 4차레벨조직코드 */
    private String dgr4LevlOgNm; /* 4차레벨조직명 */
    private String dgr5LevlOgCd; /* 5차레벨조직코드 */
    private String dgr5LevlOgNm; /* 5차레벨조직명 */
    private String dgr3LevlDgPrtnrNo; /* 지점장사번 */
    private String dgr3LevlDgPrtnrNm; /* 지점장명 */
    private String adr;
    private String adrDtl;
    private String zip;

    /* STEP1 */
    private String pstnDvCd; /* 직급코드 */
    private String prrBizRgstYn; /* 사전업무등록여부 */
    private String ogCd; /* 조직코드 */
}
