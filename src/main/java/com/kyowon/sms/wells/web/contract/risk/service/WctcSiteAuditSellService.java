package com.kyowon.sms.wells.web.contract.risk.service;

import static com.kyowon.sms.wells.web.contract.risk.dto.WctcSiteAuditSellDto.*;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.risk.mapper.WctcSiteAuditSellMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctcSiteAuditSellService {

    private final WctcSiteAuditSellMapper mapper;

    public PagingResult<SearchRes> getSiteAuditSellPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectSiteAuditSellPages(dto, pageInfo);
    }

    public List<SearchDetailRes> getSiteAuditSellsForExcelDownload(
        SearchReq dto
    ) {
        return mapper.selectSiteAuditSellsForExcelDownload(dto);
    }

}
