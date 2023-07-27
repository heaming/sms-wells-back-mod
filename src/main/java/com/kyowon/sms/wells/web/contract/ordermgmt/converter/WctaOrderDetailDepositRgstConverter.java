package com.kyowon.sms.wells.web.contract.ordermgmt.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailDepositRgstDto.SearchRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaOrderDetailDepositRgstDvo;

@Mapper(componentModel = "spring")
public interface WctaOrderDetailDepositRgstConverter {
    List<SearchRes> mapWctaOrderDetailDepositRgstDvoToSearchRes(
        List<WctaOrderDetailDepositRgstDvo> dvos
    );

}
