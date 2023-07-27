package com.kyowon.sms.wells.web.contract.interfaces.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiTaxInvoiceDetailDto.SearchReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiTaxInvoiceDetailDto.SearchRes;

@Mapper
public interface WctiTaxInvoiceDetailMapper {
    List<SearchRes> selectTaxInvoiceDetails(SearchReq req);
}
