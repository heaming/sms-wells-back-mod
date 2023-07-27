package com.kyowon.sms.wells.web.contract.changeorder.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbCustomerBaseBulkChangeDto;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbCustomerBaseBulkChangeDvo;

@Mapper(componentModel = "spring")
public interface WctbCustomerBaseBulkChangeConverter {

    WctbCustomerBaseBulkChangeDvo mapSearchReqToWctbCustomerBaseBulkChangeDvo(
        WctbCustomerBaseBulkChangeDto.SearchReq dto
    );

    WctbCustomerBaseBulkChangeDvo mapSaveReqToWctbCustomerBaseBulkChangeDvo(
        WctbCustomerBaseBulkChangeDto.SaveReq dto
    );

    WctbCustomerBaseBulkChangeDto.SearchCustomerRes mapWctbCustomerBaseBulkChangeDvoToSearchCustomerRes(
        WctbCustomerBaseBulkChangeDvo dvo
    );

    WctbCustomerBaseBulkChangeDto.SearchPartnerRes mapWctbCustomerBaseBulkChangeDvoToSearchPartnerRes(
        WctbCustomerBaseBulkChangeDvo dvo
    );

}
