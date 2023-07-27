package com.kyowon.sms.wells.web.contract.changeorder.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctbCancelBaseDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 취소등록 Search Request Dto
    @Builder
    @ApiModel("WctbCancelBaseDto-SearchReq")
    public record SearchReq(
        String cntrNo,
        String cntrSn,
        String cstNo,
        String dm,
        String reqDt,
        String cancelDt,
        String sellTpCd,
        String dscDdctam,
        String filtDdctam,
        String slCtrAmt
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 취소등록 Search Result Dto

    @ApiModel("WctbCancelBaseDto-SearchRes")
    public record SearchRes(
        String cntrNo,
        String cntrSn,
        String sellTpCd,
        String sellTpNm,
        String sellTpDtlCd,
        String cntrDtlStatCd,
        String cntrCstNo,
        String cntrCstKnm,
        String basePdCd, // 상품코드
        String pdNm, // 상품명
        String copnDvNm,
        String cntrGbn,
        String ogCd, // 소속
        String hooPrtnrNm, // 본부장명
        String hooPrtnrNo, // 본부장사번
        String pkgYn, // 멤버십패키지여부
        String cntrRcpDt, // (멤버십)계약일
        String cntrCnfmDt, // 계약확정일
        String cntrPdStrtdt, // 설치일,배송일,가입일, 매출일자
        String cntrPdEnddt, // 탈퇴일자
        String rentalTn, // 렌탈차월, 멤버십가입차월
        String rentalDc, // 가입일수(RENTAL_DC/SL_DC) - 렌탈/멤버십일수
        String slDc, // 가입일수(RENTAL_DC/SL_DC) - 매출일수
        String sppTn, // 정기배송 : 배송회차
        String stplPtrm, //의무기간
        String svPrd, // BS 주기(멤버십)
        String svPdTpNm, // 용도구분
        String stlmTpNm, // 멤버십 가입유형
        String sppDuedt, // 예정일자 - 일시불에서 사용
        String cntrAmt, // 계약금액
        String rentalRgstCost, // 렌탈등록비
        String cntramDscAmt, // 등록비 할인
        String cntrTam, // 계약총금액
        String pdBaseAmt, // 정상렌탈료
        String cntrPtrm, // 렌탈개월
        String fnlAmt, // 렌탈금액
        String dscAmt, // 렌탈할인금액
        String rstlPtrm, // 재약정개월
        String stplDscAmt, // 재약정할인금액
        String stplStrtdt, // 재약정기간
        String stplEnddt, // 재약정기간
        String machineNm,
        String addAmt, // 정기배송기기 - 추가 : 알수 없음
        String machineRentalAmt, // 정기배송 기기 렌탈료
        String recoverAmt, // 정기배송기기 원복 : 현재상품가격으로 재조회 해야 함
        String lsnt, // 분실손료
        String eotUcAmt, // 미납금 - 연체가산금, 위약금 미포함
        String sellAmt,
        String cancelStatNm,
        String disableChk
    ) {}


    // *********************************************************
    // Result Dto
    // *********************************************************
    //  위약금 Search Result Dto
    @ApiModel("WctbCancelBaseDto-SaveReq")
    public record SaveReq(
        String refPdClsfVal, // 상품참조분류 - 커피원두 구분용
        String cntrPdStrtdt, // 상품시작일자/매출일자
        String stplDscAmt, // 재약정할인금액
        String stplStrtdt, // 재약정시작일자
        String stplEnddt, // 재약정종료일자
        String exnDt, // 만료일자
        Integer useDays, // 사용일자
        String grade, // 등급
        Integer thmSlSumAmt, // 당월매출금액
        Integer thmDpTotAmt, // 당월입금총합
        Integer thmRfndTotAmt, // 당월환불총합
        String cntrNo,
        String cntrSn,
        String sellTpCd,
        String sellTpDtlCd, // 판매유형상세코드
        String basePdCd,
        Integer cntrPtrm, // 렌탈개월
        Integer rentalTn, // 렌탈차월
        Integer cntrPasgDc, // 계약경과일수
        Integer sppNmnN, // 배송차월수
        Integer rtngdQty, // 반품수량
        Integer slDc, // 매출일수
        Integer nomSlAmt, // 정상매출금액
        Integer spmtSlAmt, // 추가매출금액
        Integer nomDscAmt, // 정상할인금액
        Integer spmtDscAmt, // 추가할인금액
        Integer slCtrAmt, // 매출조정금액
        Integer canCtrAmt, // 취소조정금액
        Integer slSumAmt, // 매출합계금액
        Integer slSumVat, // 매출합계부가가치세
        Integer slAggAmt, // 매출누계금액
        Integer slAggAmtVat, // 매출누계금액부가가치세
        Integer dscAggAmt, // 할인누계금액
        Integer ctrAggAmt, // 조정누계금액
        Integer nomIntAmt, // 정상이자금액
        Integer intCtrAmt, // 이자조정금액
        Integer intSumAmt, // 이자합계금액
        Integer intVat, // 이자부가가치세
        Integer intAggAmt, // 이자누계금액
        Integer intDscAggAmt, // 이자할인누계금액
        Integer thmPaiam, // 당월원리금
        Integer thmSvAmt, // 당월서비스금액
        Integer slBlam, // 매출잔액
        Integer adnSvSpmtSlAmt, // 부가서비스추가매출금액
        Integer prmBtdAmt, // 선납기초금액
        Integer eotAtam, // 기말선수금
        Integer totPrpdAmt, // 총선수금액
        Integer slDpAmt, // 매출입금금액
        Integer slDpAggAmt, // 매출입금누계금액
        Integer ucAmt, // 미수금액
        Integer dlqAmt, // 연체금액
        Integer prmRfndAmt, // 선납환불금액
        Integer prpdRfndAmt, // 선수환불금액
        Integer dscDdctam, // 할인공제금액
        Integer filtDdctam, // 필터공제금액
        Integer rentalRgstCostRfndAmt, // 렌탈등록비환불금액
        Integer rentalRgstCostRfndAmtVat, // 렌탈등록비환불금액부가가치세
        Integer borAmt, // 위약금액
        Integer totRfndAmt, // 총환불금액
        Integer resRtlfeBorAmt, // 잔여렌탈료위약금액
        Integer rgstCostDscBorAmt, // 등록비할인위약금액
        Integer rentalDscBorAmt, // 렌탈할인위약금액
        Integer csmbCostBorAmt, // 소모품비위약금액
        Integer csmbCostBorAmt2, // 소모품비위약금액2
        Integer pBorAmt, // 포인트위약금액
        Integer reqdCsBorAmt, // 철거비용위약금액
        Integer reqdCsBorAmt2, // 철거비용위약금액2
        Integer lsnt, // 분실손료
        Integer eotDlqAddAmt, // 기말연체가산금액
        String cntrStatChRsonCd, // 계약상태변경사유코드
        String ccamExmptDvCd, // 위약금면책구분코드
        String csmbCsExmptDvCd, // 소모품비용면책구분코드
        String reqdCsExmptDvCd, // 철거비용면책구분코드
        String reqdAkRcvryDvCd, // 철거요청복구구분코드
        String rsgAplcDt, // 요청일자
        String rsgFshDt, // 요청일자
        String slCtrRqrId, // 매출조정요청자ID
        String slCtrRmkCn, // 매출조정비고내용
        String ichrOgTpCd, // 담당조직유형코드
        String ichrPrtnrNo // 담당파트너번호
        //String stplPtrm, // 의무기간
        //String reStplPtrm, // 재약정의무기간
        //String alncmpCd, // 제휴사코드
        //String sellDscDvCd, // 할인구분코드
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    //  위약금 Search Result Dto
    @ApiModel("WctbCancelBaseDto-FindSubDetailRes")
    public record FindSubDetailRes(
        String cntrNo,
        String cntrSn,
        String cntrPtrm,
        String rentalTn,
        String cntrPasgDc,
        String sppNmnN,
        String cntrPdStrtdt,
        String nomSlAmt,
        String spmtSlAmt,
        String nomDscAmt,
        String spmtDscAmt,
        String slCtrAmt,
        String slSumAmt,
        String slSumVat,
        String slAggAmt,
        String slAggAmtVat,
        String dscAggAmt,
        String ctrAggAmt,
        String nomIntAmt,
        String intCtrAmt,
        String intSumAmt,
        String intVat,
        String intAggAmt,
        String intDscAggAmt,
        String thmPaiam,
        String thmSvAmt,
        String slBlam,
        String adnSvSpmtSlAmt,
        String prmBtdAmt,
        String eotAtam,
        String totPrpdAmt,
        String slDpAmt,
        String slDpAggAmt,
        String ucAmt,
        String dlqAmt,
        String prmRfndAmt,
        String prpdRfndAmt,
        String dscDdctam,
        String filtDdctam,
        String rentalRgstCostRfndAmt,
        String rentalRgstCostRfndAmtVat,
        String borAmt,
        String totRfndAmt,
        String eotDlqAddAmt,
        String resRtlfeBorAmt,
        String rgstCostDscBorAmt,
        String rentalDscBorAmt,
        String csmbCostBorAmt,
        String pBorAmt,
        String reqdCsBorAmt,

        String alncmpCd, // 제휴사코드
        //String stplPtrm, // 의무기간
        //String stplDscAmt, // 재약정할인금액
        //String stplStrtdt, // 재약정시작일자
        //String stplEnddt, // 재약정종료일자
        Integer useDays, // 사용일자
        String grade, // 등급
        Integer thmSlSumAmt, // 당월매출금액
        Integer thmDpTotAmt, // 당월입금총합
        Integer thmRfndTotAmt // 당월환불총합
    ) {}

    @ApiModel("WctbCancelBaseDto-FindDetailRes")
    public record FindDetailRes(
        String prgsNmnN,
        String cntrPasgDc,
        String sppNmnN,
        String rtngdQty,
        //String slDc,
        String nomSlAmt,
        String spmtSlAmt,
        String nomDscAmt,
        String spmtDscAmt,
        String slCtrAmt,
        String canCtrAmt,
        String slSumAmt,
        String slSumVat,
        String slAggAmt,
        String slAggAmtVat,
        String dscAggAmt,
        String ctrAggAmt,
        String nomIntAmt,
        String intCtrAmt,
        String intSumAmt,
        String intVat,
        String intAggAmt,
        String intAggVat,
        String intDscAggAmt,
        String thmPaiam,
        String thmSvAmt,
        String slBlam,
        String adnSvSpmtSlAmt,
        String prmBtdAmt,
        String prpdBtdAmt,
        String eotAtam,
        String totPrpdAmt,
        String slDpAmt,
        String slDpAggAmt,
        String ucAmt,
        String dlqAmt,
        String rsgAplcDt,
        String rsgFshDt,
        String cntrStatChRsonCd,
        String ccamExmptDvCd,
        String csmbCsExmptDvCd,
        String reqdCsExmptDvCd,
        String reqdAkRcvryDvCd,
        String useDays,
        String grade,
        String prmRfndAmt,
        String prpdRfndAmt,
        String dscDdctam,
        String filtDdctam,
        String rentalRgstCostRfndAmt,
        String rentalRgstCostRfndAmtVat,
        String borAmt,
        String totRfndAmt,
        String lsnt,
        String resRtlfeBorAmt,
        String rgstCostDscBorAmt,
        String rentalDscBorAmt,
        String csmbCostBorAmt,
        String pBorAmt,
        String reqdCsBorAmt,
        String csmbCostBorAmt2,
        String reqdCsBorAmt2,
        String slCtrRqrId,
        String slCtrRmkCn,
        String ichrOgTpCd,
        String ichrPrtnrNo,
        String sellPrtnrNo
    ) {}
}

