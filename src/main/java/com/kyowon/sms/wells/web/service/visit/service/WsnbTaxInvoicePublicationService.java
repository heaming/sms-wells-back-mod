package com.kyowon.sms.wells.web.service.visit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.converter.WsnbTaxInvoicePublicationConverter;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbTaxInvoicePublicationDto.EditReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbTaxInvoicePublicationDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbTaxInvoicePublicationDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbTaxInvoicePublicationDvo;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbTaxInvoicePublicationMapper;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0299PM01 (법인계약공통) 세금계산서발행요청
 * </pre>
 *
 * @author hyewon.kim
 * @since 2023.05.30
 */
@Service
@RequiredArgsConstructor
public class WsnbTaxInvoicePublicationService {

    private final WsnbTaxInvoicePublicationMapper mapper;

    private final WsnbTaxInvoicePublicationConverter converter;

    public List<SearchRes> getTaxInvoices(SearchReq dto) {
        return converter.mapAllDvoToSearchRes(mapper.selectTaxInvoices(dto));
    }

    public int getBusinessNumberCount(String bzrno) {
        return mapper.selectBusinessNumberCount(bzrno);
    }

    public int editTaxInvoice(String csBilNo, EditReq dto) {
        WsnbTaxInvoicePublicationDvo dvo = converter.mapEditReqToDvo(dto);
        dvo.setCsBilNo(csBilNo);
        return mapper.updateTaxInvoice(dvo);
    }

}
