package com.kyowon.sms.wells.web.service.visit.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbPointmallStatusInfDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbPointmallStatusInfDvo;

@Mapper(componentModel = "spring")
public interface WsnbPointmallStatusInfConverter {

    SearchRes mapWsnbPointmallStatusInfDvoToSearchRes(WsnbPointmallStatusInfDvo dvo);

}
