package com.kyowon.sms.wells.web.contract.interfaces.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiRerentalOnplusoneDto.SearchReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiRerentalOnplusoneDto.SearchRes;

@Mapper
public interface WctiRerentalOnplusoneMapper {
    List<SearchRes> selectRerentalOneplusones(SearchReq dto);
}
