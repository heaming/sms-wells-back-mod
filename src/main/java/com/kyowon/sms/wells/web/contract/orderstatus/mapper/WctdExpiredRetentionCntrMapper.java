package com.kyowon.sms.wells.web.contract.orderstatus.mapper;

import static com.kyowon.sms.wells.web.contract.orderstatus.dto.WctdExpiredRetentionCntrDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.orderstatus.dto.WctdExpiredRetentionCntrDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WctdExpiredRetentionCntrMapper {
    List<SearchRes> selectExpiredRetentionCntrs(SearchReq dto);
}
