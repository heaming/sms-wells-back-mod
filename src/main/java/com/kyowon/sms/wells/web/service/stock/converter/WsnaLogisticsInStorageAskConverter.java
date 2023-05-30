package com.kyowon.sms.wells.web.service.stock.converter;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaLogisticsInStorageAskDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsInStorageAskDtlDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsInStorageAskDvo;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WsnaLogisticsInStorageAskConverter {

    WsnaLogisticsInStorageAskDto.FindRes mapWsnaLogisticsInStorageAskDtlDvoToFindRes(
        WsnaLogisticsInStorageAskDtlDvo dvo
    );

    @Mapping(source = "strHopDt", target = "strDueDt")
    @Mapping(source = "ostrAkNo", target = "rtngdAkNo")
    @Mapping(source = "ostrAkRgstDt", target = "strAkRgstDt")
    @Mapping(source = "wareMngtPrtnrNo", target = "ichrMngtPrtnrNo")
    WsnaLogisticsInStorageAskDvo mapSaveReqToWsnaLogisticsInStorageAskDvo(WsnaLogisticsInStorageAskDto.SaveReq dto);

    @Mapping(source = "ostrAkQty", target = "strAkQty")
    List<WsnaLogisticsInStorageAskDtlDvo> mapAllSaveReqToWsnaLogisticsInStorageAskDtlDvo(
        List<WsnaLogisticsInStorageAskDto.SaveReq> dtos
    );

    WsnaLogisticsInStorageAskDto.FindReq mapSaveReqToFindReq(WsnaLogisticsInStorageAskDto.SaveReq dto);

    WsnaLogisticsInStorageAskDto.FindReq mapRemoveReqToFindReq(WsnaLogisticsInStorageAskDto.RemoveReq dto);

    WsnaLogisticsInStorageAskDtlDvo mapSaveReqToWsnaLogisticsInStorageAskDtlDvo(
        WsnaLogisticsInStorageAskDto.SaveReq dto
    );
}
