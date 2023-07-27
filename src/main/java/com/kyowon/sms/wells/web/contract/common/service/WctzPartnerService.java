package com.kyowon.sms.wells.web.contract.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.common.dto.WctzPartnerDto.*;
import com.kyowon.sms.wells.web.contract.common.mapper.WctzPartnerMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctzPartnerService {

    private final WctzPartnerMapper mapper;

    public List<SearchEntrepreneurBaseRes> getEntrepreneurBases(String bzrno) {
        return mapper.selectEntrepreneurBases(bzrno);
    }

    public List<SearchGeneralDivisionsRes> getGeneralDivisions(String baseYm) {
        return mapper.selectGeneralDivisions(baseYm);
    }

    public List<SearchRegionalDivisionsRes> getRegionalDivisions(String baseYm) {
        return mapper.selectRegionalDivisions(baseYm);
    }

    public List<SearchBranchDivisionsRes> getBranchDivisions(String baseYm) {
        return mapper.selectBranchDivisions(baseYm);
    }

    public boolean isPartnerStpa(String prtnrNo, String ogTpCd) {
        return mapper.isPartnerStpa(prtnrNo, ogTpCd);
    }

    public FindPrtnrRes selectPrtnrInfo(String prtnrNo, String ogTpCd) {
        return mapper.selectPrtnrInfo(prtnrNo, ogTpCd);
    }
}
