package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbRentalCancelDvo;

@Mapper
public interface WctbRentalCancelMapper {

    List<WctbRentalCancelDvo> selectRentalCanCcamRfdamts(WctbRentalCancelDvo dvo);

    int selecdUsedPointAmt(String cntrNo, int cntrSn, String dpTpCd);

    int selectStplDiscountAmt(String cntrNo, int cntrSn, int stplTn);

    WctbRentalCancelDvo selectWorkPointIamt(String cntrNo, int cntrSn);

    WctbRentalCancelDvo selectDscAmtAgrgPrmDscAmt(String cntrNo, int cntrSn, String canDt);

}
