package com.kyowon.sms.wells.web.service.stock.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemStockItemizationDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemStockItemizationReqDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMonthlyItemStocksReqDvo;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WsnaItemStockItemizationConverter {

    @Mapping(source = "procsYm", target = "dspsYrmn")
    @Mapping(source = "procsDt", target = "dspsDt")
    @Mapping(source = "iostTp", target = "stdlTyp")
    WsnaItemStockItemizationDvo mapWsnaItemStockItemizationReqDvoToWsnaItemStockItemizationDvo(
        WsnaItemStockItemizationReqDvo dvo
    );

    WsnaMonthlyItemStocksReqDvo mapWsnaItemStockItemizationReqDvoToWsnaMonthlyItemStocksReqDvo(
        WsnaItemStockItemizationReqDvo dvo
    );

}
