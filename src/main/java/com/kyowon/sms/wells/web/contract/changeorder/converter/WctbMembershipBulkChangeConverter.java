package com.kyowon.sms.wells.web.contract.changeorder.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbMembershipBulkChangeDto;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbMembershipBulkChangeDvo;

@Mapper(componentModel = "spring")
public interface WctbMembershipBulkChangeConverter {
    WctbMembershipBulkChangeDvo mapSaveListReqToWctbMembershipBulkChangeDvo(
        WctbMembershipBulkChangeDto.SaveListReq dto
    );
}
