package com.kyowon.sms.wells.web.contract.interfaces.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractRelationDto.SearchReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractRelationDto.SearchRes;

@Mapper
public interface WctiContractRelationMapper {
    List<SearchRes> selectRelatedContracts(SearchReq req);
}
