package com.kyowon.sms.wells.web.contract.interfaces.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;

public class WctiMachineChangeDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 기기변경 정보 Search Request Dto
    @ApiModel("WctiMachineChangeDto-SearchReq")
    public record SearchReq(
        @JsonProperty("CNTR_NO")
        @NotBlank
        String cntrNo,
        @JsonProperty("CNTR_SN")
        @NotNull
        int cntrSn
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 기기변경 정보 Search Result Dto
    @ApiModel("WctiMachineChangeDto-SearchRes")
    public record SearchRes(
        @JsonProperty("SELL_TP_CD")
        String sellTpCd, // 판매유형코드
        @JsonProperty("SELL_TP_NM")
        String sellTpNm, // 판매유형코드명
        @JsonProperty("OJ_CNTR_NO")
        String ojCntrNo, // 대상계약번호
        @JsonProperty("OJ_CNTR_SN")
        int ojCntrSn, // 대상계약일련번호
        @JsonProperty("OJ_SELL_TP_CD")
        String ojSellTpCd, // 대상판매유형코드
        @JsonProperty("OJ_SELL_TP_NM")
        String ojSellTpNm, // 대상판매유형코드명
        @JsonProperty("MCHN_CH_TP_CD")
        int mchnChTpCd, // 기기변경유형코드
        @JsonProperty("MCHN_CPS_APYR")
        String mchnCpsApyr, // 기기보상적용율
        @JsonProperty("OJ_CNTR_MM_BASE_DV_CD")
        String ojCntrMmBaseDvCd, // 대상계약월기준구분코드
        @JsonProperty("FST_RGST_DTM")
        String fstRgstDtm, // 최초등록일시
        @JsonProperty("FNL_MDFC_DTM")
        String fnlMdfcDtm, // 최종수정일시
        @JsonProperty("FST_RGST_USR_ID")
        String fstRgstUsrId, // 최초등록사용자ID
        @JsonProperty("FNL_MDFC_USR_ID")
        String fnlMdfcUsrId, // 최종수정사용자ID
        @JsonProperty("FST_RGST_USR_NM")
        String fstRgstUsrNm, // 최초등록사용자명
        @JsonProperty("FNL_MDFC_USR_NM")
        String fnlMdfcUsrNm, // 최종수정사용자명
        @JsonProperty("OJ_ISTLL_KNM")
        String ojIstllKnm, // 대상설치자한글명
        @JsonProperty("OJ_RECAP_DUTY_PTRM_N")
        int ojRecapDutyPtrmN, // 대상유상의무기간수
        @JsonProperty("OJ_SL_DT")
        String ojSlDt, // 대상매출일자
        @JsonProperty("OJ_PD_NM")
        String ojPdNm, // 대상상품명
        @JsonProperty("OJ_CRAL_LOCARA_TNO")
        String ojCralLocaraTno, // 대상휴대지역전화번호
        @JsonProperty("OJ_MEXNO")
        String ojMexno, // 대상휴대전화국번호
        @JsonProperty("OJ_CRAL_IDV_TNO")
        String ojCralIdvTno, // 대상휴대개별전화번호
        @JsonProperty("OJ_LOCARA_TNO")
        String ojLocaraTno, // 대상지역전화번호
        @JsonProperty("OJ_EXNO")
        String ojExno, // 대상전화국번호
        @JsonProperty("OJ_IDV_TNO")
        String ojIdvTno, // 대상개별전화번호
        @JsonProperty("OJ_ADR_ID")
        String ojAdrId, // 대상주소ID
        @JsonProperty("OJ_ADR")
        String ojAdr, // 대상주소
        @JsonProperty("OJ_DTL_ADR")
        String ojDtlAdr, // 대상상세주소
        @JsonProperty("OJ_ADR_ZIP")
        String ojAdrZip, // 대상주소우편번호
        @JsonProperty("OJ_CAN_DT")
        String ojCanDt, // 대상취소일자
        @JsonProperty("OJ_WDWAL_DT")
        String ojWdwalDt, // 대상탈퇴일자
        @JsonProperty("OJ_REQD_DT")
        String ojReqdDt, // 대상철거일자
        @JsonProperty("OJ_CPS_DT")
        String ojCpsDt, // 대상보상일자
        @JsonProperty("OJ_COPN_DV_CD")
        String ojCopnDvCd, // 대상법인격구분코드
        @JsonProperty("DSC_TP_NM")
        String dscTpNm, // 할인유형명
        @JsonProperty("RSTL_RCT_DT")
        String rstlRctDt, // 재약정접수일자
        @JsonProperty("RSTL_CAN_DT")
        String rstlCanDt, // 재약정취소일자
        @JsonProperty("RSTL_CNFMDT")
        String rstlCnfmdt, // 재약정확정일자
        @JsonProperty("RSTL_DSC_AMT")
        int rstlDscAmt //재약정할인금액
    ) {}
}

