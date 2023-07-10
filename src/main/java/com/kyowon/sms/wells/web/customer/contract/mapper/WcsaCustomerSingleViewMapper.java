package com.kyowon.sms.wells.web.customer.contract.mapper;

import static com.kyowon.sms.wells.web.customer.contract.dto.WcsaCustomerSingleViewDto.*;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.customer.contract.dvo.WcsaCustomerDvo;

@Mapper
public interface WcsaCustomerSingleViewMapper {

    List<WcsaCustomerDvo> selectCustomerSingleViewInfos(SearchReq dto);

    PaymentRes selectCustomerSingleViewPayments(String cstNo);

    List<ContractRes> selectCustomerSingleViewContracts(String cstNo);

    List<ServiceRes> selectCustomerSingleViewServices(String cstNo);

    List<ProductRes> selectCustomerSingleViewProducts(String cstNo);

}
