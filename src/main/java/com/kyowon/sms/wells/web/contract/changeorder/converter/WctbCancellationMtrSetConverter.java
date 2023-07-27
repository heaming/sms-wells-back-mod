package com.kyowon.sms.wells.web.contract.changeorder.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbCancellationMtrSetDto;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbCancellationMtrSetDvo;

@Mapper(componentModel = "spring")
public interface WctbCancellationMtrSetConverter {
    WctbCancellationMtrSetDvo mapSearchResToWctbCancellationMtrSetDvo(WctbCancellationMtrSetDto.SearchRes res);
}
