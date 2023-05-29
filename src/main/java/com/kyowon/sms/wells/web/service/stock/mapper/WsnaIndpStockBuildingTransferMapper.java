package com.kyowon.sms.wells.web.service.stock.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WsnaIndpStockBuildingTransferMapper {
    int updateCarriedOverAddressUseYn();
}
