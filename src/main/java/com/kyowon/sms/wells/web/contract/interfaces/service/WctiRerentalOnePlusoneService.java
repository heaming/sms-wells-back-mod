package com.kyowon.sms.wells.web.contract.interfaces.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiRerentalOnplusoneDto.SearchReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiRerentalOnplusoneDto.SearchRes;
import com.kyowon.sms.wells.web.contract.interfaces.mapper.WctiRerentalOnplusoneMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctiRerentalOnePlusoneService {
    private final WctiRerentalOnplusoneMapper mapper;

    /**
     *
     *
     * @programid EAI_WSSI1058 wells 재렌탈, 1+1 정보 조회
     * @param  req
     * @return list
     */
    public List<SearchRes> getRerentalOneplusones(SearchReq req) {
        return mapper.selectRerentalOneplusones(req);
    }
}
