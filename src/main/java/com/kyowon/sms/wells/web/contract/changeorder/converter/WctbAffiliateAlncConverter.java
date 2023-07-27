package com.kyowon.sms.wells.web.contract.changeorder.converter;

import static com.kyowon.sms.wells.web.contract.changeorder.dto.WctbAffiliateAlncDto.SaveReq;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbAffiliateAlncDvo;

@Mapper(componentModel = "spring")
public interface WctbAffiliateAlncConverter {

    WctbAffiliateAlncDvo saveReqToWctbAffiliateAlncDvo(SaveReq dto);

}
