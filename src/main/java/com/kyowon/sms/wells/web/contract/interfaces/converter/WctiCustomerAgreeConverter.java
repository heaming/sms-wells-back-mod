package com.kyowon.sms.wells.web.contract.interfaces.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiCustomerAgreeDto;
import com.kyowon.sms.wells.web.contract.interfaces.dvo.WctiCustomerAgreeDvo;

@Mapper(componentModel = "spring")
public interface WctiCustomerAgreeConverter {
    WctiCustomerAgreeDvo mapSaveReqToWctiCustomerAgreeDvo(WctiCustomerAgreeDto.SaveReq dto);
}
