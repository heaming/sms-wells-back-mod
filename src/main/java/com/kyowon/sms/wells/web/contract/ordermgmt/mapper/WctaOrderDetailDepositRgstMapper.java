package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailDepositRgstDto.SearchReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaOrderDetailDepositRgstDvo;

@Mapper
public interface WctaOrderDetailDepositRgstMapper {

    List<WctaOrderDetailDepositRgstDvo> selectDepositRgstIzs(SearchReq dto);

}
