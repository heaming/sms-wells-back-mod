package com.kyowon.sms.wells.web.contract.interfaces.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.interfaces.converter.WctiContractInstallHistoryConverter;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractInstallHistoryDto.SearchReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractInstallHistoryDto.SearchRes;
import com.kyowon.sms.wells.web.contract.interfaces.mapper.WctiContractInstallHistoryMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WctiContractInstallHistoryService {
    private final WctiContractInstallHistoryMapper mapper;
    private final WctiContractInstallHistoryConverter converter;

    /**
     * 계약처, 설치처 정보 변경 이력 조회
     * @programId   EAI_WSSI1052
     * @param       req
     * @return      list
     */
    public List<SearchRes> getIstlcChHist(SearchReq req) {
        return converter.mapWctiContractInstallHistoryDvoToSearchRes(mapper.selectIstlcChHist(req));
    }
}
