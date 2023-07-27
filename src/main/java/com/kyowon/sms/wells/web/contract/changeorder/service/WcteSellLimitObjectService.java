package com.kyowon.sms.wells.web.contract.changeorder.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WcteSellLimitObjectDto.SearchRes;
import com.kyowon.sms.wells.web.contract.changeorder.mapper.WcteSellLimitObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WcteSellLimitObjectService {

    private final WcteSellLimitObjectMapper mapper;

    @Transactional
    public List<SearchRes> getCrpJLmOjRgstYnInqr(String sellLmBzrNo) {
        return mapper.selectCrpJLmOjRgstYnInqr(sellLmBzrNo);
    }
}
