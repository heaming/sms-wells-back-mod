package com.kyowon.sms.wells.web.contract.interfaces.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.interfaces.dvo.WctiCustomerDvo;

@Mapper
public interface WctiCustomerMapper {
    List<WctiCustomerDvo> selectCustomers(WctiCustomerDvo dvo);

    List<WctiCustomerDvo> selectProspactCustomers(WctiCustomerDvo dvo);
}
