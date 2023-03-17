package com.kyowon.sms.wells.web.service.allocate.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncAsTransferDto.SaveReq;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncAsTransferDvo;

@Mapper(componentModel = "spring")
public interface WsncAsTransferConverter {

    WsncAsTransferDvo mapSaveReqToWsncAsTransferDvo(SaveReq dto);

}
