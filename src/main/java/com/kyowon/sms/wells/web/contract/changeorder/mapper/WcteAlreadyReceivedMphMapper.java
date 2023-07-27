package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WcteAlreadyReceivedMphMapper {
    String selectAlreadyReceivedMph(String cntrNo, String cntCstNo);
}
