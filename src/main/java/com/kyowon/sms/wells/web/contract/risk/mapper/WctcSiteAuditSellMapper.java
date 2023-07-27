package com.kyowon.sms.wells.web.contract.risk.mapper;

import static com.kyowon.sms.wells.web.contract.risk.dto.WctcSiteAuditSellDto.*;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WctcSiteAuditSellMapper {

    PagingResult<SearchRes> selectSiteAuditSellPages(
        SearchReq dto,
        PageInfo pageInfo
    );

    List<SearchDetailRes> selectSiteAuditSellsForExcelDownload(SearchReq dto);
}
