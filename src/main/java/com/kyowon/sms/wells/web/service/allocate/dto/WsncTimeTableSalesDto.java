package com.kyowon.sms.wells.web.service.allocate.dto;

import com.kyowon.sms.wells.web.service.allocate.dvo.*;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;
import java.util.List;

/*타임테이블 조회(판매)*/
public class WsncTimeTableSalesDto {

    @ApiModel(value = "WsncTimeTableSalesDto-findReq")
    public record findReq(
        @NotBlank
        String chnlDvCd,
        @NotBlank
        String svDvCd, // DATA_GB
        @NotBlank
        String sellDate, // SEL_DATE
        @NotBlank
        String svBizDclsfCd,
        @NotBlank
        String cntrNo,
        @NotBlank
        String cntrSn,
        String sidingCd, // MOJONG_CD
        String inGb,
        String basePdCd, // GDS_CD
        String wrkDt,
        String seq,
        String dtaStatCd, // DATA_STUS
        String returnUrl,
        String userId,
        String mkCo
    ) {}

    @ApiModel(value = "WsncTimeTableSalesDto-findRes")
    public record findRes(
        String baseY,
        String baseMm,
        String baseD,
        String dowDvCd,
        String dfYn,
        String phldYn,
        String rmkCn,
        String zip,
        String svBizDclsfCd, //wrkTypDtl
        String inGb,
        String chnlDvCd, //gbCd
        String svDvCd, //dataGb
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
        String dtaStatCd, // P_DATA_STUS
        String gdsCd,
        String pajongDay,
        String lcst09,

        List<WsncTimeTableSidingDaysDvo> list,
        List<WsncTimeTableMonthScheduleDvo> ordCnt,
        List<WsncTimeTableDisableDaysDvo> diableDays,
        List<WsncTimeTableTimAssStep3Dvo> timAssStep3,
        List<WsncTimeTableTimAssStep2Dvo> timAssStep2,
        List<WsncTimeTableSidingDaysDvo> ableDays,
        List<WsncTimeTableSmPmNtDvo> SmPmNt

    ) {}

}
