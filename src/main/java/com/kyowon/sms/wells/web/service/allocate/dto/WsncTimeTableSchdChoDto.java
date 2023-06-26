package com.kyowon.sms.wells.web.service.allocate.dto;

import io.swagger.annotations.ApiModel;

/*
타임테이블 일정선택
매니저 AS 신청하는 타임테이블 캘린더
W-MP-U-0186P01
*/
public class WsncTimeTableSchdChoDto {

    @ApiModel(value = "WsncTimeTableMngtASMonthDto-FindReq")
    public record FindReq(
        String cntrNo,
        String cntrSn,
        String chnlDvCd,
        String sellDate,
        String svDvCd,
        String svBizDclsfCd,
        String prtnrNo,
        String ordDt,
        String ordSeq
    ) {}

    @ApiModel(value = "WsncTimeTableMngtASMonthDto-FindRes")
    public record FindRes() {}

}
