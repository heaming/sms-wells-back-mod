package com.kyowon.sms.wells.web.contract.ordermgmt.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaSpectxBlkPrntDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaSpectxBlkPrntDto.SaveReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaSpectxBlkPrntDvo;

@Mapper(componentModel = "spring")
public interface WctaSpectxBlkPrntConverter {
    WctaSpectxBlkPrntDvo mapSaveReqToWctaSpectxBlkPrntDvo(SaveReq dto);

    WctaSpectxBlkPrntDvo mapSaveTradeSpcshFwReqToWctaSpectxBlkPrntDvo(WctaSpectxBlkPrntDto.SaveTradeSpcshFwReq dto);
}
