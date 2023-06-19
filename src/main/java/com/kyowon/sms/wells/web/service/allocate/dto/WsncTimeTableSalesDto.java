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

        String inflwChnl,
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
        String inflwChnl, // inflwChnl
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
        String sowDay,
        String returnUrl,
        String mkCo,

        String userId,
        String rcpOgTpCd,

        String prtnrNo,
        String ogTpCd,

        String sidingYn,// 모종 여부
        String spayYn,// 일시불여부

        List<String> offDays,
        List<WsncTimeTableSidingDaysDvo> sidingDays, // list2
        List<WsncTimeTableDisableDaysDvo> disableDays, // diabledays
        WsncTimeTablePsicDataDvo psicDatas, // left_info
        // List<WsncTimeTableAssignTimeDvo> assignTimeDvos, // list1

        List<WsncTimeTableDaysDvo> days,
        List<WsncTimeTableSmPmNtDvo> arrSm,
        List<WsncTimeTableSmPmNtDvo> arrAm,
        List<WsncTimeTableSmPmNtDvo> arrPm1,
        List<WsncTimeTableSmPmNtDvo> arrPm2,
        List<WsncTimeTableSmPmNtDvo> arrNt

    ) {
        public FindRes {
            psicDatas.setExnoEncr(DbEncUtil.dec(psicDatas.getExnoEncr()));
            psicDatas.setMexnoEncr(DbEncUtil.dec(psicDatas.getMexnoEncr()));
            psicDatas.setSjHp2(DbEncUtil.dec(psicDatas.getSjHp2()));
        }
    }

}
