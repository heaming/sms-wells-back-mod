package com.kyowon.sms.wells.web.contract.interfaces.mapper;

import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiRentalAdditionalServiceDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiRentalAdditionalServiceDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WctiRentalAdditionalServiceMapper {

    List<SearchRes> selectRentalAdditionalServiceHistories(SearchReq dto);
}
