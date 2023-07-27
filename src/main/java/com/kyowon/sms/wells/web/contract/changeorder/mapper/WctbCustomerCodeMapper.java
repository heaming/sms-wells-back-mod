package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbCustomerCodeDvo;

@Mapper
public interface WctbCustomerCodeMapper {
    WctbCustomerCodeDvo selectCustomerCode(String cntrNo, int cntrSn);
}
