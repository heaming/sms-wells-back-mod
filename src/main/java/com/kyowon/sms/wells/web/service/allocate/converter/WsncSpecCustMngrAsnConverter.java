package com.kyowon.sms.wells.web.service.allocate.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncSpecCustMngrAsnDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncSpecCustMngrAsnDvo;

@Mapper(componentModel = "spring")
public interface WsncSpecCustMngrAsnConverter {
    WsncSpecCustMngrAsnDvo mapSaveProcessReqToSpecCustMngrAsnDvo(WsncSpecCustMngrAsnDto.SaveProcessReq dto) throws Exception;
}
