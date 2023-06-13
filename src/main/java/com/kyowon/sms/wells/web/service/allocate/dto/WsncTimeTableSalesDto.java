package com.kyowon.sms.wells.web.service.allocate.dto;

import com.kyowon.sms.wells.web.service.allocate.dvo.*;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;
import java.util.List;

/*타임테이블 조회(판매)*/
public class WsncTimeTableSalesDto {

    @ApiModel(value = "WsncTimeTableSalesDto-FindReq")
    public record FindReq(
        @NotBlank
        String chnlDvCd, // gbCd
        @NotBlank
        String svDvCd, // DATA_GB
        @NotBlank
        String sellDate, // SEL_DATE
        @NotBlank
        String svBizDclsfCd, // wrkTypDtl
        @NotBlank
        String cntrNo,
        String cntrSn,

        String inGb,
        String basePdCd, // GDS_CD
        String wrkDt,
        String dataStatCd, // DATA_STUS
        String returnUrl,
        String userId,
        String mkCo
    ) {}

    @ApiModel(value = "WsncTimeTableSalesDto-FindRes")
    public record FindRes(
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
        String basePdCd, // gdsCd
        String pajongDay,
        String lcst09,

        List<WsncTimeTableSidingDaysDvo> sidingDaysDvos,
        List<WsncTimeTableMonthScheduleDvo> monthScheduleDvos,
        List<WsncTimeTableDisableDaysDvo> disableDaysDvos,
        List<WsncTimeTableAssignTimeDvo> assignTimeDvo,
        List<WsncTimeTablePsicDataDvo> psicDataDvos,
        //List<WsncTimeTableSidingDaysDvo> ableDayDvos,
        List<WsncTimeTableSmPmNtDvo> smPmNtDvo

    ) {}

}
