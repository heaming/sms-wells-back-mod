package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WctaBlackContractMapper {
    String isBlacklist(String cralLocaraTno, String mexnoEncr, String cralIdvTno);
}
