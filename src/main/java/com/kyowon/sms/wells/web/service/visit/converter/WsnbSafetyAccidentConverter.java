package com.kyowon.sms.wells.web.service.visit.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbSafetyAccidentDto.*;
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

    List<SearchRes> mapDvosToSearchRes(
        List<WsnbSafetyAccidentDvo> dvos
    );

    FindRes mapWsnbSafetyAccidentDvoToFindRes(WsnbSafetyAccidentDvo dvo);

    WsnbSafetyAccidentDvo mapEditReqToWsnbSafetyAccidentDvo(EditReq dto);

    WsnbSafetyAccidentDvo mapBiztalkReqToWsnbSafetyAccidentDvo(BiztalkReq dto);

    WsnbSafetyAccidentDvo mapEditSignReqToWsnbSafetyAccidentDvo(EditSignReq dto);

    WsnbSafetyAccidentDvo mapSaveReqToWsnbSafetyAccidentDvo(SaveReq dto);

    FindInitRes mapWsnbSafetyAccidentDvoToFindInitRes(WsnbSafetyAccidentDvo dvo);
}
