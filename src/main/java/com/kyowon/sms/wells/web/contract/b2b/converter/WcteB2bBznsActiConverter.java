package com.kyowon.sms.wells.web.contract.b2b.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.b2b.dto.WcteB2bBznsActiDto;
import com.kyowon.sms.wells.web.contract.b2b.dto.WcteB2bBznsActiDto.SaveReq;
import com.kyowon.sms.wells.web.contract.b2b.dto.WcteB2bBznsActiDto.SearchKeyManRes;
import com.kyowon.sms.wells.web.contract.b2b.dto.WcteB2bBznsActiDto.SearchRes;
import com.kyowon.sms.wells.web.contract.b2b.dvo.WcteB2bBznsActiDvo;

@Mapper(componentModel = "spring")
public interface WcteB2bBznsActiConverter {
    List<SearchRes> mapWcteB2bBznsActiDvoToSearchRes(List<WcteB2bBznsActiDvo> dvos);

    SearchKeyManRes mapWcteB2bBznsActiDvoToSearchKeyManRes(WcteB2bBznsActiDvo dvo);

    WcteB2bBznsActiDvo mapSaveReqToWcteB2bBznsActiDvo(SaveReq dto);

    WcteB2bBznsActiDvo mapSaveDetailReqToWcteB2bBznsActiDvo(WcteB2bBznsActiDto.SaveDetailReq dto);

}
