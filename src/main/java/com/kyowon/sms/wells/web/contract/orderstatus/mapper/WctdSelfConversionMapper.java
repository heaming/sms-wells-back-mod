package com.kyowon.sms.wells.web.contract.orderstatus.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import static com.kyowon.sms.wells.web.contract.orderstatus.dto.WctdSelfConversionDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WctdSelfConversionMapper {

    PagingResult<SearchRes> selectSelfConversionPages(
        SearchReq dto,
        PageInfo pageInfo
    );

    List<SearchRes> selectSelfConversionPages(
        SearchReq dto
    );
}
