package com.kyowon.sms.wells.web.contract.interfaces.mapper;

import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiRegularDeliveryPackageDto.*;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WctiRegularDeliveryPackageMapper {
    FindRes selectRegularDeliveryPackage(FindReq dto);

    List<Product> selectChangPsbProducts(FindReq dto);
}
