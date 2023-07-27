package com.kyowon.sms.wells.web.contract.common.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WctzBusinessHoursMapper {
    boolean selectIsBusinessClosedHours();
}
