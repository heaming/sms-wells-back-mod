package com.kyowon.sms.wells.web.contract.interfaces.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractDetailSummaryDto.FindReq;
import com.kyowon.sms.wells.web.contract.interfaces.dvo.WctiContractDetailSummaryDvo;

@Mapper
public interface WctiContractDetailSummaryMapper {

    WctiContractDetailSummaryDvo selectDetailSummary(FindReq dto);
}
