package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaQuoteSendDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaQuoteSendDvo;

@Mapper
public interface WctaQuoteSendMapper {
    WctaQuoteSendDvo selectQuoteSendInf(WctaQuoteSendDto.SearchReq dto);

    List<WctaQuoteSendDto.SearchRes> selectQuoteSendHists(WctaQuoteSendDto.SearchReq dto);

}
