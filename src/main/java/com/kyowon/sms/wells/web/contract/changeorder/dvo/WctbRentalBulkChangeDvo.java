package com.kyowon.sms.wells.web.contract.changeorder.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctbRentalBulkChangeDvo {
    private String cntrChRcpId; // 계약변경접수ID
    private String dtlCntrNo; // 계약번호
    private String dtlCntrSn; // 계약일련번호
    private String cntrDtlNo; // 계약상세번호
    private String sellTpCd; // 계약변경유형코드
    private String cntrChTpCd; //
    private String cstKnm; // 계약자정보
    private String istDt; // 설치일자
    private String istYm; // 설치년월
    private String bfchDutyPtrmN; // 의무기간 변경전
    private String afchDutyPtrmN; // 의무기간 변경후
    private String ackmtPerfAmt; // 인정실적금액
    private String feeBaseAmt; // 수수료기준가격
    private String feeAckmtCt; // 수수료인정건수
    private String rentalDscAmt; //렌탈할인금액
    private String pdctIdno; //제품고유번호
    private String stpStrtYm; //시작기간(중지시작년월)
    private String stpEndYm; //종료기간 (중지종료년월)
    private String cralLocaraTno; //휴대전화1
    @DBDecField
    private String mexnoEncr; //휴대전화2
    private String cralIdvTno; //휴대전화3
    private String feeFxamYn; // 수수료정액여부
    private String pmotDscMcn; // 할인개월
    private String pmotDscAmt; // 할인금액
    private String sdingAckmtPerfAmt; // 수당건수 인정
    private String sdingFeeBaseAmt; // 수수료기준가격
    private String bfchFeeAckmtCt; // 수수료기준가격(현재,변경전)
    private String bfsvcBzsDvCd; // BS업체구분코드
    private String splyBzsDvCd; // 조달업체구분코드
    private String cttTpCd; // 컨택유형코드
    private String duedt; // 예정일자
    private String cntrChAkCn; // 계약변경요청내용
    private String fstRgstDtm; // 최초등록일시
    private String fstRgstUsrId; // 최초등록사용자
    private String fstRgstUsrNm;// 최초등록사용자명
    // 팝업 리스트
    private String rcgvpKnm; // 수령자명
    private String copnDvCd; // 법인격구분코드
    private String cntrPdStrtdt; // 계약상품시작일자 - 매출일
    private String stplEndDt; // 약정종료일
    private String cntrEndDt; // 계약만료일
    private String cntrPdCandt; // 취소일자
    private String cntrDtlStatCd; // 계약상세상태코드-해지여부
    private String clCrtYn; // 매출생성여부
    private String clDpYn; // 매출입금여부
    private String sppDuedt; // 배송예정일자
    private String istMmBilMthdTpCd; // 설치월청구방식유형코드
    private String sellTpDtlCd; // 판매유형상세코드-리스구분
    private String stplPtrm; // 약정기간
    private String istmMcn; // 할부개월수
    private String basePdCd; // 제품코드
    private String cttRsCd; // 캔택결과코드
    private String cntrNo; // 계약번호
    private String cntrSn; // 계약일련번호
    private String curYm; // 현재년월
    private String exmtYn; // 청구여부
    private String istBzsCd; // 설치업체
    private String cntrRcpFshDtm; // 계약접수완료일시
    private String cntrCnfmDtm; // 주문확정일자
    private String sellDscCtrAmt; // 판매할인조정금액-렌탈할인
    private String envrElhmYn; // 환경가전여부
    private String istPcsvTpCd; // 설치택배구분
    private String cntrPdEnddt; // 계약상품종료일자
    private String serlYn; // 시리얼파일 존재여부
    private String dlqAmt; // 연체금
    private String lastlcpay; // *마지막 매출 집계 년월
    private String borAmt; // 위약금
    private String reqdDt; // 철거일자
    private String cursleyn; // 이번달 매출 생성 여부
    private String sdingCntr; // 모종결합계약번호
    private String cntrStatChRsonCd; // 계약상태변경사유코드-취소유형
    private String alncmpCd; // 제휴사코드
    private String booSellTpCd; // 예약판매유형코드
    private String cntrPtrm; // 계약기간
    private String bfOrdNo; // 이전주문번호
    private String feeAckmtAmt; // 수수료인정기준금액
    // 수정값 저장
    private String procsDv; // 처리구분
    private String yrInstallation; // 설치년월
    private String dutyPtrmStrt; // 의무기간Start
    private String dutyPtrmEnd; // 의무기간End
    private String pdAccRslt; // 인정실적
    private String feeAckmtBaseAmt; // 수수료기준가격
    private String feeAckmtCnt; // 수수료인정건수
    private String rentalDc; // 렌탈DC
    private String paramIstDt; // 설치일자
    private String note; // 비고
    private String serialNo; // 시리얼번호
    private String stpPrdStrtYm; // 중지기간(시작 YYYYMM)
    private String stpPrdEndYm; // 중지기간(종료 YYYYMM)
    private String rplyContact; // 회신연락처(메일or휴대전화)
    private String stpCanYm; // 중지취소년월(YYYYMM)
    private String dscMcnt; // 할인개월
    private String dscAmt; // 할인금액
    private String lifeCstCd; // 라이프고객코드
    private String lifeCstCd2; // 라이프고객코드2
    private String modBfsvcBzsDvCd; // 수정 업체BS구분
    private String modSplyBzsDvCd; // 수정 업체구분
    private String feeFxam; // 수수료정액여부
    private String cntrDscAmt; // 할인금액
    private String alncmpCntrDrmVal; // 제휴사계약식별값
    private String prmApyDvCd; // 선납적용구분코드

    // 변경 내용
    private String bfchCn; // 변경전내용
    // 라이프코드 상태 확인
    private String klyear;
    private String klcode;
    private String kletc6;
    private String klicde;
    private String klflg8;
    private String klflg9;
    private String klrevy;
    private String klcany;
    private String klflg8Gubun1;
    private String klflg8Gubun2;
    private String cntrCstNo;
    // DATETIME 반환값
    private String fstRgstPrgId; // 최초등록프로그램ID
    private String fstRgstDeptId; // 최초등록부서ID
    private String fnlMdfcDtm; // 최종수정일시
    private String fnlMdfcUsrId; // 최종수정유저ID
    private String fnlMdfcPrgId; // 최종수정프로그램ID
    private String fnlMdfcDeptId; // 최종수정부서ID
}
