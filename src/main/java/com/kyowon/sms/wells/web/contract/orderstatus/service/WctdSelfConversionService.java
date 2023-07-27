package com.kyowon.sms.wells.web.contract.orderstatus.service;

import com.kyowon.sms.wells.web.contract.orderstatus.mapper.WctdSelfConversionMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kyowon.sms.wells.web.contract.orderstatus.dto.WctdSelfConversionDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.orderstatus.dto.WctdSelfConversionDto.SearchRes;

@Service
@RequiredArgsConstructor
public class WctdSelfConversionService {

    private final WctdSelfConversionMapper mapper;

    public PagingResult<SearchRes> getSelfConversionPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectSelfConversionPages(dto, pageInfo);
    }

    public List<SearchRes> getSelfConversionsForExcelDownload(SearchReq dto) {
        return mapper.selectSelfConversionPages(dto);
    }
}
