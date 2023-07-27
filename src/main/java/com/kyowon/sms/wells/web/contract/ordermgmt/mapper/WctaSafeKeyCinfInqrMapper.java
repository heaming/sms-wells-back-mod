package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaSafeKeyCinfInqrDto.SearchCstInfoRes;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WctaSafeKeyCinfInqrMapper {
    SearchCstInfoRes selectCstInfo(String cstNo);

    String selectDfltRsonCd(String coCd, String dfltMngtDvCd, String sfkVal);
}
