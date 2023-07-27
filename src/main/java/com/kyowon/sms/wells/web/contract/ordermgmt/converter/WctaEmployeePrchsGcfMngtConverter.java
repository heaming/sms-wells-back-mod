package com.kyowon.sms.wells.web.contract.ordermgmt.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaEmployeePrchsGcfMngtDto.SearchReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaEmployeePrchsGcfMngtDto.SearchRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaEmployeePrchsGcfMngtDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaEmployeePrchsGcfMngtRequestDvo;

@Mapper(componentModel = "spring")
public interface WctaEmployeePrchsGcfMngtConverter {
    WctaEmployeePrchsGcfMngtRequestDvo mapSearchReqToWctaEmployeePrchsGcfMngtDvo(SearchReq dto);

    List<SearchRes> mapWctaEmployeePrchsGcfMngtDvoToSearchRes(List<WctaEmployeePrchsGcfMngtDvo> dvos);

}
