package com.kyowon.sms.wells.web.contract.ordermgmt.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractExceptionDto.SaveReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaContractExOjBasDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaContractExOjDtlDvo;

@Mapper(componentModel = "spring")
public interface WctaContractExceptionConverter {

    WctaContractExOjBasDvo mapSaveReqToWctaContractExOjBasDvo(SaveReq dto);

    WctaContractExOjDtlDvo mapSaveReqToWctaContractExOjDtlDvo(SaveReq dto);
}
