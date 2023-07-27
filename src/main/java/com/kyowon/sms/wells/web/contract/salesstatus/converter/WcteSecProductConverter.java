package com.kyowon.sms.wells.web.contract.salesstatus.converter;

import com.kyowon.sms.wells.web.contract.salesstatus.dto.WcteSecProductDto.CreateConfirmReq;
import com.kyowon.sms.wells.web.contract.salesstatus.dto.WcteSecProductDto.SearchSecPdBycfRes;
import com.kyowon.sms.wells.web.contract.salesstatus.dvo.WcteInvoiceProcessIzDvo;
import com.kyowon.sms.wells.web.contract.salesstatus.dvo.WctePdOstrIzDvo;
import com.kyowon.sms.wells.web.contract.salesstatus.dvo.WcteSecPdBycfDvo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WcteSecProductConverter {
    WctePdOstrIzDvo mapCreateConfirmReqToWcteProdu1ctOutOfStorageIz(CreateConfirmReq req);

    WcteInvoiceProcessIzDvo mapCreateConfirmReqToWcteInvoiceProcessIz(CreateConfirmReq dto);

    SearchSecPdBycfRes mapWcteSecPdBycfDvoToSearchSecPdBycfRes(WcteSecPdBycfDvo wcteSecPdBycfDvo);
}
