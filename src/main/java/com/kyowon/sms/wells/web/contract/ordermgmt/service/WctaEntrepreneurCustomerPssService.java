package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaEntrepreneurCustomerPssDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaEntrepreneurCustomerPssMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaEntrepreneurCustomerPssService {
    private final WctaEntrepreneurCustomerPssMapper mapper;

    public PagingResult<WctaEntrepreneurCustomerPssDto.SearchRes> getEntrepreneurCustomerPssPages(
        WctaEntrepreneurCustomerPssDto.SearchReq dto,
        PageInfo pageInfo
    ) {
        return mapper.selectEntrepreneurCustomerPss(dto, pageInfo);
    };

    public List<WctaEntrepreneurCustomerPssDto.SearchRes> getEntrepreneurCustomerPssForExcelDownload(
        WctaEntrepreneurCustomerPssDto.SearchReq dto
    ) {
        return mapper.selectEntrepreneurCustomerPss(dto);
    };
}
