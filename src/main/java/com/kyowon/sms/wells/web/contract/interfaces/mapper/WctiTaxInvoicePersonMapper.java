package com.kyowon.sms.wells.web.contract.interfaces.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiTaxInvoicePersonDto.SearchReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiTaxInvoicePersonDto.SearchRes;

@Mapper
public interface WctiTaxInvoicePersonMapper {
    List<SearchRes> selectTaxInvoicePersons(SearchReq req);
}
