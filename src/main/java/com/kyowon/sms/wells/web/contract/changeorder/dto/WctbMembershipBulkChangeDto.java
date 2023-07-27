package com.kyowon.sms.wells.web.contract.changeorder.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;

public class WctbMembershipBulkChangeDto {
    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel("WctbMembershipBulkChangeDto-SearchRes")
    public record SearchRes(
        String cntrDtlNo,
        String cstKnm,
        String sellInflwChnlDtlCd,
        String sellTpDtlCd,
        String sellPrtnrNo,
        String prtnrNm,
        String rveCd,
        String reqdDt,
        String cntrChRcpDtm,
        String istDt,
        String cntrStlmFshDtm,
        String svPrd,
        String useyn,
        String basePdCd,
        String pdNm,
        String fnlAmt,
        String stlmTpCd,
        String frisuBfsvcPtrmN,
        String cntrwTpCd,
        String stplPtrm,
        String cntrCnfmAprDtm,
        String canDt,
        String duedt,
        String cntrCnfmDtm,
        String wdwalDt,
        String vstPrd,
        String cttRsNm,
        String cttPsicId,
        String cttPsicNm,
        String hcrDvCd,
        String feeFxamYn,
        String feeAckmtBaseAmt,
        String sellDscDvCd,
        String sellDscrCd,
        String grpGbn,
        String fstRgstDtm,
        String fstRgstUsrId,
        String fstRgstUsrNm,
        String fnlMdfcDtm,
        String fnlMdfcUsrId,
        String fnlMdfcUsrNm,
        String cntrChAkCn
    ) {}
    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel("WctbMembershipBulkChangeDto-SearchCntrRes")
    public record SearchCntrRes(
        String cntrDtlNo, // 계약상세번호
        String cntrNo, // 계약번호
        String cntrSn, // 계약일련번호
        String sellInflwChnlDtlCd, // 판매유입채널상세 - 판매구분
        String sellTpDtlCd, // 판매유형상세코드 - 판매유형
        String sellPrtnrNo, // 판매파트너번호-대리인코드
        String rveCd, // 수납코드
        String reqdDt, // 철거일자
        String rcpD, // 접수일-변경요청접수일
        String istDt, // 설치일
        String cntrPdStrtdt, // 계약상품시작일자-매출일자
        String cstKnm, // 고객명
        String svPrd, // 서비스주기
        String useyn, // 용도구분-매핑안됨
        String basePdCd, // 기준상품코드
        String pdNm, // 상품명
        String fnlAmt, // 멤버쉽회비
        String stlmTpCd, // 결제유형코드-납입방법
        String frisuBfsvcPtrmN, // 무상BS기간수-멤버십무상
        String cntrwTpCd, // 계약서유형코드-멤버십구분
        String stplPtrm, // 약정기간-멤버십기간
        String stplPtrmUnitNm, // 약정기간단위
        String dtrmDate, // 계약상품시작일자-확정일
        String cntrCanDtm, // 계약취소일시-취소일시
        String duedt, // 예정일(홈케어?)-매핑후처리필요
        String cntrCnfmDtm, // 계약확정일시 -가입일:AS-IS대비 확인필요
        String wdwalDt, // 탈퇴일
        String vstPrd, // 방문주기
        String cttRsCd, // 컨택코드
        String cttRsNm, // 컨택코드명
        String cttPsicId, // 컨택담당
        String cttPsicNm, // 컨택담당명
        String hcrDvCd, // 홈케어구분코드-상품구분
        String feeFxamYn, // 수수료정액여부-정액여부(Y:수당제외)
        String feeAckmtBaseAmt, // 수수료인정기준금액-수수료기준금액
        String sellDscDvCd, // 판매할인구분코드-할인구분
        String sellDscrCd, // 판매할인율코드-할인유형
        String fstRgstDtm, // 최초등록일시-입력일자
        String fstRgstUsrId, // 최초등록사용자ID-입력담당
        String fstRgstUsrNm, // 입력담당자명
        String fnlMdfcDtm, // 최종수정일시-수정일자
        String fnlMdfcUsrId, // 최종수정사용자ID-수정담당
        String fnlMdfcUsrNm // 수정담당자명
    ) {}
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel("WctbMembershipBulkChangeDto-SaveReq")
    public record SaveReq(
        List<SaveListReq> saveListReqs,
        SaveStatusReq saveStatusReq
    ) {}
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel("WctbMembershipBulkChangeDto-SaveListReq")
    public record SaveListReq(
        String cntrDtlNo, // 계약상세번호
        String cntrNo, // 계약번호
        String cntrSn, // 계약일련번호
        String sellInflwChnlDtlCd, // 판매유입채널상세 - 판매구분
        String sellTpDtlCd, // 판매유형상세코드 - 판매유형
        String sellPrtnrNo, // 판매파트너번호-대리인코드
        String rveCd, // 수납코드
        String reqdDt, // 철거일자
        String rcpD, // 접수일-변경요청접수일
        String istDt, // 설치일
        String cntrPdStrtdt, // 계약상품시작일자-매출일자
        String cstKnm, // 고객명
        String svPrd, // 서비스주기
        String useyn, // 용도구분-매핑안됨
        String basePdCd, // 기준상품코드
        String pdNm, // 상품명
        String fnlAmt, // 멤버쉽회비
        String stlmTpCd, // 결제유형코드-납입방법
        String frisuBfsvcPtrmN, // 무상BS기간수-멤버십무상
        String cntrwTpCd, // 계약서유형코드-멤버십구분
        String stplPtrm, // 약정기간-멤버십기간
        String stplPtrmUnitNm, // 약정기간단위
        String dtrmDate, // 계약상품시작일자-확정일
        String cntrCanDtm, // 계약취소일시-취소일시
        String duedt, // 예정일(홈케어?)-매핑후처리필요
        String cntrCnfmDtm, // 계약확정일시 -가입일:AS-IS대비 확인필요
        String wdwalDt, // 탈퇴일
        String vstPrd, // 방문주기
        String cttRsCd, // 컨택코드
        String cttRsNm, // 컨택코드명
        String cttPsicId, // 컨택담당
        String cttPsicNm, // 컨택담당명
        String hcrDvCd, // 홈케어구분코드-상품구분
        String feeFxamYn, // 수수료정액여부-정액여부(Y:수당제외)
        String feeAckmtBaseAmt, // 수수료인정기준금액-수수료기준금액
        String sellDscDvCd, // 판매할인구분코드-할인구분
        String sellDscrCd, // 판매할인율코드-할인유형
        String fstRgstDtm, // 최초등록일시-입력일자
        String fstRgstUsrId, // 최초등록사용자ID-입력담당
        String fstRgstUsrNm, // 입력담당자명
        String fnlMdfcDtm, // 최종수정일시-수정일자
        String fnlMdfcUsrId, // 최종수정사용자ID-수정담당
        String fnlMdfcUsrNm // 수정담당자명
    ) {}
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel("WctbMembershipBulkChangeDto-SaveStatusReq")
    public record SaveStatusReq(
        String procsDv, // 처리구분
        String chRson, // 변경사유
        String cttCd, // 컨택코드
        String subsDt, // 가입일
        String fxamYnCh, // 정액여부 변경
        String pdStdFee // 기준수수료
    ) {}
}
