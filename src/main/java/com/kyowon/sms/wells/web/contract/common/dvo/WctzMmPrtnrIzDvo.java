package com.kyowon.sms.wells.web.contract.common.dvo;

import lombok.*;

/**
 * <pre>
 * TB_OGBS_MM_PRTNR_IZ 만을 위한 DVO
 * </pre>
 *
 * @author Administrator
 * @since 2023-07-11
 */

@Getter
@Setter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class WctzMmPrtnrIzDvo {
    private String baseYm; /*기준년월*/
    private String ogTpCd; /*조직유형코드*/
    private String prtnrNo; /*파트너번호*/
    private String prtnrClsfCd; /*파트너분류코드*/
    private String cikVal; /*CI키값*/
    private String sfkVal; /*세이프키값*/
    private String copnDvCd; /*법인격구분코드*/
    private String lnfDvCd; /*내외국인구분코드*/
    private String prtnrKnm; /*파트너한글명*/
    private String prtnrEnm; /*파트너영문명*/
    private String ogId; /*조직ID*/
    private String ogCd; /*조직코드*/
    private String ogNm; /*조직명*/
    private String hmnrscDeptCd; /*인사부서코드*/
    private String hmnrscEmpno; /*인사사원번호*/
    private String sapDlpnrCd; /*SAP거래처코드*/
    private String sapDlpnrDtlIzRfltDt; /*\SAP거래처상세내역반영일자*/
    private String wkGrpCd; /*작업그룹코드*/
    private String wkGrpNm; /*작업그룹명*/
    private String rcrtWrteDt; /*리쿠르팅작성일자*/
    private String fstCntrDt; /*최초계약일자*/
    private String fnlCltnDt; /*최종해약일자*/
    private String rcntrDt; /*재계약일자*/
    private String cltnDt; /*해약일자*/
    private String cntrDt; /*계약일자*/
    private String prfmtDmtnDvCd; /*승진강등구분코드*/
    private String prfmtDt; /*승진일자*/
    private String dmtnDt; /*강등일자*/
    private String ccpsYn; /*겸직여부*/
    private String cltnChoYn; /*해약선택여부*/
    private String prrBizRgstYn; /*사전업무등록여부*/
    private String pstnDvCd; /*직급구분코드*/
    private String rsbDvCd; /*직책구분코드*/
    private String rolDvCd; /*직무구분코드*/
    private String prtnrGdCd; /*파트너등급코드*/
    private String perfExcdOjYn; /*실적제외대상여부*/
    private String rdsDsbExcdOjYn; /*RDS지급제외대상여부*/
    private String dtaDlYn; /*데이터삭제여부*/
    private String bzStatCd; /*사업상태코드*/
    private String prtnrEnglLnm; /*파트너영문성*/
    private String prtnrEnglFnm; /*파트너영문성명*/
    private String qlfDvCd; /*자격구분코드*/
    private String sellPsbPrtnrYn; /*판매가능파트너여부*/
    private String hirFomCd; /*고용형태코드*/
    private String sellInflwChnlDtlCd; /*판매유입채널상세코드*/
    private String wkcrCd; /*작업조코드*/
}
