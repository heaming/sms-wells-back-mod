package com.kyowon.sms.wells.web.service.visit.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbTaxInvoicePublicationDto.EditReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbTaxInvoicePublicationDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbTaxInvoicePublicationDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbTaxInvoicePublicationDvo;

/**
 * <pre>
 * W-SV-U-0299PM01 (법인계약공통) 세금계산서발행요청
 * </pre>
 *
 * @author hyewon.kim
 * @since 2023.05.30
 */
@Mapper(componentModel = "spring")
public interface WsnbTaxInvoicePublicationConverter {

    List<SearchRes> mapAllDvoToSearchRes(List<WsnbTaxInvoicePublicationDvo> dvos);

    WsnbTaxInvoicePublicationDvo mapSearchReqToDvo(SearchReq dto);

    WsnbTaxInvoicePublicationDvo mapEditReqToDvo(EditReq dto);

}
