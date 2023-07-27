package com.kyowon.sms.wells.web.contract.changeorder.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.changeorder.mapper.WctbDateCalculationMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctbDateCalculationService {
    private final WctbDateCalculationMapper mapper;

    public String getDateBusinessDayCalc(String date, String day) {
        String CalculationDay = mapper.selectDateBusinessDayCalc(date, day);
        return StringUtils.isEmpty(CalculationDay) ? "Y" : CalculationDay;
    }
}
