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
    String newAdrZip;
    String svBizDclsfCd; //wrkTypDtl
    String inGb;
    String chnlDvCd; //gbCd
    String svDvCd; //dataGb
    String cntrNo;
    String cntrSn;
    String selDate;
    String ordDt;
    String ordSeq;
    String userId;
    String saleCd;
    String addGb;
    String offDays;
    String curDateTimeString;
    String wrkGb;
    String wrkDt;
    String dataStatCd; // =P_DATA_STUS
    String basePdCd;
    String sowDay;
    String lcst09; // 확인필요
    String returnurl;
    String mkCo;

    List<WsncTimeTableSidingDaysDvo> list;
    List<WsncTimeTableMonthScheduleDvo> ordCnt;
    List<WsncTimeTableDisableDaysDvo> diableDays;
    List<WsncTimeTableTimAssStep3Dvo> timAssStep3;
    List<WsncTimeTableTimAssStep2Dvo> timAssStep2;
    List<WsncTimeTableSidingDaysDvo> ableDays;
    List<WsncTimeTableSmPmNtDvo> SmPmNt = new ArrayList<WsncTimeTableSmPmNtDvo>();
}
