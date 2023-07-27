package com.kyowon.sms.wells.web.contract.ordermgmt.converter;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailVirtualAcSmsDto.SaveReq;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaOrderDetailVirtualAcSmsDvo;

@Mapper(componentModel = "spring")
public interface WctaOrderDetailVirtualAcSmsConverter {
    WctaOrderDetailVirtualAcSmsDvo mapSaveReqToOrderDetailVirtualAcSmsDvo(SaveReq req);
}
