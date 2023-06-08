package com.kyowon.sms.wells.web.service.stock.converter;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.kyowon.sms.wells.web.service.stock.dvo.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WsnaLogisticsOutStorageAskConverter {

    @Mapping(source = "ostrOjWareNo", target = "ostrWareNo")
    @Mapping(source = "ostrAkRgstDt", target = "ostrRqdt")
    @Mapping(source = "strHopDt", target = "ostrHopDt")
    @Mapping(source = "wareMngtPrtnrNo", target = "ichrPrtnrNo")
    @Mapping(source = "wareMngtPrtnrOgTpCd", target = "ichrPrtnrOgTpCd")
    WsnaLogisticsOutStorageAskDvo mapWsnaLogisticsOutStorageAskReqDvoToWsnaLogisticsOutStorageAskDvo(
        WsnaLogisticsOutStorageAskReqDvo dvo
    );

    @Mapping(source = "itmGdCd", target = "lgstItmGdCd")
    List<WsnaLogisticsOutStorageAskDtlDvo> mapAllWsnaLogisticsOutStorageAskReqDvoToWsnaLogisticsOutStorageAskDtlDvo(
        List<WsnaLogisticsOutStorageAskReqDvo> dvos
    );

    @Mapping(source = "itmGdCd", target = "lgstItmGdCd")
    WsnaLogisticsOutStorageAskDtlDvo mapWsnaLogisticsOutStorageAskReqDvoToWsnaLogisticsOutStorageAskDtlDvo(
        WsnaLogisticsOutStorageAskReqDvo dvo
    );

    @Mapping(source = "adrsTnoVal", target = "adrsLkTnoEncr")
    @Mapping(source = "adrsCphonNoVal", target = "adrsLkCralTnoEncr")
    WsnaLogisticsOutStorageAskPcsvDvo mapWsnaLogisticsOutStorageAskReqDvoToWsnaLogisticsOutStorageAskPcsvDvo(
        WsnaLogisticsOutStorageAskReqDvo dvo
    );

    List<WsnaLogisticsDeliveryAskReqDvo> mapAllCWsnaLogisticsOutStorageAskReqDvoToWsnaLogisticsDeliveryAskReqDvo(
        List<WsnaLogisticsOutStorageAskReqDvo> dvos
    );
}
