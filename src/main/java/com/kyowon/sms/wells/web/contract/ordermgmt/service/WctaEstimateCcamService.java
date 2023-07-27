package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaEstimateCcamDto.SearchReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaEstimateCcamDto.SearchRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaEstimateCcamMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaEstimateCcamService {

    private final WctaEstimateCcamMapper mapper;

    public List<SearchRes> getEstimateCcam(SearchReq dto) {
        return mapper.selectEstimateCcam(dto);
    }
}
