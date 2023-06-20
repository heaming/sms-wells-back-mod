package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbContractDtlStatCdChDvo;

@Mapper
public interface WctbContractDtlStatCdChMapper {
    int updateContractDtlStatCd(WctbContractDtlStatCdChDvo dvo);

    int updateCntrDetailStatChangeHist(String cntrNo, String cntrSn);
    int insertCntrDetailStatChangeHist(String cntrNo, String cntrSn);

    int updateCntrDetailChangeHist(String cntrNo, String cntrSn);
    int insertCntrDetailChangeHist(String cntrNo, String cntrSn);

}
