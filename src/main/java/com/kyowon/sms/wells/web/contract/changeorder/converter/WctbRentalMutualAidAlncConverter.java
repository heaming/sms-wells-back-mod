package com.kyowon.sms.wells.web.contract.changeorder.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbRentalMutualAidAlncDto.SearchReq;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbRentalMutualAidAlncDvo;

@Mapper(componentModel = "spring")
public interface WctbRentalMutualAidAlncConverter {

    WctbRentalMutualAidAlncDvo mapSearchReqToWctbRentalMutualAidAlncDvo(SearchReq dto);

}
