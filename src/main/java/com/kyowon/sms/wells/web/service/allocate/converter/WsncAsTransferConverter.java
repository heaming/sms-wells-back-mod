package com.kyowon.sms.wells.web.service.allocate.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncAsTransferDto.SaveReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncAsTransferDto.SearchRes;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncAsTransferDvo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper(componentModel = "spring")
public interface WsncAsTransferConverter {

    PagingResult<SearchRes> mapWsncAsTransferDvoToSearchRes(
        List<WsncAsTransferDvo> dvos
    );

    SearchRes mapWsncAsTransferDvoToSearchRes(WsncAsTransferDvo dvo);

    WsncAsTransferDvo mapSaveReqToWsncAsTransferDvo(SaveReq dto);

}
