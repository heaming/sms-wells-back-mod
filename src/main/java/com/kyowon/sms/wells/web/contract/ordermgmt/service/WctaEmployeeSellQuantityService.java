package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaEmployeeSellQuantityDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaEmployeeSellQuantityMapper;
import com.sds.sflex.system.config.validation.ValidAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaEmployeeSellQuantityService {
    private final WctaEmployeeSellQuantityMapper mapper;

    public WctaEmployeeSellQuantityDvo getEmployeeSellQuantity(WctaEmployeeSellQuantityDvo dvo) {
        ValidAssert.hasText(dvo.getRcpMm());
        ValidAssert.hasText(dvo.getContractFromYm());
        ValidAssert.hasText(dvo.getContractToYm());
        ValidAssert.hasText(dvo.getSellPartnerNo());
        ValidAssert.hasText(dvo.getContractCustomerNo());
        ValidAssert.hasText(dvo.getBaseProductCd());
        return mapper.selectEmployeeSellQuantity(dvo);
    }
}
