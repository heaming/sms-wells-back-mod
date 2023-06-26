package com.kyowon.sms.wells.web.service.allocate.converter;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableSalesDto;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableSchdChoDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncTimeTableSalesDvo;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncTimeTableParamDvo;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncTimeTableSchdChoDvo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WsncTimeTableConverter {
    WsncTimeTableSalesDto.FindRes mapSalesDvoToRes(WsncTimeTableSalesDvo dvo);
    WsncTimeTableSchdChoDto.FindRes mapSchdChoDvoToRes(WsncTimeTableSchdChoDvo dvo);

    WsncTimeTableParamDvo mapSalesParamReqToDvo(WsncTimeTableSalesDto.FindReq req);

}
