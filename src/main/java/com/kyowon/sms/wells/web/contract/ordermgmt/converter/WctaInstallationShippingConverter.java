package com.kyowon.sms.wells.web.contract.ordermgmt.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaInstallationShippingDto.*;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaInstallationShippingDvo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper(componentModel = "spring")
public interface WctaInstallationShippingConverter {
    WctaInstallationShippingDvo mapSearchReqToWctaIstShippingDvo(SearchIstShippingReq dto);

    SearchIstShippingRes mapWctaIstShippingDvoToSearchRes(WctaInstallationShippingDvo dvo);

    PagingResult<SearchIstShippingRes> mapAllWctaIstShippingDvoToSearchRes(List<WctaInstallationShippingDvo> dvos);

    WctaInstallationShippingDvo mapSearchAsnProcsReqToWctaIstShippingDvo(SearchAssignProcessingReq dto);

    WctaInstallationShippingDvo mapSaveAsnProcsReqToWctaIstShippingDvo(SaveAssignProcessingReq dto);

    WctaInstallationShippingDvo mapEditDueDateCancelReqToWctaIstShippingDvo(EditDueDateCancelReq dto);

    WctaInstallationShippingDvo mapEditDueDateReqoWctaIstShippingDvo(EditDueDateReq dto);
}
