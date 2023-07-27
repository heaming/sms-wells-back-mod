package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WctaContractDocumentMailMapper {

    String selectContractCstNm(String cntrNo, int cntrSn);

    int selectCountContract(String cntrNo, String cntrSn);
}
