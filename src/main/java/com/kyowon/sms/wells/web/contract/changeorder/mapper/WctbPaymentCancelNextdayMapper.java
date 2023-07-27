package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbPaymentCancelNextdayDvo;

@Mapper
public interface WctbPaymentCancelNextdayMapper {
    WctbPaymentCancelNextdayDvo selectSsctCntrDtl(String cntrNo, int cntrSn);

    int updateSsctCntrDtl(String cntrNo, int cntrSn);

    int updateCntrDtlStatChHist(String cntrNo, int cntrSn);

    int insertCntrDtlStatChHist(String cntrNo, int cntrSn);

    int updateCntrDchHist(String cntrNo, int cntrSn);

    int insertCntrDchHist(String cntrNo, int cntrSn);

    int updateSsopCttBas(String cntrNo, int cntrSn);

    int updateSsopCttChHist(String cntrNo, int cntrSn);

    int insertSsopCttChHist(String cntrNo, int cntrSn);

}
