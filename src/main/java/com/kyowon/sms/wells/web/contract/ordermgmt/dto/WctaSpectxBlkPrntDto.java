package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctaSpectxBlkPrntDto {
    @ApiModel("WctaSpectxBlkPrntDto-SearchRes")
    @Builder
    public record SearchRes(
        String spectxGrpNo,
        String sellTpCd,
        String cntrDtlNo,
        String cntrNo,
        String cntrSn,
        String cstNm,
        String spectxPrntY,
        String spectxPblDDvCd,
        String spectxPrdDvCd,
        String emadr,
        String faxLocaraTno,
        String faxExno,
        String faxIdvTno,
        String fstRgstUsrId,
        String epNo,
        String fstRgstD,
        String fstRgstDtm,
        String lstmmYn,
        String cstNo
    ) {}
    @ApiModel("WctaSpectxBlkPrntDto-SearchReq")
    @Builder
    public record SearchReq(
        @NotBlank
        @ValidDate
        String rgstStartDt,
        @NotBlank
        @ValidDate
        String rgstEndDt,
        String cntrNo,
        String cntrSn,
        String grpStartNo,
        String grpEndNo
    ) {}
    @ApiModel("WctaSpectxBlkPrntDto-SearchCntrRes")
    @Builder
    public record SearchCntrRes(
        String sellTpCd,
        String cntrCstNo,
        String cstKnm,
        String cstNo,
        String emadr,
        String spectxPblD,
        String cntrNo,
        String cntrSn,
        String dtlCntrNo
    ) {}

    @ApiModel("WctaSpectxBlkPrntDto-SaveReq")
    @Builder
    public record SaveReq(
        @NotBlank
        String rowState,
        @NotBlank
        String spectxGrpNo,
        @NotBlank
        String sellTpCd,
        String cntrDtlNo,
        @NotBlank
        String cntrNo,
        @NotBlank
        String cntrSn,
        String cstNm,
        String spectxPrntY,
        String spectxPblDDvCd,
        String spectxPrdDvCd,
        String emadr,
        String faxLocaraTno,
        String faxExno,
        String faxIdvTno,
        String fstRgstUsrId,
        String epNo,
        String fstRgstD,
        String fstRgstDtm,
        String lstmmYn,
        String cstNo
    ) {}

    @ApiModel("WctaSpectxBlkPrntDto-SpectxFwReq")
    @Builder
    public record SpectxFwReq(
        @NotBlank
        @ValidDate
        String fromDate,
        @NotBlank
        @ValidDate
        String toDate,
        String spectxPblDDvCd,
        String fromGrpNo,
        String toGrpNo,
        String isYn
    ) {}

    @ApiModel("WctaSpectxBlkPrntDto-SpectxFwRes")
    @Builder
    public record SpectxFwRes(
        String spectxGrpNo, // 그룹번호
        String cstNm, // 발급명
        String slClYm, // 기간
        String cstKnm, // 고객한글명
        String cstNo, // 고객번호
        String spectxPblDDvCd, // 발행일
        String spectxPrntDt, // 출력일
        String spectxPrdDvCd, // 거래명세서주기구분코드
        String isYn, // 발급여부
        String spectxFwDtm, // 발급일시
        String spectxFwD, // 거래명세서발송일
        String spectxFwTm, // 거래명세서발송일시
        String dpTpCd, // 수납유형
        String cntrNo, // 계약번호
        String cntrSn, // 계약일련번호
        String cntrCn, // 계약건수
        String basePdCd, // 대표상품코드
        String basePdNm, // 상품명
        String nomSlAmt, // 매출액
        String emadr, // 이메일
        String faxLocaraTno, // 팩스지역전화번호
        String faxExno, // 팩스전화국번호
        String faxIdvTno, // 팩스개별전화번호
        String lstmmYn // 전월여부
    ) {}

    @ApiModel("WctaSpectxBlkPrntDto-SaveTradeSpcshFwReq")
    @Builder
    public record SaveTradeSpcshFwReq(
        String spectxGrpNo, // 그룹번호
        String cstNm, // 발급명
        String slClYm, // 기간
        String cstKnm, // 고객한글명
        String cstNo, // 고객번호
        String spectxPblDDvCd, // 발행일
        String spectxPrntDt, // 출력일
        String spectxPrdDvCd, // 거래명세서주기구분코드
        String isYn, // 발급여부
        String spectxFwDtm, // 발급일시
        String spectxFwD, // 거래명세서발송일
        String spectxFwTm, // 거래명세서발송일시
        String dpTpCd, // 수납유형
        String cntrNo, // 계약번호
        String cntrSn, // 계약일련번호
        String cntrCn, // 계약건수
        String basePdCd, // 대표상품코드
        String basePdNm, // 상품명
        String nomSlAmt, // 매출액
        String emadr, // 이메일
        String faxLocaraTno, // 팩스지역전화번호
        String faxExno, // 팩스전화국번호
        String faxIdvTno, // 팩스개별전화번호
        String lstmmYn // 전월여부
    ) {}
}
