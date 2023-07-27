package com.kyowon.sms.wells.web.contract.interfaces.mapper;

import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiFreeAsPeriodDto.FindReq;
import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiFreeAsPeriodDto.FindRes;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WctiFreeAsPeriodMapper {

    FindRes selectFreeAsPeriodFromEx(FindReq dto);

    FindRes selectFreeAsPeriod(FindReq dto);
}
