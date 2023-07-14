package com.kyowon.sms.wells.web.service.allocate.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncSpecCustAsnDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncSpecCustAsnDvo;

@Mapper(componentModel = "spring")
public interface WsncSpecCustAsnConverter {
    WsncSpecCustAsnDvo mapSaveProcessReqToSpecCustAsnDvo(WsncSpecCustAsnDto.SaveProcessReq dto) throws Exception;
}
