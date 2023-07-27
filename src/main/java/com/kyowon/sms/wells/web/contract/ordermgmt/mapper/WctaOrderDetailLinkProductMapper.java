package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import com.sds.sflex.system.config.datasource.PageInfo;
import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailLinkProductDto;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WctaOrderDetailLinkProductMapper {
    PagingResult<WctaOrderDetailLinkProductDto.SearchRes> selectLinkProducts(
        String cntrNo, String cntrSn, PageInfo pageInfo
    );
}
