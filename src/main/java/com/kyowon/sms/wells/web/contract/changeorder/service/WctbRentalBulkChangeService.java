package com.kyowon.sms.wells.web.contract.changeorder.service;

import static com.kyowon.sms.wells.web.contract.changeorder.dto.WctbRentalBulkChangeDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.changeorder.dto.WctbRentalBulkChangeDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.changeorder.converter.WctbRentalBulkChangeConverter;
import com.kyowon.sms.wells.web.contract.changeorder.mapper.WctbRentalBulkChangeMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctbRentalBulkChangeService {

    private final WctbRentalBulkChangeMapper mapper;
    private final WctbRentalBulkChangeConverter converter;

    public List<SearchRes> getRentalBulkChanges(SearchReq dto) {
        return converter.mapAllRentalBulkChangeDvoToSearchRes(mapper.selectRentalBulkChanges(dto));
    }
}



