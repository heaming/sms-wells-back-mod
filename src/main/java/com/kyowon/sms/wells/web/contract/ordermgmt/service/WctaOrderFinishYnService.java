package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaOrderFinishYnDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaOrderFinishYnMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaOrderFinishYnService {
    private final WctaOrderFinishYnMapper mapper;

    public WctaOrderFinishYnDvo getOrderFinishYn(String cntrNo, String cntrSn, String baseYm) {
        return mapper.selectOrderFinishYn(cntrNo, cntrSn, baseYm);
    }
}
