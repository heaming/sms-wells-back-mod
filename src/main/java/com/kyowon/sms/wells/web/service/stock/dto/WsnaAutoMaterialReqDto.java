package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;

public class WsnaAutoMaterialReqDto {

    @ApiModel(value = "WsnaAutoMaterialReqDto-SearchRes")
    public record SearchRes(
        String ostrAkNo,
        String wareNo
    ) {}

    @ApiModel(value = "WsnaAutoMaterialReqDto-CreateReq")
    public record CreateReq(

    ) {}
}
