package com.kyowon.sms.wells.web.service.allocate.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncRegularBfsvcAsnDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncRegularBfsvcAsnDvo;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncSpecCustAsnDvo;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncSpecCustMngrAsnDvo;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncSpecCustPlanMatDvo;

@Mapper(componentModel = "spring")
public interface WsncRegularBfsvcAsnConverter {
//    WsncSpecCustAsnDvo mapSaveProcessReqToSpecCustAsnDvo(WsncRegularBfsvcAsnDto.SaveProcessReq dto) throws Exception;
//
//    WsncSpecCustPlanMatDvo mapSaveProcessReqToSpecCustPlanMatDvo(WsncRegularBfsvcAsnDto.SaveProcessReq dto) throws Exception;
//
//    WsncSpecCustMngrAsnDvo mapSaveProcessReqToSpecCustMngrAsnDvo(WsncRegularBfsvcAsnDto.SaveProcessReq dto) throws Exception;

    WsncRegularBfsvcAsnDvo mapSaveProcessReqToDvo(WsncRegularBfsvcAsnDto.SaveProcessReq dto) throws Exception;

    WsncSpecCustAsnDvo mapDvoToSpecCustAsnDvo(WsncRegularBfsvcAsnDvo dvo) throws Exception;

    WsncSpecCustPlanMatDvo mapDvoToSpecCustPlanMatDvo(WsncRegularBfsvcAsnDvo dvo) throws Exception;

    WsncSpecCustMngrAsnDvo mapDvoToSpecCustMngrAsnDvo(WsncRegularBfsvcAsnDvo dvo) throws Exception;
}
