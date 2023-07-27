package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaEntrepreneurCustomerPssDto.SearchReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaEntrepreneurCustomerPssDto.SearchRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WctaEntrepreneurCustomerPssMapper {
    PagingResult<SearchRes> selectEntrepreneurCustomerPss(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectEntrepreneurCustomerPss(SearchReq dto);
}
