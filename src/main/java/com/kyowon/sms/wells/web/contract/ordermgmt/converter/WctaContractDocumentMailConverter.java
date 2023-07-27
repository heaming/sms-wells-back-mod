package com.kyowon.sms.wells.web.contract.ordermgmt.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractDocumentMailDto.SaveReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaContractDocumentMailDvo;

@Mapper(componentModel = "spring")
public interface WctaContractDocumentMailConverter {
    WctaContractDocumentMailDvo saveReqToWctaContractDocumentMailDvo(SaveReq dto);

}
