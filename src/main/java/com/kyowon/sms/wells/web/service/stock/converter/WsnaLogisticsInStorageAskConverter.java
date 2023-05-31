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

    @Mapping(source = "ostrAkNo", target = "rtngdAkNo")
    @Mapping(source = "ostrAkTpCd", target = "lgstStrTpCd")
    @Mapping(source = "ostrAkRgstDt", target = "strAkRgstDt")
    @Mapping(source = "strHopDt", target = "strDueDt")
    @Mapping(source = "wareMngtPrtnrNo", target = "ichrPrtnrNo")
    WsnaLogisticsInStorageAskDvo mapSaveReqToWsnaLogisticsInStorageAskDvo(WsnaLogisticsInStorageAskDto.SaveReq dto);

    @Mapping(source = "ostrAkQty", target = "strAkQty")
    List<WsnaLogisticsInStorageAskDtlDvo> mapAllSaveReqToWsnaLogisticsInStorageAskDtlDvo(
        List<WsnaLogisticsInStorageAskDto.SaveReq> dtos
    );

    @Mapping(source = "ostrAkNo", target = "ostrNo")
    @Mapping(source = "ostrAkSn", target = "ostrSn")
    WsnaLogisticsInStorageAskDto.RemoveReq mapSaveReqToRemoveReq(WsnaLogisticsInStorageAskDto.SaveReq dto);

    @Mapping(source = "ostrAkQty", target = "strAkQty")
    WsnaLogisticsInStorageAskDtlDvo mapSaveReqToWsnaLogisticsInStorageAskDtlDvo(
        WsnaLogisticsInStorageAskDto.SaveReq dto
    );
}
