package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctaVirtualAccountMailDto {
    @Builder
    @ApiModel("WctaVirtualAccountMailDto-SaveReq")
    public record SaveReq(
        String mailAddr, // 메일주소
        String vacBnkNm, // 가상계좌은행명
        String vacNo, // 가상계좌번호
        String vacGbn, // 가상계좌구분
        String custNm // 고객명
    ) {}
}
