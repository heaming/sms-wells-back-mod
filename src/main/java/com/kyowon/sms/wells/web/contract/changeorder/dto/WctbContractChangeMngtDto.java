package com.kyowon.sms.wells.web.contract.changeorder.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctbContractChangeMngtDto {

    @ApiModel(value = "WctbContractChangeMngtDto-SearchContractChangeReq")
    public record SearchContractChangeReq(
        String cntrNo, // 계약번호
        String cntrSn, // 계약일련번호
        String sellTpCd, // 판매유형코드
        String cstKnm, // 계약자명

        @ValidDate
        @NotEmpty
        String cntrCnfmDtmFr, // 계약시작접수일자
        @ValidDate
        @NotEmpty
        String cntrCnfmDtmTo // 계약종료접수일자
    ) {}

    @ApiModel(value = "WctbContractChangeMngtDto-FindBeforeChangeCheckReq")
    public record FindBeforeChangeCheckReq(

        @NotBlank
        String cntrNo, // 계약번호
        @NotNull
        int cntrSn, // 계약일련번호
        String alncmpCd, // 제휴사코드
        String mclsfRefPdClsfVal,
        String sellTpDtlCd, // 판매유형상세코드
        String sellTpCd, // 판매유형코드
        String inDv, // 처리구분
        String istDt, // 설치일자
        String cntrCnfmDt, // 계약시작일자
        String aprvDv // 승인구분
    ) {}

    @ApiModel(value = "WctbContractChangeMngtDto-SaveCancelReq")
    public record SaveCancelReq(
        @NotBlank
        String cntrNo, // 계약번호
        @NotNull
        int cntrSn, // 계약일련번호
        String cntrDtlNo, // 계약상세번호
        String alncmpCd, // 제휴사코드
        String mclsfRefPdClsfVal,
        String sellTpDtlCd, // 판매유형상세코드
        String sellTpCd, // 판매유형코드
        String inDv, // 처리구분
        String istDt, // 설치일자
        String cntrCnfmDt, // 계약시작일자
        String cstNo, // 고객번호
        String cstKnm, // 고객명
        String aprvDv // 승인구분
    ) {}

    @ApiModel(value = "WctbContractChangeMngtDto-SaveChangeReq")
    public record SaveChangeReq(
        @NotBlank
        String cntrNo, /* 계약번호 */
        @NotNull
        int cntrSn, /* 계약일련번호 */
        String cstKnm, /* 계약자명 - 고객명(lc31.lccnam) */
        String cntrCstNo, /* 계약자정보 - 고객번호 */
        String bryyMmdd, /* 계약자정보 - 생년월일 */
        String cntrAdrpcId, /* 계약주소지 ID */
        String bzrno, /* 계약자정보 - 사업자번호(lccino) */
        String copnDvNm, /* 법인격구분명 (lccgub) 개인:1; 법인:2*/
        String cntrCralLocaraTno, /* 계약자　휴대폰번호1 LCCNOT. LCCNO1 bindMsgs[7] */
        String cntrMexnoEncr, /* 계약자　휴대폰번호2 LCCNOT. LCCNO2 bindMsgs[7] */
        String cntrCralIdvTno, /* 계약자　휴대폰번호3 LCCNOT. LCCNO3 bindMsgs[7] */
        String cntrAdrZip, /* 계약자정보 - 우편번호 */
        String cntrCstRnadr, /* 계약자정보 - 기준주소 */
        String cntrCstRdadr, /* 계약자정보 - 상세주소 */
        String cntrAdrId, /* 계약자정보 -주소ID */
        String cntrAdrDvCd, /* 계약자정보 -주소구분코드  */
        String rcgvpKnm, /* 설치처정보 - 설치자　명(lcwnam) */
        String istCralLocaraTno, /* 설치자 - 휴대전화번호1 */
        String istMexnoEncr, /* 설치자 - 휴대전화번호2 */
        String istCralIdvTno, /* 설치자 - 주소 ID */
        String istAdrId, /* 설치자 - 주소 ID */
        String istAdrDvCd, /* 설치자 - 주소구분코드 */
        String istAdrZip, /* 설치처정보 - 우편번호 : lcwzip */
        String istRnadr, /* 설치처정보 - 기준주소 : lcwad1 + lcwad2 +lcwad3 */
        String istRdadr /* 설치처정보 - 상세주소 : lcwad1 + lcwad2 +lcwad3 */
    ) {}

    @ApiModel(value = "WctbContractChangeMngtDto-EditPartnerReq")
    public record EditPartnerReq(
        @NotBlank
        String cntrNo, /* 계약번호 */
        @NotBlank
        String cntrSn, /* 계약일련번호 */
        @NotBlank
        String prtnrKnm, /* 판매자한글명 */
        @NotBlank
        String sellPrtnrNo, /* 판매파트너번호 */
        @NotBlank
        String ogTpCd, /* 조직유형코드*/
        String bfPrtnrNo, /* 변경전 파트너번호 */
        String bfOgTpCd /* 변경전 조직유형코드 */
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel("WctbContractChangeMngtDto-SearchContractChangeRes")
    public record SearchContractChangeRes(
        String cntrNo, /* 계약번호  lcyear + lccode*/
        int cntrSn, /* 계약일련번호  */
        String sellTpCd, /* 판매유형코드 lcType (2:l20)*/
        String sellTpDtlCd, /* 판매유형상세코드 */
        String cttRsCd, /* 컨택결과코드 */
        /* 화면 표시 */
        String sellTpDtlNm, /* 판매유형사케코드명 (판매유형:lcTypeNm)*/
        String cstKnm, /* 계약자명 - 고객명(lc31.lccnam) */
        String cstNo, /* 고객번호 */
        String bryyBzrno, /* 계약자정보 - 사업자번호(lccino) / 생년 */
        String copnDvCd, /* 법인격구분코드 (lccgub) 개인:1, 법인:2*/
        String copnDvNm, /* 법인격구분명 (lccgub) 개인:1, 법인:2*/
        String cntrDtlNo, /* 계약상세번호 (lcOrdrNo)*/
        String cntrCnfmDt, /* 계약확정일시(접수일) : 접수일자(lccrtDt) */
        String pdNm, /* 상품명 (kainam) */
        String basePdCd, /* 상품코드 (lcicde) */
        String svPdTpCd, /* 용도구분코드 - 상품의 서비스유형 (용도구분명:lciuseNm, lciuse) */
        String svPdTpNm, /* 용도구분 - 상품의 서비스유형 (용도구분명:lciuseNm, lciuse) */
        String svPrd, /* (계약상세.서비스주기) lc31.lcimon */
        String svPtrmUnitCd, /* 서비스기간단위코드 */
        String svPtrmUnitNm, /* 서비스기간단위명 */
        String sexDvCd, /* 성별 (lccsex) */
        String cntrDtlStatCd, /* 계약상세상태코드 */
        String cntrDtlStatNm, /* 계약상세상태명 (사용구분 - 1:관리(101),2:탈퇴(201~303), 만료(401,402) :usedivnm)*/
        int rentalAmt1, /* 렌탈료-최종값 (렌탈료1:lcamt1) */
        int cntrPtrm1, /* 계약기간 (렌탈기간1 : lcmon1) */
        int rentalAmt2, /* todo.맵핑없음 (렌탈료2:lcamt2) */
        int cntrPtrm2, /* todo.맵핑없음 (렌탈기간2 : lcmon2) */
        String sellDscDvCd,
        String sellDscDvNm, /* 판매할인구분코드-할인적용유형명 - 할인구분 명(lcetc3Nm) */
        String sellDscrCd,
        String sellDscrNm, /* 판매할인율코드- - 할인유형 명(lcetc4M,) */
        String sellDscTpCd,
        String sellDscTpNm, /* 판매할인유형코드  (프로모션코드 :lcflg4Nm , lcflg4)  */
        String alncmpCd, /* 제휴사코드 (제휴코드:lcetc8)*/
        String alncmpNm, /* 제휴코드명- 제휴코드명(lcetc8Nm) */
        String fgptInfo, /* 사은품정보 (lcGift, prmt) 첫번째 사은품명 + 첫번째 사은품코드 외 사은품갯수 */
        String hclsfRefPdClsfVal, /* kaetc1 06(order.kaetc1=='8') */

        String mclsfRefPdClsfVal, /* kaetc2 06003("order.kaetc1=='8' and order.kaetc2=='m'),  06005(order.kaetc1=='8' and order.kaetc2=='2') */
        String pdHclsfNm, /* 대분류 kaetc1 kaetc1nm*/
        String pdMclsfNm, /* 중분류 kaetc2 kaetc2nm*/
        String hcrDvNm, /* 홈케어구분코드(합쳐짐) = lcprt1Nm + lcprt2Nm*/
        String bdtMnftNm, /* 제조회사 (일시불일때만 제조회사 표시하지만, 맵핑없음 공통코드:bdtMnftCoCd) lcjejoNm */
        String istDt, /* 설치일자  lcsetDt*/
        String sellInflwChnlDtlCd /* 판매유입채널상세코드 (직원구매 체크용(9020:직원구매->vSalediv:9)) */
    ) {}

    @Builder
    @ApiModel(value = "WctbContractChangeMngtDto-FindCustomerInformationRes")
    public record FindCustomerInformationRes(
        String cntrNo, /* 계약번호 */
        int cntrSn, /* 계약일련번호 */
        String cntrAdrpcId, /* 계약주소지 ID */
        String cstKnm, /* 계약자정보 - 계약자명 / 법인명 */
        String cntrCstNo, /* 계약자정보 - 고객번호 */
        String bryyMmdd, /* 계약자정보 - 생년월일 */
        String bzrno, /* 계약자정보 - 사업자번호(lccino) */
        String copnDvNm, /* 법인격구분코드 - 구분(개인 String법인)  */
        String cntrCralLocaraTno, /* 계약자정보 -휴대지역전화번호 */
        String cntrMexnoEncr, /* 계약자정보 -휴대전화국번호암호화 */
        String cntrCralIdvTno, /* 계약자정보 -휴대개별전화번호  */
        String cntrCopnLocaraTno, /* 계약자정보 - 법인지역번호 */
        String cntrCopnExnoEncr, /* 계약자정보 - 법인전화국번호암호화 */
        String cntrCopnIdvTno, /* 계약자정보 - 법인개별전화번호 */
        String cntrAdrZip, /* 계약자정보 - 우편번호 */
        String cntrCstRnadr, /* 계약자정보 - 기준주소 */
        String cntrCstRdadr, /* 계약자정보 - 상세주소 */
        String cntrAdrId, /* 계약자정보 -주소ID */
        String cntrAdrDvCd, /* 계약자정보 -주소구분코드 */
        String rcgvpKnm, /* 설치처정보 - 설치자　명(lcwnam)*/
        String istCralLocaraTno, /* 설치자 - 휴대전화번호1 */
        String istMexnoEncr, /* 설치자 - 휴대전화번호2 */
        String istAdrId, /* 설치자 - 주소 ID */
        String istAdrDvCd, /* 설치자 - 주소구분코드 */
        String istCralIdvTno, /* 설치자 - 휴대전화번호3 */
        String istAdrZip, /* 설치처정보 - 우편번호 : lcwzip */
        String istRnadr, /* 설치처정보 - 기준주소 : lcwad1 + lcwad2 +lcwad3 */
        String istRdadr /* 설치처정보 - 상세주소 : lcwad1 + lcwad2 +lcwad3 */
    ) {}

    @Builder
    @ApiModel(value = "WctbContractChangeMngtDto-FindBeforeChangeCheckRes")
    public record FindBeforeChangeCheckRes(
        String checkYn, // 처리여부
        String warnMsg // 경고메세지
    ) {}

    @ApiModel(value = "WctbContractChangeMngtDto-FindPartnerRes")
    public record FindPartnerRes(
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String prtnrKnm, /* 판매자한글명 (akdnam) */
        String sellPrtnrNo, /* 판매파트너번호 (lcdcde) */
        String dgr1LevlOgNm, /* 1차레벨조직명 */
        String dgr2LevlOgNm, /* 2차레벨조직명 */
        String dgr3LevlOgNm, /* 3차레벨조직명 */
        String ogCd, /* (판매자의) 조직코드 */
        String ogTpCd /* 조직유형코드*/
    ) {}
}
