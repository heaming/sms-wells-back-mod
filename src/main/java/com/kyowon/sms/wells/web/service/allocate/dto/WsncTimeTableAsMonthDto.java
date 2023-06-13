package com.kyowon.sms.wells.web.service.allocate.dto;

import io.swagger.annotations.ApiModel;

/*
타임테이블 일정선택
매니저 AS 신청하는 타임테이블 캘린더
W-MP-U-0186P01
*/
public class WsncTimeTableAsMonthDto {

    @ApiModel(value = "WsncTimeTableMngtASMonthDto-FindReq")
    public record FindReq() {}

    @ApiModel(value = "WsncTimeTableMngtASMonthDto-FindRes")
    public record FindRes() {}

}
