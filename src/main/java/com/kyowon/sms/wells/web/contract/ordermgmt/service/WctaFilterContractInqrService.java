package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import com.kyowon.sms.wells.web.contract.ordermgmt.converter.WctaFilterContractInqrConverter;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaFilterContractInqrMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaFilterContractInqrDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaFilterContractInqrDto.SearchRes;

@Service
@RequiredArgsConstructor
public class WctaFilterContractInqrService {

    private final WctaFilterContractInqrMapper mapper;
    private final WctaFilterContractInqrConverter converter;

    public PagingResult<SearchRes> getFilterContractInqrPages(SearchReq dto, PageInfo pageInfo) {


        PagingResult<SearchRes> pagingResultDto = converter
            .mapWctaFilterContractInqrDvoToSearchRes(
                mapper.selectFilterContractInqrPages(dto, pageInfo)
            );
        pagingResultDto.setPageInfo(pageInfo);

        return pagingResultDto;

    }

}
