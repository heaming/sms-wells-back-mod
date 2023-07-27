package com.kyowon.sms.wells.web.contract.interfaces.mapper;

import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiRegularDeliveryProductDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiRegularDeliveryProductDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WctiRegularDeliveryProductMapper {
    List<SearchRes> selectRegularDeliveryProducts(SearchReq dto);
}
