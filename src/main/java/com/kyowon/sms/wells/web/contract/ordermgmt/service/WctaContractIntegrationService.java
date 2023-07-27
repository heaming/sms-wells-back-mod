package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractIntegrationDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractIntegrationDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.ordermgmt.converter.WctaContractIntegrationConverter;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaContractIntegrationPagesRequestDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaContractIntegrationMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WctaContractIntegrationService {
    private final WctaContractIntegrationMapper mapper;
    private final WctaContractIntegrationConverter converter;

    public PagingResult<SearchRes> getContractIntegrationsPages(SearchReq dto, PageInfo pageInfo) {
        WctaContractIntegrationPagesRequestDvo dvo = converter.mapSearchReqToWctaContractIntegrationPagesDvo(dto);

        PagingResult<SearchRes> pagingResultDto = converter
            .mapWctaContractIntegrationPagesDvoToSearchRes(mapper.selectContractIntegrationsPages(dvo, pageInfo));
        pagingResultDto.setPageInfo(pageInfo);

        return pagingResultDto;
    }

    public List<SearchRes> getContractIntegrationsExcels(SearchReq dto) {
        return converter
            .mapWctaContractIntegrationPagesExcelDvoToSearchRes(mapper.selectContractIntegrationsPages(dto));
    }
}
