package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaEmployeeSellQuantityDvo;

@Mapper
public interface WctaEmployeeSellQuantityMapper {
    WctaEmployeeSellQuantityDvo selectEmployeeSellQuantity(WctaEmployeeSellQuantityDvo dvo);
}
