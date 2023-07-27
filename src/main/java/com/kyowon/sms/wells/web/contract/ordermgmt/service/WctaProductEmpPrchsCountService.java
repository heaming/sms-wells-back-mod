package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaProductEmpPrchsCountDto.SearchReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaProductEmpPrchsCountMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaProductEmpPrchsCountService {
    private final WctaProductEmpPrchsCountMapper mapper;

    public int getProductEmpPrchsCount(SearchReq dto) {
        return mapper.selectProductEmpPrchsCount(dto);
    }

}
