package com.kyowon.sms.wells.web.contract.changeorder.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctbCancelPresentStateDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 취소현황 Search Request Dto
    @Builder
    @ApiModel("WctbCancelPresentStateDto-SearchReq")
    public record SearchReq(
        String ogCd, // 소속구분
        String dtDiv, // 일자검색구분
        String cancelFromDt, // 시작일
        String cancelToDt, // 종료일
        String cntrNo, // 계약번호
        String cntrSn, // 계약일련변호
        String sellOgTpCd, // 판매구분
        String basePdCd, // 상품콛,
        String rgstUsrEpNo, // 등록담당자사번
        String copnDvCd, // 계약구분
        String sellTpCd, // 판매유형
        String sellTpDtlCd, // 판매세부
        String cntrStatChRsonCd, // 취소유형
        String reqdDiv, // 철거구
        String wellsFarmCancelDiv, // 웰스팜취소구분
        String pdHclsfId, // 상품대분류
        String pdMclsfId, // 상품중분류
        String omssnDiv,
        String partDiv,
        String cntrFromDt, // 계약시작일
        String cntrToDt, // 계약종료일
        String installFromDt, // 설치시작일
        String installToDt // 설치종료일
    ) {}

    // *********************************************************
    // Rental - Result Dto
    // *********************************************************
    // 렌탈 취소현황 Search Result Dto
    @ApiModel("WctbCancelPresentStateDto-SearchRentalRes")
    public record SearchRentalRes(
        String sellTpNm,
        String sellTpDtlNm,
        String cntrNo,
        String cntrSn,
        String cntrCstKnm,
        String copnDvNm,
        String bzrno,
        String txinvPblOjYn,
        String cstNo,
        String ogCd,
        String pdHclsfNm,
        String pdMclsfNm,
        String pdNm,
        String stplPtrm,
        String cntrCnfmDtm,
        String istDt,
        String rsgAplcDt,
        String rsgFshDt,
        String canDtInDt,
        String cntrStatChRsonCd,
        String cntrStatChRsonNm,
        String ichrPrtnrNo,
        String ichrPrtnrNm,
        String reqdDt,
        String pdUseDc,
        String prgsNmnN,
        String rsonNm,
        Integer slAmt,
        Integer slCtrAmt,
        Integer totPrpdAmt,
        Integer csmbCostBorAmt,
        Integer reqdCsBorAmt,
        Integer borAmt,
        Integer lsRntf,
        Integer totRfndAmt,
        String exCntrNoSn,
        String bizSpptPrtnrNo,
        String bizSpptPrtnrNm
    ) {}


    // *********************************************************
    // RegularShipping - Result Dto
    // *********************************************************
    // 정기배송 취소현황 Search Result Dto
    @ApiModel("WctbCancelPresentStateDto-SearchRegularShippingRes")
    public record SearchRegularShippingRes(
        String cntrNo,
        String cntrSn,
        String cntrCstKnm,
        String bzrno,
        String txinvPblOjYn,
        String sellTpDtlNm,
        String pdNm,
        String cntrPdStrtdt,
        String rsgAplcDt,
        String rsgFshDt,
        String farmEnddt,
        String pdUseDc,
        Integer rentalAmt,
        Integer frisuBfsvcPtrmN,
        Integer canCtrAmt,
        Integer totRfndAmt,
        String ogNm,
        String prtnrNo,
        String prtnrKnm,
        String cntrStatChRsonCd,
        String cntrStatChRsonNm,
        String ojDtlCntrNo,
        String ojDtlCntrSn
    ) {}

    // *********************************************************
    // Single payment - Result Dto
    // *********************************************************
    // Single payment 취소현황 Search Result Dto
    @ApiModel("WctbCancelPresentStateDto-SearchSinglePaymentRes")
    public record SearchSinglePaymentRes(
        String cntrNo, // 계약번호
        String cntrSn, // 계약일련번호
        String ogNm, // 소속구분명
        String cntrCstKnm, // 고객명
        String ogCd, //  조직코드
        String sellPrtnrNo, // 판매자사번
        String prtnrKnm, // 판매자명
        String alncmpNm, // 제휴회사
        String sellEvCd, // 행사코드
        String sellEvNm, // 행사명
        String basePdCd, // 상품코드
        String pdNm, // 상품명
        String cntrCnfmDt, // 계약일자
        String istDt, // 설치일자
        String cntrPdEnddt, // 원장
        String pdChDt, // 상변
        String slChDt, // 매변
        String reqdAkDt, // 철거요청일
        String reqdDt, // 철거완료일
        String reqdPsicId, // 철거담당자
        String prsGbn, // 처리구분
        String cntrTam, // 총판매금
        String cntrAmt, // 청약금
        String crpUcAmt, // 법인미수금
        String cntrDpAmt, // 청약/인수 총입금
        String cntrRfAmt, // 청약/인수 총환불
        String cntrInsAmt, // 최종 할부총입금
        String pdChPeriod, // 경과기간
        String pdChPeriodRntf, // 손율
        String pdChPeriodAmt, // 금액
        String pdChRntfRplcDpAmt, // 대체입금
        String pdChRntfAddDpAmt, // 추가입금
        String pdChRntfDpAmt, // 손료입금
        String npdRntfAmt, // 상변미납손료
        String rntfRplcAmt, // 손료대체
        String etcPrpdAmt // 기타선수
    ) {}

    // *********************************************************
    // Membership - Result Dto
    // *********************************************************
    // Membership 취소현황 Search Result Dto
    @ApiModel("WctbCancelPresentStateDto-SearchMembershipRes")
    public record SearchMembershipRes(
        String cntrNo,
        String cntrSn,
        String sellOgTpCd,
        String ogTpNm,
        String prtnrNo,
        String prtnrKnm,
        String ogCd,
        String ogNm,
        String hooPrtnrNo,
        String cntrCstKnm,
        String copnDvCd,
        String copnDvNm,
        String zip,
        String adr,
        String dtlAdr,
        String pdHclsfNm,
        String pdMclsfNm,
        String basePdCd,
        String pdNm,
        String txinvPblOjYn,
        String dpTpNm,
        String istDt,
        String rsgAplcDt,
        String rsgFshDt,
        String pdUseDc,
        String cntrStatChRsonCd,
        String cntrStatChRsonNm,
        String ojDtlCntrNo,
        String ojDtlCntrSn,
        String slSumAmt,
        String slCtrAmt,
        String totPrpdAmt,
        String borAmt,
        String lsRntf,
        String totRfndAmt,
        String fstRgstUsrId,
        String fstRgstDtm,
        String bizSpptPrtnrNo,
        String bizSpptPrtnrNm
    ) {}
}



