package com.kyowon.sms.wells.web.service.allocate.dvo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class WsncTimeTableDvo {

    String baseY;
    String baseMm;
    String baseD;
    String dowDvCd;
    String dfYn;
    String phldYn;
    String rmkCn;
    String zip;
    String wrkTypDtl;
    String inGb;
    String gbCd;
    String dataGb;
    String cntrNo;
    String selDate;
    String ordDt;
    String ordSeq;
    String empId;
    String saleCd;
    String addGb;
    String offDays;
    String curDateTimeString;
    String wrkGb;
    String wrkDt;
    String dataStus;
    String gdsCd;
    String pajongDay;
    String lcst09;

    List<WsncTimeTableMojongDaysDvo> list1;
    List<WsncTimeTableMonthScheduleDvo> ordcnt;
    List<WsncTimeTableDisableDaysDvo> diabledays;
    List<WsncTimeTableTimAssStep3Dvo> timAssStep3;
    List<WsncTimeTableTimAssStep2Dvo> timAssStep2;
    List<WsncTimeTableIlsibulDvo> abledays;
    List<WsncTimeTableSmPmNtDvo> SmPmNt = new ArrayList<WsncTimeTableSmPmNtDvo>();
}
