package com.kyowon.sms.wells.web.contract.interfaces.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.interfaces.converter.WctiContractDetailSummaryConverter;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractDetailSummaryDto.FindReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractDetailSummaryDto.FindRes;
import com.kyowon.sms.wells.web.contract.interfaces.mapper.WctiContractDetailSummaryMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctiContractDetailSummaryService {

    private final WctiContractDetailSummaryMapper mapper;
    private final WctiContractDetailSummaryConverter converter;

    public FindRes getDetailSummary(FindReq dto) {
        return converter.mapWctiContractDetailSummaryDvoToFindRes(mapper.selectDetailSummary(dto));
    }
}
