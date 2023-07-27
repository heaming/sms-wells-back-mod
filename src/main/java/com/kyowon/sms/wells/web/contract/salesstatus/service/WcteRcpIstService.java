package com.kyowon.sms.wells.web.contract.salesstatus.service;

import static com.kyowon.sms.wells.web.contract.salesstatus.dto.WcteRcpIstDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.salesstatus.dto.WcteRcpIstDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.salesstatus.converter.WcteRcpIstConverter;
import com.kyowon.sms.wells.web.contract.salesstatus.mapper.WcteRcpIstMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WcteRcpIstService {

    private final WcteRcpIstMapper mapper;
    private final WcteRcpIstConverter converter;

    public PagingResult<SearchRes> getRcpIstPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectRcpIstPages(dto, pageInfo);
    }

    public List<SearchRes> getRcpIstsForExcelDownload(SearchReq dto) {
        return mapper.selectRcpIstPages(dto);
    }
}
