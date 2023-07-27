package com.kyowon.sms.wells.web.contract.ordermgmt.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractIntegrationDto.SearchReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractIntegrationDto.SearchRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaContractIntegrationPagesDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaContractIntegrationPagesRequestDvo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper(componentModel = "spring")
public interface WctaContractIntegrationConverter {
    WctaContractIntegrationPagesRequestDvo mapSearchReqToWctaContractIntegrationPagesDvo(SearchReq dto);

    PagingResult<SearchRes> mapWctaContractIntegrationPagesDvoToSearchRes(
        PagingResult<WctaContractIntegrationPagesDvo> dvos
    );

    List<SearchRes> mapWctaContractIntegrationPagesExcelDvoToSearchRes(List<WctaContractIntegrationPagesDvo> dvos);
}
