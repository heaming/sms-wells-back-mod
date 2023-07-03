package com.kyowon.sms.wells.web.service.allocate.dvo;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
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
    String prtnrNo;
    String basePdCd;
    String pdctPdCd;
    String sidingYn;
    String rpbLocaraCd;

    List<WsncTimeTableDto.Days> days;
    List<WsncTimeTableDto.SidingDays> sidingDays; // list2 abledays
    List<WsncTimeTableDto.MonthSchedule> monthSchedules;
    List<WsncTimeTableDto.DisableDays> disableDays;
    List<String> offDays;
    WsncTimeTableDto.Psics psic; // left_info

    List<WsncTimeTableDto.SmPmNt> smTimes = new ArrayList<WsncTimeTableDto.SmPmNt>();
    List<WsncTimeTableDto.SmPmNt> amTimes = new ArrayList<WsncTimeTableDto.SmPmNt>();
    List<WsncTimeTableDto.SmPmNt> pmTimes1 = new ArrayList<WsncTimeTableDto.SmPmNt>();
    List<WsncTimeTableDto.SmPmNt> pmTimes2 = new ArrayList<WsncTimeTableDto.SmPmNt>();
    List<WsncTimeTableDto.SmPmNt> ntTimes = new ArrayList<WsncTimeTableDto.SmPmNt>();
}
