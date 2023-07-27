package com.kyowon.sms.wells.web.contract.interfaces.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractDetailDto.SearchReq;
import com.kyowon.sms.wells.web.contract.interfaces.dvo.WctiContractDetailDvo;

@Mapper
public interface WctiContractDetailMapper {
    List<WctiContractDetailDvo> selectContractDetails(SearchReq req);
}
