package com.kyowon.sms.wells.web.contract.risk.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.risk.dto.WctcIncompletenessSalesDto.SaveReq;
import com.kyowon.sms.wells.web.contract.risk.dvo.WctcIcptSellChHistDvo;
import com.kyowon.sms.wells.web.contract.risk.dvo.WctcIncompletenessSellIzDvo;

@Mapper(componentModel = "spring")
public interface WctcIncompletenessSalesConverter {

    WctcIncompletenessSellIzDvo mapSaveReqToIncompletenessSellIzDvo(SaveReq dto);

    WctcIcptSellChHistDvo mapIncompletenessSellIzDvoToHistDvo(WctcIncompletenessSellIzDvo dvo);
}
