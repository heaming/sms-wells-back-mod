package com.kyowon.sms.wells.web.contract.ordermgmt.converter;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.kyowon.sms.wells.web.contract.common.dto.WctzPartnerDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaContractCstRelDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaContractPrtnrRelDvo;

@Mapper(componentModel = "spring")
public interface WctaContractRegConverter {
    WctaContractPrtnrRelDvo mapPrtnrDtoToWctaContractPrtnrRelDvo(
        WctzPartnerDto.FindPrtnrRes prtnr
    );

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    WctaContractCstRelDvo mergeContractCstRelDvo(
        WctaContractCstRelDvo source,
        @MappingTarget
        WctaContractCstRelDvo target
    );
}
