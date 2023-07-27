package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import io.swagger.annotations.ApiModel;

public class WctaKakaotalksDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    //카카오톡 발송 내역 - 카카오톡 발송 내역 조회 Search Request Dto
    @ApiModel(value = "WctaKakaotalksDto-SearchReq")
    public record SearchReq(
        String cntrNo,
        String cntrSn,
        String concDiv
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    //카카오톡 발송 내역 - 카카오톡 발송 내역 조회 Search Result Dto
    @ApiModel("WctaKakaotalksDto-SearchRes")
    public record SearchRes(
        String recipientNum,
        String dateClientReq,
        String dateRslt,
        String reslCdCntn,
        String rplcSend,
        String msg,
        String userId
    ) {}
}
