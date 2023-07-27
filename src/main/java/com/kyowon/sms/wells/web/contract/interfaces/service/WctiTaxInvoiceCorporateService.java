package com.kyowon.sms.wells.web.contract.interfaces.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiTaxInvoiceCorporateDto.SearchReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiTaxInvoiceCorporateDto.SearchRes;
import com.kyowon.sms.wells.web.contract.interfaces.mapper.WctiTaxInvoiceCorporateMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctiTaxInvoiceCorporateService {
    private final WctiTaxInvoiceCorporateMapper mapper;

    /**
     * 세금계산서 사업자번호 조회
     *
     * @programid EAI_WSSI1090
     * @param  req 
     * @return list
     */
    public List<SearchRes> getTaxInvoiceCorporates(SearchReq req) {
        return mapper.selectTaxInvoiceCorporates(req);
    }
}
