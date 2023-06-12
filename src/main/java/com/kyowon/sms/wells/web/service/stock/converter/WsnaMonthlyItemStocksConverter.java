package com.kyowon.sms.wells.web.service.stock.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaMonthlyItemStocksDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMonthlyItemStocksDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMonthlyItemStocksReqDvo;

@Mapper(componentModel = "spring")
public interface WsnaMonthlyItemStocksConverter {

    WsnaMonthlyItemStocksDto.CrdovrReq mapWsnaMcbyItmStocsDvoToCrdovrRes(WsnaMonthlyItemStocksDvo dvo);

    WsnaMonthlyItemStocksDvo mapWsnaMonthlyItemStocksReqDvoToWsnaMonthlyItemStocksDvo(WsnaMonthlyItemStocksReqDvo dvo);

}
