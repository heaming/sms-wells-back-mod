package com.kyowon.sms.wells.web.contract.orderstatus.mapper;

import static com.kyowon.sms.wells.web.contract.orderstatus.dto.WctdNewChangeMachinePerfDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.orderstatus.dto.WctdNewChangeMachinePerfDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WctdNewChangeMachinePerfMapper {

    PagingResult<SearchRes> selectNewChangeMachinePerfPages(
            SearchReq dto,
            PageInfo pageInfo
    );

    List<SearchRes> selectNewChangeMachinePerfPages(
            SearchReq dto
    );

    SearchRes selectNewChangeMachinePerfSumm(SearchReq dto);
}
