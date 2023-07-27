package com.kyowon.sms.wells.web.contract.interfaces.service;

import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiRestipulationDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiRestipulationDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.interfaces.mapper.WctiRestipulationMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctiRestipulationService {

    private final WctiRestipulationMapper mapper;

    public List<SearchRes> getRestipulations(SearchReq req) {
        return mapper.selectRestipulations(req);
    }
}
