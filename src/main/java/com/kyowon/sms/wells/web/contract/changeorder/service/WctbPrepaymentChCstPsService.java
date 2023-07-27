package com.kyowon.sms.wells.web.contract.changeorder.service;

import static com.kyowon.sms.wells.web.contract.changeorder.dto.WctbPrepaymentChCstPsDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.changeorder.dto.WctbPrepaymentChCstPsDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.changeorder.converter.WctbPrepaymentChCstPsConverter;
import com.kyowon.sms.wells.web.contract.changeorder.mapper.WctbPrepaymentChCstPsMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctbPrepaymentChCstPsService {

    private final WctbPrepaymentChCstPsMapper mapper;
    private final WctbPrepaymentChCstPsConverter converter;

    public PagingResult<SearchRes> getPrepaymentChCstPsPages(SearchReq dto, PageInfo pageInfo) {
        PagingResult<SearchRes> pagingResult = converter
            .mapWctbPrepaymentDvoToSearchRes(mapper.selectPrepaymentChCstPsPages(dto, pageInfo));
        pagingResult.setPageInfo(pageInfo);
        return pagingResult;
    }

    public List<SearchRes> getPrepaymentChCstPssForExcelDownload(SearchReq dto) {
        return converter.mapWctbPrepaymentExcelDvoToSearchRes(mapper.selectPrepaymentChCstPsPages(dto));
    }
}
