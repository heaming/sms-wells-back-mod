package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaInterestFreeIstmYnDto.SearchReq;

@Mapper
public interface WctaInterestFreeIstmYnMapper {
    String selectSellTypeCode(SearchReq dto);
}
