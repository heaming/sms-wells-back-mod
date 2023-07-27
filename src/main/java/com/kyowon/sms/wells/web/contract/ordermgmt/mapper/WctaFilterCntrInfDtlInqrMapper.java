package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaFilterCntrInfDtlInqrDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaFilterCntrInfDtlInqrDto.SearchRes;

@Mapper
public interface WctaFilterCntrInfDtlInqrMapper {

    PagingResult<SearchRes> selectFilterCntrInfDtlInqrPages(
        SearchReq dto,
        PageInfo pageInfo
    );

}
