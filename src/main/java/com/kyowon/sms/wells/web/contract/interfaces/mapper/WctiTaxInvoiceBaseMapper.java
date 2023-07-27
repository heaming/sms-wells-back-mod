package com.kyowon.sms.wells.web.contract.interfaces.mapper;

import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiTaxInvoiceBaseDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiTaxInvoiceBaseDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WctiTaxInvoiceBaseMapper {
    List<SearchRes> selectTaxInvoices(SearchReq dto);
}
