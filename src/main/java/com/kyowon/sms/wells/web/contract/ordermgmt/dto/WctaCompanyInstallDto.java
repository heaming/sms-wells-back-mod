package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctaCompanyInstallDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 회사설치 Search Request Dto
    @Builder
    @ApiModel("WctaCompanyInstallDto-SearchReq")
    public record SearchReq(
        String cntrCnfmDtFrom, // 접수일FROM
        String cntrCnfmDtTo, // 접수일TO
        String insDtFrom, // 설치일FROM
        String insDtTo, // 설치일TO
        String cntrNo, // 계약번호
        String cntrSn, // 계약일련번호
        String pdHclsfId, // 상품대분류
        String pdMclsfId, // 상품중분류
        String basePdCd, // 싱픔코드
        String pdNm, // 상품명
        String rcgvpKnm, // 설치자명
        String coIstDvCd, // 설치구분
        String coIstUswyCd, // 설치용도
        String cralLocaraTno, // 휴대전화번호1
        String mexnoEncr, // 휴대전화번호2
        String cralIdvTno, // 휴대전화번호3
        String outputDiv, // 출력구분
        String sellOgTpCd // 조직유형코드
    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 회사설치 Search Result Dto
    @ApiModel("WctaCompanyInstallDto-SearchRes")
    public record SearchRes(
        String cntrNo,
        String cntrSn,
        String cstKnm,
        String bryyMmdd,
        String bzrno,
        String cntrCstNo,
        String rcgvpKnm,
        String cntrCnfmDt,
        String sellTpCd,
        String sellTpNm,
        String coCd,
        String ogCd,
        String coIstMngtDvCd,
        String coIstMngtDvNm,
        String wpDvNm,
        String basePdCd,
        String pdNm,
        String pkgPdCd,
        String pkgPdNm,
        String svPrd,
        String svPdTpCd,
        String svTpNm,
        String pdGdCd,
        String coIstDvCd,
        String coIstDvNm,
        String coIstUswyCd,
        String coIstUswyNm,
        String coIstPlace,
        String frisuBfsvcPtrmN,
        String frisuAsPtrmN,
        String filterExp,
        String dscRate,
        String sppDuedt,
        String istDt,
        String rtnDt,
        String cpsDt,
        String istAkArtcMoCn,
        String cttRsCd,
        String cttPsicId,
        String sconCn,
        String sellTpDtlCd,
        String sellTpDtlNm,
        String cntrNo216,
        String cntrSn216,
        String reguDelYn,
        String memExpGbn,
        String fstRgstDt,
        String fstRgstTm,
        String fstRgstUsrId,
        String fstRgstUsrNm,
        String fnlMdfcDt,
        String fnlMdfcTm,
        String fnlMdfcUsrId,
        String fnlMdfcUsrNm
    ) {
    }
}


