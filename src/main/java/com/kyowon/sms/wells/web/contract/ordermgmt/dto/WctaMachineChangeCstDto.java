package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WctaMachineChangeCstDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 기기변경 고객 Find Request Dto
    @ApiModel("WctaMachineChangeCstDto-FindReq")
    public record FindReq(
        String baseCntrNo, // 현재 진행중인 계약번호
        String baseCntrSn, // 현재 진행중인 계약일련번호
        @NotBlank
        String cstNo, // 계약자 고객번호
        @NotBlank
        String indvCrpDv, // 법인격구분코드(1.개인, 2.법인)
        @NotBlank
        String pdCd, // 기준상품코드
        String dscDv, // 할인적용유형코드
        String dscTp, // 할인적용상세코드
        @NotBlank
        String sellTpCd, // 판매유형코드
        String alncmpCd, // 제휴사코드
        @NotBlank
        String cntrNo, // 기기변경을 수행할 계약번호
        @NotBlank
        String cntrSn, // 기기변경을 수행할 계약일련번호
        @NotBlank
        String rgstMdfcDv // 1.등록, 2.수정
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 기기변경 고객 Find Result Dto
    @ApiModel("WctaMachineChangeCstDto-FindRes")
    public record FindRes(
        // 기기변경 유효성 체크 값
        String workFlag, // 기기변경유형
        String resultDvCheck, // 결과구분CHECK
        String resultMessage,
        int finalPerfRt, // 최종실적율
        String cntrNo, // 기변 계약번호
        String cntrSn, // 기변 계약일련번호
        String ptyCopnDvCd, // 상대법인격구분코드
        String pdCd, // 기변상품코드
        String pdNm, // 기변상품명
        int rentalNmnN, // 렌탈차월
        String rerntPsbDt, // 재렌탈가능일
        String stplDutyStrtDt, // 약정의무시작일
        String stplDutyEndDt, // 약정의무종료일
        String rstlDutyStrtDt, // 재약정의무시작일
        String rstlDutyEndDt, // 재약정의무종료일
        String ownrsExnDt, // 소유권이전종료일
        int dlqAmt, // 연체금액
        int ucAmt, // 미수금액
        int recapDutyPtrmN, // 의무기간 수(월)
        //기타 조회
        String adr, //주소 (기본주소+상세주소)
        String sellTpCd, //판매유형코드
        String cstKnm, //계약고객명
        String istDt, // 설치일자
        String reqdDt // 취소일자(철거일자)
    ) {
    }
}
