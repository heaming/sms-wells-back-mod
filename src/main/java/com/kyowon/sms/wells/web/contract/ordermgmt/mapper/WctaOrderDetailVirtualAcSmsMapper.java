package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailVirtualAcSmsDto.SearchReq;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaOrderDetailVirtualAcSmsDvo;

@Mapper
public interface WctaOrderDetailVirtualAcSmsMapper {

    WctaOrderDetailVirtualAcSmsDvo selectVirtualAcCustomer(SearchReq dto);

}
