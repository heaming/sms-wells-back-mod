package com.kyowon.sms.wells.web.service.visit.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbMultipleTaskOrderDto.SaveReq;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbMultipleTaskOrderDvo;

/**
 * <pre>
 * W-SV-S-0012 다건 작업오더, 정보변경 처리
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.02.09
 */
@Mapper(componentModel = "spring")
public interface WsnbMultipleTaskOrderConverter {

    WsnbMultipleTaskOrderDvo mapSaveReqToWsnbMultipleTaskOrderDvo(SaveReq dto);

}
