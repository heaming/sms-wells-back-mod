package com.kyowon.sms.wells.web.service.allocate.dvo;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableSchdChoDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class WsncTimeTableSalesDvo {

    String baseYm;
    String dowDvCd;
    String svBizDclsfCd; //wrkTypDtl
    String inflwChnl; // inflwChnl 채널구분
    String chnlDvCd; //gbCd
    String svDvCd; //dataGb
    String cntrNo;
    String cntrSn;
    String sellDate;
    String empId;
    String curDateTimeString;
    String wrkDt;
    String dataStatCd; // P_DATA_STUS
    String basePdCd;
    String sdingCombin; //lcst09
    String newAdrZip;
    String sowDay;
    String returnUrl;
    String mkCo;

    String userId;
    String rcpOgTpCd;

    String prtnrNo;
    String ogTpCd;

    String sidingYn; // 모종 여부
    String spayYn; // 일시불여부
    String seq; // 일시불여부
    String cstSvAsnNo;

    List<String> offDays;
    List<WsncTimeTableSchdChoDto.SidingDays> sidingDays; // list2 abledays
    List<WsncTimeTableSchdChoDto.DisableDays> disableDays;
    WsncTimeTableSchdChoDto.PsicData psicDatas; // left_info
    // List<WsncTimeTableAssignTimeDvo> assignTimeDvos; // list1

    List<WsncTimeTableDaysDvo> days;

    List<WsncTimeTableSchdChoDto.SmPmNt> arrSm = new ArrayList<WsncTimeTableSchdChoDto.SmPmNt>();
    List<WsncTimeTableSchdChoDto.SmPmNt> arrAm = new ArrayList<WsncTimeTableSchdChoDto.SmPmNt>();
    List<WsncTimeTableSchdChoDto.SmPmNt> arrPm1 = new ArrayList<WsncTimeTableSchdChoDto.SmPmNt>();
    List<WsncTimeTableSchdChoDto.SmPmNt> arrPm2 = new ArrayList<WsncTimeTableSchdChoDto.SmPmNt>();
    List<WsncTimeTableSchdChoDto.SmPmNt> arrNt = new ArrayList<WsncTimeTableSchdChoDto.SmPmNt>();

}
