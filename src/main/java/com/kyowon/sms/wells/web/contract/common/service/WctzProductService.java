package com.kyowon.sms.wells.web.contract.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.common.dto.WctzProductDto.SearchRes;
import com.kyowon.sms.wells.web.contract.common.mapper.WctzProductMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctzProductService {
    private final WctzProductMapper mapper;

    public List<SearchRes> getHighClasses() {
        return mapper.selectHighClasses();
    }

    public List<SearchRes> getMiddleClasses() {
        return mapper.selectMiddleClasses();
    }

}
