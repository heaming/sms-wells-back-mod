package com.kyowon.sms.wells.web.contract.interfaces.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiRequidationDateDto.FindReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiRequidationDateDto.FindRes;

@Mapper
public interface WctiRequidationDateMapper {

    FindRes selectRequidationDate(FindReq dto);
}
