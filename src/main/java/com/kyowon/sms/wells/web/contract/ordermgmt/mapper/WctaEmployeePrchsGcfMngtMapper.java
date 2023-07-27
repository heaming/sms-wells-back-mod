package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaEmployeePrchsGcfMngtDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaEmployeePrchsGcfMngtDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaEmployeePrchsGcfMngtRequestDvo;

@Mapper
public interface WctaEmployeePrchsGcfMngtMapper {

    List<WctaEmployeePrchsGcfMngtDvo> selectEmployeePurchaseGcfs(WctaEmployeePrchsGcfMngtRequestDvo dvo);

    List<WctaEmployeePrchsGcfMngtDto.SearchCntrRes> selectEmployeePurchases(WctaEmployeePrchsGcfMngtDto.SearchCntrReq dto);
}
