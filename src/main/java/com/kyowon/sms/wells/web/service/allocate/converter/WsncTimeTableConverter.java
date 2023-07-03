package com.kyowon.sms.wells.web.service.allocate.converter;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WsncTimeTableConverter {
    WsncTimeTableDto.FindRes mapTimeAssignDvoToRes(WsncTimeTableDvo dvo);

    WsncTimeTableDto.FindRes mapTimeChoDvoToRes(WsncTimeTableDvo dvo);

    WsncTimeTableDto.FindRes mapSchdChoDvoToRes(WsncTimeTableDvo dvo);

    WsncTimeTableDvo mapTimeAssignReqToParamDvo(WsncTimeTableDto.FindTimeAssignReq req);

    WsncTimeTableDvo mapScheChoReqToDvo(WsncTimeTableDto.FindScheChoReq req);

    WsncTimeTableDvo mapTimeChoReqToDvo(WsncTimeTableDto.FindTimeChoReq req);

    //List<WsncTimeTableDto.TimeTableDays> mapDaysDvoToDto(List<WsncTimeTableDaysDvo> dvo);

    List<WsncTimeTableDto.SidingDays> mapSidingDaysDvoToDto(List<WsncTimeTableSidingDaysDvo> dvo);

    List<WsncTimeTableDto.DisableDays> mapDisableDaysDvoToDto(List<WsncTimeTableDisableDaysDvo> dvo);

    List<WsncTimeTableDto.MonthSchedule> mapMonthScheduleDvoToDto(List<WsncTimeTableMonthScheduleDvo> dvo);

    List<WsncTimeTableDto.AssignTime> mapAssignTimeDvoToDto(List<WsncTimeTableAssignTimeDvo> dvo);

    WsncTimeTableDto.SmPmNt mapSmPmNtDvoToSchDto(WsncTimeTableSmPmNtDvo dvo);

    WsncTimeTableDto.SmPmNt mapSmPmNtDvoToTimDto(WsncTimeTableSmPmNtDvo dvo);

    WsncTimeTableDto.Psics mapPsicsDvoToDto(WsncTimeTablePsicDvo dvo);

    WsncTimeTableSmPmNtDvo mapAssignTimeDvoToSmPmNtDvo(WsncTimeTableAssignTimeDvo dvo);

}
