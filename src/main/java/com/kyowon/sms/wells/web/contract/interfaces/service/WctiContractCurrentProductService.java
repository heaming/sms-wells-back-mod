package com.kyowon.sms.wells.web.contract.interfaces.service;

import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractCurrentProductDto.FindReq;
import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractCurrentProductDto.FindRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.interfaces.mapper.WctiContractCurrentProductMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctiContractCurrentProductService {

    private final WctiContractCurrentProductMapper mapper;

    public List<FindRes> getContractCurrentProduct(FindReq dto) {
        return mapper.selectContractCurrentProduct(dto);
    }
}
