package com.kyowon.sms.wells.web.contract.orderstatus.service;

import static com.kyowon.sms.wells.web.contract.orderstatus.dto.WctdNewChangeMachinePerfDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.orderstatus.dto.WctdNewChangeMachinePerfDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.orderstatus.mapper.WctdNewChangeMachinePerfMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctdNewChangeMachinePerfService {

    private final WctdNewChangeMachinePerfMapper mapper;

    public PagingResult<SearchRes> getNewChangeMachinePerfPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectNewChangeMachinePerfPages(dto, pageInfo);
    }

    public List<SearchRes> getNewChangeMachinePerfsForExcelDownload(SearchReq dto) {
        return mapper.selectNewChangeMachinePerfPages(dto);
    }

    public SearchRes getNewChangeMachinePerfSumm(SearchReq dto){ return mapper.selectNewChangeMachinePerfSumm(dto); }
}
