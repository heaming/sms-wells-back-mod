package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaRentalProductChngPsDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WctaRentalProductChngPsMapper {

    PagingResult<SearchRes> selectRentalProductChngPss(
        SearchReq dto,
        PageInfo pageInfo
    );

    List<SearchRes> selectRentalProductChngPss(
        SearchReq dto
    );
}
