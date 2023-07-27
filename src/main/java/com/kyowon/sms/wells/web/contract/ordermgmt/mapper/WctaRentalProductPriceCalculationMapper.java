package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaRentalProductPriceCalculationDvo;

@Mapper
public interface WctaRentalProductPriceCalculationMapper {
    List<WctaRentalProductPriceCalculationDvo> selectRentalFeeCalculation(WctaRentalProductPriceCalculationDvo dvo);

    List<WctaRentalProductPriceCalculationDvo> selectRecoveryRentalFee(String cntrNo, String cntrSn);
}
