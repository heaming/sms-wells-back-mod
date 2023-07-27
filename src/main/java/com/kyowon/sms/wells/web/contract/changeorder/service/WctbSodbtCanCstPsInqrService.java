package com.kyowon.sms.wells.web.contract.changeorder.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.changeorder.converter.WctbSodbtCanCstPsInqrConverter;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbSodbtCanCstPsInqrDto;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbSodbtCanCstPsInqrDvo;
import com.kyowon.sms.wells.web.contract.changeorder.mapper.WctbSodbtCanCstPsInqrMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctbSodbtCanCstPsInqrService {
    private final WctbSodbtCanCstPsInqrMapper mapper;
    private final WctbSodbtCanCstPsInqrConverter converter;

    public PagingResult<WctbSodbtCanCstPsInqrDto.SearchRes> getCancelContractsPaging(
        WctbSodbtCanCstPsInqrDto.SearchReq dto, PageInfo pageInfo
    ) {
        PagingResult<WctbSodbtCanCstPsInqrDvo> dvos = mapper.selectCancelContracts(dto, pageInfo);
        PagingResult<WctbSodbtCanCstPsInqrDto.SearchRes> reqs = new PagingResult<WctbSodbtCanCstPsInqrDto.SearchRes>();

        for (int i = 0; i < dvos.size(); i++) {
            WctbSodbtCanCstPsInqrDvo dvo = dvos.get(i);
            WctbSodbtCanCstPsInqrDto.SearchRes req = converter.mapWctbSodbtCanCstPsInqrDvoToSearchRes(dvo);
            reqs.add(req);
        }
        reqs.setPageInfo(pageInfo);
        return reqs;
    }
}
