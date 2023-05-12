package com.kyowon.sms.wells.web.service.allocate.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncOutsourcedpdAsReceiptDto;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncOutsourcedpdAsReceiptDto.BiztalkReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncOutsourcedpdAsReceiptDto.SaveReceiptIzReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncOutsourcedpdAsReceiptDto.SearchReceiptBzRes;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncOutsourcedpdAsReceiptDto.SearchReceiptIzRes;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncOutsourcedpdAsReceiptDvo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper(componentModel = "spring")
public interface WsncOutsourcedpdAsReceiptConverter {

    WsncOutsourcedpdAsReceiptDvo mapBiztalkReqToWsncOutsourcedpdAsReceiptDvo(BiztalkReq dto);

    PagingResult<SearchReceiptIzRes> mapWsncAsTransferDvoToSearchReceiptIzRes(
        List<WsncOutsourcedpdAsReceiptDvo> dvos
    );

    SearchReceiptIzRes mapWsncAsTransferDvoToSearchReceiptIzRes(WsncOutsourcedpdAsReceiptDvo dvo);

    WsncOutsourcedpdAsReceiptDvo mapSaveReceiptIzReqToWsncOutsourcedpdAsReceiptDvo(SaveReceiptIzReq dto);

    PagingResult<SearchReceiptBzRes> mapWsncAsTransferDvoToSearchReceiptBzRes(
        List<WsncOutsourcedpdAsReceiptDvo> dvos
    );

    SearchReceiptBzRes mapWsncAsTransferDvoToSearchReceiptBzRes(WsncOutsourcedpdAsReceiptDvo dvo);

    WsncOutsourcedpdAsReceiptDvo mapSaveReceiptBzReqToWsncOutsourcedpdAsReceiptDvo(
        WsncOutsourcedpdAsReceiptDto.SaveReceiptBzReq dto
    );
}
