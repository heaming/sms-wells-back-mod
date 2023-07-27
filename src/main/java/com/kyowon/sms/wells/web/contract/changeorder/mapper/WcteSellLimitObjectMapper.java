package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WcteSellLimitObjectDto.SearchRes;

@Mapper
public interface WcteSellLimitObjectMapper {
    List<SearchRes> selectCrpJLmOjRgstYnInqr(String sellLmBzrNo);
}
