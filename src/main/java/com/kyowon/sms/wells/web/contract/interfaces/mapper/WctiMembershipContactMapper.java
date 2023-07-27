package com.kyowon.sms.wells.web.contract.interfaces.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiMembershipContactDto.SearchReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiMembershipContactDto.SearchRes;

@Mapper
public interface WctiMembershipContactMapper {
    List<SearchRes> selectMembershipContracts(SearchReq dto);
}
