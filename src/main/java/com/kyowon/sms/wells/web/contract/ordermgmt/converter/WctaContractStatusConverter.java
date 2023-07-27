package com.kyowon.sms.wells.web.contract.ordermgmt.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractStatusDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaContractStatusDvo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper(componentModel = "spring")
public interface WctaContractStatusConverter {
    PagingResult<WctaContractStatusDto.SearchRes> mapAllContractStatusDvoToSearchRes(PagingResult<WctaContractStatusDvo> dvos);
}
