package com.kyowon.sms.wells.web.contract.changeorder.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctbRentalBulkChangeDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 멤버십 일괄변경 조회 Search Request Dto
    @Builder
    @ApiModel("WctbRentalBulkChangeDto-SearchReq")
    public record SearchReq(
        String cntrChTpCd, // 처리구분
        String srchDiv, // 검색구분
        String srchDt, // 반영일자
        String cntrNo, // 계약번호
        String cntrSn // 계약일련번호
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 멤버십 일괄변경 조회 Search Result Dto
    @ApiModel("WctbRentalBulkChangeDto-SearchRes")
    public record SearchRes(
        String cntrChRcpId, // 계약변경접수ID
        String dtlCntrNo, // 계약번호
        String dtlCntrSn, // 계약일련번호
        String cntrDtlNo, // 계약상세번호
        String sellTpCd, // 계약변경유형코드
        String cntrChTpCd, //
        String cstKnm, // 계약자정보
        String istDt, // 설치일자
        String istYm, // 설치년월
        String bfchDutyPtrmN, // 의무기간 변경전
        String afchDutyPtrmN, // 의무기간 변경후
        String ackmtPerfAmt, // 인정실적금액
        String feeBaseAmt, // 수수료기준가격
        String feeAckmtCt, // 수수료인정건수
        String rentalDscAmt, //렌탈할인금액
        String pdctIdno, //제품고유번호
        String stpStrtYm, //시작기간(중지시작년월)
        String stpEndYm, //종료기간 (중지종료년월)
        String stpCanYm, //중지취소년월
        String cralLocaraTno, //휴대전화
        String feeFxamYn, // 수수료정액여부
        String pmotDscMcn, // 할인개월
        String pmotDscAmt, // 할인금액
        String sdingAckmtPerfAmt, // 수당건수 인정
        String sdingFeeBaseAmt, // 수수료기준가격
        String bfchFeeAckmtCt, // (모종)수수료 인정건수
        String bfsvcBzsDvCd, // BS업체구분코드
        String splyBzsDvCd, // 조달업체구분코드
        String cttTpCd, // 컨택유형코드
        String duedt, // 예정일자
        String cntrChAkCn, // 계약변경요청내용
        String fstRgstDtm, // 최초등록일시
        String fstRgstUsrId, // 최초등록사용자
        String fstRgstUsrNm // 최초등록사용자명
    ) {};
    // *********************************************************
    // Result Dto
    // *********************************************************
    // 멤버십 일괄변경 조회 Search Result Dto
    @ApiModel("WctbRentalBulkChangeDto-SearchCntrRes")
    public record SearchCntrRes(
        String rcgvpKnm, // 수령자명
        String copnDvCd, // 법인격구분코드
        String cntrPdStrtdt, // 계약상품시작일자 - 매출일
        String stplEndDt, // 약정종료일
        String cntrEndDt, // 계약만료일
        String cntrPdCandt, // 취소일자
        String cntrDtlStatCd, // 계약상세상태코드-해지여부
        String clCrtYn, // 매출생성여부
        String clDpYn, // 매출입금여부
        String sppDuedt, // 배송예정일자
        String istMmBilMthdTpCd, // 설치월청구방식유형코드
        String sellTpDtlCd, // 판매유형상세코드-리스구분
        String stplPtrm, // 약정기간
        String istmMcn, // 할부개월수
        String basePdCd, // 제품코드
        String cttRsCd, // 캔택결과코드
        String cntrDtlNo, // 계약상세번호
        String cntrNo, // 계약번호
        String cntrSn, // 계약일련번호
        String curYm, // 현재년월
        String exmtYn, // 청구여부
        String istBzsCd, // 설치업체
        String istDt, // 설치일자
        String cntrRcpFshDtm, // 계약접수완료일시
        String cntrCnfmDtm, // 주문확정일자
        String sellDscCtrAmt, // 판매할인조정금액-렌탈할인
        String envrElhmYn, // 환경가전여부
        String istPcsvTpCd, // 설치택배구분
        String cntrPdEnddt, // 계약상품종료일자
        String serlYn, // 시리얼파일 존재여부
        String dlqAmt, // 연체금
        String lastlcpay, // *마지막 매출 집계 년월
        String borAmt, // 위약금
        String reqdDt, // 철거일자
        String cursleyn, // 이번달 매출 생성 여부
        String sdingCntr, // 모종결합계약번호
        String cntrStatChRsonCd, // 계약상태변경사유코드-취소유형
        String alncmpCd, // 제휴사코드
        String booSellTpCd, // 예약판매유형코드
        String cntrPtrm, // 계약기간
        String bfOrdNo, // 이전주문번호
        String ackmtPerfAmt, // 인정실적금액
        String feeAckmtAmt, // 수수료인정기준금액
        String feeAckmtCt, // 수수료인정건수
        String pdctIdno, // 출고일련번호
        String feeFxam, // 수수료정액여부
        String cntrDscAmt, // 할인금액
        String alncmpCntrDrmVal, // 제휴사계약식별값
        String bfsvcBzsDvCd, // 업체BS구분
        String splyBzsDvCd, // 업체구분
        String prmApyDvCd // 선납적용구분코드
    ) {}
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel("WctbRentalBulkChangeDto-SaveReq")
    public record SaveReq(
        List<WctbRentalBulkChangeDto.SaveListReq> saveListReqs,
        WctbRentalBulkChangeDto.SaveStatusReq saveStatusReq
    ) {}
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel("WctbRentalBulkChangeDto-SaveListReq")
    public record SaveListReq(
        String rcgvpKnm, // 수령자명
        String copnDvCd, // 법인격구분코드
        String cntrPdStrtdt, // 계약상품시작일자 - 매출일
        String stplEndDt, // 약정종료일
        String cntrEndDt, // 계약만료일
        String cntrPdCandt, // 취소일자
        String cntrDtlStatCd, // 계약상세상태코드-해지여부
        String clCrtYn, // 매출생성여부
        String clDpYn, // 매출입금여부
        String sppDuedt, // 배송예정일자
        String istMmBilMthdTpCd, // 설치월청구방식유형코드
        String sellTpDtlCd, // 판매유형상세코드-리스구분
        String stplPtrm, // 약정기간
        String istmMcn, // 할부개월수
        String basePdCd, // 제품코드
        String cttRsCd, // 캔택결과코드
        String cntrDtlNo, // 계약상세번호
        String cntrNo, // 계약번호
        String cntrSn, // 계약일련번호
        String curYm, // 현재년월
        String exmtYn, // 청구여부
        String istBzsCd, // 설치업체
        String istDt, // 설치일자
        String cntrRcpFshDtm, // 계약접수완료일시
        String cntrCnfmDtm, // 주문확정일자
        String sellDscCtrAmt, // 판매할인조정금액-렌탈할인
        String envrElhmYn, // 환경가전여부
        String istPcsvTpCd, // 설치택배구분
        String cntrPdEnddt, // 계약상품종료일자
        String serlYn, // 시리얼파일 존재여부
        String dlqAmt, // 연체금
        String lastlcpay, // *마지막 매출 집계 년월
        String borAmt, // 위약금
        String reqdDt, // 철거일자
        String cursleyn, // 이번달 매출 생성 여부
        String sdingCntr, // 모종결합계약번호
        String cntrStatChRsonCd, // 계약상태변경사유코드-취소유형
        String alncmpCd, // 제휴사코드
        String booSellTpCd, // 예약판매유형코드
        String cntrPtrm, // 계약기간
        String bfOrdNo, // 이전주문번호
        String ackmtPerfAmt, // 인정실적금액
        String feeAckmtAmt, // 수수료인정기준금액
        String feeAckmtCt, // 수수료인정건수
        String note, // 비고
        String serialNo, // 시리얼번호
        String stpPrdStrtYm, // 중지기간(시작 YYYYMM)
        String stpPrdEndYm, // 중지기간(종료 YYYYMM)
        String rplyContact, // 회신연락처(메일or휴대전화)
        String stpCanYm, // 중지취소년월(YYYYMM)
        String feeFxamYn, // 수수료정액여부
        String dscMcnt, // 할인개월
        String dscAmt, // 할인금액
        String lifeCstCd, // 라이프고객코드
        String lifeCstCd2, // 라이프고객코드2
        String bfsvcBzsDvCd, // 업체BS구분
        String splyBzsDvCd, // 업체구분
        String modBfsvcBzsDvCd, // 수정 업체BS구분
        String modSplyBzsDvCd, // 수정 업체구분
        String pdctIdno, // 출고일련번호
        String feeFxam, // 수수료정액여부
        String cntrDscAmt, // 할인금액
        String alncmpCntrDrmVal, // 제휴사계약식별값
        String prmApyDvCd // 선납적용구분코드
    ) {}
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel("WctbRentalBulkChangeDto-SaveStatusReq")
    public record SaveStatusReq(
        String procsDv, // 처리구분
        String yrInstallation, // 설치년월
        String dutyPtrmStrt, // 의무기간Start
        String dutyPtrmEnd, // 의무기간End
        String pdAccRslt, // 인정실적
        String feeAckmtBaseAmt, // 수수료기준가격
        String feeAckmtCnt, // 수수료인정건수
        String rentalDc, // 렌탈DC
        String paramIstDt // 설치일자
    ) {}
}
