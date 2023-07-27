package com.kyowon.sms.wells.web.contract.orderstatus.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import static com.kyowon.sms.wells.web.contract.orderstatus.dto.WctdHoldingCustomerDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WctdHoldingCustomerMapper {

    PagingResult<SearchRes> selectHoldingCustomerPages(
        SearchReq dto,
        PageInfo pageInfo
    );

    List<SearchRes> selectHoldingCustomerPages(
        SearchReq dto
    );
}
