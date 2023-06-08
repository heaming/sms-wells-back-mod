package com.kyowon.sms.wells.web.service.stock.converter;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsInStorageAskDtlDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsInStorageAskDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsInStorageAskReqDvo;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WsnaLogisticsInStorageAskConverter {

    @Mapping(source = "ostrAkNo", target = "rtngdAkNo")
    @Mapping(source = "ostrAkRgstDt", target = "strAkRgstDt")
    @Mapping(source = "strHopDt", target = "strDueDt")
    @Mapping(source = "wareMngtPrtnrNo", target = "ichrPrtnrNo")
    WsnaLogisticsInStorageAskDvo mapWsnaLogisticsInStorageAskReqDvoToWsnaLogisticsInStorageAskDvo(
        WsnaLogisticsInStorageAskReqDvo dvo
    );

    @Mapping(source = "ostrAkSn", target = "strAkSn")
    @Mapping(source = "ostrAkQty", target = "strAkQty")
    @Mapping(source = "itmGdCd", target = "lgstItmGdCd")
    List<WsnaLogisticsInStorageAskDtlDvo> mapAllWsnaLogisticsInStorageAskReqDvoToWsnaLogisticsInStorageAskDtlDvo(
        List<WsnaLogisticsInStorageAskReqDvo> dvos
    );

    @Mapping(source = "ostrAkSn", target = "strAkSn")
    @Mapping(source = "ostrAkQty", target = "strAkQty")
    @Mapping(source = "itmGdCd", target = "lgstItmGdCd")
    WsnaLogisticsInStorageAskDtlDvo mapWsnaLogisticsInStorageAskReqDvoToWsnaLogisticsInStorageAskDtlDvo(
        WsnaLogisticsInStorageAskReqDvo dvo
    );
}
