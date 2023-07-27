package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaEstimateCcamDto.SearchReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaEstimateCcamDto.SearchRes;

@Mapper
public interface WctaEstimateCcamMapper {
    List<SearchRes> selectEstimateCcam(SearchReq dto);

}
