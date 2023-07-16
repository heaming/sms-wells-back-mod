package com.kyowon.sms.wells.web.service.allocate.converter;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncBsPeriodChartDto;
import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncVisitPeriodRecrtDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncVisitPeriodRecrtDvo;

@Mapper(componentModel = "spring")
public interface WsncVisitPeriodRecrtConverter {
    WsncVisitPeriodRecrtDvo mapPeriodRecrtSaveReqToDvo(WsncVisitPeriodRecrtDto.SaveReq dto) throws Exception;

    WsncBsPeriodChartDto.SearchReq mapVisitPeriodDvoToBsPeriodSearchReq(WsncVisitPeriodRecrtDvo req) throws Exception;
}
