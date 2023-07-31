package com.kyowon.sms.wells.web.contract.interfaces.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sds.sflex.common.utils.StringUtil;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctiContractCancelDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 렌털 계약취소 Request Dto
    @Builder
    @ApiModel("WctiContractCancelDto-CancelRentalReq")
    public record CancelRentalContractReq(
        @NotBlank
        @JsonProperty("CNTR_NO")
        String cntrNo,
        @NotBlank
        @JsonProperty("CNTR_SN")
        String cntrSn,
        @NotBlank
        @JsonProperty("CAN_DT")
        String canDt,
        @NotBlank
        @JsonProperty("RGST_USR_ID")
        String rgstUsrId
    ) {}

    // 멤버쉽 계약취소 Request Dto
    @Builder
    @ApiModel("WctiContractCancelDto-CancelMembershipContractReq")
    public record CancelMembershipContractReq(
        @NotBlank
        @JsonProperty("CNTR_NO")
        String cntrNo,
        @NotBlank
        @JsonProperty("CNTR_SN")
        String cntrSn,
        @NotBlank
        @JsonProperty("CAN_DT")
        String canDt,
        @NotBlank
        @JsonProperty("RGST_USR_ID")
        String rgstUsrId,
        @JsonProperty("SL_CTR_AMT")
        String slCtrAmt,
        @JsonProperty("DSC_DDCTAM")
        String dscDdctam,
        @JsonProperty("FILT_DDCTAM")
        String filtDdctam
    ) {
        public CancelMembershipContractReq {
            slCtrAmt = StringUtil.nvl(slCtrAmt, "0");
            dscDdctam = StringUtil.nvl(dscDdctam, "0");
            filtDdctam = StringUtil.nvl(filtDdctam, "0");
        }
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 렌털 계약취소 Result Dto
    @ApiModel("WctiContractCancelDto-CancelRentalRes")
    public record CancelRentalContractRes(
        @JsonProperty("CHK_RS")
        String chkRs,
        @JsonProperty("RS_CD")
        String rsCd,
        @JsonProperty("RS_MSG")
        String rsMsg
    ) {}

    // 멤버쉽 계약취소 Result Dto
    @ApiModel("WctiContractCancelDto-CancelMembershipContractRes")
    public record CancelMembershipContractRes(
        @JsonProperty("CHK_RS")
        String chkRs,
        @JsonProperty("RS_CD")
        String rsCd,
        @JsonProperty("RS_MSG")
        String rsMsg
    ) {}
}
