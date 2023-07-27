package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaProductEmpPrchsCountDto.SearchReq;

@Mapper
public interface WctaProductEmpPrchsCountMapper {
    int selectProductEmpPrchsCount(SearchReq req);
}
