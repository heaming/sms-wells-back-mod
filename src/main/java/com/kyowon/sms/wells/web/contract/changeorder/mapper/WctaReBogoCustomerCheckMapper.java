package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctaReBogoCustomerCheckDvo;

@Mapper
public interface WctaReBogoCustomerCheckMapper {
    String selectReRntlPmotApyYn(WctaReBogoCustomerCheckDvo dvo);
}
