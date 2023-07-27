package com.kyowon.sms.wells.web.contract.ordermgmt.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaQuoteSendDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaQuoteSendDvo;

@Mapper(componentModel = "spring")
public interface WctaQuoteSendConverter {
    WctaQuoteSendDto.SearchInfRes mapWctaQuoteSendDvoToSearchInfRes(WctaQuoteSendDvo dvo);

    WctaQuoteSendDvo mapSaveReqToWctaQuoteSendDvo(WctaQuoteSendDto.SaveReq dto);
}
