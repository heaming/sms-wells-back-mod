package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.ordermgmt.converter.WctaEmployeePrchsGcfMngtConverter;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaEmployeePrchsGcfMngtDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaEmployeePrchsGcfMngtDto.SearchReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaEmployeePrchsGcfMngtDto.SearchRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaEmployeePrchsGcfMngtRequestDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaEmployeePrchsGcfMngtMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WctaEmployeePrchsGcfMngtService {

    private final WctaEmployeePrchsGcfMngtMapper mapper;
    private final WctaEmployeePrchsGcfMngtConverter converter;

    public List<SearchRes> getEmployeePurchaseGcfs(SearchReq dto) {
        WctaEmployeePrchsGcfMngtRequestDvo dvo = converter.mapSearchReqToWctaEmployeePrchsGcfMngtDvo(dto);
        return converter.mapWctaEmployeePrchsGcfMngtDvoToSearchRes(mapper.selectEmployeePurchaseGcfs(dvo));
    }

    public List<SearchRes> getEmployeePurchaseGcfsExcelDownload(SearchReq dto) {
        WctaEmployeePrchsGcfMngtRequestDvo dvo = converter.mapSearchReqToWctaEmployeePrchsGcfMngtDvo(dto);
        return converter.mapWctaEmployeePrchsGcfMngtDvoToSearchRes(mapper.selectEmployeePurchaseGcfs(dvo));
    }

    public List<WctaEmployeePrchsGcfMngtDto.SearchCntrRes> getEmployeePurchases(
        WctaEmployeePrchsGcfMngtDto.SearchCntrReq dto
    ) {
        return mapper.selectEmployeePurchases(dto);
    }
}
