package com.kyowon.sms.wells.web.service.allocate.converter;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableSalesDto;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableSchdChoDto;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableTimeChoDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncTimeTableSalesDvo;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncTimeTableParamDvo;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncTimeTableSchdChoDvo;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncTimeTableTimeChoDvo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WsncTimeTableConverter {
    WsncTimeTableSalesDto.FindRes mapSalesDvoToRes(WsncTimeTableSalesDvo dvo);
    WsncTimeTableTimeChoDto.FindRes mapTimeChoDvoToRes(WsncTimeTableTimeChoDvo dvo);
    WsncTimeTableSchdChoDto.FindRes mapSchdChoDvoToRes(WsncTimeTableSchdChoDvo dvo);

    WsncTimeTableParamDvo mapSalesParamReqToDvo(WsncTimeTableSalesDto.FindReq req);
    WsncTimeTableParamDvo mapTimeChoParamReqToDvo(WsncTimeTableTimeChoDto.FindReq req);

}
