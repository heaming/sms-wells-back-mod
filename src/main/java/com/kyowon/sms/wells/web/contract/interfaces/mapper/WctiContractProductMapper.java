package com.kyowon.sms.wells.web.contract.interfaces.mapper;

import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractProductDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractProductDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WctiContractProductMapper {
    List<SearchRes> selectContractProduct(SearchReq dto);
}
