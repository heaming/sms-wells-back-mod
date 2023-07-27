package com.kyowon.sms.wells.web.contract.orderstatus.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.orderstatus.dvo.WctdInChargeCustomerOrderDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WctdInChargeCustomerOrderMapper {

    PagingResult<WctdInChargeCustomerOrderDvo> selectInChargeCustomerOrderPages(
        WctdInChargeCustomerOrderDvo dvo,
        PageInfo pageInfo
    );

    List<WctdInChargeCustomerOrderDvo> selectInChargeCustomerOrderPages(WctdInChargeCustomerOrderDvo dvo);
}
