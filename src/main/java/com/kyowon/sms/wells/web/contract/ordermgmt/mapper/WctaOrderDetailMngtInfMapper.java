package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailMngtInfDto;

@Mapper
public interface WctaOrderDetailMngtInfMapper {
    List<WctaOrderDetailMngtInfDto.SearchRes> selectOrderDetailRentalMngtInqr(String cntrNo, String cntrSn);

    List<WctaOrderDetailMngtInfDto.SearchRes> selectOrderDetailMembershipMngtInqr(String cntrNo, String cntrSn);

    List<WctaOrderDetailMngtInfDto.SearchRes> selectOrderDetailSpayCntrtMngtInqr(String cntrNo, String cntrSn);

    List<WctaOrderDetailMngtInfDto.SearchRes> selectOrderDetailRegularShippingsMngtInqr(String cntrNo, String cntrSn);

    List<WctaOrderDetailMngtInfDto.SearchCntrPmotRes> selectCntrPmotList(String cntrNo, String cntrSn);

    List<WctaOrderDetailMngtInfDto.SearchFgptCntrRes> selectFgptCntrList(String cntrNo, String cntrSn);

}
