package com.kyowon.sms.wells.web.contract.interfaces.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiRentalContractsDto;
import com.kyowon.sms.wells.web.contract.interfaces.dvo.WctiRentalContractsDvo;

@Mapper(componentModel = "spring")
public interface WctiRentalContractsConverter {
    WctiRentalContractsDto.SearchRes mapWctiRentalContractsDvoToSearchRes(WctiRentalContractsDvo dvo);
}
