package com.kyowon.sms.wells.web.service.stock.converter;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaMcbyItmStocsDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMcbyItmStocsDvo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WsnaMcbyItmStocsConverter {

    WsnaMcbyItmStocsDto.CrdovrReq mapWsnaMcbyItmStocsDvoToCrdovrRes(WsnaMcbyItmStocsDvo dvo);

}
