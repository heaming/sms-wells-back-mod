package com.kyowon.sms.wells.web.contract.changeorder.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctbMembershipBulkChangeDvo {
    private String cntrDtlNo; // 계약상세번호
    private String cntrNo; // 계약번호
    private String cntrSn; // 계약일련번호
    private String sellInflwChnlDtlCd; // 판매유입채널상세 - 판매구분
    private String sellTpDtlCd; // 판매유형상세코드 - 판매유형
    private String sellPrtnrNo; // 판매파트너번호-대리인코드
    private String rveCd; // 수납코드
    private String reqdDt; // 철거일자
    private String rcpD; // 접수일-변경요청접수일
    private String istDt; // 설치일
    private String cntrPdStrtdt; // 계약상품시작일자-매출일자
    private String cstKnm; // 고객명
    private String svPrd; // 서비스주기
    private String useyn; // 용도구분-매핑안됨
    private String basePdCd; // 기준상품코드
    private String pdNm; // 상품명
    private String fnlAmt; // 멤버쉽회비
    private String stlmTpCd; // 결제유형코드-납입방법
    private String frisuBfsvcPtrmN; // 무상BS기간수-멤버십무상
    private String cntrwTpCd; // 계약서유형코드-멤버십구분
    private String stplPtrm; // 약정기간-멤버십기간
    private String stplPtrmUnitNm; // 약정기간단위
    private String dtrmDate; // 계약상품시작일자-확정일
    private String cntrCanDtm; // 계약취소일시-취소일시
    private String duedt; // 예정일(홈케어?)-매핑후처리필요
    private String cntrCnfmDtm; // 계약확정일시 -가입일:AS-IS대비 확인필요
    private String wdwalDt; // 탈퇴일
    private String vstPrd; // 방문주기
    private String cttRsCd; // 컨택코드
    private String cttRsNm; // 컨택코드명
    private String cttPsicId; // 컨택담당
    private String cttPsicNm; // 컨택담당명
    private String hcrDvCd; // 홈케어구분코드-상품구분
    private String feeFxamYn; // 수수료정액여부-정액여부(Y:수당제외)
    private String feeAckmtBaseAmt; // 수수료인정기준금액-수수료기준금액
    private String sellDscDvCd; // 판매할인구분코드-할인구분
    private String sellDscrCd; // 판매할인율코드-할인유형
    private String fstRgstDtm; // 최초등록일시-입력일자
    private String fstRgstUsrId; // 최초등록사용자ID-입력담당
    private String fstRgstUsrNm; // 입력담당자명
    private String fnlMdfcDtm; // 최종수정일시-수정일자
    private String fnlMdfcUsrId; // 최종수정사용자ID-수정담당
    private String fnlMdfcUsrNm; // 수정담당자명
    private String firstBznsYn; // 첫 영업 일
    private String cntrChRcpId; // 계약변경접수 ID
    // 수정값 저장
    private String procsDv; // 처리구분
    private String chRson; // 변경사유
    private String cttCd; // 컨택코드
    private String subsDt; // 가입일
    private String fxamYnCh; // 정액여부 변경
    private String pdStdFee; // 기준수수료
    private String lstmmLstDays; // 전월 말일
    private String cntrChAkCn; // 변경 전 데이터
    private String bfchCn; // 변경 전 데이터

    // DATETIME 반환값
    private String fstRgstPrgId; // 최초등록프로그램ID
    private String fstRgstDeptId; // 최초등록부서ID
    private String fnlMdfcPrgId; // 최종수정프로그램ID
    private String fnlMdfcDeptId; // 최종수정부서ID
}
