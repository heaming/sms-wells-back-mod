package com.kyowon.sms.wells.web.service.common.service;

import static com.kyowon.sms.wells.web.service.common.dto.WsnzOrganizationDto.*;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.common.mapper.WsnzOrganizationMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnzOrganizationService {
    private final WsnzOrganizationMapper mapper;

    public List<SearchManagerOgRes> getGeneralDivisions() {
        return mapper.selectGeneralDivisions();
    }

    public List<SearchManagerOgRes> getRegionalGroups(String ogId) {
        return mapper.selectRegionalGroups(ogId);
    }

    public List<SearchManagerOgRes> getBranchs(String ogId) {
        return mapper.selectBranchs(ogId);
    }

    public List<SearchManagerRes> getManagers(SearchPrtnrReq dto) {
        return mapper.selectManagers(dto);
    }

    public List<SearchEngineerOgRes> getServiceCenters() {
        return mapper.selectServiceCenters();
    }

    public List<SearchEngineerRes> getEngineers(SearchPrtnrReq dto) {
        return mapper.selectEngineers(dto);
    }
}
