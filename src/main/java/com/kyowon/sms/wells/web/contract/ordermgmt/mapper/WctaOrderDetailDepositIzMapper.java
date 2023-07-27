package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailDepositIzDto;

@Mapper
public interface WctaOrderDetailDepositIzMapper {
    List<WctaOrderDetailDepositIzDto.SearchRentalDepositIzRes> selectOrderDetailRentalDepositIz(
        String cntrNo, String cntrSn
    );

    List<WctaOrderDetailDepositIzDto.SearchMembershipDepositIzRes> selectOrderDetailMembershipDepositIz(
        String cntrNo, String cntrSn
    );

    List<WctaOrderDetailDepositIzDto.SearchSpayCntrtDepositIzRes> selectOrderDetailSpayCntrtDepositIz(
        String cntrNo, String cntrSn
    );

    List<WctaOrderDetailDepositIzDto.SearchRegularShippingsDepositIzRes> selectOrderDetailRegularShippingsDepositIz(
        String cntrNo, String cntrSn
    );

    List<WctaOrderDetailDepositIzDto.SearchLendingLimitDepositIzRes> selectOrderLendingLimitDepositIz(
        String cntrCstNo
    );
}
