package com.kyowon.sms.wells.web.contract.risk.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.contract.risk.dto.WctcSalesLimitsDto.SaveBlacklistReq;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcSalesLimitsDto.SaveEntrpJLmOjReq;
import com.kyowon.sms.wells.web.contract.risk.dvo.WctcSellLimitOjIzDvo;

@Mapper(componentModel = "spring")
public interface WctcSalesLimitsConverter {
    @Mapping(source = "fnlMdfcDtm", target = "orglFnlMdfcDtm")
    @Mapping(source = "cntrNo", target = "sellLmCntrNo")
    @Mapping(source = "cntrSn", target = "sellLmCntrSn")
    WctcSellLimitOjIzDvo mapSaveBlacklistReqToWctcSellLimitOjIzDvo(SaveBlacklistReq dto);

    WctcSellLimitOjIzDvo mapSaveEntrpJLmOjReqToDvo(SaveEntrpJLmOjReq dto);

    SaveEntrpJLmOjReq mapSaveEntrpJLmOjReqToDvoToSaveEntrpJLmOjReq(WctcSellLimitOjIzDvo dvo);
}
