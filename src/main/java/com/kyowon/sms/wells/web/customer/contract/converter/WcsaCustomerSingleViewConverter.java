package com.kyowon.sms.wells.web.customer.contract.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.customer.contract.dto.WcsaCustomerSingleViewDto.SearchRes;
import com.kyowon.sms.wells.web.customer.contract.dvo.WcsaCustomerDvo;

@Mapper(componentModel = "spring")
public interface WcsaCustomerSingleViewConverter {

    List<SearchRes> mapAllWcsaCustomerDvoToSearchRes(List<WcsaCustomerDvo> dvos);
}
