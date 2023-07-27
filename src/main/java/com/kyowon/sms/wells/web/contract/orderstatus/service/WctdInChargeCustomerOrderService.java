package com.kyowon.sms.wells.web.contract.orderstatus.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.orderstatus.converter.WctdInChargeCustomerOrderConverter;
import com.kyowon.sms.wells.web.contract.orderstatus.dto.WctdInChargeCustomerOrderDto.SearchReq;
import com.kyowon.sms.wells.web.contract.orderstatus.dto.WctdInChargeCustomerOrderDto.SearchRes;
import com.kyowon.sms.wells.web.contract.orderstatus.mapper.WctdInChargeCustomerOrderMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctdInChargeCustomerOrderService {

    private final WctdInChargeCustomerOrderMapper mapper;
    private final WctdInChargeCustomerOrderConverter converter;

    public PagingResult<SearchRes> getInChargeCustomerOrderPages(SearchReq dto, PageInfo pageInfo) {
        PagingResult<SearchRes> searchRes = converter.mapWctdInChargeCustomerOrderDvoToSearchRes(
            mapper.selectInChargeCustomerOrderPages(converter.mapSearchReqToWctdInChargeCustomerOrderDvo(dto), pageInfo)
        );
        searchRes.setPageInfo(pageInfo);

        return searchRes;
    }

    public List<SearchRes> getInChargeCustomerOrdersForExcelDownload(SearchReq dto) {
        return converter.mapWctdInChargeCustomerOrderDvoToSearchRes(
                mapper.selectInChargeCustomerOrderPages(converter.mapSearchReqToWctdInChargeCustomerOrderDvo(dto))
        );
    }
}
