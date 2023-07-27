package com.kyowon.sms.wells.web.contract.interfaces.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiTaxInvoiceBaseDto.SearchReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiTaxInvoiceBaseDto.SearchRes;
import com.kyowon.sms.wells.web.contract.interfaces.mapper.WctiTaxInvoiceBaseMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctiTaxInvoiceBaseService {
    private final WctiTaxInvoiceBaseMapper mapper;

    /**
    * 세금계산서 목록 조회
    * @programId   EAI_WSSI1087
    * @param       req
    * @return      list
    */
    public List<SearchRes> getTaxInvoices(SearchReq req) {
        return mapper.selectTaxInvoices(req);
    }
}
