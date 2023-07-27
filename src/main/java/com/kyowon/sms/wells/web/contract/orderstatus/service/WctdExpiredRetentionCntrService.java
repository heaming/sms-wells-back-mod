package com.kyowon.sms.wells.web.contract.orderstatus.service;

import static com.kyowon.sms.wells.web.contract.orderstatus.dto.WctdExpiredRetentionCntrDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.orderstatus.dto.WctdExpiredRetentionCntrDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.orderstatus.mapper.WctdExpiredRetentionCntrMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctdExpiredRetentionCntrService {
    private final WctdExpiredRetentionCntrMapper mapper;

    public List<SearchRes> getExpiredRetentionCntrs(SearchReq dto) {
        return mapper.selectExpiredRetentionCntrs(dto);
    }
}
