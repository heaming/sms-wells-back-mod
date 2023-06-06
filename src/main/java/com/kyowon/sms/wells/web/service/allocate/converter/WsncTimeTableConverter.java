package com.kyowon.sms.wells.web.service.allocate.converter;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncTimeTableDto.SearchRes;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncTimeTableDvo;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface WsncTimeTableConverter {
    SearchRes mapDvoToRes(WsncTimeTableDvo dvo);
}
