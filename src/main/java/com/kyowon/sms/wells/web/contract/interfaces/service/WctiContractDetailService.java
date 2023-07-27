package com.kyowon.sms.wells.web.contract.interfaces.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.interfaces.converter.WctiContractDetailConverter;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractDetailDto.SearchReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractDetailDto.SearchRes;
import com.kyowon.sms.wells.web.contract.interfaces.mapper.WctiContractDetailMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WctiContractDetailService {
    private final WctiContractDetailMapper mapper;
    private final WctiContractDetailConverter converter;
    /**
     * 고객센터 내 계약상세 리스트 조회를 위한 I/F 대상 프로그램
     *
     * @programId EAI_WSSI1045
     * @param req
     * @return list
     */
    public List<SearchRes> getContractDetails(SearchReq req) {
        return converter.mapWctiContractDetailDvoToSearchRes(mapper.selectContractDetails(req));
    }
}
