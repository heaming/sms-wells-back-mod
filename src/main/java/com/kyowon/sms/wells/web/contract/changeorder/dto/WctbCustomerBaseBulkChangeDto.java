package com.kyowon.sms.wells.web.contract.changeorder.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;

public class WctbCustomerBaseBulkChangeDto {
    @ApiModel(value = "WctbCustomerBaseBulkChDto-SearchReq")
    public record SearchReq(
        String sellTpCd, // 업무구분
        String prcDvCd, // 처리구분
        String cntrNo, // 계약번호
        String cntrSn, // 계약일련번호
        String cntrCstNo, // 고객번호
        String rcpDtFrom, // 접수기간From
        String rcpDtTo, // 접수기간To
        String prtnrNo, // 파트너번호
        String emadr, // 이메일
        String cardAccNo, // 계좌/카드번호
        String ogCd // 조직코드
    ) {}

    @ApiModel(value = "WctbCustomerBaseBulkChDto-SaveReq")
    public record SaveReq(

        @NotEmpty
        List<Contract> contractList, // 계약리스트
        String istNm, // 설치자명
        String pblYn, // 세금계산서발행대상여부
        String prtnrNo, // 판매파트너번호
        String ogTpCd, // 판매조직유형코드
        String prcDvCd, // 처리구분
        String prtnrKnm, // 판매파트너명
        String fntDvCd // 이체구분
    ) {}
    public record Contract(
        @NotBlank
        String cntrNo, // 계약번호
        @NotBlank
        String cntrSn, // 계약일련번호
        @NotBlank
        String cntrCstNo, // 계약고객번호

        /* 이전 데이터(설치자 명 변경, 세금계산서 발행 ) */
        String istKnm, // 수령자 한글명
        String txinvPblOjYn, // 세금계산서변경대상여부
        String sellPrtnrNo, // 판매자파트너번호
        String prtnrKnm, // 판매자 파트너 명

        /* 자동이체 */
        String aftnInfFntDvCd, // 이체구분코드
        String copnDvCd, // 법인격구분코드
        String dpTpCd, // 입금유형코드
        String sellTpCd, // 판매유형코드
        String cntrStlmId // 계약주소ID
    ) {}

    @ApiModel(value = "WctbCustomerBaseBulkChDto-SearchCustomerRes")
    public record SearchCustomerRes(
        String sellPrtnrNo, /* 판매파트너번호 */
        String prtnrKnm, /* [이름] 파트너한글명 */
        String copnDvCd, /* 법인격구분코드 */
        String sellTpCd, /* 판매유형코드 */
        String sellTpNm, /* [업무구분] 판매유형명 */
        String cntrNo, /* [계약번호] */
        String cntrSn, /* [일련번호] 계약일련번호 */
        String cstKnm, /* [고객명] 고객한글명 */
        String cntrCnfmDtm, /* [접수일자] 계약확정일시 */
        String cntrCstNo, /* [고객번호] 계약고객번호 */
        String txinvPblOjYn, /* [세금계산서] 세금계산서발행대상여부 */
        String emadr, /*[이메일]이메일주소*/
        String atmtStat, /* [자동이체정보-상태] */
        String mpyBsdt, /* [자동이체정보-이체일] 납부기준일자 */
        String aftnInfFntDvCd, // 자동이체정보-이체구분코드
        String aftnInfFntDvNm, // [자동이체정보-이체구분] 이체구분명
        String dpTpCd, /* 입금유형코드 */
        String dpTpNm, /* 입금유형명 */
        String bnkCdcoCd, /* 은행/카드사코드 */
        String bnkCdcoNm, /* [자동이체정보-카드사/은행] 은행/카드사명 */
        String acnoCrcdno, /* [자동이체정보-카드/계좌번호] */
        String isBndl, /* [자동이체정보-묶음/대표] 묶음출금여부 */
        String isBndlMast, /* [자동이체정보-묶음/대표] 묶음출금 대표주문 여부 */
        String evidOcyInqr, /* [자동이체정보-선택] */
        String resign, /*[자동이체정보-해지]*/
        String cntrStlmId, /* [자동이체정보-계약주소ID] */
        String istKnm, /* [설치자 정보-설치고객명] 수령자한글명 */
        String wCralLocaraTno, /* [설치자 정보-휴대전화번호1] */
        String wMexnoEncr, /* [설치자 정보-휴대전화번호2] */
        String wCralIdvTno, /* [설치자 정보-휴대전화번호3] */
        String wLocaraTno, /* [설치자 정보-전화번호1] */
        String wExnoEncr, /* [설치자 정보-전화번호2] */
        String wIdvTno, /* [설치자 정보-전화번호3] */
        String wAdrZip, /* [설치자 정보-우편번호] */
        String wRnadr, /* [설치자 정보-주소1] 주소 */
        String wRdadr, /* [설치자 정보-주소2] 상세주소 */
        String cralLocaraTno, /* [계약자 정보-휴대전화번호1] */
        String mexnoEncr, /* [계약자 정보-휴대전화번호2] */
        String cralIdvTno, /* [계약자 정보-휴대전화번호3] */
        String locaraTno, /* [계약자 정보-전화번호1] */
        String exnoEncr, /* [계약자 정보-전화번호2] */
        String idvTno, /* [계약자 정보-전화번호3] */
        String adrZip, /* [계약자 정보-우편번호] */
        String rnadr, /*[계약자정보-주소1]*/
        String rdadr, /*[계약자정보-주소2]*/
        String rprsCntrNo /* 묶음출금 대표계약번호 */
    ) {}

    @ApiModel(value = "WctbCustomerBaseBulkChDto-SearchPartnerRes")
    public record SearchPartnerRes(
        String prtnrKnm, /* [이름] 파트너한글명 */
        String sellTpCd, /* 판매유형코드 */
        String sellTpNm, /* [업무구분] 판매유형명 */
        String cntrNo, /* [계약번호] */
        String cntrSn, /* [계약일련번호] 계약일련번호 */
        String cstKnm, /* [고객명] 고객한글명 */
        String cntrCnfmDtm, /* [접수일자] 계약확정일시 */
        String cntrCstNo, /* [고객번호] 계약고객번호 */
        String slDt, /* [매출일자] 매출인식일자 */
        String sellPrtnrNo, /* [사번] 판매파트너번호 */
        String ogCd, /* [계약마스터-조직코드] 조직코드 */
        String dgr3LevlDgPrtnrNo, /* [계약마스터-지점장] 3차레벨대표파트너번호 */
        String dgr2LevlDgPrtnrNo, /* [계약마스터-지역단장] 2차레벨대표파트너번호 */
        String dgr1LevlDgPrtnrNo, /* [계약마스터-총괄단장] 1차레벨대표파트너번호 */
        String dgr1LevlOgCd, /* [계약마스터-총괄단코드] 1차레벨조직코드 */
        String curOgCd, /* [대리인마스터-조직코드] 조직코드 */
        String curDgr3LevlDgPrtnrNo, /* [대리인마스터-지점장] 3차레벨대표파트너번호 */
        String curDgr2LevlDgPrtnrNo, /* [대리인마스터-지역단장] 2차레벨대표파트너번호 */
        String curDgr1LevlDgPrtnrNo, /* [대리인마스터-총괄단장] 1차레벨대표파트너번호 */
        String curDgr1LevlOgCd, /* [대리인마스터-총괄단코드] 1차레벨조직코드 */
        String chEpNo /* [변경사번] */
    ) {}

}
