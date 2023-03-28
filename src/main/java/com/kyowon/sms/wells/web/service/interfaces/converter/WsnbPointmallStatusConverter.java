package com.kyowon.sms.wells.web.service.interfaces.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsnbPointmallStatusDto.SearchRes;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsnbPointmallStatusDvo;

@Mapper(componentModel = "spring")
public interface WsnbPointmallStatusConverter {

    SearchRes mapWsnbPointmallStatusDvoToSearchRes(WsnbPointmallStatusDvo dvo);

}
