package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import com.sds.sflex.system.config.datasource.PageInfo;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailLinkProductDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaOrderDetailLinkProductMapper;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaOrderDetailLinkProductService {

    private final WctaOrderDetailLinkProductMapper mapper;

    public PagingResult<WctaOrderDetailLinkProductDto.SearchRes> getLinkProducts(
        String cntrNo, String cntrSn, PageInfo pageInfo
    ) {
        return mapper.selectLinkProducts(cntrNo, cntrSn, pageInfo);
    }
}
