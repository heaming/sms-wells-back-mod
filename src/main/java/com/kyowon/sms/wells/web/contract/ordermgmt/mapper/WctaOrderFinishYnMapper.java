package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaOrderFinishYnDvo;

@Mapper
public interface WctaOrderFinishYnMapper {
    WctaOrderFinishYnDvo selectOrderFinishYn(String cntrNo, String cntrSn, String baseYm);
}
