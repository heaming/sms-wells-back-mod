package com.kyowon.sms.wells.web.contract.ordermgmt.converter;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractSettelmentDto.FindBasicInfoRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaContractForAuthDvo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WctaContractSettlementConverter {
    FindBasicInfoRes mapWctaContractForAuthDvoToFindBasicInfoRes(WctaContractForAuthDvo dvo);
}
