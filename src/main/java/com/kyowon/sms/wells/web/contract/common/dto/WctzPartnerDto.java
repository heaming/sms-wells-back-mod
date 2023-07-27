package com.kyowon.sms.wells.web.contract.common.dto;

import io.swagger.annotations.ApiModel;

public class WctzPartnerDto {
    // *********************************************************
    // Request Dto
    // *********************************************************

    // *********************************************************
    // Result Dto
    // *********************************************************
    //  Search Result Dto
    @ApiModel("WctzPartnerDto-SearchEntrepreneurBasesRes")
    public record SearchEntrepreneurBaseRes(
        String bzrno,
        String dlpnrNm,
        String dlgpsNm,
        String bryyMmdd
    ) {}

    //  Search Result Dto
    @ApiModel("WctzPartnerDto-SearchGeneralDivisionsRes")
    public record SearchGeneralDivisionsRes(
        String dgr1LevlOgCd,
        String dgr1LevlOgNm,
        String dgr1LevlDgPrtnrNo,
        String dgr1LevlOgId,
        String ogTpCd
    ) {}
    //  Search Result Dto
    @ApiModel("WctzPartnerDto-SearchRegionalDivisionsRes")
    public record SearchRegionalDivisionsRes(
        String dgr2LevlOgCd,
        String dgr2LevlOgNm,
        String dgr2LevlDgPrtnrNo,
        String dgr2LevlOgId,
        String ogTpCd,
        String dgr1LevlOgCd
    ) {}

    //  Search Result Dto
    @ApiModel("WctzPartnerDto-SearchBranchDivisionsRes")
    public record SearchBranchDivisionsRes(
        String dgr3LevlOgCd,
        String dgr3LevlOgNm,
        String dgr3LevlDgPrtnrNo,
        String dgr3LevlOgId,
        String ogTpCd,
        String dgr1LevlOgCd,
        String dgr2LevlOgCd
    ) {}

    @ApiModel(value = "WctzContractDto-FindPrtnrRes")
    public record FindPrtnrRes(
        String baseYm, /* 기준년월 */
        String ogTpCd, /* 조직유형코드 */
        String prtnrNo, /* 파트너번호 */
        String sellInflwChnlDtlCd, /* 판매채널구분코드 */
        String prtnrClsfCd, /* 파트너분류코드 */
        String cikVal, /* CI키값 */
        String sfkVal, /* 세이프키값 */
        String copnDvCd, /* 법인격구분코드 */
        String lnfDvCd, /* 내외국인구분코드 */
        String prtnrKnm, /* 파트너한글명 */
        String prtnrEnglLnm, /* 파트너영문성 */
        String prtnrEnm, /* 파트너영문명 */
        String prtnrEnglFnm, /* 파트너영문성명 */
        String ogId, /* 조직ID */
        String ogCd, /* 조직코드 */
        String ogNm, /* 조직명 */
        String hmnrscDeptCd, /* 인사부서코드 */
        String hmnrscEmpno, /* 인사사원번호 */
        String sapDlpnrCd, /* SAP거래처코드 */
        String sapDlpnrDtlIzRfltDt, /* SAP거래처상세내역반영일자 */
        String wkGrpCd, /* 작업그룹코드 */
        String wkGrpNm, /* 작업그룹명 */
        String wkcrCd, /* 작업조번호 */
        String rcrtWrteDt, /* 리쿠르팅작성일자 */
        String fstCntrDt, /* 최초계약일자 */
        String fnlCltnDt, /* 최종해약일자 */
        String rcntrDt, /* 재계약일자 */
        String cltnDt, /* 해약일자 */
        String cntrDt, /* 계약일자 */
        String bzStatCd, /* 사업상태코드 */
        String prfmtDmtnDvCd, /* 승진강등구분코드 */
        String prfmtDt, /* 승진일자 */
        String dmtnDt, /* 강등일자 */
        String ccpsYn, /* 겸직여부 */
        String cltnChoYn, /* 해약선택여부 */
        String sellPsbPrtnrYn, /* 판매가능파트너여부 */
        String prrBizRgstYn, /* 사전업무등록여부 */
        String pstnDvCd, /* 직급구분코드 */
        String rsbDvCd, /* 직책구분코드 */
        String rolDvCd, /* 직무구분코드 */
        String prtnrGdCd, /* 파트너등급코드 */
        String qlfDvCd, /* 자격구분코드 */
        String perfExcdOjYn, /* 실적제외대상여부 */
        String rdsDsbExcdOjYn, /* RDS지급제외대상여부 */
        String dtaDlYn, /* 데이터삭제여부 */
        String dgr1LevlOgCd, /* 1차레벨조직코드 */
        String dgr1LevlOgNm, /* 1차레벨조직명 */
        String dgr2LevlOgCd, /* 2차레벨조직코드 */
        String dgr2LevlOgNm, /* 2차레벨조직명 */
        String dgr3LevlOgCd, /* 3차레벨조직코드 */
        String dgr3LevlOgNm, /* 3차레벨조직명 */
        String dgr4LevlOgCd, /* 4차레벨조직코드 */
        String dgr4LevlOgNm, /* 4차레벨조직명 */
        String dgr5LevlOgCd, /* 5차레벨조직코드 */
        String dgr5LevlOgNm, /* 5차레벨조직명 */
        String dgr4LevlDgPrtnrNo, /* 지국장사번 */
        String dgr4LevlDgPrtnrNm /* 지국장명 */
    ) {}
}
