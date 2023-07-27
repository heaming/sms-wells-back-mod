package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbCancelPresentStateDto.*;

@Mapper
public interface WctbCancelPresentStateMapper {

    List<SearchRentalRes> selectRentalCancelPresentStates(
            SearchReq dto
    );

    List<SearchRegularShippingRes> selectRegularShippingCancelPresentStates(
        SearchReq dto
    );

    List<SearchSinglePaymentRes> selectSinglePaymentCancelPresentStates(
        SearchReq dto
    );

    List<SearchMembershipRes> selectMembershipCancelPresentStates(
            SearchReq dto
    );
}
