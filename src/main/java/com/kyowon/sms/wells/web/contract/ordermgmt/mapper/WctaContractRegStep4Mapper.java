package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaContractRegStep4Dvo;

@Mapper
public interface WctaContractRegStep4Mapper {
    List<WctaContractRegStep4Dvo.CntrDtlDvo> selectCntrDtls(String cntrNo);

    List<WctaContractRegStep4Dvo.StlmDtlDvo> selectStlmDtls(String cntrNo);

    int updateCntrBasStep4(String cntrNo, String cstStlmInMthCd);
}
