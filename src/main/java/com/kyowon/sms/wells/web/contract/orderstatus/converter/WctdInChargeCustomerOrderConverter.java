package com.kyowon.sms.wells.web.contract.orderstatus.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.orderstatus.dto.WctdInChargeCustomerOrderDto.SearchReq;
import com.kyowon.sms.wells.web.contract.orderstatus.dto.WctdInChargeCustomerOrderDto.SearchRes;
import com.kyowon.sms.wells.web.contract.orderstatus.dvo.WctdInChargeCustomerOrderDvo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper(componentModel = "spring")
public interface WctdInChargeCustomerOrderConverter {

    WctdInChargeCustomerOrderDvo mapSearchReqToWctdInChargeCustomerOrderDvo(SearchReq dto);

    PagingResult<SearchRes> mapWctdInChargeCustomerOrderDvoToSearchRes(PagingResult<WctdInChargeCustomerOrderDvo> dvo);

    List<SearchRes> mapWctdInChargeCustomerOrderDvoToSearchRes(List<WctdInChargeCustomerOrderDvo> dvo);

}
