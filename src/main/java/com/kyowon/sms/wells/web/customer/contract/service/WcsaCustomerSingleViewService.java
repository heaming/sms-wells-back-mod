package com.kyowon.sms.wells.web.customer.contract.service;

import static com.kyowon.sms.wells.web.customer.contract.dto.WcsaCustomerSingleViewDto.*;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.customer.contract.converter.WcsaCustomerSingleViewConverter;
import com.kyowon.sms.wells.web.customer.contract.mapper.WcsaCustomerSingleViewMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WcsaCustomerSingleViewService {

    private final WcsaCustomerSingleViewMapper mapper;
    private final WcsaCustomerSingleViewConverter converter;


    public List<SearchRes> getCustomerSingleViewInfos(SearchReq dto) {
        return converter.mapAllWcsaCustomerDvoToSearchRes(mapper.selectCustomerSingleViewInfos(dto));
    }

    public PaymentRes getCustomerSingleViewPayments(String cstNo) {
        return mapper.selectCustomerSingleViewPayments(cstNo);
    }
    public List<ContractRes> getCustomerSingleViewContracts(String cstNo) {
        return mapper.selectCustomerSingleViewContracts(cstNo);
    }

    public List<ServiceRes> getCustomerSingleViewServices(String cstNo) {
        return mapper.selectCustomerSingleViewServices(cstNo);
    }

    public List<ProductRes> getCustomerSingleViewProducts(String cstNo) {
        return mapper.selectCustomerSingleViewProducts(cstNo);
    }

}
