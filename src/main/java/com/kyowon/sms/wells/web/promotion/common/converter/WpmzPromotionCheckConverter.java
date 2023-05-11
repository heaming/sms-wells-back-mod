package com.kyowon.sms.wells.web.promotion.common.converter;


import static com.kyowon.sms.wells.web.promotion.common.dto.WpmzPromotionCheckDto.SearchReq;
import static com.kyowon.sms.wells.web.promotion.common.dto.WpmzPromotionCheckDto.SearchRes;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.promotion.common.dvo.WpmzPromotionInputDvo;
import com.kyowon.sms.wells.web.promotion.common.dvo.WpmzPromotionOutputDvo;

/**
 * <pre>
 *     Wells 공통 프로모션 체크 조회 DTO
 * </pre>
 * @author domin.pyun
 * @since 2023-05-11
 */
@Mapper(componentModel = "spring")
public interface WpmzPromotionCheckConverter {

    WpmzPromotionInputDvo mapSearchReqToWpmzPromotionInputDvo(SearchReq req);

    List<SearchRes> mapAllWpmzPromotionOutputDvoToSearchRes(List<WpmzPromotionOutputDvo> dvos);
}
