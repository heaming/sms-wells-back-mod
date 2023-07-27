package com.kyowon.sms.wells.web.contract.risk.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.risk.dto.WctcUserSellLimitMgtDto.SaveReq;
import com.kyowon.sms.wells.web.contract.risk.dvo.WctcUserSellLimitDvo;

@Mapper(componentModel = "spring")
public interface WctcUserSellLimitMgtConverter {
    WctcUserSellLimitDvo mapSaveReqToWctcUserSellLimitDvo(SaveReq dto);

}
