package com.kyowon.sms.wells.web.contract.changeorder.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctaChdvcRsgTpInqrDto.SearchRes;
import com.kyowon.sms.wells.web.contract.changeorder.mapper.WctaChdvcRsgTpInqrMapper;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaChdvcRsgTpInqrService {

    private final WctaChdvcRsgTpInqrMapper mapper;

    @Transactional
    public List<SearchRes> getChdvcRsgTpInqr(String ojCntrNo, String ojCntrSn) {

        if (StringUtil.isEmpty(ojCntrNo)) {
            throw new BizException("MSG_ALT_CHK_CNTR_NO"); //계약번호를 확인해주세요.
        }

        if (StringUtil.isEmpty(ojCntrSn)) {
            throw new BizException("MSG_ALT_CHK_CNTR_SN"); //계약일련번호를 확인해주세요.
        }

        return mapper.selectChdvcRsgTpInqr(ojCntrNo, ojCntrSn);
    }
}
