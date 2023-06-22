package com.kyowon.sms.wells.web.service.visit.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbSafetyAccidentDto.EditReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbSafetyAccidentDto.FindRes;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbSafetyAccidentDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbSafetyAccidentDvo;
import com.sds.sflex.system.config.datasource.PagingResult;

/**
 * <pre>
 * W-MP-U-0209M01 안전사고 관리 API
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.06.19
 */
@Mapper(componentModel = "spring")
public interface WsnbSafetyAccidentConverter {

    PagingResult<SearchRes> mapWsnbSafetyAccidentDvoToSearchRes(
        List<WsnbSafetyAccidentDvo> dvos
    );

    FindRes mapWsnbSafetyAccidentDvoToFindRes(WsnbSafetyAccidentDvo dvo);

    WsnbSafetyAccidentDvo mapEditReqToWsnbSafetyAccidentDvo(EditReq dto);
}
