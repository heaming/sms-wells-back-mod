package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaPartnerInfInqrPywdDvo;

@Mapper
public interface WctaPartnerInfInqrPywdMapper {
    WctaPartnerInfInqrPywdDvo selectPartnerInfInqrPywd(String cntrNo);
}
