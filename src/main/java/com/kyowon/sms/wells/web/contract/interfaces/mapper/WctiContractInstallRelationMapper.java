package com.kyowon.sms.wells.web.contract.interfaces.mapper;

import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractInstallRelationDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractInstallRelationDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WctiContractInstallRelationMapper {
    List<SearchRes> selectRelatedContracts(SearchReq dto);
}
