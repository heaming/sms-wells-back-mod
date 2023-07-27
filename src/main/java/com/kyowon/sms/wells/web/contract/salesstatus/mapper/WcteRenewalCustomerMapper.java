package com.kyowon.sms.wells.web.contract.salesstatus.mapper;

import static com.kyowon.sms.wells.web.contract.salesstatus.dto.WcteRenewalCustomerDto.SearchReq;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.salesstatus.dvo.WcteRenewalCustomerDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WcteRenewalCustomerMapper {

    PagingResult<WcteRenewalCustomerDvo> selectExnCstContactAssigns(SearchReq dto, PageInfo pageInfo);

    List<WcteRenewalCustomerDvo> selectExnCstContactAssigns(SearchReq dto);

    PagingResult<WcteRenewalCustomerDvo> selectRstlCstContactAssigns(SearchReq dto, PageInfo pageInfo);

    List<WcteRenewalCustomerDvo> selectRstlCstContactAssigns(SearchReq dto);
}
