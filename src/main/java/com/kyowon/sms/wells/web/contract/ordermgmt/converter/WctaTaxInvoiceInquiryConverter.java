package com.kyowon.sms.wells.web.contract.ordermgmt.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaTaxInvoiceInquiryDto.SaveReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaTaxInvoiceInquiryDvo;

@Mapper(componentModel = "spring")
public interface WctaTaxInvoiceInquiryConverter {

    WctaTaxInvoiceInquiryDvo mapSaveReqToWctaTaxInvoiceInquiryDvo(SaveReq dto);

}
