package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaRentalProductChngPsMapper;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaRentalProductChngPsDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaRentalProductChngPsService {

    private final WctaRentalProductChngPsMapper mapper;

    public PagingResult<SearchRes> getRentalProductChngPsPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectRentalProductChngPss(dto, pageInfo);
    }

    public List<SearchRes> getRentalProductChngPssForExcelDownload(SearchReq dto) {
        return mapper.selectRentalProductChngPss(dto);
    }
}
