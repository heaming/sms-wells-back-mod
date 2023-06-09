package com.kyowon.sms.wells.web.service.allocate.dto;

import com.kyowon.sms.wells.web.service.allocate.dvo.*;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class WsncTimeTableDto {

    @ApiModel(value = "WsncTimeTableDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String baseYm,
        String prevTag,
        String gbCd,
        String svBizHclsfCd, // DATA_GB
        String prtnrNo,
        @NotBlank
        String sellDate, // SEL_DATE
        @NotBlank
        String Dt,
        String ordSeq,
        String svBizDclsfCd,
        String newAdrZip,
        @NotBlank
        String cntrNo,
        String sidingCd, // MOJONG_CD
        String inGb,
        String basePdCd, // GDS_CD
        String wrkDt,
        String seq,
        String dataStus,
        String returnUrl,
        String userId
    ) {}

    @ApiModel(value = "WsncTimeTableDto-SearchRes")
    public record SearchRes(
        String baseY,
        String baseMm,
        String baseD,
        String dowDvCd,
        String dfYn,
        String phldYn,
        String rmkCn,
        String zip,
        String wrkTypDtl,
        String inGb,
        String gbCd,
        String dataGb,
        String cntrNo,
        String selDate,
        String ordDt,
        String ordSeq,
        String empId,
        String saleCd,
        String addGb,
        String offDays,
        String curDateTimeString,
        String wrkGb,
        String wrkDt,
        String dataStus,
        String gdsCd,
        String pajongDay,
        String lcst09,

        List<WsncTimeTableSidingDaysDvo> list,
//        List<SidingDays> list,
        List<WsncTimeTableMonthScheduleDvo> ordCnt,
        List<WsncTimeTableDisableDaysDvo> diableDays,
        List<WsncTimeTableTimAssStep3Dvo> timAssStep3,
        List<WsncTimeTableTimAssStep2Dvo> timAssStep2,
        List<WsncTimeTableSidingDaysDvo> ableDays,
        List<WsncTimeTableSmPmNtDvo> SmPmNt

    ) {}

//    @ApiModel(value = "WsncTimeTableDto-SidingDays")
//    public record SidingDays(
//        String title,
//        String sumCnt,
//        String st,
//        String ed,
//        String w3th,
//        String ablDays,
//        String sowDay
//    ) {}
}
