package com.kyowon.sms.wells.web.service.allocate.dto;

import com.kyowon.sms.wells.web.service.allocate.dvo.*;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;
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
        @NotBlank
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
        @NotBlank
        String baseYm,
        String cstSvAsnNo
    ) {}

    @ApiModel(value = "WsncTimeTableTimeChoDto-FindRes")
    public record FindRes(
        WsncTimeTablePsicDataDvo psicDatas, // left_info
        List<WsncTimeTableTimeChoDto.AssignTime> assignTimes, // list1
        List<WsncTimeTableSmPmNtDvo> arrSm,
        List<WsncTimeTableSmPmNtDvo> arrAm,
        List<WsncTimeTableSmPmNtDvo> arrPm1,
        List<WsncTimeTableSmPmNtDvo> arrPm2,
        List<WsncTimeTableSmPmNtDvo> arrNt
    ) {}

    @ApiModel(value = "WsncTimeTableTimeChoDto-AssignTime")
    public record AssignTime(
        String vstDt,
        String empId,
        String tm,
        String wrkCnt,
        String wrkTCnt,
        String wrkCCnt,
        String wrkTChk,
        String wrkTRn,
        String wrkNextChk,
        String wrkNextChk2,
        String wrkChk2,
        String wrkChk1Rn,
        String empTWrkCnt,
        String degWrkCnt,
        String tWrkCnt
    ) {}

    @ApiModel(value = "WsncTimeTableTimeChoDto-SmPmNt")
    public record SmPmNt(
        String time,
        String cnt,
        String enableYn
    ) {}
}
