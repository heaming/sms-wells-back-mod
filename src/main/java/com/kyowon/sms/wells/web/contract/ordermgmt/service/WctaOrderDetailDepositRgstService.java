package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.ordermgmt.converter.WctaOrderDetailDepositRgstConverter;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailDepositRgstDto.SearchReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailDepositRgstDto.SearchRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaOrderDetailDepositRgstMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WctaOrderDetailDepositRgstService {
    private final WctaOrderDetailDepositRgstMapper mapper;
    private final WctaOrderDetailDepositRgstConverter converter;

    public List<SearchRes> getDepositRgstIzs(SearchReq dto) {
        return converter
            .mapWctaOrderDetailDepositRgstDvoToSearchRes(mapper.selectDepositRgstIzs(dto));
    }
}
