package com.kyowon.sms.wells.web.service.allocate.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dvo.WsncBsPeriodChartReqDvo;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncBsPeriodChartResDvo;

@Mapper(componentModel = "spring")
public interface WsncBsPeriodChartConverter {
    WsncBsPeriodChartReqDvo mapBaseInfoResToPeriodChartDvo(WsncBsPeriodChartResDvo res) throws Exception;
}
