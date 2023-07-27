package com.kyowon.sms.wells.web.contract.interfaces.mapper;

import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiShippingProductHistoryDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiShippingProductHistoryDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WctiShippingProductHistoryMapper {

    List<SearchRes> selectShippingProductHistories(SearchReq dto);
}
