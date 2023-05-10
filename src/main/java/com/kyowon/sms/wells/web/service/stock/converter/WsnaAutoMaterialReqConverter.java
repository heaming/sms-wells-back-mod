package com.kyowon.sms.wells.web.service.stock.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaAutoMaterialReqDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaAutoMaterialReqDvo;

@Mapper(componentModel = "spring")

public interface WsnaAutoMaterialReqConverter {
    List<WsnaAutoMaterialReqDvo> mapAllWsnaAutoMaterialReqDvo(List<SearchRes> list);
}
