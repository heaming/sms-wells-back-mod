package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctaDeviceChangeCancelTypeDto.SearchRes;

@Mapper
public interface WctaDeviceChangeCancelTypeMapper {
    List<SearchRes> selectDeivceChangeCancelType(String ojCntrNo, String ojCntrSn);
}
