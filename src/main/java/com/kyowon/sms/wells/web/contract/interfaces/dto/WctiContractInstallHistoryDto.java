package com.kyowon.sms.wells.web.contract.interfaces.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;

public class WctiContractInstallHistoryDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 계약처, 설치처 정보 변경 이력 Search Request Dto
    @ApiModel("WctiContractInstallHistoryDto-SearchReq")
    public record SearchReq(
        @JsonProperty("CNTR_NO")
        @NotBlank
        String cntrNo,
        @JsonProperty("CNTR_SN")
        @NotNull
        int cntrSn,
        @JsonProperty("INQR_DV_CD")
        @NotBlank
        @Pattern(regexp = "2")
        String inqrDvCd
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 계약처, 설치처 정보 변경 이력 Search Response Dto
    @ApiModel("WctiContractInstallHistoryDto-SearchRes")
    public record SearchRes(
        @JsonProperty("CNTR_NO")
        String cntrNo, //계약번호
        @JsonProperty("CNTR_SN")
        int cntrSn, //계약일련번호
        @JsonProperty("CRAL_LOCARA_TNO")
        String cralLocaraTno, //휴대지역전화번호
        @JsonProperty("MEXNO")
        String mexno, //휴대전화국번호
        @JsonProperty("CRAL_IDV_TNO")
        String cralIdvTno, //휴대개별전화번호
        @JsonProperty("LOCARA_TNO")
        String locaraTno, //지역전화번호
        @JsonProperty("EXNO")
        String exno, //전화국번호
        @JsonProperty("IDV_TNO")
        String idvTno, //개별전화번호
        @JsonProperty("ADDR_ID")
        String addrId, // 주소ID
        @JsonProperty("ADDR")
        String addr, // 주소
        @JsonProperty("DTL_ADDR")
        String dtlAddr, // 상세주소
        @JsonProperty("CH_DTM")
        String chDtm, //변경일시
        @JsonProperty("FNL_MDFC_USR_ID")
        String fnlMdfcUsrId, //최종수정사용자ID
        @JsonProperty("FNL_MDFC_USR_NM")
        String fnlMdfcUsrNm //최종수정사용자명
    ) {}
}
