package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WctbDateCalculationMapper {
    String selectDateBusinessDayCalc(String date, String day);
}
