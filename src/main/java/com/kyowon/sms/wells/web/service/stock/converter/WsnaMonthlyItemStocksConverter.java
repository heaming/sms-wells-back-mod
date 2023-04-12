package com.kyowon.sms.wells.web.service.stock.converter;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaMonthlyItemStocksDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMonthlyItemStocksDvo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WsnaMonthlyItemStocksConverter {

    WsnaMonthlyItemStocksDto.CrdovrReq mapWsnaMcbyItmStocsDvoToCrdovrRes(WsnaMonthlyItemStocksDvo dvo);

}
