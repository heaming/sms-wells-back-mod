package com.kyowon.sms.wells.web.contract.orderstatus.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.orderstatus.mapper.WctdHoldingCustomerMapper;

import static com.kyowon.sms.wells.web.contract.orderstatus.dto.WctdHoldingCustomerDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctdHoldingCustomerService {
    private final WctdHoldingCustomerMapper mapper;

    public PagingResult<SearchRes> getHoldingCustomerPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectHoldingCustomerPages(dto, pageInfo);
    }

    public List<SearchRes> getHoldingCustomers(SearchReq dto) {
        return mapper.selectHoldingCustomerPages(dto);
    }
}
