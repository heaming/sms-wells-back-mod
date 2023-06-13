package com.kyowon.sms.wells.web.service.allocate.dvo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class WsncTimeTableSalesDvo {

    String baseY;
    String baseMm;
    String baseD;
    String dowDvCd;
    String dfYn;
    String phldYn;
    String rmkCn;
    String zip;
    String svBizDclsfCd; //wrkTypDtl
    String inGb;
    String chnlDvCd; //gbCd
    String svDvCd; //dataGb
    String cntrNo;
    String cntrSn;
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
    String dataStatCd; // P_DATA_STUS
    String basePdCd;
    String pajongDay;
    String lcst09;
    String newAdrZip;
    String userId;
    String sowDay;
    String returnurl;
    String mkCo;

    List<WsncTimeTableSidingDaysDvo> sidingDayDvos;
    List<WsncTimeTableMonthScheduleDvo> monthScheduleDvos;
    List<WsncTimeTableDisableDaysDvo> disableDayDvos;
    List<WsncTimeTablePsicDataDvo> psicDataDvos;
    List<WsncTimeTableAssignTimeDvo> assignTimeDvos;
    List<WsncTimeTableSmPmNtDvo> smPmNtDvos = new ArrayList<WsncTimeTableSmPmNtDvo>();
}
