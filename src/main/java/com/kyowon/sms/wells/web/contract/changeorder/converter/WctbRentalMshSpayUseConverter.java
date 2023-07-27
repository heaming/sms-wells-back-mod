package com.kyowon.sms.wells.web.contract.changeorder.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbRentalMshSpayUseDto;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbRentalMshSpayUseDvo;

@Mapper(componentModel = "spring")
public interface WctbRentalMshSpayUseConverter {
    WctbRentalMshSpayUseDto.SearchRes mapWctbRentalMshSpayUseDvoToSearchRes(WctbRentalMshSpayUseDvo dvo);
}
