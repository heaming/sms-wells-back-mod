package com.kyowon.sms.wells.web.contract.ordermgmt.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDeletionRjRsonDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaOrderDeletionRjRsonDvo;

@Mapper(componentModel = "spring")
public interface WctaOrderDeletionRjRsonConverter {
    WctaOrderDeletionRjRsonDvo mapSaveReqToWctaOrderDeletionRjRsonDvo(WctaOrderDeletionRjRsonDto.SaveReq dto);
}
