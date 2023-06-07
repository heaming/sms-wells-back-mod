package com.kyowon.sms.wells.web.service.stock.converter;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaLogisticsDeliveryAskDto;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaLogisticsOutStorageAskDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsOutStorageAskDtlDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsOutStorageAskDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsOutStorageAskPcsvDvo;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WsnaLogisticsOutStorageAskConverter {

    @Mapping(source = "ostrOjWareNo", target = "ostrWareNo")
    @Mapping(source = "ostrAkRgstDt", target = "ostrRqdt")
    @Mapping(source = "strHopDt", target = "ostrHopDt")
    @Mapping(source = "wareMngtPrtnrNo", target = "ichrPrtnrNo")
    @Mapping(source = "wareMngtPrtnrOgTpCd", target = "ichrPrtnrOgTpCd")
    WsnaLogisticsOutStorageAskDvo mapSaveReqToWsnaLogisticsOutStorageAskDvo(WsnaLogisticsOutStorageAskDto.SaveReq dto);

    @Mapping(source = "itmGdCd", target = "lgstItmGdCd")
    List<WsnaLogisticsOutStorageAskDtlDvo> mapAllSaveReqToWsnaLogisticsOutStorageAskDtlDvo(
        List<WsnaLogisticsOutStorageAskDto.SaveReq> dtos
    );

    @Mapping(source = "itmGdCd", target = "lgstItmGdCd")
    WsnaLogisticsOutStorageAskDtlDvo mapSaveReqToWsnaLogisticsOutStorageAskDtlDvo(
        WsnaLogisticsOutStorageAskDto.SaveReq dto
    );

    WsnaLogisticsOutStorageAskDto.RemoveReq mapSaveReqToRemoveReq(WsnaLogisticsOutStorageAskDto.SaveReq dto);

    List<WsnaLogisticsOutStorageAskDto.SaveReq> mapAllCreateQomReqToSaveReq(
        List<WsnaLogisticsOutStorageAskDto.CreateQomReq> dtos
    );

    List<WsnaLogisticsOutStorageAskDto.SaveReq> mapAllSaveSelfFilterReqToSaveReq(
        List<WsnaLogisticsOutStorageAskDto.SaveSelfFilterReq> dtos
    );

    WsnaLogisticsOutStorageAskDto.RemoveReq mapSaveSelfFilterReqToRemoveReq(
        WsnaLogisticsOutStorageAskDto.SaveSelfFilterReq dto
    );

    @Mapping(source = "adrsTnoVal", target = "adrsLkTnoEncr")
    @Mapping(source = "adrsCphonNoVal", target = "adrsLkCralTnoEncr")
    WsnaLogisticsOutStorageAskPcsvDvo mapSaveSelfFilterReqToWsnaLogisticsOutStorageAskPcsvDvo(
        WsnaLogisticsOutStorageAskDto.SaveSelfFilterReq dto
    );

    List<WsnaLogisticsDeliveryAskDto.CreateReq> mapAllCreateQomReqToCreateReq(
        List<WsnaLogisticsOutStorageAskDto.CreateQomReq> dtos
    );
}
