package com.kyowon.sms.wells.web.service.allocate.converter;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableSalesDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncTimeTableSalesDvo;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncTimeTableSalesParamDvo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WsncTimeTableConverter {
    WsncTimeTableSalesDto.FindRes mapSalesDvoToRes(WsncTimeTableSalesDvo dvo);

    WsncTimeTableSalesParamDvo mapSalesParamReqToDvo(WsncTimeTableSalesDto.FindReq req);
}
