package com.kyowon.sms.wells.web.contract.interfaces.service;

import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractProductDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractProductDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.interfaces.mapper.WctiContractProductMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctiContractProductService {
    private final WctiContractProductMapper mapper;

    /**
    * 상품정보 조회
    * @param       req
    * @return      list
    */
    public List<SearchRes> selectContractProduct(SearchReq dto) {
        return mapper.selectContractProduct(dto);
    }
}
