package com.kyowon.sms.wells.web.contract.changeorder.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbPaymentCancelNextdayDto;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbPaymentCancelNextdayDvo;

@Mapper(componentModel = "spring")
public interface WctbPaymentCancelNextdayConverter {
    WctbPaymentCancelNextdayDto.SearchRes mapWctbPaymentCancelNextdayDvoToSearchRes(WctbPaymentCancelNextdayDvo dvo);
}
