package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbRentalMchnChCstCanDvo;

@Mapper
public interface WctbRentalMchnChCstCanMapper {
    WctbRentalMchnChCstCanDvo selectCntrDtlMembership(String cntrNo);

    int updateCntrBas(String cntrNo, String cntrCanDtm);

    int updateCntrBasRental(String cntrNo, String cntrSn, String cntrCanDtm);

    int updateCntrDtl(String cntrNo, String cntrSn);

    int updateCntrDchHist(String cntrNo, String cntrSn);

    int insertCntrDchHist(String cntrNo, String cntrSn);
}
