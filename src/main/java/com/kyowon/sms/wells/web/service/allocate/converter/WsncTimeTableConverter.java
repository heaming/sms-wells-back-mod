package com.kyowon.sms.wells.web.service.allocate.converter;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableSalesDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncTimeTableSalesDvo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WsncTimeTableConverter {
    WsncTimeTableSalesDto.findRes mapSalesDvoToRes(WsncTimeTableSalesDvo dvo);
}
