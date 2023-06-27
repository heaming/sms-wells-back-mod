package com.kyowon.sms.wells.web.service.allocate.dvo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class WsncTimeTableSchdChoDvo {

    String newAdrZip;
    String svBizDclsfCd;
    String chnlDvCd;
    String svDvCd;
    String cntrNo;
    String cntrSn;
    String sellDate;
    String ordDt;
    String ordSeq;
    String empId;
    String basePdCd;

    List<WsncTimeTableDaysDvo> days;
    List<WsncTimeTableSidingDaysDvo> sidingDay;
    List<WsncTimeTableMonthScheduleDvo> monthSchedule;
    List<WsncTimeTableDisableDaysDvo> disableDays;
}
