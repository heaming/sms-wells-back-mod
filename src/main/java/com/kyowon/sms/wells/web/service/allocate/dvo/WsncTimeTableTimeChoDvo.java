package com.kyowon.sms.wells.web.service.allocate.dvo;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WsncTimeTableTimeChoDvo {

    WsncTimeTablePsicDataDvo psicDatas; // left_info
    List<WsncTimeTableAssignTimeDvo> assignTimes; // list1

    List<WsncTimeTableSmPmNtDvo> arrSm = new ArrayList<WsncTimeTableSmPmNtDvo>();
    List<WsncTimeTableSmPmNtDvo> arrAm = new ArrayList<WsncTimeTableSmPmNtDvo>();
    List<WsncTimeTableSmPmNtDvo> arrPm1 = new ArrayList<WsncTimeTableSmPmNtDvo>();
    List<WsncTimeTableSmPmNtDvo> arrPm2 = new ArrayList<WsncTimeTableSmPmNtDvo>();
    List<WsncTimeTableSmPmNtDvo> arrNt = new ArrayList<WsncTimeTableSmPmNtDvo>();
}
