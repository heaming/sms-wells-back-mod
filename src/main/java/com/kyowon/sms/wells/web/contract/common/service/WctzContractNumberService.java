package com.kyowon.sms.wells.web.contract.common.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.common.dto.WctzContractNumberDto.SearchRes;
import com.kyowon.sms.wells.web.contract.common.mapper.WctzContractNumberMapper;
import com.sds.sflex.common.utils.StringUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctzContractNumberService {

    private final WctzContractNumberMapper mapper;

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public SearchRes getContractNumber(String cntrNo) {
        // 새로운 계약번호, 계약일련번호 채번
        if (StringUtil.isEmpty(cntrNo)) {
            return mapper.selectNewContractNumber();
        }
        // 기존 계약번호에서 계약일련번호 채번
        else {
            return mapper.selectNewContractSn(cntrNo);
        }
    }
}
