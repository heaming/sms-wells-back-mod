package com.kyowon.sms.wells.web.service.allocate.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dvo.WsncOutsourcedpdAsReceiptDvo;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncOutsourcedpdAsReceiptDto.BiztalkReq;

@Mapper(componentModel = "spring")
public interface WsncOutsourcedpdAsReceiptConverter {

    WsncOutsourcedpdAsReceiptDvo mapBiztalkReqToWsncOutsourcedpdAsReceiptDvo(BiztalkReq dto);

}
