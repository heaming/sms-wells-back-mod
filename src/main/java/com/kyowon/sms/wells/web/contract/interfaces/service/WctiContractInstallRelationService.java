package com.kyowon.sms.wells.web.contract.interfaces.service;

import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractInstallRelationDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractInstallRelationDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.interfaces.mapper.WctiContractInstallRelationMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctiContractInstallRelationService {

    private final WctiContractInstallRelationMapper mapper;

    public List<SearchRes> getRelatedContracts(SearchReq dto) { return mapper.selectRelatedContracts(dto); }
}
