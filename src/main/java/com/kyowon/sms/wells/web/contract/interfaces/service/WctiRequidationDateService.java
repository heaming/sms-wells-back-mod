package com.kyowon.sms.wells.web.contract.interfaces.service;

import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiRequidationDateDto.FindReq;
import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiRequidationDateDto.FindRes;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.interfaces.mapper.WctiRequidationDateMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctiRequidationDateService {

    private final WctiRequidationDateMapper mapper;

    public FindRes getRequidationDate(FindReq dto) {
        return mapper.selectRequidationDate(dto);
    }
}
