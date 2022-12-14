package com.kyowon.sms.wells.web.service.allocate.converter;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncZipMngtDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncZipMngtDvo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WsncZipMngtConverter {

    WsncZipMngtDvo mapZipCodeResReqToWsncZipMngtDvo(WsncZipMngtDto.SaveZipCodeReq dto);
}
