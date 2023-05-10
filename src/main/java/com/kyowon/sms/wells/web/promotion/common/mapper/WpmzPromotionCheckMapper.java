package com.kyowon.sms.wells.web.promotion.common.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WpmzPromotionCheckMapper {
    String selectProductClassificationInfo(String pdCd);
}
