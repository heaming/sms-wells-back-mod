package com.kyowon.sms.wells.web.contract.changeorder.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbSinglePaymentBulkChangeDto;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbSinglePaymentBulkChangeDvo;

@Mapper(componentModel = "spring")
public interface WctbSinglePaymentBulkChangeConverter {
    WctbSinglePaymentBulkChangeDvo mapSaveListReqToWctbSinglePaymentBulkChangeDvo(
        WctbSinglePaymentBulkChangeDto.SaveListReq dto
    );
}
