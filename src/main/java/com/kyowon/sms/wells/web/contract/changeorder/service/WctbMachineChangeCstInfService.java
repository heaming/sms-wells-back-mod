package com.kyowon.sms.wells.web.contract.changeorder.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbMachineChangeCstInfDto;
import com.kyowon.sms.wells.web.contract.changeorder.mapper.WctbMachineChangeCstInfMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctbMachineChangeCstInfService {
    private final WctbMachineChangeCstInfMapper mapper;

    public WctbMachineChangeCstInfDto.SearchRes getMachineChangeCstInf(String baseCntrNo, String baseCntrSn) {

        return mapper.selectMachineChangeCstInf(baseCntrNo, baseCntrSn);
    }
}
