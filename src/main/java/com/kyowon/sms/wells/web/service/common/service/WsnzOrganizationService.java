package com.kyowon.sms.wells.web.service.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.common.dto.WsnzOrganizationDto.SearchOrganizationRes;
import com.kyowon.sms.wells.web.service.common.dto.WsnzOrganizationDto.SearchPrtnrReq;
import com.kyowon.sms.wells.web.service.common.dto.WsnzOrganizationDto.SearchPrtnrRes;
import com.kyowon.sms.wells.web.service.common.mapper.WsnzOrganizationMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnzOrganizationService {
    private final WsnzOrganizationMapper mapper;

    public List<SearchOrganizationRes> getGeneralDivisions() {
        return mapper.selectGeneralDivisions();
    }

    public List<SearchOrganizationRes> getRegionalGroups(String ogId) {
        return mapper.selectRegionalGroups(ogId);
    }

    public List<SearchOrganizationRes> getBranchs(String ogId) {
        return mapper.selectBranchs(ogId);
    }

    public List<SearchPrtnrRes> getManagers(SearchPrtnrReq dto) {
        return mapper.selectManagers(dto);
    }
}
