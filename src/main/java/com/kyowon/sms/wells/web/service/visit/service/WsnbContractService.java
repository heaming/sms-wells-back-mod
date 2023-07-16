package com.kyowon.sms.wells.web.service.visit.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.dvo.WsnbContractDvo;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbContractMapper;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsnbContractService {

    private final WsnbContractMapper mapper;

    public WsnbContractDvo getContract(String cntrNo, String cntrSn) {
        return mapper.selectContract(cntrNo, cntrSn).orElseThrow(() -> new BizException("MSG_ALT_NO_DATA"));
    }
}
