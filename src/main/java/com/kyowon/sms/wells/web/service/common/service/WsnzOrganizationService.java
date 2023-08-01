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

    public List<SearchManagerOgRes> getGeneralDivisions(String authYn) {
        return mapper.selectGeneralDivisions(authYn);
    }

    public List<SearchManagerOgRes> getRegionalGroups(String ogId, String authYn) {
        return mapper.selectRegionalGroups(ogId, authYn);
    }

    public List<SearchManagerOgRes> getBranchs(String ogId, String authYn) {
        return mapper.selectBranchs(ogId, authYn);
    }

    public List<SearchManagerRes> getManagers(SearchPrtnrReq dto) {
        return mapper.selectManagers(dto);
    }

    public List<SearchEngineerOgRes> getServiceCenters(String authYn) {
        return mapper.selectServiceCenters(authYn);
    }

    public List<SearchEngineerRes> getEngineers(SearchPrtnrReq dto) {
        return mapper.selectEngineers(dto);
    }
}
