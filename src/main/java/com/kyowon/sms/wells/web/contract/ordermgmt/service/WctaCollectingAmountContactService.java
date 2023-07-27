package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaCollectingAmountContactDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaCollectingAmountContactMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaCollectingAmountContactService {

    private final WctaCollectingAmountContactMapper mapper;

    public List<WctaCollectingAmountContactDto.SearchRes> getCollectingAmountContacts(String cntrNo, String cntrSn) {
        return mapper.selectCollectingAmountContacts(cntrNo, cntrSn);
    }
}
