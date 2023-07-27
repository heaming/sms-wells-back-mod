package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaRentalAccountMgtDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaRentalAccountMgtMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaRentalAccountMgtService {
    private final WctaRentalAccountMgtMapper mapper;

    public List<WctaRentalAccountMgtDto.SearchBpdRentalAccountRes> getBpdRentalAccount(
        WctaRentalAccountMgtDto.SearchBpdRentalAccountReq dto
    ) {
        return mapper.selectBpdRentalAccount(dto);
    }

    public List<WctaRentalAccountMgtDto.SearchByoRentalAccountRes> getByoRentalAccount(
        WctaRentalAccountMgtDto.SearchByoRentalAccountReq dto
    ) {
        return mapper.selectByoRentalAccount(dto);
    }
}
