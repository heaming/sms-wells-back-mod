package com.kyowon.sms.wells.web.contract.changeorder.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctbSinglePaymentBulkChangeDvo {
    //처리상태
    private String procsDv; // 처리구분
    private String chRson; // 변경사유
    private String compD; // 보상일
    private String cancDt; // 취소일자
    private String cttCd; // 컨택코드
    private String duedt; // 예정일
    private String duedtDel; // 예약일 삭제
    private String istmFnt; // 할부이체
    private String membrshpSrsfr; // 멤버십이체
    private String fxamYnCh; // 정액여부 변경
    private String pdAccCnt; // 인정건수
    private String recogAmt; // 인정금액
    private String recogRt; // 인정율
    private String pdStdFee; // 기준수수료
    private String frisuMsh; // 무상멤버십
    private String frisuAs; // 무상AS
    private String evCd; // 행사코드
    private String rcpDtChDuedt; // 예정일(접수일변경)
    //그리드 정보
    private String cntrDtlNo; // 계약상세번호
    private String cntrNo; // 계약번호
    private String cntrSn; // 계약일련번호
    private String cstKnm; // 고객한글명
    private String rveCd; // 수납코드
    private String pyerNo; // 납부자번호
    private String basePdCd; // 기준상품코드
    private String pdNm; // 상품명
    private String cntrRcpFshDtm; // 계약접수완료일시
    private String istDt; // 설치일자
    private String cntrCanDt; // 계약취소일
    private String cpsDt; // 보상일자
    private String sppDuedt; // 배송예정일자
    private String sppDuedt2; // 배송예정일자
    private String canPerfDt; // 실적취소일
    private String reqdDt; // 철거일자
    private String cttRsCd; // 컨택결과코드
    private String cttRsNm; // 컨택코드명
    private String booSellTpCd; // 예약판매유형코드
    private String booSellYn; // 예약판매여부
    private String booSellTpNm; // 예약판매유형코드명
    private String feeAckmtCt; // 수수료인정건수
    private String ackmtPerfAmt; // 인정실적금액
    private String ackmtPerfRt; // 인정실적율
    private String feeAckmtBaseAmt; // 수수료인정기준금액
    private String feeFxamYn; // 수수료정액여부
    private String dpTpCd; // 입금유형코드
    private String bfsvcBzsDvCd; // BS업체코드
    private String mmbsDpTpCd; // 입금유형코드
    private String copnDvYn; // 멤버십원장
    private String frisuBfsvcPtrmN; // 무상BS기간수
    private String frisuAsPtrmN; // 무상AS기간수
    private String sellEvCd; // 판매행사코드
    private String rgstDtm; // 계약기준 등록일
    private String cntrAmt; // 계약금액 - 청약금
    private String alncmpCd; // 제휴사코드
    private String cntrCnfmDtm; // 계약확정일시
    private String cttOjId; // 컨택대상ID
    private String cntrDtlStatCd; // 계약상세상태코드
    private String sellTpDtlCd; // 판매유형상세코드
    private String cntrPdStrtdt; // 계약상품시작일자
    private String crpUcAmt; // 법인미수금
    private String splyBzsDvCd; // 조달업체구분코드
    private String cttDuedt;
    private String modBfsvcBzsDvCd; // 수정 업체BS구분
    private String modSplyBzsDvCd; // 수정 업체구분
    // 계약변경접수 기본 값
    private String cntrChRcpId; // 계약변경접수ID
    private String cntrChAkCn; // 계약변경요청내용
    private String bfchCn; // 변경전내용
    // DATETIME 반환값
    private String fstRgstDtm; // 최초등록일시
    private String fstRgstUsrId; // 최초등록유저ID
    private String fstRgstPrgId; // 최초등록프로그램ID
    private String fstRgstDeptId; // 최초등록부서ID
    private String fnlMdfcDtm; // 최종수정일시
    private String fnlMdfcUsrId; // 최종수정유저ID
    private String fnlMdfcPrgId; // 최종수정프로그램ID
    private String fnlMdfcDeptId; // 최종수정부서ID
}
