package com.kyowon.sms.wells.web.contract.changeorder.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctaDeviceChangeCancelTypeDto.SearchRes;
import com.kyowon.sms.wells.web.contract.changeorder.mapper.WctaDeviceChangeCancelTypeMapper;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaDeviceChangeCancelTypeService {

    private final WctaDeviceChangeCancelTypeMapper mapper;

    public List<SearchRes> getDeivceChangeCancelType(String ojCntrNo, String ojCntrSn) {

        BizAssert.hasText(ojCntrNo, "MSG_ALT_CHK_CNTR_NO"); //계약번호를 확인해주세요.

        BizAssert.hasText(ojCntrSn, "MSG_ALT_CHK_CNTR_SN"); //계약일련번호를 확인해주세요.

        return mapper.selectDeivceChangeCancelType(ojCntrNo, ojCntrSn);
    }
}
