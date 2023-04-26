package com.kyowon.sms.wells.web.service.visit.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.visit.dvo.WsnbOutsourcedpdAsReceiptDvo;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbOutsourcedpdAsReceiptDto.BiztalkReq;

@Mapper(componentModel = "spring")
public interface WsnbOutsourcedpdAsReceiptConverter {

    WsnbOutsourcedpdAsReceiptDvo mapBiztalkReqToWsncOutsourcedpdAsReceiptDvo(BiztalkReq dto);

}
