package com.kyowon.sms.wells.web.contract.changeorder.converter;

import org.mapstruct.*;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbCancelBaseDto.FindSubDetailRes;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbCancelBaseDto.SaveReq;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbCancelBaseDvo;

@Mapper(componentModel = "spring")
public interface WctbCancelBaseConverter {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    WctbCancelBaseDvo convertCancelBaseDvo(
        WctbCancelBaseDvo source,
        @MappingTarget
        WctbCancelBaseDvo target
    );

    @Mapping(source = "ptBorAmt", target = "pBorAmt")
    FindSubDetailRes mapCancelBaseDvoToFindSubDetailRes(WctbCancelBaseDvo dvo);

    @Mapping(source = "pBorAmt", target = "ptBorAmt")
    WctbCancelBaseDvo mapSaveReqToCancelBaseDvo(SaveReq dto);
}
