package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WctaQuoteSendDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel("WctaQuoteSendDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String cntrNo,
        @NotBlank
        String cntrSn
    ) {}
    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel("WctaQuoteSendDto-SearchRes")
    public record SearchRes(
        String fwTpCd,
        String callback,
        String recipientNum,
        String fwDtm
    ) {}
    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel("WctaQuoteSendDto-SearchInfRes")
    public record SearchInfRes(
        String cntrCstNo,
        String cntrCstNm,
        String cntrCstCralLocaraTno,
        String cntrCstMexnoEncr,
        String cntrCstCralIdvTno,
        String emadr,
        String copnDvCd,
        String basePdCd,
        String basePdNm,
        String sellDscTpCd,
        String sellDscTpNm,
        String stplPtrmUnitCd,
        String stplPtrm,
        String svPtrmUnitCd,
        String svPrd,
        String cntrAmt,
        String pd_baseAmt,
        String fnlAmt,
        String linkUrl,
        String prtnrKnm,
        String prtnrCralLocaraTno,
        String prtnrMexnoEncr,
        String prtnrCralIdvTno,
        String cntrAprId
    ) {}
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel("WctaQuoteSendDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String fwTpCd,
        String sendTelNo1,
        String sendTelNo2,
        String sendTelNo3,
        String recpTelNo1,
        String recpTelNo2,
        String recpTelNo3,
        String sendMail,
        String recpMail,
        String cntrCstNo,
        String cntrCstNm,
        String cntrCstCralLocaraTno,
        String cntrCstMexnoEncr,
        String cntrCstCralIdvTno,
        String emadr,
        String copnDvNm,
        String basePdCd,
        String basePdNm,
        String sellDscTpCd,
        String sellDscTpNm,
        String stplPtrmUnitCd,
        String stplPtrm,
        String svPtrmUnitCd,
        String svPrd,
        String alncmpCdNm,
        String cntrAmt,
        String pdBaseAmt,
        String fnlAmt,
        String linkUrl,
        String prtnrKnm,
        String prtnrCralLocaraTno,
        String prtnrMexnoEncr,
        String prtnrCralIdvTno,
        String cntrAprId,
        String cntrNo,
        String cntrSn
    ) {}
}
