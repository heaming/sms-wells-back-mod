package com.kyowon.sms.wells.web.contract.salesstatus.service;

import static com.kyowon.sms.wells.web.contract.salesstatus.dto.WcteRenewalCustomerDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.salesstatus.dto.WcteRenewalCustomerDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.salesstatus.converter.WcteRenewalCustomerConverter;
import com.kyowon.sms.wells.web.contract.salesstatus.dvo.WcteRenewalCustomerDvo;
import com.kyowon.sms.wells.web.contract.salesstatus.mapper.WcteRenewalCustomerMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WcteRenewalCustomerService {

    private final WcteRenewalCustomerMapper mapper;
    private final WcteRenewalCustomerConverter converter;

    public PagingResult<SearchRes> getExnRstlCstContactAssigns(SearchReq dto, PageInfo pageInfo) {

        PagingResult<WcteRenewalCustomerDvo> resPagingDvos = null;

        // 만료렌탈 고객 컨택배정 조회
        if ("1".equals(dto.asnDvCd())) {
            resPagingDvos = mapper.selectExnCstContactAssigns(dto, pageInfo);
        }
        // 재약정 고객 컨택배정 조회
        else if ("2".equals(dto.asnDvCd())) {
            resPagingDvos = mapper.selectRstlCstContactAssigns(dto, pageInfo);
        }

        PagingResult<SearchRes> resPagingDtos = converter.mapAllWcteRenewalCustomerDvoToSearchRes(resPagingDvos);
        resPagingDtos.setPageInfo(pageInfo);

        return resPagingDtos;
    }

    public List<SearchRes> getExnRstlCstContactAssignsForExcelDownload(SearchReq dto) {

        List<WcteRenewalCustomerDvo> resDvos = null;

        // 만료렌탈 고객 컨택배정 조회
        if ("1".equals(dto.asnDvCd())) {
            resDvos = mapper.selectExnCstContactAssigns(dto);
        }
        // 재약정 고객 컨택배정 조회
        else if ("2".equals(dto.asnDvCd())) {
            resDvos = mapper.selectRstlCstContactAssigns(dto);
        }

        return converter.mapAllWcteRenewalCustomerDvoToSearchRes(resDvos);
    }
}
