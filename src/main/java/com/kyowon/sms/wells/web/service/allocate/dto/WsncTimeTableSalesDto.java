package com.kyowon.sms.wells.web.service.allocate.dto;

import com.kyowon.sms.wells.web.service.allocate.dvo.*;
import com.sds.sflex.common.utils.DbEncUtil;
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
        String dowDvCd,
        String svBizDclsfCd, //wrkTypDtl
        String inflwChnl, // inGb
        String chnlDvCd, //gbCd
        String svDvCd, //dataGb
        String cntrNo,
        String cntrSn,
        String sellDate,
        String empId,
        String curDateTimeString,
        String wrkDt,
        String dataStatCd, // P_DATA_STUS
        String basePdCd,
        String lcst09,
        String newAdrZip,
        String userId,
        String sowDay,
        String returnUrl,
        String mkCo,

        String sidingYn,// 모종 여부
        String spayYn,// 일시불여부

        List<String> offDays,
        List<WsncTimeTableSidingDaysDvo> sidingDayDvos, // list2
        List<WsncTimeTableDisableDaysDvo> disableDayDvos, // diabledays
        WsncTimeTablePsicDataDvo psicDataDvos, // left_info
        List<WsncTimeTableAssignTimeDvo> assignTimeDvos, // list1

        List<WsncTimeTableDaysDvo> days,
        List<WsncTimeTableSmPmNtDvo> arrSm,
        List<WsncTimeTableSmPmNtDvo> arrAm,
        List<WsncTimeTableSmPmNtDvo> arrPm1,
        List<WsncTimeTableSmPmNtDvo> arrPm2,
        List<WsncTimeTableSmPmNtDvo> arrNt

    ) {
        public FindRes {
            psicDataDvos.setExnoEncr(DbEncUtil.dec(psicDataDvos.getExnoEncr()));
            psicDataDvos.setMexnoEncr(DbEncUtil.dec(psicDataDvos.getMexnoEncr()));
            psicDataDvos.setSjHp2(DbEncUtil.dec(psicDataDvos.getSjHp2()));
        }
    }

}
