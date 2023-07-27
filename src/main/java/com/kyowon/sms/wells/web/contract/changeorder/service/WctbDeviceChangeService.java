package com.kyowon.sms.wells.web.contract.changeorder.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbDeviceChangeDvo;
import com.kyowon.sms.wells.web.contract.changeorder.mapper.WctbDeviceChangeMapper;
import com.sds.sflex.system.config.validation.ValidAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctbDeviceChangeService {
    private final WctbDeviceChangeMapper mapper;

    public List<WctbDeviceChangeDvo> getDeviceChanges(String cntrNo) {
        ValidAssert.hasText(cntrNo);
        return mapper.selectDeviceChanges(cntrNo);
    }
}
