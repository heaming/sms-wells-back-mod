package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaFilterCntrInfDtlInqrMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaFilterCntrInfDtlInqrDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaFilterCntrInfDtlInqrDto.SearchRes;

@Service
@RequiredArgsConstructor
public class WctaFilterCntrInfDtlInqrService {

    private final WctaFilterCntrInfDtlInqrMapper mapper;

    public PagingResult<SearchRes> getFilterCntrInfDtlInqrPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectFilterCntrInfDtlInqrPages(dto, pageInfo);
    }

}
