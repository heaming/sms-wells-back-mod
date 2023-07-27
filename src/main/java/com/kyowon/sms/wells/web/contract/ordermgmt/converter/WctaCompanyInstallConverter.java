package com.kyowon.sms.wells.web.contract.ordermgmt.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaCompanyInstallDto.SearchReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaCompanyInstallDvo;

@Mapper(componentModel = "spring")
public interface WctaCompanyInstallConverter {
    WctaCompanyInstallDvo mapSearchReqToDvo(SearchReq dto);
}
