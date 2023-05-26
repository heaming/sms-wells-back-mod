package com.kyowon.sms.wells.web.service.stock.converter;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaLogisticsOutStorageAskDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsOutStorageAskDtlDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsOutStorageAskDvo;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WsnaLogisticsOutStorageAskConverter {

    @Mapping(source = "ostrOjWareNo", target = "ostrWareNo")
    @Mapping(source = "wareMngtPrtnrNo", target = "ichrMngtPrtnrNo")
    @Mapping(source = "wareMngtPrtnrOgTpCd", target = "ichrPrtnrOgTpCd")
    WsnaLogisticsOutStorageAskDvo mapSaveReqToWsnaLogisticsOutStorageAskDvo(WsnaLogisticsOutStorageAskDto.SaveReq dto);

    List<WsnaLogisticsOutStorageAskDtlDvo> mapAllSaveReqToWsnaLogisticsOutStorageAskDtlDvo(
        List<WsnaLogisticsOutStorageAskDto.SaveReq> dtos
    );

    WsnaLogisticsOutStorageAskDtlDvo mapSaveReqToWsnaLogisticsOutStorageAskDtlDvo(
        WsnaLogisticsOutStorageAskDto.SaveReq dto
    );

    WsnaLogisticsOutStorageAskDto.RemoveReq mapSaveReqToRemoveReq(WsnaLogisticsOutStorageAskDto.SaveReq dto);

}
