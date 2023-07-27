package com.kyowon.sms.wells.web.contract.changeorder.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbCustomerCodeDvo;
import com.kyowon.sms.wells.web.contract.changeorder.mapper.WctbCustomerCodeMapper;
import com.sds.sflex.system.config.validation.ValidAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctbCustomerCodeService {
    private final WctbCustomerCodeMapper mapper;

    /**
     * 고객코드 검사
     *  - 요청한 계약번호의 존재여부를 확인하고,   고객명, 업무유형을 리턴한다.
     * @programId  W-SS-S-0070
     * @param  cntrNo  계약번호
     * @param  cntrSn  계약일련번호
     * @return dvo
     */
    public WctbCustomerCodeDvo getCustomerCode(String cntrNo, int cntrSn) {
        ValidAssert.hasText(cntrNo);
        ValidAssert.notNull(cntrSn);

        return mapper.selectCustomerCode(cntrNo, cntrSn);
    }
}
