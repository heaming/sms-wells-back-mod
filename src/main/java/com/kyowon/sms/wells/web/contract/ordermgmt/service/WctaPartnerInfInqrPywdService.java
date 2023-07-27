package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaPartnerInfInqrPywdDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaPartnerInfInqrPywdMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WctaPartnerInfInqrPywdService {
    private final WctaPartnerInfInqrPywdMapper mapper;

    public WctaPartnerInfInqrPywdDvo getPartnerInfInqrPywd(String cntrNo) {

        return mapper.selectPartnerInfInqrPywd(cntrNo);
    }
}
