package com.kyowon.sms.wells.web.contract.interfaces.service;

import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiRentalAdditionalServiceDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiRentalAdditionalServiceDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.interfaces.mapper.WctiRentalAdditionalServiceMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctiRentalAdditionalServiceService {

    private final WctiRentalAdditionalServiceMapper mapper;

    public List<SearchRes> getRentalAdditionalServiceHistories(SearchReq dto) { return mapper.selectRentalAdditionalServiceHistories(dto); }
}
