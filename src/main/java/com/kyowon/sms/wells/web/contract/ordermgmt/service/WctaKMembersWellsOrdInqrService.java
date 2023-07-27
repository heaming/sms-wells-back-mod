package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.ordermgmt.converter.WctaKMembersWellsOrdInqrConverter;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaKMembersWellsOrdInqrDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaKMembersWellsOrdInqrMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaKMembersWellsOrdInqrService {
    private final WctaKMembersWellsOrdInqrMapper mapper;
    private final WctaKMembersWellsOrdInqrConverter converter;

    public List<WctaKMembersWellsOrdInqrDto.SearchRes> getKMembersWellsOrdInqrs(
        String cntrNo, String cntrSn, String cmnSfkVal
    ) {
        if (StringUtils.isAnyEmpty(cntrNo, cntrSn)) {
            return null;
        }
        return converter
            .mapWctaKMembersWellsOrdInqrDvoToSearchRes(mapper.selectKMembersWellsOrdInqrs(cntrNo, cntrSn, cmnSfkVal));
    }

}
