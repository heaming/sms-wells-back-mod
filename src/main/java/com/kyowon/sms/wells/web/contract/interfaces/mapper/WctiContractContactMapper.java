package com.kyowon.sms.wells.web.contract.interfaces.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractContactDto.SearchReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractContactDto.SearchRes;

@Mapper
public interface WctiContractContactMapper {
    List<SearchRes> selectContractContacts(SearchReq dto);
}
