package com.kyowon.sms.wells.web.contract.interfaces.service;

import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiShippingProductHistoryDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiShippingProductHistoryDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.interfaces.mapper.WctiShippingProductHistoryMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctiShippingProductHistoryService {

    private final WctiShippingProductHistoryMapper mapper;

    public List<SearchRes> getShippingProductHistories(SearchReq dto) {
        return mapper.selectShippingProductHistories(dto);
    }
}
