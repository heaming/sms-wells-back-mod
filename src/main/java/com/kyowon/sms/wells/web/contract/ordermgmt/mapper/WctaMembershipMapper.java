package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaMembershipDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaMembershipDto.SearchRes;

@Mapper
public interface WctaMembershipMapper {
    PagingResult<SearchRes> selectMembershipCustomers(
        SearchReq dto,
        PageInfo pageInfo
    );

    Integer selectMembershipCustomersCount(
        SearchReq dto
    );
}
