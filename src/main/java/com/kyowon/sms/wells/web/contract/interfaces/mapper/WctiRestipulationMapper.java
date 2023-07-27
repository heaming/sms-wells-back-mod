package com.kyowon.sms.wells.web.contract.interfaces.mapper;

import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiRestipulationDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiRestipulationDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WctiRestipulationMapper {
    List<SearchRes> selectRestipulations(SearchReq dto);
}
