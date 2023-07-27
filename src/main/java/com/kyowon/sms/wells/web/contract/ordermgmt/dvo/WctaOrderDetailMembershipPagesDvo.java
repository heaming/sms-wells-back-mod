package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaOrderDetailMembershipPagesDvo {
    private String cntrNo; /* 계약번호 */
    private String cntrSn; /* 계약순번 */
    private String cntrDtlNo; /* 계약상세번호 */
    private String sellTpCd; /* 판매유형코드 */
    private String copnDvCd; /* 고객구분코드 */
    private String cstKnm; /* 계약자명 */
    private String rcgvpKnm; /* 설치자명 */
    private String sellTpDtlNm; /* 계약구분*/
    private String mshDvNm; /* 멤버십구분*/
    private String pdClsfNm; /* 상품분류 */
    private String pdCd; /* 상품코드 */
    private String pdNm; /* 상품명 */
    private String pdQty; /* 상품수량 */
    private String svTpNm; /* 용도구분 명 */
    private String stplPtrm; /* 약정개월 */
    private String svPrd; /* 관리주기 */
    private String frisuBfsvcPtrmN; /* 멤버십/무상멤버십 */
    private String spayFrisuBfsvcPtrmN; /* 일시불/무상멤버십 */
    private String pdLclsfNm; /* 상품구분1 */
    private String pdDclsfNm; /* 상품구분2 */
    private String cntrDtlStatNm; /* 멤버십상태 */
    private String sellDscDvNm; /* 할인적용구분명 */
    private String sellDscTpNm; /* 할인적용유형명 */
    private String feeAckmtCt; /* 인정건수 */
    private String ackmtPerfAmt; /* 인정금액 */
    private String cntrCtrAmt; /* 등록할인 */
    private String stlmTpNm; /* 납입구분 */
    private String prmPtrmMcn; /* 선납구분 */
    private String adjDvNm; /* 정상구분 */
    private String sellEvNm; /* 약정구분 */
    private String frisuMshCrtYn; /* 무상M자동생성 */
    private String cntrRcpFshDt; /* 접수일 */
    private String cntrCnfmYn; /* 확정여부 */
    private String cntrCnfmDt; /* 확정일 */
    private String cntrPdStrtdt; /* 가입일 */
    private String hcrDuedt; /* 홈케어예정일 */
    private String istDt; /* 설치일 */
    private String dutyExnDtFrisu; /* 의무만료일(무상) */
    private String dutyExnDtRecap; /* 의무만료일자(유상) */
    private String cntrPdEnddt; /* 탈퇴일 */
    private String canDt; /* 취소일 */
    private String vstDuedt; /* 방문일 */
    private String cttFshDt; /* 컨택일 */
    private String cntrCanRsonCd; /* 탈퇴유형코드 */
    private String cntrCanRsonNm; /* 탈퇴유형명 */
    private String hcrOstrDvNm; /* 홈케어출고구분 */
    private String ojIstDt; /* 원주문매출일 */
    private String ojCanDt; /* 원주문취소일 */
    private String ojEnddt; /* 원주문보상/종료일 */
    private String cttRsCd; /* 컨택코드 */
    private String cttRsNm; /* 컨택내용 */
    private String cttRsNmUsrId; /* 컨택사번 */
    private String sellInflwChnlDtlNm; /* 판매유형 */
    private String sellPrtnrNo; /* 파트너코드 */
    private String prtnrKnm; /* 파트너명 */
    private String ogCd; /* 조직코드 */
    private String rveCd; /* 수납코드 */
    private String ichrUsrId; /* 업무담당사번 */
    private String mpyMthdTpNm; /* 자동이체 */
    private String aftnInf; /* 이체계좌/카드 */
    private String mpyBsdt; /* 이체일 */
    private String cntrCstNo; /* 고객번호 */
    private String cntrCralTno; /* 계약자 휴대전화번호 */
    private String cntrCralLocaraTno; /* 계약자 휴대지역전화번호 */
    @DBEncField
    @DBDecField
    private String cntrMexnoEncr; /* 계약자 휴대전화국번호암호화 */
    private String cntrCralIdvTno; /* 계약자 휴대개별전화번호 */
    private String adrZip; /* 계약자 우편번호 */
    private String cntrCstRnadr; /* 계약자 정보-기준주소 */
    private String cntrCstRdadr; /* 계약자 정보-상세주소 */
    private String istCralTno; /* 설치정보-휴대전화번호 */
    private String istCralLocaraTno; /* 설치정보-휴대지역전화번호 */
    @DBEncField
    @DBDecField
    private String istMexnoEncr; /* 설치정보-휴대전화국번호암호화 */
    private String istCralIdvTno; /* 설치정보-휴대개별전화번호 */
    private String istAdrZip; /* 설치정보-우편번호 */
    private String istRnadr; /* 설치정보-기준주소 */
    private String istRdadr; /* 설치정보-상세주소 */
    private String lcck05; /* 일시불즉시유상M여부 */
    private String combiDv; /* 렌탈,일시불결합구분 */
    private String sdingCntrNo; /* 웰스팜 계약번호 */
    private String feeFxamYn; /* 수당제외여부 */
    private String sconCn; /* 특약내용 */
    private String ordCnfp; /* 주문확인구분 */
    private String fstRgstDt; /* 주문 등록일 */
    private String fstRgstTm; /* 주문 등록시간 */
    private String fstRgstUsrId; /* 업무담당사번 */
    private String fstRgstPrgId; /* 등록프로그램 */
    private String fnlMdfcDt; /* 주문 변경일 */
    private String fnlMdfcTm; /* 주문 변경시간 */
    private String fnlMdfcUsrId; /* 변경자사번 */
    private String fnlMdfcPrgId; /* 변경프로그램 */
    private String levelsnm; /* 등급구분 */
    private String dgr3LevlDgPrtnrNo; /* 지점(LCDBON) - 지점장ID */
    private String bryyBzrno; /* 계약자정보 */
    private String cntrwPosndTpNm; /* 계약서발송처유형코드 */
    private String stlmFnit; /* 결제금융기관 -(은행/카드사(LCHNAM)) */
    private String cardExpdtYm; /*  카드유효기간년월 - 유효년월(EDYYMM) */
    private String pdBaseAmt; /* 상품기준금액 */
    private String pyMcn; /* 납입개월 */
    private String dutyPtrmN; /* 의무사용기간 */
    private String dscAmt; /* 할인금액 */
    private String prmMcn; /* 선납개월 */
    private String txinvPblOjYn; /* 세금계산서발행대상여부 */
    private String vstNmnN; /* 방문차월수 */
    private String svVstPrdCd; /* 방문주기 */
    private String bryyMmdd; /* 렌탈료2 */
    private String bzrno; /* 사업자번호 */
    private String sexDvCd; /* 성별 */
    private String ojSellTpCd; /* 판매유형(원주문) */
    private String mshDvCd; /* 계약서유형코드(멤버십구분) */
    private String sellInflwChnlDtlCd; /* 판매유입채널상세코드 */
    private String refPdClsfVal; /* 상품분류코드 참조키 */
}
