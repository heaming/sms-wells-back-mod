package com.kyowon.sms.wells.web.contract.interfaces.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiCustomerDto;
import com.kyowon.sms.wells.web.contract.interfaces.dvo.WctiCustomerDvo;

@Mapper(componentModel = "spring")
public interface WctiCustomerConverter {
    WctiCustomerDvo mapSearchReqToWctiCustomerDvo(WctiCustomerDto.SearchReq dto);

    List<WctiCustomerDto.SearchRes> mapAllWctiCustomerDvoToSearchRes(List<WctiCustomerDvo> dvo);
}
