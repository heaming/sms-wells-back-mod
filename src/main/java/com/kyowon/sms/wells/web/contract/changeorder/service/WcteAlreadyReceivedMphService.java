package com.kyowon.sms.wells.web.contract.changeorder.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.changeorder.mapper.WcteAlreadyReceivedMphMapper;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WcteAlreadyReceivedMphService {

    private final WcteAlreadyReceivedMphMapper mapper;

    public String getAlreadyReceivedMph(String cntrNo, String cntCstNo) {

        BizAssert.hasText(cntrNo, "MSG_ALT_CHK_CNTR_NO"); //계약번호를 확인해주세요.

        BizAssert.hasText(cntCstNo, "MSG_ALT_CHK_CNTR_CST_NO"); //계약고객번호를 확인해주세요.

        String cralLocapaMexnoIdvTno = mapper.selectAlreadyReceivedMph(cntrNo, cntCstNo);
        return cralLocapaMexnoIdvTno;
    }
}
