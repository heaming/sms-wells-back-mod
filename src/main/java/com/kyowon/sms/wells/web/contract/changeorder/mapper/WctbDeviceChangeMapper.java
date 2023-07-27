package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbDeviceChangeDvo;

@Mapper
public interface WctbDeviceChangeMapper {
    List<WctbDeviceChangeDvo> selectDeviceChanges(String cntrNo);
}
