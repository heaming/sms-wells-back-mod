package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import static com.kyowon.sms.wells.web.contract.changeorder.dto.WctbRentalBulkChangeDto.SearchReq;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbRentalBulkChangeDvo;

@Mapper
public interface WctbRentalBulkChangeMapper {
    List<WctbRentalBulkChangeDvo> selectRentalBulkChanges(
            SearchReq dto
    );
}
