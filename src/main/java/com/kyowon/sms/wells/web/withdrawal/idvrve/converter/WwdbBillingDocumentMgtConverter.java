package com.kyowon.sms.wells.web.withdrawal.idvrve.converter;

import org.apache.commons.lang.StringUtils;
import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillingDocumentMgtDto.RemoveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillingDocumentMgtDto.SaveDtlsReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillingDocumentMgtDto.SaveFwReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillingDocumentMgtDto.SaveMainReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbBillingDocumentDetailDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbBillingDocumentDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbBillingDocumentForwardingDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbBillingDocumentMgtDvo;

@Mapper(componentModel = "spring", imports = {StringUtils.class})
public interface WwdbBillingDocumentMgtConverter {
    WwdbBillingDocumentMgtDvo mapDeleteWwwdbBillingDocumentMgtDvo(RemoveReq dto);

    WwdbBillingDocumentDvo mapSaveWwwdbBillingDocumentDvo(SaveMainReq dto);

    WwdbBillingDocumentDetailDvo mapSaveWwwdbBillingDocumentDetailDvo(SaveDtlsReq dto);

    WwdbBillingDocumentForwardingDvo mapSaveWwwBillingDocumentForwardingDvo(SaveFwReq dto);
}
