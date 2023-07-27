package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractIntegrationDto.SearchReq;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaContractIntegrationPagesDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaContractIntegrationPagesRequestDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WctaContractIntegrationMapper {
    PagingResult<WctaContractIntegrationPagesDvo> selectContractIntegrationsPages(
        WctaContractIntegrationPagesRequestDvo dvo,
        PageInfo pageInfo
    );

    List<WctaContractIntegrationPagesDvo> selectContractIntegrationsPages(
        SearchReq dto
    );
}
