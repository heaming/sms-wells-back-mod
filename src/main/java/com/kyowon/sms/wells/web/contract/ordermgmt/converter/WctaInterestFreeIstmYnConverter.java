package com.kyowon.sms.wells.web.contract.ordermgmt.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaInterestFreeIstmYnDto.SearchReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaInterestFreeIstmYnDvo;

@Mapper(componentModel = "spring")
public interface WctaInterestFreeIstmYnConverter {
    WctaInterestFreeIstmYnDvo mapSearchReqToWctaInterestDvo(SearchReq dto);

}
