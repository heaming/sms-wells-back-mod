package com.kyowon.sms.wells.web.contract.changeorder.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbRentalMshSmdCstUseCtDvo;
import com.kyowon.sms.wells.web.contract.changeorder.mapper.WctbRentalMshSmdCstUseCtMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctbRentalMshSmdCstUseCtService {
    private final WctbRentalMshSmdCstUseCtMapper mapper;

    public WctbRentalMshSmdCstUseCtDvo getRentalMshSmdCstUseCt(WctbRentalMshSmdCstUseCtDvo dvo) {
        return mapper.selectRentalMshSmdCstUseCt(dvo);
    }
}
