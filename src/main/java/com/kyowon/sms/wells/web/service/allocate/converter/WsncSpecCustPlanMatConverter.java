package com.kyowon.sms.wells.web.service.allocate.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncSpecCustPlanMatDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncSpecCustPlanMatDvo;

@Mapper(componentModel = "spring")
public interface WsncSpecCustPlanMatConverter {
    WsncSpecCustPlanMatDvo mapSaveProcessReqToSpecCustPlanMatDvo(WsncSpecCustPlanMatDto.SaveProcessReq dto) throws Exception;
}
