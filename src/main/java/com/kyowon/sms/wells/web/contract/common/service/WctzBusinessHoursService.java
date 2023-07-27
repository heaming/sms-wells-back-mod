package com.kyowon.sms.wells.web.contract.common.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.common.mapper.WctzBusinessHoursMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctzBusinessHoursService {
    private final WctzBusinessHoursMapper mapper;

    public Boolean getIsBusinessClosedHours() {
        return !mapper.selectIsBusinessClosedHours();
    }
}
