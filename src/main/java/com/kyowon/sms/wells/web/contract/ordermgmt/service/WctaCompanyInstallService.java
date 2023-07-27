package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaCompanyInstallDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaCompanyInstallDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.ordermgmt.converter.WctaCompanyInstallConverter;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaCompanyInstallMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaCompanyInstallService {

    private final WctaCompanyInstallMapper mapper;
    private final WctaCompanyInstallConverter converter;

    public PagingResult<SearchRes> getCompanyInstallPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectCompanyInstallPages(converter.mapSearchReqToDvo(dto), pageInfo);
    }

    public List<SearchRes> getCompanyInstallsForExcelDownload(SearchReq dto) {
        return mapper.selectCompanyInstallPages(converter.mapSearchReqToDvo(dto));
    }
}
