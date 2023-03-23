package com.kyowon.sms.wells.web.service.visit.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbPointmallStatusDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbPointmallStatusDvo;

@Mapper(componentModel = "spring")
public interface WsnbPointmallStatusConverter {

    SearchRes mapWsnbPointmallStatusDvoToSearchRes(WsnbPointmallStatusDvo dvo);

}
