package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbMachineChangeCstInfDto;

@Mapper
public interface WctbMachineChangeCstInfMapper {
    WctbMachineChangeCstInfDto.SearchRes selectMachineChangeCstInf(String baseCntrNo, String baseCntrSn);
}
