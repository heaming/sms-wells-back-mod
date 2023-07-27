package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaCollectingAmountContactDto;

@Mapper
public interface WctaCollectingAmountContactMapper {
    List<WctaCollectingAmountContactDto.SearchRes> selectCollectingAmountContacts(String cntrNo, String cntrSn);
}
