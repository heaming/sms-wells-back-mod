package com.kyowon.sms.wells.web.contract.orderstatus.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;

public class WctdNewChangeMachinePerfDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 신규/기변 실적 조회 Search Request Dto
    @ApiModel("WctdNewChangeMachinePerfDto-SearchReq")
    public record SearchReq(
        @NotBlank
        @ValidDate
        String perfStrtDt, // (필수) 실적기간 - 시작일
        @NotBlank
        @ValidDate
        String perfEndDt, // (필수) 실적기간 - 종료일
        @NotBlank
        String perfDv, // (필수) 실적구분 T.총주문 / S.순주문
        String optnDv, // 가동구분 가동 / 비가동
        String inqrDv, // (필수) 조회구분 총괄단 / 지역단 / 지점 / 개인
        String dgr1LevlOgId, // 총괄단
        String dgr2LevlOgId, // 지역단
        String dgr3LevlOgId, // 지점
        String ogTpCd // 판매조직구분 W01.P조직 / W02.M조직
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 신규/기변 실적 조회 Search Result Dto
    @ApiModel("WctdNewChangeMachinePerfDto-SearchRes")
    public record SearchRes(
        String ogTpCd, // 판매구분코드
        String prtnrNm, // 이름
        String ogNm, // 소속
        String prtnrNo, // 사번
        String rsbDvCd, // 직급
        String dgr1LevlOgNm, // 총괄단
        String dgr2LevlOgNm, // 지역단
        String dgr3LevlOgNm, // 지점
        String optnDv, // 가동구분
        String newCnt, // 렌탈 - 신규
        String reRntlCnt, // 렌탈 - 재렌탈
        String reformCnt, // 렌탈 - 재약정
        String mchnCh1Cnt, // 렌탈 - 기변1
        String mchnCh2Cnt, // 렌탈 - 기변2
        String rntlTotCnt, // 렌탈 - 총건수
        String rntlAckmtCnt, // 렌탈 - 인정
        String spayTotCnt, // 일시불 - 총건수
        String spayAckmtCnt, // 일시불 - 인정
        String totCnt, // 합계 - 총건수
        String totAckmtCnt // 합계 - 인정
    ) {}
}
