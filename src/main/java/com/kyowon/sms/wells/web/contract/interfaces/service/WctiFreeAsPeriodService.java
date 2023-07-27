package com.kyowon.sms.wells.web.contract.interfaces.service;

import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiFreeAsPeriodDto.FindReq;
import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiFreeAsPeriodDto.FindRes;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.interfaces.mapper.WctiFreeAsPeriodMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctiFreeAsPeriodService {

    private final WctiFreeAsPeriodMapper mapper;

    public FindRes getFreeAsPeriod(FindReq dto) {
        FindRes res = mapper.selectFreeAsPeriodFromEx(dto);

        if(null == res)
            res = mapper.selectFreeAsPeriod(dto);

        return res;
    }
}
