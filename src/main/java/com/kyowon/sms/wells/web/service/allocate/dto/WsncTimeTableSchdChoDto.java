package com.kyowon.sms.wells.web.service.allocate.dto;

import com.kyowon.sms.wells.web.service.allocate.dvo.WsncTimeTableDaysDvo;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncTimeTableDisableDaysDvo;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncTimeTableMonthScheduleDvo;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncTimeTableSidingDaysDvo;
import io.swagger.annotations.ApiModel;

import java.util.List;

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
        String ordSeq,
        String newAdrZip,
        String baseYm
    ) {}

    @ApiModel(value = "WsncTimeTableMngtASMonthDto-FindRes")
    public record FindRes(
        String newAdrZip,
        String svBizDclsfCd,
        String chnlDvCd,
        String svDvCd,
        String cntrNo,
        String cntrSn,
        String sellDate,
        String ordDt,
        String ordSeq,
        String empId,
        String basePdCd,
        String sidingYn,

        List<WsncTimeTableDaysDvo> days,
        List<WsncTimeTableSidingDaysDvo> sidingDay,
        List<WsncTimeTableMonthScheduleDvo> monthSchedule,
        List<WsncTimeTableDisableDaysDvo> disableDays
    ) {}

}
