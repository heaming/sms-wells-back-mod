package com.kyowon.sms.wells.web.contract.interfaces.mapper;

import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiTaxInvoiceCorporateDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiTaxInvoiceCorporateDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WctiTaxInvoiceCorporateMapper {
    List<SearchRes> selectTaxInvoiceCorporates(SearchReq req);
}
