package com.kyowon.sms.wells.web.service.allocate.dto;

import com.kyowon.sms.wells.web.service.allocate.dvo.*;
import io.swagger.annotations.ApiModel;

import java.util.ArrayList;
import java.util.List;

/*
타임테이블 일정선택
매니저 AS 신청하는 타임테이블 캘린더
W-MP-U-0186P01
*/
public class WsncTimeTableTimeChoDto {

    @ApiModel(value = "WsncTimeTableTimeChoDto-FindReq")
    public record FindReq(
        String newAdrZip,
        String cntrNo,
        String cntrSn,
        String chnlDvCd,
        String sellDate,
        String svDvCd,
        String svBizDclsfCd,
        String pdctPdCd,
        String basePdCd,
        String hcrYn, // add_gb
        String prtnrNo,
        String ordDt,
        String ordSeq,
        String baseYm,
        String cstSvAsnNo
    ) {}

    @ApiModel(value = "WsncTimeTableTimeChoDto-FindRes")
    public record FindRes(
        WsncTimeTablePsicDataDvo psicDatas, // left_info
        List<WsncTimeTableAssignTimeDvo> assignTimes, // list1
        List<WsncTimeTableSmPmNtDvo> arrSm,
        List<WsncTimeTableSmPmNtDvo> arrAm,
        List<WsncTimeTableSmPmNtDvo> arrPm1,
        List<WsncTimeTableSmPmNtDvo> arrPm2,
        List<WsncTimeTableSmPmNtDvo> arrNt
    ) {}

}
