package com.kyowon.sms.wells.web.contract.changeorder.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbRentalBulkChangeDto;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbRentalBulkChangeDvo;

@Mapper(componentModel = "spring")
public interface WctbRentalBulkChangeConverter {
List<WctbRentalBulkChangeDto.SearchRes> mapAllRentalBulkChangeDvoToSearchRes(
        List<WctbRentalBulkChangeDvo> dvos
    );
}
