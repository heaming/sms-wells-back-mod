package com.kyowon.sms.wells.web.contract.ordermgmt.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaKMembersWellsOrdInqrDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaKMembersWellsOrdInqrDvo;

@Mapper(componentModel = "spring")
public interface WctaKMembersWellsOrdInqrConverter {
    List<WctaKMembersWellsOrdInqrDto.SearchRes> mapWctaKMembersWellsOrdInqrDvoToSearchRes(
        List<WctaKMembersWellsOrdInqrDvo> dvo
    );
}
