package com.kyowon.sms.wells.web.service.allocate.dvo;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableSchdChoDto;
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
    String sidingYn;

    List<WsncTimeTableSchdChoDto.TimeTableDays> days;
    List<WsncTimeTableSchdChoDto.SidingDays> sidingDay;
    List<WsncTimeTableSchdChoDto.MonthSchedule> monthSchedule;
    List<WsncTimeTableSchdChoDto.DisableDays> disableDays;
}
