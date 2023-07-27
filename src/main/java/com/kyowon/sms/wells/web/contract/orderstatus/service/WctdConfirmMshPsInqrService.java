package com.kyowon.sms.wells.web.contract.orderstatus.service;

import static com.kyowon.sms.wells.web.contract.orderstatus.dto.WctdConfirmMshPsInqrDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.orderstatus.dto.WctdConfirmMshPsInqrDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.orderstatus.mapper.WctdConfirmMshPsInqrMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctdConfirmMshPsInqrService {

    private final WctdConfirmMshPsInqrMapper mapper;

    public PagingResult<SearchRes> getConfirmMshPsInqrPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectConfirmMshPsInqrPages(dto, pageInfo);
    }

    public List<SearchRes> getConfirmMshPsInqrsForExcelDownload(SearchReq dto) {
        return mapper.selectConfirmMshPsInqrPages(dto);
    }
}
