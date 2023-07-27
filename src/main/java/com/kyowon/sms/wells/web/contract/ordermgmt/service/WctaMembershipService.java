package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaMembershipMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaMembershipDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaMembershipDto.SearchRes;

@Service
@RequiredArgsConstructor
public class WctaMembershipService {

    private final WctaMembershipMapper mapper;

    public PagingResult<SearchRes> getMembershipCustomerPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectMembershipCustomers(dto, pageInfo);
    }

    public Integer getMembershipCustomersCounts(SearchReq dto) {
        return mapper.selectMembershipCustomersCount(dto);
    }
}
