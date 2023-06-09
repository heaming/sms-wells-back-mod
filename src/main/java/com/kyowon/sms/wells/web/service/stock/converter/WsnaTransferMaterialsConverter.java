package com.kyowon.sms.wells.web.service.stock.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaTransferMaterialsDataDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaTransferMaterialsHgrDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaTransferMaterialsIostDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaTransferMaterialsOstrAkDvo;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WsnaTransferMaterialsConverter {

    @Mapping(source = "ostrHgrWareNo", target = "strOjWareNo")
    @Mapping(source = "ostrHgrDvCd", target = "strWareDvCd")
    @Mapping(source = "ostrHgrPrtnrNo", target = "strPrtnrNo")
    @Mapping(source = "ostrHgrPrtnrOgTpCd", target = "strPrtnrOgTpCd")
    WsnaTransferMaterialsIostDvo mapWsnaTransferMaterialsListDvoToWsnaTransferMaterialsReturnDvo(
        WsnaTransferMaterialsDataDvo dvo
    );

    WsnaTransferMaterialsHgrDvo mapWsnaTransferMaterialsListDvoToWsnaTransferMaterialsHgrDvo(
        WsnaTransferMaterialsDataDvo dvo
    );

    @Mapping(source = "ostrHgrWareNo", target = "ostrOjWareNo")
    @Mapping(source = "ostrHgrDvCd", target = "ostrOjWareDvCd")
    @Mapping(source = "ostrHgrPrtnrNo", target = "ostrWareMngtPrtnrNo")
    @Mapping(source = "ostrHgrPrtnrOgTpCd", target = "ostrWareMngtPrtnrOgTpCd")
    @Mapping(source = "strHgrWareNo", target = "strOjWareNo")
    @Mapping(source = "strHgrDvCd", target = "ostrAkWareDvCd")
    @Mapping(source = "strHgrPrtnrNo", target = "wareMngtPrtnrNo")
    @Mapping(source = "strHgrPrtnrOgTpCd", target = "wareMngtPrtnrOgTpCd")
    WsnaTransferMaterialsOstrAkDvo mapWsnaTransferMaterialsHgrDvoToWsnaTransferMaterialsOstrAkDvo(
        WsnaTransferMaterialsHgrDvo dvo
    );

    @Mapping(source = "strQty", target = "ostrAkQty")
    @Mapping(source = "ostrOjWareDvCd", target = "ostrWareDvCd")
    @Mapping(source = "ostrWareMngtPrtnrNo", target = "ostrPrtnrNo")
    @Mapping(source = "ostrWareMngtPrtnrOgTpCd", target = "ostrPrtnrOgTpCd")
    @Mapping(source = "ostrAkWareDvCd", target = "strWareDvCd")
    @Mapping(source = "wareMngtPrtnrNo", target = "strPrtnrNo")
    @Mapping(source = "wareMngtPrtnrOgTpCd", target = "strPrtnrOgTpCd")
    WsnaTransferMaterialsIostDvo mapWsnaTransferMaterialsOstrAkDvoToWsnaTransferMaterialsIostDvo(
        WsnaTransferMaterialsOstrAkDvo dvo
    );

}
