package com.kyowon.sms.wells.web.contract.interfaces.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiMembershipContactDto.SearchReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiMembershipContactDto.SearchRes;
import com.kyowon.sms.wells.web.contract.interfaces.mapper.WctiMembershipContactMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctiMembershipContactService {
    private final WctiMembershipContactMapper mapper;

    /**
    * 멤버십 계약 접수 현황 조회
    * -   고객센터 아웃바운드운영팀에서 멤버십 계약 접수 컨택을 진행하는 건들을 조회한다.
    * @interfaceId   EAI_WSSI1076
    * @programId   W-SS-I-0033
    * @param       dto
    * @return      list
    */
    public List<SearchRes> getMembershipContracts(SearchReq dto) {
        if (StringUtils.isEmpty(dto.cntrCnfmDtFr()) && StringUtils.isEmpty(dto.slDtFr())
            && StringUtils.isEmpty(dto.cntrCnfmDtFr())) {
            return null;
        }
        if ((StringUtils.isEmpty(dto.cntrCnfmDtFr()) && StringUtils.isNotEmpty(dto.cntrCnfmDtTo()))
            || (StringUtils.isNotEmpty(dto.cntrCnfmDtFr()) && StringUtils.isEmpty(dto.cntrCnfmDtTo()))) {
            return null;
        }
        if ((StringUtils.isEmpty(dto.slDtFr()) && StringUtils.isNotEmpty(dto.slDtTo()))
            || (StringUtils.isNotEmpty(dto.slDtFr()) && StringUtils.isEmpty(dto.slDtTo()))) {
            return null;
        }
        return mapper.selectMembershipContracts(dto);
    }
}
