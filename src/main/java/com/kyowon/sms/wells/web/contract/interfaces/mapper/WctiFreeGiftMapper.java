package com.kyowon.sms.wells.web.contract.interfaces.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiFreeGiftDto.SearchReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiFreeGiftDto.SearchRes;

@Mapper
public interface WctiFreeGiftMapper {
    List<SearchRes> selectFreeGift(SearchReq req);
}
