package com.kyowon.sms.wells.web.contract.interfaces.service;

import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiRegularDeliveryProductDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiRegularDeliveryProductDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.interfaces.mapper.WctiRegularDeliveryProductMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctiRegularDeliveryProductService {
    private final WctiRegularDeliveryProductMapper mapper;

    public List<SearchRes> getRegularDeliveryProducts(SearchReq dto) { return mapper.selectRegularDeliveryProducts(dto); }
}
