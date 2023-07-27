package com.kyowon.sms.wells.web.contract.salesstatus.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WcteRcpIstDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 접수 및 설치 현황 조회 Search Request Dto
    @Builder
    @ApiModel("WcteRcpIstDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String inqrDv, // 조회구분
        @NotBlank
        String cntrCnfmDtFr, // 조회시작일자
        @NotBlank
        String cntrCnfmDtTo, // 조회종료일자
        @NotEmpty
        List<String> pdDvs, // 제품구분
        String cstDvCd, // 고객구분
        String sellInflwChnlDtlCd, // 조직구분
        String dgr1LevlOgId, // 총괄ID
        String dgr2LevlOgId, // 지역단ID
        String dgr3LevlOgId, // 지점ID
        String incentiveYn, // 인센티브여부
        String pdHclsfId, // 상품대분류ID
        String pdMclsfId, // 상품중분류ID
        String basePdCd, // 기본상품코드
        String pdNm, // 상품명
        String sppDuedt, // 예정일
        String sppDuedtYn, // 예정일여부
        String mngSv, // 관리서비스
        String sellTpCd // 판매유형코드
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 접수 및 설치 현황 조회 Search Result Dto
    @ApiModel("WcteRcpIstDto-SearchRes")
    public record SearchRes(
        String sellTpCd, //  판매유형코드
        String sellDvNm, // 접수구분
        String dpTpNm, // 이체구분
        String mpyBsdt, // 이체약정일
        String newCstYn, // 교원키 신규
        String mchnCh, // 기변
        String rentalYn, // 재렌탈
        String lkCntrNo, // 상대코드
        String useTn, // 사용차월
        String lkPdNm, // 상대상품명

        String sellTpNm, // 계약구분
        String cntrSno, // 계약번호
        String cstKnm, // 계약자명
        String emadr, // 이메일
        String bzrno, // 사업자번호
        String bryyMmdd, // 고객생년
        String sfkVal, // 세이프키

        String pdClsfDv, // 상품분류
        String basePdCd, // 상품코드
        String pdNm, // 상품명
        int mmIstmAmt, // 렌탈료
        String stplPtrm, // 의무기간
        String sdingNm, // 모종
        String sdingCntrSno, // 모종주문번호
        int sdingIstmAmt, // 모종월청구액
        String mngSv, // 관리서비스

        String dgr1LevlOgCd, // 총괄단
        String dgr2LevlOgCd, // 지역단
        String rcpOgNm, // 접수당시소속
        String istOgNm, // 설치당시소속
        String dgr3LevlDgPrtnrNm, // 지점장
        String dgr3LevlDgPrtnrNo, // 지점장 사번
        String pstnDvCd, // 직급
        String a3, // 업무차월
        String cntrDt, // 업무등록일
        String prtnrSexDv, // 성별
        String prtnrGdYn, // 수석플래너
        String prtnrMngYn, // 웰스매니저

        String cntrCnfmDt, // 접수일
        String cntrCnfmTm, // 접수시간
        String fnlMdfcDt, // 최종변경일
        String fnlMdfcTm, // 최종변경시간
        String sppDuedt, // 예정일
        String istDt, // 설치일
        String reqdDt, // 철거일
        String ssBooDt, // 삼성예약일
        String ssStocStrDt, // 삼성재고 입고일

        String pkgCd, // 패키지상품번호
        String pkgSn, // 패키지일련번호
        int ackmtPerfAmt, // 인정실적
        String ackmtPerfRt, // 인정실적률(%)
        String booSellTpCd, // 예약
        String e2, // 제휴
        String cttRsCd, // 컨택코드
        String sellEvCd, // 행사코드
        String dc1, // 판매할인구분코드
        String dc2, // 판매할인율코드
        String dc3, // 판매할인유형코드

        int feeAckmtBaseAmt, // 기준수수료
        int feeAckmtRstlBaseAmt, // 재약정기준수수료
        String feeFxamYn, // 정액여부
        String ocoCpsBzsDvNm, // 타사보상업체
        String zip, // 계약자 우편번호
        String fmmbN, // 세대구성원수
        String feeAckmtCt, // 인정건수
        String rentalTn, // 렌탈차월
        String f2, // 내부구매
        String sapMatCd, // SAP상품코드
        String sellPrtnrNo, // 지점장 사번
        String bfsvcBzsDvCd, // 업체BS구분
        String splyBzsDvCd // 업체구분
    ) {}
}
