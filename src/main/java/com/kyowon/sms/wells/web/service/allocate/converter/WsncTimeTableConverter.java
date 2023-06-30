package com.kyowon.sms.wells.web.service.allocate.converter;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableSalesDto;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableSchdChoDto;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableTimeChoDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WsncTimeTableConverter {
    WsncTimeTableSalesDto.FindRes mapSalesDvoToRes(WsncTimeTableSalesDvo dvo);

    WsncTimeTableTimeChoDto.FindRes mapTimeChoDvoToRes(WsncTimeTableTimeChoDvo dvo);

    WsncTimeTableSchdChoDto.FindRes mapSchdChoDvoToRes(WsncTimeTableSchdChoDvo dvo);

    WsncTimeTableParamDvo mapSalesParamReqToDvo(WsncTimeTableSalesDto.FindReq req);

    WsncTimeTableParamDvo mapTimeChoParamReqToDvo(WsncTimeTableTimeChoDto.FindReq req);

    List<WsncTimeTableSchdChoDto.TimeTableDays> mapDaysDvoToDto(List<WsncTimeTableDaysDvo> dvo);

    List<WsncTimeTableSchdChoDto.SidingDays> mapSidingDaysDvoToDto(List<WsncTimeTableSidingDaysDvo> dvo);

    List<WsncTimeTableSchdChoDto.DisableDays> mapDisableDaysDvoToDto(List<WsncTimeTableDisableDaysDvo> dvo);

    List<WsncTimeTableSchdChoDto.MonthSchedule> mapMonthScheduleDvoToDto(List<WsncTimeTableMonthScheduleDvo> dvo);

    List<WsncTimeTableTimeChoDto.AssignTime> mapAssignTimeDvoToDto(List<WsncTimeTableAssignTimeDvo> dvo);

    WsncTimeTableSchdChoDto.SmPmNt mapSmPmNtDvoToSchDto(WsncTimeTableSmPmNtDvo dvo);
    WsncTimeTableTimeChoDto.SmPmNt mapSmPmNtDvoToTimDto(WsncTimeTableSmPmNtDvo dvo);

    WsncTimeTableSchdChoDto.PsicData mapSchdPsicDatasDvoToDto(WsncTimeTablePsicDataDvo dvo);
    WsncTimeTableTimeChoDto.psicData mapTimePsicDatasDvoToDto(WsncTimeTablePsicDataDvo dvo);

}
