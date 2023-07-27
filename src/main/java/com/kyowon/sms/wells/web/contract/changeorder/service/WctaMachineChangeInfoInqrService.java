package com.kyowon.sms.wells.web.contract.changeorder.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctaMachineChangeInfoInqrDto.SearchRes;
import com.kyowon.sms.wells.web.contract.changeorder.mapper.WctaMachineChangeInfoInqrMapper;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaMachineChangeInfoInqrService {

    private final WctaMachineChangeInfoInqrMapper mapper;

    public List<SearchRes> getMachineChangeInfoInqr(String cntrNo, String cntrSn) {
        BizAssert.hasText(cntrNo, "MSG_ALT_CHK_CNTR_NO"); //계약번호를 확인해주세요.

        BizAssert.hasText(cntrSn, "MSG_ALT_CHK_CNTR_CST_NO"); //계약일련번호를 확인해주세요.

        return mapper.selectMachineChangeInfoInqr(cntrNo, cntrSn);
    }
}
