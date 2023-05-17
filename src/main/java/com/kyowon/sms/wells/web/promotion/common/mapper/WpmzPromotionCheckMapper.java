package com.kyowon.sms.wells.web.promotion.common.mapper;

import com.kyowon.sms.wells.web.promotion.common.dvo.WpmzPromotionPriceDetailDvo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WpmzPromotionCheckMapper {
    WpmzPromotionPriceDetailDvo selectProductPriceDetailInfo(String pdPrcDtlCd);
    WpmzPromotionPriceDetailDvo selectProductPriceMetaInfo();
}
